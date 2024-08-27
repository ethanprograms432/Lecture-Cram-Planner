import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculations {

   /* get the weekly catch up time in minutes */
   
   public static int getWeeklyCatchUpTime(int totalActivityMinutes, int lecturesMinutesMissed) {
   
      int weeklyCatchUpTime = (10080 - (totalActivityMinutes + lecturesMinutesMissed));
      return weeklyCatchUpTime;
   
   }
   
   public static void fillLectureCatchUpSlots(int minutesBehind) {
   
      while(minutesBehind > 0) {
      
         int size = Activity.activityNames.size();
         MissedLecture.findGap("0:00","Monday","Lecture Catch Up",50);
         minutesBehind -= 50;
         int newSize = Activity.activityNames.size();
         
         if(size == newSize && LectureCramPlanner.factor < 4) {
         
            MissedLecture.findGap("0:00","Monday","50% Lecture Catch Up",25);
            minutesBehind -= 25;
            
         }
      
      }
   
   }
   
   public static double convertToDecimal(String time) {
   
      double hour = Double.valueOf(time.substring(0,time.indexOf(":")));
      
      double minute = Double.valueOf(time.substring(time.indexOf(":") + 1,time.length()));
      
      minute = (minute/100);
      minute = (minute/0.6);
      
      return hour + minute;
      
   }
   
   public static String YcoordToTime(double coordinate) {
   
      String TIME = "";
      coordinate -= 100;
      double minTimeDecimal = (coordinate/900)*24;

      DecimalFormat df = new DecimalFormat("#.00");
      String point = df.format(minTimeDecimal);
      String hour = (point.substring(0,point.indexOf(".")));
      
      if(hour.equals("")) {
      
         hour = "0";
         
      }
      
      int decimal = Integer.valueOf(point.substring(point.indexOf(".") + 1,point.length()));
      
      double minute = ((double)decimal*0.6);
      String MIN = "";
      
      if(minute < 10) {
      
         MIN = "0" + Double.toString(minute);
         
      } else {
      
         MIN = Double.toString(minute);
         
      }
      
      if(MIN.contains(".")) {
      
         String finalMinute = (MIN.substring(0,MIN.indexOf(".")));
         TIME = (hour + ":" + finalMinute);
         
      } else {
      
         TIME = (hour + ":" + MIN);
      
      }
      return TIME;
   }
   
   public static String XcoordToDay(double coordinate) {
   
      String day = "";
      if(coordinate < Diagram.tuesdayX) {
      
         day = "Monday";
         
      } else if(coordinate < Diagram.wednesdayX) {
      
         day = "Tuesday";
         
      } else if(coordinate < Diagram.thursdayX) {
      
         day = "Wednesday";
         
      } else if(coordinate < Diagram.fridayX) {
      
         day = "Thursday";
         
      } else if(coordinate < Diagram.saturdayX) {
      
         day = "Friday";
         
      } else if(coordinate < Diagram.sundayX) {
      
         day = "Saturday";
         
      } else if(coordinate < 990) {
      
         day = "Sunday";
         
      } 
      return day;
   
   }
   
   public static void getCoordinateRange(String sD, String eD, String sH, String eH, String aN) {
   
      double startDecimal = convertToDecimal(sH);
      double endDecimal = convertToDecimal(eH);
      
      double startingY = 100 + ((startDecimal/24)*900);
      double endingY = 100 + ((endDecimal/24)*900);
      
      int STARTY = (int)startingY;
      int ENDY = (int)endingY;
      int indexOfDay = 0;
      int indexOfDayTwo = 0;
      
      for (int i = 0; i < LectureCramPlanner.weekDays.length; i++) {
      
         if(sD.equals(LectureCramPlanner.weekDays[i])) {
         
            indexOfDay = i;
         
         }
         
         if(eD.equals(LectureCramPlanner.weekDays[i])) {
         
            indexOfDayTwo = i;
         
         }
      
      }
      
      if(sD.contains("everyday")) {
      
         if(STARTY < ENDY) {
         
            Activity.activityCoords.add(aN + ":" + "101-989," + STARTY + "-" + ENDY);
            
         } else {
         
            Activity.activityCoords.add(aN + ":" + "101-989," + "101-" + ENDY);
            Activity.activityCoords.add(aN + ":" + "101-989," + STARTY + "-999");
         
         }
      
      } else if(sD.equals(eD)) {
      
         if(STARTY > ENDY) {
         
            switch(indexOfDay) {
            
               case(0):
                  
                  Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(1):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(2):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(3):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(4):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(5):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + ",101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + "," + STARTY + "-999");
                  break;
               
               case(6):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-989,101-" + ENDY);
                  Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-989," + STARTY + "-999");
                  break;
            
            }
            
            int otherIndex = (indexOfDay + 1);
            
            while(otherIndex != indexOfDay) { // UP TO HERE
            
               switch(otherIndex) {
               
                  case(0):
                  
                     Activity.activityCoords.add(aN + ":101-" + (Diagram.tuesdayX-1) + ",101-999");
                     break;
                     
                  case(1):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX-1) + "-" + (Diagram.wednesdayX-1) + ",101-999");
                     break;
                     
                  case(2):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX-1) + "-" + (Diagram.thursdayX-1) + ",101-999");
                     break;
                     
                  case(3):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX-1) + "-" + (Diagram.fridayX-1) + ",101-999");
                     break;
                     
                  case(4):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.fridayX-1) + "-" + (Diagram.saturdayX-1) + ",101-999");
                     break;
                     
                  case(5):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX-1) + "-" + (Diagram.sundayX-1) + ",101-999");
                     break;
                     
                  case(6):
                  
                     Activity.activityCoords.add(aN + ":" + (Diagram.sundayX-1) + "-989,101-999");
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
               
                  Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + "," + STARTY + "-" + ENDY);
                  break;  
               case(1):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + "," + STARTY + "-" + ENDY);
                  break;
               case(2):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + "," + STARTY + "-" + ENDY);
                  break;
               case(3):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + "," + STARTY + "-" + ENDY);
                  break;
               case(4):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + "," + STARTY + "-" + ENDY);
                  break;
               case(5):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + "," + STARTY + "-" + ENDY);
                  break;
                
                case(6):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-988" + "," + STARTY + "-" + ENDY);
                  break;
            }
            
         }
        
      
      } else {
      
         switch(indexOfDay) {
         
            case(0):
            
               Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + "," + STARTY + "-999");
               break;  
            case(1):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + "," + STARTY + "-999");
               break;
            case(2):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + "," + STARTY + "-999");
               break;
            case(3):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + "," + STARTY + "-999");
               break;
            case(4):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + "," + STARTY + "-999");
               break;
            case(5):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + "," + STARTY + "-999");
               break;
             
             case(6):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-988" + "," + STARTY + "-999");
               break;
         }
         
         int currIndex = indexOfDay + 1;
         
         if(currIndex == 7) {
         
            currIndex = 0;
            
         }
         
         while(currIndex != (indexOfDayTwo)) {
         
            switch(currIndex) {
               
               case(0):
               
                  Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + ",101-999");
                  break;  
               case(1):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + ",101-999");
                  break;
               case(2):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + ",101-999");
                  break;
               case(3):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + ",101-999");
                  break;
               case(4):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + ",101-999");
                  break;
               case(5):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + ",101-999");
                  break;
                
                case(6):
               
                  Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-988" + ",101-999");
                  break;
               
                  
            } 
            currIndex++;
            
            if(currIndex == 7) {
            
               currIndex = 0;
               
            }   
         
         }
         
         switch(indexOfDayTwo) {
         
            case(0):
            
               Activity.activityCoords.add(aN + ":" + "101-" + (Diagram.tuesdayX - 1) + "," + STARTY + "-" + ENDY);
               break;  
            case(1):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.tuesdayX - 1) + "-" + (Diagram.wednesdayX - 1) + ",101-" + ENDY);
               break;
            case(2):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.wednesdayX - 1) + "-" + (Diagram.thursdayX - 1) + ",101-" + ENDY);
               break;
            case(3):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.thursdayX - 1) + "-" + (Diagram.fridayX - 1) + ",101-" + ENDY);
               break;
            case(4):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.fridayX - 1) + "-" + (Diagram.saturdayX - 1) + ",101-" + ENDY);
               break;
            case(5):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.saturdayX - 1) + "-" + (Diagram.sundayX - 1) + ",101-" + ENDY);
               break;
             
            case(6):
            
               Activity.activityCoords.add(aN + ":" + (Diagram.sundayX - 1) + "-988" + ",101-" + ENDY);
               break;
            
               
         
         }
      
      }
   }
   
   
   public static int getCatchUpDays( int minutesBehind) {
   
      
      double minsBehind = (double)minutesBehind;
      double weeklyLectures = 0;
      
      for (int i = 0; i < Activity.activityNames.size(); i++) {
      
         if(Activity.activityNames.get(i).equals("Lecture Catch Up")) {
         
            weeklyLectures++;
            
         } else if(Activity.activityNames.get(i).equals("50% Lecture Catch Up")) {
         
            weeklyLectures += 0.5;
         
         }
      
      }

      double totalLectures = (minsBehind/50.0);
      
      double numOfWeeks = (totalLectures/weeklyLectures);
      double numOfDays = (numOfWeeks*7);
      int days = (int)numOfDays;
      return days;
   }
   
   
   
   public static double calculateMinuteInterval(String sD,String eD,String sT,String eT) {
   
         String minuteTest1 = sT.substring(sT.indexOf(":") + 1,sT.length());
         int minuteONE = Integer.valueOf(minuteTest1);
         int minuteOne = minutesToDecimals(minuteONE);
         String minuteToString = Integer.toString(minuteOne);
         
         String minuteTest2 = eT.substring(eT.indexOf(":") + 1,eT.length());
         int minuteTWO = Integer.valueOf(minuteTest2);
         int minuteTwo = minutesToDecimals(minuteTWO);
         String minuteToString2 = Integer.toString(minuteTwo);
         
         String startHour = sT.substring(0,sT.indexOf(":"));
         String endHour = eT.substring(0,eT.indexOf(":"));
         
         String ST = "";
         String ET = "";
         
         if (minuteOne < 10) {
         
            ST = (startHour + ".0" + minuteToString);
            
         } else {
         
            ST = (startHour + "." + minuteToString);
            
         }
         
         if (minuteTwo < 10) {
         
            ET = (endHour + ".0" + minuteToString2);
            
         } else {
         
            ET = (endHour + "." + minuteToString2);
            
         }
         
         double x = Double.valueOf(ST);
         double y = Double.valueOf(ET);
         
         x *= 60;
         y *= 60;
      
      if (sD.contains("everyday")) {
      
         if(x > y) {
         
            double componentOne = (1440 - x);
            double componentTwo = y;
            return 7*(componentOne + componentTwo);
         
         } else {
         
            return 7*(y - x);
         
         }
      
      } else if(sD.contains(eD)) {
        
         return y - x;
         
      } else {
      
         int indexOfDayOne = 0;
         int indexOfDayTwo = 0;
         
         for (int i = 0; i < LectureCramPlanner.weekDays.length; i++) {
         
            
            
            if(sD.contains(LectureCramPlanner.weekDays[i])) {
            
               indexOfDayOne = i;
            
            }
            
            if(eD.contains(LectureCramPlanner.weekDays[i])) {
            
               indexOfDayTwo = i;
            
            }
            
         
         }
         
         if((indexOfDayTwo - indexOfDayOne) == 1) {
         
            double componentOne = (1440 - x);
            return (componentOne + y);
         
         } else {
         
            int additionalDays = (indexOfDayTwo - indexOfDayOne - 1);
            double additionalTime = (1440*additionalDays);
            
            double componentOne = (1440 - x);
            return (componentOne + y) + additionalTime;
         
         }
      }
   
   }
   
   public static int minutesToDecimals(int minutes) {
   
         double decimal = (minutes/0.6);
         int decimal2 = (int)decimal;
         return decimal2;
   
   }
   
   public static String timeFormat(double days) {
   
      double years = 0;
      double months = 0;
      double weeks = 0;
      String y = "";
      String m = "";
      String w = "";
      String d = "";
      
      if (days > 365) {
      
         years = Math.floor(days/365);
         days = (days - (years*365));
      } else {
      
         years = 0;
         
      }
      
      if (days > 30) {
      
         months = Math.floor(days/30.41666);
         days = Math.floor(days - (months*30.41666));
         
      } else {
      
         months = 0;
         
      }
      
      if (days > 6) {
      
         weeks = Math.floor(days/7);
         days = days - (7*weeks);
         
      } else {
      
         weeks = 0;
         
      }
      
      int YEARS = (int)years;
      int MONTHS = (int)months;
      int WEEKS = (int)weeks;
      int DAYS = (int)days;
      
      if(DAYS == 1) {
      
         d = "day";
         
      } else {
      
         d = "days";
         
      }
      
      if(WEEKS == 1) {
      
         w = "week";
         
      } else {
      
         w = "weeks";
         
      }
      
      if(MONTHS == 1) {
      
         m = "month";
         
      } else {
      
         m = "months";
         
      }
      
      if(YEARS == 1) {
      
         y = "year";
         
      } else {
      
         y = "years";
         
      }

      
      return YEARS + " " + y + ", " + MONTHS + " " + m + ", " + WEEKS + " " + w + ", and " + DAYS + " " + d + ".";
   }
   

}