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

    /**
     * If the request URI ends with "/planner", then call the getPlanner() method and
     * set the url to the return value of that method. Otherwise, if the request URI
     * ends with "/workout", then call the getWorkout() method and set the url to the
     * return value of that method
     * 
     * @param request The request object that was sent to the servlet.
     * @param response The response object that will be sent back to the client.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = "/routine/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = getPlanner(request, response);
        } else if (requestURI.endsWith("/workout")) {
            url = getWorkout(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * If the user is signed in, get today's day of week, include the exercises list,
     * and set the user's plans by day.
     * 
     * @param request the request object
     * @param response the response object
     * @return The planner.jsp page.
     */
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

    /**
     * Get today's day of week in short format, eg. 'Mon', and convert it to lowercase.
     * 
     * @return The day of the week in lowercase.
     */
    private static String getDayOfWeek() {
        return LocalDate.now() // today
                .getDayOfWeek() // day of week
                .getDisplayName( // short format, eg. 'Mon'
                        TextStyle.SHORT, Locale.getDefault())
                .toLowerCase(); // lowercase
    }

    /**
     * If the user is signed in, get the plan for the day of the week and return the
     * workout page.
     *
     * @param request The request object that was sent to the servlet.
     * @return A list of plans for the day of the week.
     */
    private String getWorkout(HttpServletRequest request)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to view today's workout.");
            return "/user/signin.jsp";
        }

        Byte dayOfWeek = Plan.getDayOfWeek(getDayOfWeek());
        List<Plan> planList = user.getPlanListForDay(dayOfWeek);

        request.setAttribute("planList", planList);

        return "/routine/workout.jsp";
    }

    /**
     * If the request URI ends with "/planner", then call the postPlanner method.
     * Otherwise, if the request URI ends with "/workout", then call the getWorkout
     * method and set the url to the return value of the getWorkout method
     *
     * @param request The request object that was sent to the servlet.
     * @param response The response object is used to send data back to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/routine/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = postPlanner(request, response);
        } else if (requestURI.endsWith("/workout")) {
            url = getWorkout(request); // TODO: actually update workout
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    /**
     * If the action is "add", then call the doAddAction method. If the action is
     * "update", then call the doUpdateAction method
     * 
     * @param request The request object that was sent to the servlet.
     * @param response The response object is used to send data back to the client.
     * @return The url is being returned.
     */
    private String postPlanner(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String url = "/routine/planner.jsp";

        if (action.equals("add")) {
            url = doAddAction(request, response);
        } else if (action.equals("update")) {
            url = doUpdateAction(request, response);
        }

        return url;
    }

    /**
     * The function checks if the user is logged in, if not, it redirects to the sign
     * in page. If the user is logged in, it creates a new plan object and adds it to
     * the user's plan for the day
     * 
     * @param request the request object
     * @param response The response object that will be sent back to the client.
     * @return The return value is the path to the next page to be displayed.
     */
    private String doAddAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to create a plan.");
            return "/user/signin.jsp";
        }

        byte dayOfWeek = Plan.getDayOfWeek(request.getParameter("day"));
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

        return getPlanner(request, response);
    }

    /**
     * It takes the request parameters, parses them into a map, then uses that map to
     * update the user's planner
     * 
     * @param request The request object
     * @param response The response object.
     * @return The planner page.
     */
    private String doUpdateAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            // TODO: I'm cheating a little here
            Collection<Plan> oldPlans = user.getPlansById();
            user.setPlansById(new ArrayList<>());

            try {
                plans.forEach((d, m) -> {
                    byte dayOfWeek = Plan.getDayOfWeek((String) d);
                    Map<?, ?> dayMap = new HashMap<>((Map<?, ?>) m);

                    dayMap.forEach((p, pm) -> {
                        Map<?, ?> planParameterMap = new HashMap<>((Map<?, ?>) pm);

                        String exerciseName = ((String[]) planParameterMap.get("exerciseName"))[0];
                        int position = Integer.valueOf(((String[]) planParameterMap.get("position"))[0]);
                        
                        short sets   = Short.valueOf(((String[]) planParameterMap.get("sets"))[0]);
                        short reps   = Short.valueOf(((String[]) planParameterMap.get("reps"))[0]);
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

        return getPlanner(request, response);
    }

}
