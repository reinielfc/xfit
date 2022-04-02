package coach.xfitness.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.business.Exercise;
import coach.xfitness.data.EquipmentDB;
import coach.xfitness.data.ExerciseDB;
import coach.xfitness.data.MuscleDB;

@WebServlet(name = "ExerciseController", urlPatterns = { "/exercise" })
public class ExerciseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
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

}