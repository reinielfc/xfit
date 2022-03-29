package coach.xfitness.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.business.Exercise;
import coach.xfitness.data.ExerciseDB;

@WebServlet(name = "ExerciseController", urlPatterns = { "/exercises" })
public class ExerciseController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =  list(request, response);
        
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }

    private String list(HttpServletRequest request, HttpServletResponse response) {
        List<Exercise> exerciseList = ExerciseDB.selectAll();

        request.setAttribute("exerciseList", exerciseList);

        return "/exercises/list.jsp";
    }

}