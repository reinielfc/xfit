package coach.xfitness.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import coach.xfitness.data.UserDB;
import coach.xfitness.util.ParameterAsMapRequestWrapper;

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

    /**
     * Get all the exercises, exercise types, equipment names, and muscle names,
     * and put them in the request.
     * 
     * @param request The request object that was sent to the servlet.
     * @return The URL of the JSP page for the exercise list.
     */
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

    /**
     * Gets the exercise name from the request, gets the exercise from the database,
     * and puts the exercise in the request
     * 
     * @param request The request object that was sent to the servlet.
     * @return The URL of the JSP page for the exercise details.
     */
    private String getDetails(HttpServletRequest request) {
        String exerciseName = request.getParameter("name");
        Exercise exercise = ExerciseDB.selectByName(exerciseName);

        request.setAttribute("exercise", exercise);

        return "/exercise/details.jsp";
    }

    /**
     * If the action is favorite, create, save, or delete, then do the appropriate action
     * 
     * @param request The request object that was sent to the servlet.
     * @param response The response object that will be sent back to the client.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String requestURI = request.getRequestURI();

        switch (action) {
            case "favorite":
                doFavoriteAction(request);
                break;
            case "create":
                doCreateAction(request);
                break;
            case "save":
                doSaveAction(request);
                break;
            case "delete":
                doDeleteAction(request);
                break;
        }

        String url = getList(request);

        if (requestURI.endsWith("/exercise")) {
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);
        }
    }

    /**
     * It takes the request, extracts the exercise parameters, and updates the user's
     * favorite exercises
     * 
     * @param request The HttpServletRequest object that contains the parameters.
     */
    private void doFavoriteAction(HttpServletRequest request) {
        ParameterAsMapRequestWrapper requestWrapper = new ParameterAsMapRequestWrapper(request);
        Map<String, Object> parameterAsMap = requestWrapper.getParameterAsMap("exercise");

        // Updating the user's favorite exercises.
        if (!(parameterAsMap == null || parameterAsMap.isEmpty())) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");

            Map<?, ?> exercises = new HashMap<>((Map<?, ?>) parameterAsMap.get("exercise"));

            // Iterate through the exercises and update the user's favorite exercises.
            exercises.forEach((k, v) -> {
                String name = (String) k;

                Map<?, ?> exerciseParameterMap = new HashMap<>((Map<?, ?>) v);

                boolean isFavorite = Boolean.parseBoolean(
                        ((String[]) exerciseParameterMap.get("isFavorite"))[0]);

                Exercise exercise = ExerciseDB.selectByName(name);

                if (isFavorite) {
                    user.addExerciseAsFavorite(exercise);
                } else {
                    user.removeExerciseAsFavorite(exercise);
                }
            });

            session.setAttribute("user", UserDB.update(user));
        }
    }

    /**
     * If the user is signed in, creates a new exercise with the given title and
     * primer, and inserts it into the database.
     * 
     * @param request The request object that was sent to the servlet.
     */
    private void doCreateAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String name = ExerciseDB.makeUniqueName(user.getName(), title);

        Exercise exercise = new Exercise();
        exercise.setUserByUserId(user);
        exercise.setName(name);
        exercise.setTitle(title);
        exercise.setPrimer(description);

        ExerciseDB.insert(exercise);
    }

    /**
     * If the user is signed and is the owner of the exercise, then it updates
     * the exercise.
     * 
     * @param request The request object that was sent to the servlet.
     */
    private void doSaveAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String name = request.getParameter("name");

        Exercise exercise = ExerciseDB.selectByName(name);

        if (exercise.getUserByUserId().getId() == user.getId()) {
            String title = request.getParameter("title");
            String description = request.getParameter("description");

            name = ExerciseDB.makeUniqueName(user.getName(), title);

            exercise.setName(name);
            exercise.setTitle(title);
            exercise.setPrimer(description);

            ExerciseDB.update(exercise);
        }
    }

    /**
     * If the exercise exists and the user is the owner of the exercise, delete the
     * exercise
     * 
     * @param request The request object is an object that contains the request the
     * client made of the servlet.
     */
    private void doDeleteAction(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        String name = request.getParameter("name");

        Exercise exercise = ExerciseDB.selectByName(name);

        if (exercise != null && exercise.getUserByUserId().getId() == user.getId()) {
            ExerciseDB.deleteByName(name);
        }
    }

}