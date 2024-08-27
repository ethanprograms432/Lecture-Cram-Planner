import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;

public class Diagram extends JPanel implements Runnable {

   private static Random rand = new Random();
   public static final int mondayX = 100;
   public static final int tuesdayX = 227;
   public static final int wednesdayX = 354;
   public static final int thursdayX = 481;
   public static final int fridayX = 608;
   public static final int saturdayX = 735;
   public static final int sundayX = 862;
   public static String[] activityInfoDisplayed = new String[5];
   public static boolean showActivityInfo = false;
   BufferedImage image;
   BufferedImage logoImage;
   int counter = 0;
   Thread gameThread;
   MouseManager MM = new MouseManager();

   public Diagram() {
   
      this.setPreferredSize(new Dimension(1700,1000));
      this.setBackground(Color.white);
      this.setFocusable(true);
      this.addMouseListener(MM);
      try {
      
         image = ImageIO.read(getClass().getResourceAsStream("lecturebckgd.jpg"));
         logoImage = ImageIO.read(getClass().getResourceAsStream("logosmaller.png"));
         
      } catch(IOException e) {
      
         e.printStackTrace();
         
      }
      
   }
   
   public void startGameThread() {
   
      gameThread = new Thread(this);
      gameThread.start();
   
   }
   
   @Override
   public void run() {
   
      while(gameThread != null) {
      
         try {
         
            TimeUnit.MILLISECONDS.sleep(17);
            
         } catch(InterruptedException e) {
         
            e.printStackTrace();
            
         }
         repaint();
      
      }
   
   }
   
   public void paintComponent(Graphics g) {

        super.paintComponent(g);

        drawBackground(g);
        drawActivities(g,Activity.activityNames,Activity.startTimes,Activity.endTimes,Activity.startDays,Activity.endDays);
        counter++;


        drawActivityInfo(g);

   }
   
   public void drawActivityInfo(Graphics g) {
   
      if(showActivityInfo == true) {
         
         g.setColor(Color.white);
         g.fillRect(1200,400,400,400);
         g.setColor(Color.black);
         g.setFont(new Font("TimesRoman",Font.PLAIN,27));
         
         g.drawString("Activity: " + activityInfoDisplayed[0],1210,430);
         
         if(activityInfoDisplayed[1].equals(activityInfoDisplayed[2])) {
         
            g.drawString("Day: " + activityInfoDisplayed[1],1210,490);
            
         } else {
         
            g.drawString("Days: " + activityInfoDisplayed[1] + "-" + activityInfoDisplayed[2],1210,490);
            
         }
         
         g.drawString("Start Time: " + activityInfoDisplayed[3],1210,610);
         g.drawString("End Time: " + activityInfoDisplayed[4],1210,670);
         g.setFont(new Font("TimesRoman",Font.PLAIN,11));
      }
   
   }
   
