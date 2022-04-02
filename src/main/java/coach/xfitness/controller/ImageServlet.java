package coach.xfitness.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.data.ExerciseDB;

@WebServlet(name = "ImageServlet", urlPatterns = { "/img/*" })
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageName = request.getPathInfo().substring(1);
        String exerciseName = request.getParameter("exercise");

        byte[] content = new byte[0];

        if (!(exerciseName == null || exerciseName.isEmpty())) {
            content = getExerciseImageContent(exerciseName, request, response);
        }

        response.setContentType(getServletContext().getMimeType(imageName));
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }

    private byte[] getExerciseImageContent(String exerciseName, HttpServletRequest request, HttpServletResponse response) {
        String exerciseImageState = request.getParameter("state");

        return ExerciseDB.select(exerciseName).getExerciseImagesById()
            .stream()
            .filter(ei -> ei.getExerciseState().equals(exerciseImageState))
            .findFirst()
            .get()
            .getImageByImageId()
            .getImage();
    }

}