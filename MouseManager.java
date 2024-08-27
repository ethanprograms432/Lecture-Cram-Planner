import java.awt.event.*;
import java.awt.MouseInfo;

public class MouseManager implements MouseListener {

   public static boolean mouseDown;
   public static double mouseX;
   public static double mouseY;
   
   @Override
   public void mouseClicked(MouseEvent e) {
   
      mouseDown = true;
      int x = e.getX();
      int y = e.getY();
      getActivityClicked(x,y);
   
   }
   
   @Override
   public void mousePressed(MouseEvent e) {
   
     
   
   }
   
   @Override
   public void mouseReleased(MouseEvent e) {
   
      mouseDown = false;
   
   }
   
   @Override
   public void mouseEntered(MouseEvent e) {
   
   
   }
   
   @Override
   public void mouseExited(MouseEvent e) {
   
   
   }
   
   
   public void getActivityClicked(double mouseX,double mouseY) {
   
      for (int i = 0; i < Activity.activityCoords.size(); i++) {
      
         String testPoint = Activity.activityCoords.get(i);
         int startIndex = testPoint.indexOf(":");
         
         double minimumX = Double.valueOf(testPoint.substring(startIndex + 1,startIndex + 4));
         minimumX += 5;
         double maximumX = Double.valueOf(testPoint.substring(startIndex + 5,startIndex + 8));
         
         double minimumY = Double.valueOf(testPoint.substring(startIndex + 9,startIndex + 12));
         double maximumY = Double.valueOf(testPoint.substring(startIndex + 13,startIndex + 16));
         
         if(mouseX > minimumX && mouseX < maximumX && mouseY > minimumY && mouseY < maximumY) {
         
            String startDay = Calculations.XcoordToDay(minimumX);
            String endDay = Calculations.XcoordToDay(maximumX);
            String startTime = Calculations.YcoordToTime(minimumY);
            String endTime = Calculations.YcoordToTime(maximumY);
            String name = testPoint.substring(0,startIndex);
            
            Diagram.activityInfoDisplayed[0] = name;
            Diagram.activityInfoDisplayed[1] = startDay;
            Diagram.activityInfoDisplayed[2] = endDay;
            Diagram.activityInfoDisplayed[3] = startTime;
            Diagram.activityInfoDisplayed[4] = endTime;
            Diagram.showActivityInfo = true;
         }
      
      }
   
   }

}