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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String imageType = request.getParameter("of");
        
        if (!(imageType == null || imageType.isEmpty())) {
            byte[] content = new byte[0];
            
            if (imageType.equals("exercise")) {
                content = getExerciseImageContent(request, response);
            } else if (imageType.equals("equipment")) {
                content = getEquipmentImageContent(request, response);
            }

            String imageName = request.getPathInfo().substring(1);

            response.setContentType(getServletContext().getMimeType(imageName));
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
        }
    }

    private byte[] getExerciseImageContent(HttpServletRequest request, HttpServletResponse response) {
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

    private byte[] getEquipmentImageContent(HttpServletRequest request, HttpServletResponse response) {
        int equipmentId = Integer.valueOf(request.getParameter("id"));

        return EquipmentDB.selectById(equipmentId).getEquipmentImagesById()
                .stream()
                .findFirst()
                .get()
                .getImageByImageId()
                .getImage();
    }

}