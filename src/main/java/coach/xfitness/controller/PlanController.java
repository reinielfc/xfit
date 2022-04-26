package coach.xfitness.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
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
import coach.xfitness.data.PlanDB;
import coach.xfitness.util.ParameterAsMapRequestWrapper;

@WebServlet(name = "PlanController", urlPatterns = { "/planner", "/workout" })
public class PlanController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String url = "/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = showPlanner(request, response);
        } else if (requestURI.endsWith("/workout")) {
            url = showWorkout(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String showPlanner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to plan your routine.");
            return "/user/signin.jsp";
        }

        // set today's day of week
        String today = getDayOfWeek();

        request.setAttribute("today", today);

        // include exercises list
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/exercise");
        requestDispatcher.include(request, response);

        // set user plans by day
        request.setAttribute("planListByDayMap", user.getPlanListByDayMap());

        return "/routine/planner.jsp";
    }

    private static String getDayOfWeek() {
        return LocalDate.now()      // today
                .getDayOfWeek()     // day of week
                .getDisplayName(    // short format, eg. 'Mon'
                    TextStyle.SHORT, Locale.getDefault())
                .toLowerCase();     // lowercase
    }

    private String showWorkout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // get user if in session
        User user = (session == null ? null : (User) session.getAttribute("user"));

        if (user == null) {
            request.setAttribute("message", "Sign in to view today's workout.");
            return "/user/signin.jsp";
        }

        Byte dayOfWeek = Plan.getDayOfWeek(request.getParameter("dayOfWeek"));
        List<Plan> workout = user.getPlanListForDay(dayOfWeek);

        request.setAttribute("workout", workout);

        return "/routine/workout.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "/planner.jsp";

        if (requestURI.endsWith("/planner")) {
            url = updatePlanner(request);
        } else if (requestURI.endsWith("/workout")) {
            url = updateWorkout(request);
        }

        updatePlanner(request);
        // TODO: redirect to workout.jsp when /wprkout
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    private String updatePlanner(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            request.setAttribute("validationMessage", "Please sign in to update your planner.");
            return "/user/signin.jsp";
        }

        User user = (User) session.getAttribute("user");

        ParameterAsMapRequestWrapper requestWrapper = new ParameterAsMapRequestWrapper(request);

        Map<Byte, Map<Integer, Map<String, String>>> planNestedParameterMap = getPlanNestedParameterMap(request);

        List<Plan> insertList = new ArrayList<>();
        List<Plan> updateList = new ArrayList<>();
        List<Plan> deleteList = new ArrayList<>();

        planNestedParameterMap.forEach((dayOfWeek, planDayMap) -> {
            planDayMap.forEach((position, parameterMap) -> {
                String exerciseName = parameterMap.get("exerciseName");
                Short sets = Short.valueOf(parameterMap.get("sets"));
                Short reps = Short.valueOf(parameterMap.get("sets"));
                Short weight = Short.valueOf(parameterMap.get("sets"));
                String action = parameterMap.get("action");

                Exercise exercise = ExerciseDB.selectByName(exerciseName);

                if (exercise == null) {
                    @SuppressWarnings("unchecked")
                    Map<String, Exercise> updatedCustomExercisesMap = (Map<String, Exercise>) session
                            .getAttribute("updatedCustomExercisesMap");

                    exercise = updatedCustomExercisesMap.get(exerciseName);
                    // TODO: throw exception of this ^ exercise is null / show error
                }

                Plan plan;

                if (action.equals("add")) {
                    plan = new Plan(); // make new plan
                } else {
                    plan = PlanDB.selectBy(dayOfWeek, position);
                }

                if (!action.equals("delete")) {
                    // set plan properties
                    plan.setUserByUserId(user);
                    plan.setExerciseByExerciseId(exercise);
                    plan.setDayOfWeek(dayOfWeek);
                    plan.setPosition(position);
                    plan.setSets(sets);
                    plan.setReps(reps);
                    plan.setWeight(weight);
                }

                // filter to each list depending on action
                switch (action) {
                    case "add":
                        insertList.add(plan);
                        break;
                    case "update":
                        updateList.add(plan);
                        break;
                    case "delete":
                        deleteList.add(plan);
                        break;
                }

            });
        });

        // update database
        PlanDB.insertList(insertList);
        PlanDB.updateList(updateList);
        PlanDB.deleteList(deleteList);

        return "/routine/planner.jsp";
    }

    private Map<Byte, Map<Integer, Map<String, String>>> getPlanNestedParameterMap(HttpServletRequest request) {
        Map<Byte, Map<Integer, Map<String, String>>> planNestedParameterMap = new HashMap<>();

        // initialize planNestedParameterMap with days of week
        //TODO: dayOfWeekMap.values().forEach(dayOfWeek -> planNestedParameterMap.put(dayOfWeek, new HashMap<>()));

        Map<String, String[]> requestParameterMap = request.getParameterMap();

        requestParameterMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("plannedExercise"))
                .forEach(entry -> {
                    // split parameter name with format "plannedExercise[<dayOfWeek>][<position>][<requestParameterName>]" into tokens
                    String[] tokens = entry.getKey().replace("]", "").split("\\[");

                    //TODO: Byte dayOfWeek = dayOfWeekMap.get(tokens[1]);
                    Integer position = Integer.valueOf(tokens[2]);

                    String parameterName = tokens[3];
                    String parameterValue = entry.getValue()[0];

                    //TODO: Map<Integer, Map<String, String>> planDayMap = planNestedParameterMap.get(dayOfWeek);

                    Map<String, String> planParameterMap;
                    // TODO: if (planDayMap.containsKey(position)) {
                    // TODO:     planParameterMap = planDayMap.get(position);
                    // TODO: } else {
                    // TODO:     planParameterMap = new HashMap<>();
                    // TODO: }
                    // TODO: 
                    // TODO: planParameterMap.put(parameterName, parameterValue);
                    // TODO: planDayMap.put(position, planParameterMap);
                    //planNestedParameterMap.put(dayOfWeek, plannedDayMap); // TODO: remove?
                });

        return planNestedParameterMap;
    }

    private String updateWorkout(HttpServletRequest request) {
        // workoutPlan[position]
        HttpSession session = request.getSession(false);

        if (session == null) {
            request.setAttribute("validationMessage", "Please sign in to update your workout.");
            return "/user/signin.jsp";
        }

        User user = (User) request.getAttribute("user");

        Map<String, String[]> requestParameterMap = request.getParameterMap();
        //TODO: Byte dayOfWeek = dayOfWeekMap.get(request.getParameter("dayOfWeek"));

        List<Plan> planList = new ArrayList<>();

        requestParameterMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("workoutPlan"))
                .forEach(entry -> {
                    // split parameter name with format "workoutPlan[<position>]" into tokens
                    String[] tokens = entry.getKey().replace("]", "").split("\\[");

                    Integer position = Integer.valueOf(tokens[1]);
                    Byte isDone = (byte) (Boolean.valueOf(entry.getValue()[0]) ? 1 : 0);

                    //TODO: Plan plan = user.getPlanByPositionInDayOfWeek(position, dayOfWeek);

                    //TODO: plan.setIsDone(isDone);
                    //TODO: 
                    //TODO: planList.add(plan);
                });

        PlanDB.updateList(planList);

        return "/routine/workout.jsp";
    }

}
