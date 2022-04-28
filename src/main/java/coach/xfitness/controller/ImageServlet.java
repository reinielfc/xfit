package coach.xfitness.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.data.EquipmentDB;
import coach.xfitness.data.ExerciseDB;

@WebServlet(name = "ImageServlet", urlPatterns = { "/img/*" })
public class ImageServlet extends HttpServlet {

    /**
    * If the request is for an exercise image, get the exercise image content,
    * otherwise get the equipment image content
    * 
    * @param request The request object that was sent to the servlet.
    * @param response The response object that will be sent back to the client.
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageType = request.getParameter("of");

        if (!(imageType == null || imageType.isEmpty())) {
            byte[] content = new byte[0];

            if (imageType.equals("exercise")) {
                content = getExerciseImageContent(request);
            } else if (imageType.equals("equipment")) {
                content = getEquipmentImageContent(request);
            }

            String imageName = request.getPathInfo().substring(1);

            response.setContentType(getServletContext().getMimeType(imageName));
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        }
    }

    /**
    * Get the exercise image content from the database, given the exercise name and
    * exercise image state.
    * 
    * @param request The request object that was sent to the servlet.
    * @return The image of the exercise.
    */
    private byte[] getExerciseImageContent(HttpServletRequest request) {
        String exerciseName = request.getParameter("name");
        String exerciseImageState = request.getParameter("state");

        return ExerciseDB.selectByName(exerciseName).getExerciseImagesById()
                .stream()
                .filter(ei -> ei.getExerciseState().equals(exerciseImageState))
                .findFirst()
                .get()
                .getImageByImageId()
                .getImage();
    }

    /**
    * Get the first image of the equipment with the given id.
    * 
    * @param request The request object that was sent to the servlet.
    * @return The first image of the equipment with the given id.
    */
    private byte[] getEquipmentImageContent(HttpServletRequest request) {
        int equipmentId = Integer.valueOf(request.getParameter("id"));

        return EquipmentDB.selectById(equipmentId).getEquipmentImagesById()
                .stream()
                .findFirst()
                .get()
                .getImageByImageId()
                .getImage();
    }

}
