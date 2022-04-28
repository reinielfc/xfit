package coach.xfitness.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.Exercise;
import coach.xfitness.business.Plan;
import coach.xfitness.business.User;
import coach.xfitness.data.ExerciseDB;
import coach.xfitness.data.UserDB;
import coach.xfitness.util.ParameterAsMapRequestWrapper;

@WebServlet(name = "PlanController", urlPatterns = { "/planner", "/workout" })
public class PlanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = "/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = getPlanner(request, response);
        } else if (requestURI.endsWith("/workout")) {
            url = getWorkout(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String getPlanner(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to plan your routine.");
            return "/user/signin.jsp";
        }

        // get today's day of week
        String today = getDayOfWeek();

        request.setAttribute("today", today);

        // include exercises list
        request.getRequestDispatcher("/exercise")
                .include(request, response);

        // set user plans by day
        request.setAttribute("planListByDayMap", user.getPlanListByDayMap());

        return "/routine/planner.jsp";
    }

    private static String getDayOfWeek() {
        return LocalDate.now() // today
                .getDayOfWeek() // day of week
                .getDisplayName( // short format, eg. 'Mon'
                        TextStyle.SHORT, Locale.getDefault())
                .toLowerCase(); // lowercase
    }

    private String getWorkout(HttpServletRequest request)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to view today's workout.");
            return "/user/signin.jsp";
        }

        Byte dayOfWeek = Plan.getDayOfWeek(request.getParameter("dayOfWeek"));
        List<Plan> planList = user.getPlanListForDay(dayOfWeek);

        request.setAttribute("planList", planList);

        return "/routine/workout.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = getPlanner(request, response);
        } else if (requestURI.endsWith("/workout")) {
            url = getWorkout(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String doAddAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to create a plan.");
            return "/user/signin.jsp";
        }

        byte dayOfWeek = Integer.valueOf(request.getParameter("day")).byteValue();
        short sets = Short.valueOf(request.getParameter("sets"));
        short reps = Short.valueOf(request.getParameter("reps"));
        short weight = Short.valueOf(request.getParameter("weight"));

        String exerciseName = request.getParameter("exerciseName");
        Exercise exercise = ExerciseDB.selectByName(exerciseName);

        Plan plan = new Plan(user, exercise);
        plan.setSets(sets);
        plan.setReps(reps);
        plan.setWeight(weight);

        user.addPlanToDay(plan, dayOfWeek);

        UserDB.update(user);

        return "/planner";
    }

    private String doUpdateAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to update your planner.");
            return "/user/signin.jsp";
        }

        ParameterAsMapRequestWrapper requestWrapper = new ParameterAsMapRequestWrapper(request);
        Map<String, Object> parameterAsMap = requestWrapper.getParameterAsMap("plan");

        if (!(parameterAsMap == null || parameterAsMap.isEmpty())) {
            Map<?, ?> plans = new HashMap<>((Map<?, ?>) parameterAsMap.get("plan"));

            Collection<Plan> oldPlans = user.getPlansById();

            user.setPlansById(new ArrayList<>());

            try {
                plans.forEach((d, m) -> {
                    byte dayOfWeek = Plan.getDayOfWeek((String) d);
                    Map<?, ?> dayMap = new HashMap<>((Map<?, ?>) m);

                    dayMap.forEach((p, pm) -> {
                        int position = Integer.valueOf((String) p);

                        Map<?, ?> planParameterMap = new HashMap<>((Map<?, ?>) pm);

                        String exerciseName = ((String[]) planParameterMap.get("exerciseName"))[0];
                        short sets = Short.valueOf(((String[]) planParameterMap.get("sets"))[0]);
                        short reps = Short.valueOf(((String[]) planParameterMap.get("reps"))[0]);
                        short weight = Short.valueOf(((String[]) planParameterMap.get("weight"))[0]);

                        Exercise exercise = ExerciseDB.selectByName(exerciseName);

                        Plan plan = new Plan(user, exercise);
                        plan.setPosition(position);
                        plan.setDayOfWeek(dayOfWeek);
                        plan.setSets(sets);
                        plan.setReps(reps);
                        plan.setWeight(weight);

                        user.getPlansById().add(plan);
                    });
                });

                session.setAttribute("user", UserDB.update(user));
            } catch (Exception e) {
                e.printStackTrace();
                user.setPlansById(oldPlans);
            }

        }

        return "/"; // TODO
    }

}