   public void drawActivities(Graphics g,ArrayList<String> names, ArrayList<String> startTimes, ArrayList<String> endTimes, ArrayList<String> startDays, ArrayList<String> endDays) {
   
      for (int i = 0; i < names.size(); i++) {
      
         g.setColor(Activity.activityColors.get(i));
         
         String name = names.get(i);
         double startTime = Calculations.convertToDecimal(startTimes.get(i));
         double endTime = Calculations.convertToDecimal(endTimes.get(i));
         String startDay = startDays.get(i);
         String endDay = endDays.get(i);
         
         double initialStartingY = (100 + (900*(startTime/24)));
         double initialEndingY = (100 + (900*(endTime/24)));
         int startingY = (int)initialStartingY;
         int endingY = (int)initialEndingY;
         
         int indexOfDay = 0;
            
         for (int j = 0; j < 7; j++) {
            
            if(startDay.equals(LectureCramPlanner.weekDays[j])) {
               
               indexOfDay = j;
               
            }
            
         }
         
         int indexOfDayTwo = 0;
            
         for (int j = 0; j < 7; j++) {
            
            if(endDay.equals(LectureCramPlanner.weekDays[j])) {
               
               indexOfDayTwo = j;
               
            }
            
         }
         
         if(startDay.equals("everyday")) {
         
            if(startTime < endTime) {
           
               g.fillRect(mondayX+1,startingY,126,(endingY-startingY));
               g.fillRect(tuesdayX+1,startingY,126,(endingY-startingY));
               g.fillRect(wednesdayX+1,startingY,126,(endingY-startingY));
               g.fillRect(thursdayX+1,startingY,126,(endingY-startingY));
               g.fillRect(fridayX+1,startingY,126,(endingY-startingY));
               g.fillRect(saturdayX+1,startingY,126,(endingY-startingY));
               g.fillRect(sundayX+1,startingY,126,(endingY-startingY));
               
               g.setColor(Color.black);
               g.drawString(name,mondayX + 10,startingY + 10);
               g.drawString(name,tuesdayX + 10,startingY + 10);
               g.drawString(name,wednesdayX + 10,startingY + 10);
               g.drawString(name,thursdayX + 10,startingY + 10);
               g.drawString(name,fridayX + 10,startingY + 10);
               g.drawString(name,saturdayX + 10,startingY + 10);
               g.drawString(name,sundayX + 10,startingY + 10);
            
            } else {
            
               g.fillRect(mondayX+1,100,126,(endingY-100));
               g.fillRect(tuesdayX+1,100,126,(endingY-100));
               g.fillRect(wednesdayX+1,100,126,(endingY-100));
               g.fillRect(thursdayX+1,100,126,(endingY-100));
               g.fillRect(fridayX+1,100,126,(endingY-100));
               g.fillRect(saturdayX+1,100,126,(endingY-100));
               g.fillRect(sundayX+1,100,126,(endingY-100));
               
               g.fillRect(mondayX+1,startingY,126,(1000-startingY));
               g.fillRect(tuesdayX+1,startingY,126,(1000-startingY));
               g.fillRect(wednesdayX+1,startingY,126,(1000-startingY));
               g.fillRect(thursdayX+1,startingY,126,(1000-startingY));
               g.fillRect(fridayX+1,startingY,126,(1000-startingY));
               g.fillRect(saturdayX+1,startingY,126,(1000-startingY));
               g.fillRect(sundayX+1,startingY,126,(1000-startingY));
               
               g.setColor(Color.black);
               g.drawString(name,mondayX + 10,startingY + 10);
               g.drawString(name,tuesdayX + 10,startingY + 10);
               g.drawString(name,wednesdayX + 10,startingY + 10);
               g.drawString(name,thursdayX + 10,startingY + 10);
               g.drawString(name,fridayX + 10,startingY + 10);
               g.drawString(name,saturdayX + 10,startingY + 10);
               g.drawString(name,sundayX + 10,startingY + 10);
               
               g.drawString(name,mondayX + 10,110);
               g.drawString(name,tuesdayX + 10,110);
               g.drawString(name,wednesdayX + 10,110);
               g.drawString(name,thursdayX + 10,110);
               g.drawString(name,fridayX + 10,110);
               g.drawString(name,saturdayX + 10,110);
               g.drawString(name,sundayX + 10,110);
            
           }
           
        } else if (startDay.equals(endDay)) {
        
            
            if(startTime > endTime) {
            
               switch(indexOfDay) {
               
                  case(0):
                  
                     g.fillRect(mondayX+1,startingY,126,(1000-startingY));
                     g.fillRect(mondayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,mondayX + 10,startingY + 10);
                     break;
                     
                  case(1):
                  
                     g.fillRect(tuesdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(tuesdayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,tuesdayX + 10,startingY + 10);
                     break;
                     
                  case(2):
                  
                     g.fillRect(wednesdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(wednesdayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,wednesdayX + 10,startingY + 10);
                     break;
                     
                  case(3):
                  
                     g.fillRect(thursdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(thursdayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,thursdayX + 10,startingY + 10);
                     break;
                     
                  case(4):
                  
                     g.fillRect(fridayX+1,startingY,126,(1000-startingY));
                     g.fillRect(fridayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,fridayX + 10,startingY + 10);
                     break;
                     
                  case(5):
                  
                     g.fillRect(saturdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(saturdayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,saturdayX + 10,startingY + 10);
                     break;
                     
                  case(6):
                  
                     g.fillRect(sundayX+1,startingY,126,(1000-startingY));
                     g.fillRect(sundayX+1,100,126,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,sundayX + 10,startingY + 10);
                     break;
                     
                 }
                 
                 int otherIndex = indexOfDay + 1;
                 
                 while(otherIndex != indexOfDay) {
                 
                    g.setColor(Activity.activityColors.get(i));
                    
                    switch(otherIndex) {
                    
                       case(0):
                       
                           g.fillRect(mondayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,mondayX + 10,110);
                           break;
                           
                       case(1):
                       
                           g.fillRect(tuesdayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,tuesdayX + 10,110);
                           break;
                           
                       case(2):
                       
                           g.fillRect(wednesdayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,wednesdayX + 10,110);
                           break;
                           
                       case(3):
                       
                           g.fillRect(thursdayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,thursdayX + 10,110);
                           break;
                           
                       case(4):
                       
                           g.fillRect(fridayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,fridayX + 10,110);
                           break;
                           
                       case(5):
                       
                           g.fillRect(saturdayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,saturdayX + 10,110);
                           break;
                           
                       case(6):
                       
                           g.fillRect(sundayX+1,100,126,1000);
                           g.setColor(Color.black);
                           g.drawString(name,sundayX + 10,110);
                           break;
                    
                    }
                    
                    otherIndex++;
                    
                    if(otherIndex == 7) {
                    
                        otherIndex = 0;
                        
                    }
                 
                 }
                 
                 
            
            } else {
            
               switch(indexOfDay) {
       
                 case(0):
                  
                     g.fillRect(mondayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,mondayX + 10,startingY + 10);
                     break;
                     
                 case(1):
                  
                     g.fillRect(tuesdayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,tuesdayX + 10,startingY + 10);
                     break;
                     
                 case(2):
                  
                     g.fillRect(wednesdayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,wednesdayX + 10,startingY + 10);
                     break;
                     
                 case(3):
                  
                     g.fillRect(thursdayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,thursdayX + 10,startingY + 10);
                     break;
                     
                 case(4):
                  
                     g.fillRect(fridayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,fridayX + 10,startingY + 10);
                     break;
                     
                 case(5):
                  
                     g.fillRect(saturdayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,saturdayX + 10,startingY + 10);
                     break;
                     
                 case(6):
                  
                     g.fillRect(sundayX+1,startingY,126,(endingY-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,sundayX + 10,startingY + 10);
                     break;
                     
               
               }
            
            }
        
        } else {
        
            if((indexOfDayTwo - indexOfDay) > 1 || (indexOfDayTwo - indexOfDay) < 0) {
            
               int currDay = (indexOfDay + 1);
               
               switch(indexOfDay) {
               
                  case(0):
                  
                     g.fillRect(mondayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,mondayX + 10,startingY + 10);
                     break;
                     
                  case(1):
                  
                     g.fillRect(tuesdayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,tuesdayX + 10,startingY + 10);
                     break;
                  
                  case(2):
                  
                     g.fillRect(wednesdayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,wednesdayX + 10,startingY + 10);
                     break;
                     
                  case(3):
                  
                     g.fillRect(thursdayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,thursdayX + 10,startingY + 10);
                     break;
                  
                  case(4):
                  
                     g.fillRect(fridayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,fridayX + 10,startingY + 10);
                     break;
                  
                  case(5):
                  
                     g.fillRect(saturdayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,saturdayX + 10,startingY + 10);
                     break;
                  
                  case(6):
                  
                     g.fillRect(sundayX+1,startingY,126,(1000-startingY));
                     g.setColor(Color.black);
                     g.drawString(name,sundayX + 10,startingY + 10);
                     break;
               
               }
               
              
               g.setColor(Activity.activityColors.get(i));
               

               while(currDay != indexOfDayTwo) {
               
                  g.setColor(Activity.activityColors.get(i));
                  switch(currDay) {
                  
                     case(0):
                     
                        g.fillRect(mondayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,mondayX + 10,110);
                        break;
                        
                     case(1):
                     
                        g.fillRect(tuesdayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,tuesdayX + 10,110);
                        break;
                        
                     case(2):
                     
                        g.fillRect(wednesdayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,wednesdayX + 10,110);
                        break;
                     
                     case(3):
                     
                        g.fillRect(thursdayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,thursdayX + 10,110);
                        break;
                        
                     case(4):
                     
                        g.fillRect(fridayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,fridayX + 10,110);
                        break;
                     
                     case(5):
                     
                        g.fillRect(saturdayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,saturdayX + 10,110);
                        break;
                     
                     case(6):
                     
                        g.fillRect(sundayX+1,100,126,1000);
                        g.setColor(Color.black);
                        g.drawString(name,sundayX + 10,110);
                        break;
                  
                  }
                  
                  currDay++;
                  
                  if(currDay == 7) {
                  
                     currDay = 0;
                     
                  }
               
               }
               
               g.setColor(Activity.activityColors.get(i));
               switch(indexOfDayTwo) {
                  
                     case(0):
                     
                        g.fillRect(mondayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,mondayX + 10,110);
                        break;
                        
                     case(1):
                     
                        g.fillRect(tuesdayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,tuesdayX + 10,110);
                        break;
                        
                     case(2):
                     
                        g.fillRect(wednesdayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,wednesdayX + 10,110);
                        break;
                     
                     case(3):
                     
                        g.fillRect(thursdayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,thursdayX + 10,110);
                        break;
                        
                     case(4):
                     
                        g.fillRect(fridayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,fridayX + 10,110);
                        break;
                     
                     case(5):
                     
                        g.fillRect(saturdayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,saturdayX + 10,110);
                        break;
                     
                     case(6):
                     
                        g.fillRect(sundayX+1,100,126,(endingY-100));
                        g.setColor(Color.black);
                        g.drawString(name,sundayX + 10,110);
                        break;
                  
                  }
               
            } else {
               
               
               
               switch(indexOfDay) {
               
                  case(0):
                  
                     g.fillRect(mondayX+1,startingY,126,(1000-startingY));
                     g.fillRect(tuesdayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,mondayX + 10,startingY + 10);
                     break;
                     
                  case(1):
                  
                     g.fillRect(tuesdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(wednesdayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,tuesdayX + 10,startingY + 10);
                     break;
                     
                  case(2):
                     
                     g.fillRect(wednesdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(thursdayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,wednesdayX + 10,startingY + 10);
                     break;
                  
                  case(3):
                  
                     g.fillRect(thursdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(fridayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,thursdayX + 10,startingY + 10);
                     break;
                  
                  case(4):
                  
                     g.fillRect(fridayX+1,startingY,126,(1000-startingY));
                     g.fillRect(saturdayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,fridayX + 10,startingY + 10);
                     break;
                  
                  case(5):
                  
                     g.fillRect(saturdayX+1,startingY,126,(1000-startingY));
                     g.fillRect(sundayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,saturdayX + 10,startingY + 10);
                     break;
                     
                  case(6):
                  
                     g.fillRect(sundayX+1,startingY,126,(1000-startingY));
                     g.fillRect(mondayX+1,100,125,(endingY-100));
                     g.setColor(Color.black);
                     g.drawString(name,sundayX + 10,startingY + 10);
                     break;
                 
               
               }
            
            }
        
        }
      
      }     
   
   }
   
   public void drawBackground(Graphics g) {
   
      Graphics2D g2 = (Graphics2D)g;

      g2.drawImage(image,0,0,1700,1000,null);
      g2.drawImage(logoImage,1500,0,200,200,null);
      
      g2.setFont(new Font("TimesRoman", Font.BOLD, 11));
      
      g2.setColor(Color.gray);
      g2.fillRect(100,0,889,100);
      g2.fillRect(60,100,40,900);
      g2.setColor(Color.black);
      
      g2.drawLine(100,80,989,80);
      g2.drawLine(100,100,989,100);
      g2.drawLine(100,100,100,1000);
      g2.drawLine(100,1000,989,1000);
      
      for (int i = 0; i < 25; i++) {
      
         g2.drawString(i + ":00",65,110 + (37*i));
      
      }
      
      g2.drawString("Monday",mondayX + 10,90);
      g2.drawString("Tuesday",tuesdayX + 10,90);
      g2.drawString("Wednesday",wednesdayX + 10,90);
      g2.drawString("Thursday",thursdayX + 10,90);
      g2.drawString("Friday",fridayX + 10,90);
      g2.drawString("Saturday",saturdayX + 10,90);
      g2.drawString("Sunday",sundayX + 10,90);
      
      g2.drawLine(227,80,227,1000);
      g2.drawLine(354,80,354,1000);
      g2.drawLine(481,80,481,1000);
      g2.drawLine(608,80,608,1000);
      g2.drawLine(735,80,735,1000);
      g2.drawLine(862,80,862,1000);
      g2.drawLine(989,80,989,1000);
      
      if(LectureCramPlanner.daysToCatchUp == 2147483647) {
      
         g2.drawString("It is unrealistic to catch up with this schedule, I would suggest cutting it down.",300,25);
         
      } else if(LectureCramPlanner.daysToCatchUp <= 7) {
      
         g2.drawString("You should be caught up in under a week :)",400,25);
      
      } else {
      
         String time = Calculations.timeFormat(LectureCramPlanner.daysToCatchUp);
         g2.drawString("TIME TO CATCH UP: " + time,375,25);
      
      }
   
   }

}