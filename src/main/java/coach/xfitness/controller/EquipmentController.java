package coach.xfitness.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coach.xfitness.business.Equipment;
import coach.xfitness.data.EquipmentDB;

@WebServlet(name = "EquipmentController", urlPatterns = { "/equipment" })
public class EquipmentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "equipment.jsp";

        // list equipment
        List<Equipment> equipmentList = EquipmentDB.selectAll();
        request.setAttribute("equipmentList", equipmentList);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
