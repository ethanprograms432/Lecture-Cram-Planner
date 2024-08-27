/* ONLY MANAGES USER INPUT */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LectureCramPlanner {

   public static String[] weekDays = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
   public static Scanner scan = new Scanner(System.in);
   public static int minutesBehind = 0;
   public static int minutesMissedPerWeek = 0;
   public static int weeklyActivityMinutes = 0;
   public static int daysToCatchUp = 0;
   public static int factor = 0;
   public static int windowCounter = 0;
   
   public static void main(String[] args) {
   
   
      JFrame frame = new JFrame("Lecture Cram Planner v1.0");
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      LectureCramPlannerGUI lP = new LectureCramPlannerGUI();
      
      frame.getContentPane().add(lP);
      
      frame.pack();
      frame.setVisible(true);
   }
   
   public static void getScheduleReady() {
   
      if(windowCounter == 0) {
      
         Calculations.fillLectureCatchUpSlots(minutesBehind);
         daysToCatchUp = Calculations.getCatchUpDays(minutesBehind);
         
         JFrame frame = new JFrame("Lecture Cram Planner v1.0 Timetable");
         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         frame.setLayout(new FlowLayout());
         
         Diagram diagram = new Diagram();
         frame.getContentPane().add(diagram);
         
         frame.pack();
         frame.setResizable(true);
         frame.setVisible(true);
         diagram.startGameThread();
         
      }
      windowCounter++;
   
   }

}   