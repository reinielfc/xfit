package coach.xfitness.controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet(name = "ExerciseController", urlPatterns = { "/exercise" })
public class ExerciseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "/exercise/list.jsp";
        String exerciseName = request.getParameter("name");

        if (exerciseName == null || exerciseName.isBlank()) {
            url = list(request, response);
        } else {
            url = showDetails(exerciseName, request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        List<Exercise> exerciseList = ExerciseDB.selectAll();
        List<String> exerciseTypesList = ExerciseDB.fetchTypesList();
        List<String> equipmentNamesList = EquipmentDB.fetchNamesList();
        List<String> muscleNamesList = MuscleDB.fetchNamesList();

        request.setAttribute("exerciseList", exerciseList);
        request.setAttribute("exerciseTypesList", exerciseTypesList);
        request.setAttribute("equipmentList", equipmentNamesList);
        request.setAttribute("muscleList", muscleNamesList);

        request.setAttribute("activePage", "exercises");
        return "/exercise/list.jsp";
    }

    private String showDetails(String exerciseName, HttpServletRequest request, HttpServletResponse response) {
        Exercise exercise = ExerciseDB.select(exerciseName);

        request.setAttribute("exercise", exercise);

        request.setAttribute("activePage", "exercises");
        return "/exercise/details.jsp";
    }

    private String addCustomExercise(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userID = user.getUserID();
        String title = request.getParameter("title");
        String steps = request.getParameter("steps");
        String [] tags = request.getParameterValues("tags");

        Exercise exercise = new Exercise();
        
        if (userID != null ) {
            exercise.setUserID(userID);
        }
    
        // title
        if (title != null && !title.isEmpty()) {
            
            exercise.setName(title);
        }
        // steps
        if (steps != null && !steps.isEmpty()) {
            exercise.setDescription(steps);
        }
        //tags
        if (tags != null && tags.length != 0) {
        
            exercise.setTags(tags);
        } 
        
        ExerciseDB.insert(exercise);//find out how to respond , how does it return, what is response
        return title;
    }

    private String updateCustomExercise(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Long userID = user.getUserID();
        String title = request.getParameter("title");
        String steps = request.getParameter("steps");
        String [] tags = request.getParameterValues("tags");

        Exercise exercise = new Exercise();
        
        if (userID != null ) {
            exercise.setUserID(userID);
        }
    
        // title
        if (title != null && !title.isEmpty()) {
            
            exercise.setName(title);
        }
        // steps
        if (steps != null && !steps.isEmpty()) {
            exercise.setDescription(steps);
        }
        //tags
        if (tags != null && tags.length != 0) {
        
            exercise.setTags(tags);
        } 
        
        ExerciseDB.updateExercise(exercise);//find out how to respond , how does it return, what is response
        return title;
    }

}