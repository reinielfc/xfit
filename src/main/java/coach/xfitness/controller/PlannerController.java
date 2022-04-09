package coach.xfitness.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coach.xfitness.business.Plan;
import coach.xfitness.data.PlanDB;

@WebServlet(name = "PlannerController", urlPatterns = {"/planner/exercise/add"})
public class PlannerController extends HttpServlet {
    public void processRequest (HttpServletRequest request, HttpServletResponse response) {
        //initializing/setting the parameters from the Plan Table
        String userID = request.getParameter("userID*");
        String exerciseID = request.getParameter("exerciseID*");
        String dayOfWeek = request.getParameter("day");
        String sets = request.getParameter("sets*");
        String reps = request.getParameter("reps");
        String time = request.getParameter("time");// might not be needed.
        
//getting plan session
        HttpSession session = request.getSession();
        Plan plan = (Plan) session.getAttribute("plan");

        if (userID != null && !userID.isEmpty()) {
            long user_id = Long.valueOf(userID);
            plan.setUserID(user_id);
        }
//exerciseID
        if (exerciseID != null && !exerciseID.isEmpty()) {
            long exercise_id = Long.valueOf(exerciseID);
            plan.setExerciseID(exercise_id);
        }
//day
        if (dayOfWeek != null && !dayOfWeek.isEmpty()) {
            plan.setDayOfWeek(dayOfWeek);
        }

        if (sets != null && !sets.isEmpty()) {
            int  sets_ = Integer.valueOf(sets);
            plan.setSets(sets_);
        }
        if (reps != null && !reps.isEmpty()) {
            int reps_ = Integer.valueOf(reps);
            plan.setReps(reps_);
        }
        if (time != null && !time.isEmpty()) {
        int time_ = Integer.valueOf(time);
            plan.setUserID(time_);
        }
        
    } 
    @Override
    public void doGet (HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }
    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) {

        //processRequest(request, response);
        String userID = request.getParameter("userID*");
        String exerciseID = request.getParameter("exerciseID*");//How are we bringing the exercises
        String [] dayOfWeek = request.getParameterValues("day");
        String sets = request.getParameter("sets");
        String reps = request.getParameter("reps");
        String time = request.getParameter("time");
//
    
        Plan plan = new Plan ();

        if (userID != null && !userID.isEmpty()) {
            long user_id = Long.valueOf(userID);
            plan.setUserID(user_id);
        }
//exerciseID
        if (exerciseID != null && !exerciseID.isEmpty()) {
            long exercise_id = Long.valueOf(exerciseID);
            plan.setExerciseID(exercise_id);
        }
//day
        if (dayOfWeek != null && !dayOfWeek.isEmpty()) {
            plan.setDayOfWeek(dayOfWeek);
        }

        if (sets != null && !sets.isEmpty()) {
            int  sets_ = Integer.valueOf(sets);
            plan.setSets(sets_);
        }
        if (reps != null && !reps.isEmpty()) {
            int reps_ = Integer.valueOf(reps);
            plan.setReps(reps_);
        }
        if (time != null && !time.isEmpty()) {
        int time_ = Integer.valueOf(time);
            plan.setUserID(time_);
        }
        PlanDB.insert(plan);
    }
}
