package coach.xfitness.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.Exercise;
import coach.xfitness.business.User;
import coach.xfitness.data.EquipmentDB;
import coach.xfitness.data.ExerciseDB;
import coach.xfitness.data.MuscleDB;
import coach.xfitness.util.PasswordUtil;
import coach.xfitness.util.ServletUtil;

@WebServlet(name = "ExerciseController", urlPatterns = { "/exercise" })
public class ExerciseController extends HttpServlet {

    /**
     * If the request is for a specific exercise, show it, otherwise list all exercises
     * 
     * @param request The request object that was sent to the servlet.
     * @param response The response object that will be sent back to the client.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/exercise/list.jsp";
        String exerciseName = request.getParameter("name");
        String requestURI = request.getRequestURI();

        if (exerciseName == null || exerciseName.isBlank()) {
            url = getList(request);
        } else {
            url = getDetails(request);
        }

        if (requestURI.endsWith("/exercise")) {
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        }
    }

    private String getList(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        List<Exercise> exerciseList = ExerciseDB.selectAllAvailableTo(user);
        List<String> exerciseTypesList = ExerciseDB.fetchTypesList();
        List<String> equipmentNamesList = EquipmentDB.fetchNamesList();
        List<String> muscleNamesList = MuscleDB.fetchNamesList();
        
        request.setAttribute("exerciseTypesList", exerciseTypesList);
        request.setAttribute("exerciseList", exerciseList);
        request.setAttribute("equipmentList", equipmentNamesList);
        request.setAttribute("muscleList", muscleNamesList);

        return "/exercise/list.jsp";
    }

    private String getDetails(HttpServletRequest request) {
        String exerciseName = request.getParameter("name");
        Exercise exercise = ExerciseDB.selectByName(exerciseName);

        request.setAttribute("exercise", exercise);

        return "/exercise/details.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        update(request, response);
        ServletUtil.forwardToReferer(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null) {
            // TODO: take to sign in jsp
            request.getRequestDispatcher("/signin").forward(request, response);
            return;
        }

        User user = (User) session.getAttribute("user");

        Map<String, Map<String, String>> customExerciseParameterMap = getCustomExerciseInNestedParameterMap(request);

        Map<String, Exercise> insertMap = new HashMap<>();
        Map<String, Exercise> updateMap = new HashMap<>();
        List<Exercise> deleteList = new ArrayList<>();

        // filter to each list
        customExerciseParameterMap.forEach((name, parameterMap) -> {
            // get exercise field values
            String title = parameterMap.get("title");
            String primer = parameterMap.get("primer");

            // get action
            String action = parameterMap.get("action");

            Exercise exercise;

            if (action.equals("add")) {
                exercise = new Exercise(); // make new exercise
            } else {
                exercise = ExerciseDB.selectByName(name); // fetch from database
            }

            if (!action.equals("delete")) {
                // set exercise properties
                exercise.setUserByUserId(user);
                exercise.setName(makeUniqueName(user.getName(), title));
                exercise.setTitle(title);
                exercise.setPrimer(primer);
            }

            // filter to each list depending on action
            switch (action) {
                case "add":
                    insertMap.put(name, exercise);
                    break;
                case "update":
                    updateMap.put(name, exercise);
                    break;
                case "delete":
                    deleteList.add(exercise);
                    break;
            }

        });

        // update database
        ExerciseDB.insertList(insertMap.values().stream().collect(Collectors.toList()));
        ExerciseDB.updateList(insertMap.values().stream().collect(Collectors.toList()));
        ExerciseDB.deleteList(deleteList);

        // merge insert and updates map
        // TODO: rename variable and session attribute name
        Map<String, Exercise> updatedCustomExercisesMap = new HashMap<>();
        updatedCustomExercisesMap.putAll(insertMap);
        updatedCustomExercisesMap.putAll(updateMap);

        // make inserts and updates available to session
        session.setAttribute("updatedCustomExercisesMap", updatedCustomExercisesMap);
    }

    private Map<String, Map<String, String>> getCustomExerciseInNestedParameterMap(HttpServletRequest request) {
        Map<String, Map<String, String>> customExerciseNestedParameterMap = new HashMap<>();

        Map<String, String[]> requestParameterMap = request.getParameterMap();

        requestParameterMap.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("customExercise"))
                .forEach(entry -> {
                    // split parameter name with format "customExercise[<exerciseName>][<requestParameterName>]" into tokens
                    String[] tokens = entry.getKey().replace("]", "").split("\\[");

                    String exerciseName = tokens[1];
                    String requestParameterName = tokens[2];
                    String requestParameterValue = entry.getValue()[0];

                    Map<String, String> parameterMap;
                    if (customExerciseNestedParameterMap.containsKey(exerciseName)) {
                        parameterMap = customExerciseNestedParameterMap.get(exerciseName);
                    } else {
                        parameterMap = new HashMap<>();
                    }

                    // add to parameter map
                    parameterMap.put(requestParameterName, requestParameterValue);
                });

        return customExerciseNestedParameterMap;
    }

    private String makeUniqueName(String userName, String exerciseTitle) {
        String name = "";

        StringBuilder sb = new StringBuilder()
                .append(userName.replaceAll("[^A-Za-z0-9_]+", ""))
                .append("-")
                .append(exerciseTitle.replaceAll("[^A-Za-z0-9 ]+", "").replace(' ', '-'));

        name = sb.toString();

        while (ExerciseDB.hasExerciseByName(name)) {
            String id = PasswordUtil.generateRandomBase64String(6);
            name = new StringBuilder(sb).append("-").append(id).toString();
        }

        return name;
    }

}