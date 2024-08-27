import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;

public class Activity {

   
   public static String[] possibleMinutes = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
   

   public static ArrayList<String> startTimes = new ArrayList<String>();
   public static ArrayList<String> endTimes = new ArrayList<String>();
   public static ArrayList<String> startDays = new ArrayList<String>();
   public static ArrayList<String> endDays = new ArrayList<String>();
   public static ArrayList<String> activityNames = new ArrayList<String>();
   public static ArrayList<String> activityCoords = new ArrayList<String>();
   public static ArrayList<Color> activityColors = new ArrayList<Color>();
   

   public static String[][] timesUnavailableMonday = new String[24][60];
   public static String[][] timesUnavailableTuesday = new String[24][60];
   public static String[][] timesUnavailableWednesday = new String[24][60];
   public static String[][] timesUnavailableThursday = new String[24][60];
   public static String[][] timesUnavailableFriday = new String[24][60];
   public static String[][] timesUnavailableSaturday = new String[24][60];
   public static String[][] timesUnavailableSunday = new String[24][60];
   
   
   public Activity(String sT,String eT,String sD,String eD, String aN) {
   
      startTimes.add(sT);
      endTimes.add(eT);
      startDays.add(sD);
      endDays.add(eD);
      activityNames.add(aN);
      getUnavailableTimes(sT,eT,sD,eD);
      Calculations.getCoordinateRange(sD,eD,sT,eT,aN);
      
      Random rand = new Random();
      
      if(!aN.contains("Lecture Catch Up")) {
      
         int R = rand.nextInt(0,256);
         int G = rand.nextInt(0,256);
         int B = rand.nextInt(0,256);
         activityColors.add(new Color(R,G,B));
         
      } else {
      
         activityColors.add(Color.white);
         
      }
         
      
   }
   
   public static boolean isBigger(int sM,int sH,int eM,int eH) {
   
      if(sH > eH) {
      
         return true;
         
      } else if((sM > eM) && (sH == eH)) {
         
            return true;
            
      } else {
         
            return false;
            
      }
         

      
   }
 
   
   public void getUnavailableTimes(String sT,String eT,String sD,String eD) {
   
      String startMinute = sT.substring(sT.indexOf(":") + 1,sT.length());
      int sM = Integer.valueOf(startMinute);
      
      String startHour = sT.substring(0,sT.indexOf(":"));
      int sH = Integer.valueOf(startHour);
      
      String endMinute = eT.substring(eT.indexOf(":") + 1,eT.length());
      int eM = Integer.valueOf(endMinute);
      
      String endHour = eT.substring(0,eT.indexOf(":"));
      int eH = Integer.valueOf(endHour);
      
      boolean larger = isBigger(sM,sH,eM,eH);
      
      if(sD.contains("everyday")) {
      
         if (larger == false) {
            for (int i = sM; i < 60; i++) {
         

               

               timesUnavailableMonday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableTuesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableWednesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableThursday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableFriday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableSaturday[sH][i] = (startHour + ":" + possibleMinutes[i]);
               timesUnavailableSunday[sH][i] = (startHour + ":" + possibleMinutes[i]);

            
            }
         
            for (int j = sH + 1; j < eH; j++) {
         
               for (int k = 0; k < 60; k++) {
            

                  

                  timesUnavailableMonday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableTuesday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableWednesday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableThursday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableFriday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableSaturday[j][k] = (j + ":" + possibleMinutes[k]);
                  timesUnavailableSunday[j][k] = (j + ":" + possibleMinutes[k]);

               }
              
            
            }
            
            for (int q = 0; q < eM + 1; q++) {
            

               
 
                  timesUnavailableMonday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableTuesday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableWednesday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableThursday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableFriday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableSaturday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableSunday[eH][q] = (eH + ":" + possibleMinutes[q]);

               
            }
            
         } else {
         
            
            
            for (int i = sM; i < 60; i++) {
            

                

                  timesUnavailableMonday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableTuesday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableWednesday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableThursday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableFriday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableSaturday[sH][i] = (sH + ":" + possibleMinutes[i]);
                  timesUnavailableSunday[sH][i] = (sH + ":" + possibleMinutes[i]);

     
            
            }
            
            for (int i = sH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {


                  timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                  timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);

                     
               }
               
            }
               
            for (int k = 0; k < eH; k++) {
               
               for (int j = 0; j < 60; j++) {
                  


                  timesUnavailableMonday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableTuesday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableWednesday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableThursday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableFriday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableSaturday[k][j] = (k + ":" + possibleMinutes[j]);
                  timesUnavailableSunday[k][j] = (k + ":" + possibleMinutes[j]);

                          
                     
               }
                  
            }
               
              
               
           for (int q = 0; q < eM + 1; q++) {
               
     
  
                  
        
                  timesUnavailableMonday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableTuesday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableWednesday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableThursday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableFriday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableSaturday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  timesUnavailableSunday[eH][q] = (eH + ":" + possibleMinutes[q]);
                  
                  
            
            }
         }
         
      } else if(sD.contains(eD)) {
      
         int indexOfDay = 0;
         
         for (int i = 0; i < LectureCramPlanner.weekDays.length; i++) {
         
            if(LectureCramPlanner.weekDays[i].contains(sD)) {
            
               indexOfDay = i;
               
            }
         
         }
         
         switch(indexOfDay) {
         
            case(0):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableMonday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableMonday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableMonday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableMonday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableMonday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableMonday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableMonday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
               
            case(1): /* ACCOUNT FOR ACTIVITIES UNDER AN HOUR SO ADD IF STATEMENT FOR IF START AND END HOUR ARE EQUAL */
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableTuesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableTuesday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableTuesday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableTuesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableTuesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableTuesday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableTuesday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                  }
               
               }
               
               
               break;
               
            case(2):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableWednesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableWednesday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableWednesday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableWednesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableWednesday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableWednesday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableWednesday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
               
            case(3):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableThursday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableThursday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableThursday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableThursday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableThursday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableThursday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableThursday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
               
            case(4):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableFriday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableFriday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableFriday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableFriday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableFriday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableFriday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableFriday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
               
            case(5):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableSaturday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableSaturday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableSaturday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableSaturday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableSaturday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableSaturday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableSaturday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
               
            case(6):
            
               if(sH > eH) {
               
                  for(int i = sM; i < 60; i++) {
                  
                     timesUnavailableSunday[sH][i] = (startHour + ":" + possibleMinutes[i]);
                  
                  }
                  
                  for (int j = sH + 1; j < 24; j++) {
                  
                     for (int i = 0; i < 60; i++) {
                     
                        timesUnavailableSunday[j][i] = (j + ":" + possibleMinutes[i]);
                     
                     }
                  
                  }
                  
                  int otherIndex = (indexOfDay+1);
                  
                  while(otherIndex != indexOfDay) {
                  
                     switch(otherIndex) {
                     
                        case(0):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableMonday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(1):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableTuesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(2):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableWednesday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(3):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableThursday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(4):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableFriday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(5):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSaturday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                           
                         case(6):
                           
                           for (int i = 0; i < 24; i++) {
                           
                              for(int j = 0; j < 60; j++) {
                              
                                 timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                              
                              }
                           
                           }
                           break;
                     
                     }
                     
                     otherIndex++;
                     
                     if(otherIndex == 7) {
                     
                        otherIndex = 0;
                        
                     }
                  
                  }
                  
                  for(int i = 0; i < eH; i++) {
                  
                     for (int j = 0; j < 60; j++) {
                     
                       timesUnavailableSunday[i][j] = (i + ":" + possibleMinutes[j]);
                     
                     }
                  
                  }
                  
                  for(int k = 0; k < eM + 1; k++) {
                  
                     timesUnavailableSunday[eH][k] = (eH + ":" + possibleMinutes[k]);
                  
                  }
               
               } else if(sH == eH) {
               
                  for (int i = sM; i < eM + 1; i++) {
         
                     timesUnavailableSunday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
               
               } else {
               
                  for (int i = sM; i < 60; i++) {
         
                     timesUnavailableSunday[sH][i] = (startHour + ":" + possibleMinutes[i]);
         
                  }
                  
                  for (int q = 0; q < eM + 1; q++) {
         
                     timesUnavailableSunday[eH][q] = (eH + ":" + possibleMinutes[q]);
            
                  }
               
               }
         
               for (int j = sH + 1; j < eH; j++) {
         
                  for (int k = 0; k < 60; k++) {
            
                     timesUnavailableSunday[j][k] = (j + ":" + possibleMinutes[k]);
                   
                   }
               
               }
               
               
               break;
            
         
         }
                       
      
      } else {
      
         int indexOfDayOne = 0;
         int indexOfDayTwo = 0;
         
         for (int i = 0; i < LectureCramPlanner.weekDays.length; i++) {
         
            if(sD.contains(LectureCramPlanner.weekDays[i])) {
            
               indexOfDayOne = i;
            
            }
         
         }
         
         for (int j = 0; j < LectureCramPlanner.weekDays.length; j++) {
         
            if(eD.contains(LectureCramPlanner.weekDays[j])) {
            
               indexOfDayTwo = j;
            
            }
         
         }
         
         for (int k = sM; k < 60; k++) {
         
            switch(indexOfDayOne) {
            
               case(0):

                  timesUnavailableMonday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(1):
               

                  timesUnavailableTuesday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(2):
        
                  timesUnavailableWednesday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(3):
               
                  timesUnavailableThursday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(4):
               
                  timesUnavailableFriday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(5):
               
                  timesUnavailableSaturday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
               case(6):
               
                  timesUnavailableSunday[sH][k] = (sH + ":" + possibleMinutes[k]);
                  break;
                  
            }
         
         }
         
         for(int u = sH + 1; u < 24; u++) {
         
            for(int h = 0; h < 60; h++) {
            
               switch(indexOfDayOne) {
               
                  case(0):
                  
                     timesUnavailableMonday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(1):
                  
                     timesUnavailableTuesday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(2):
                  
                     timesUnavailableWednesday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(3):
                  
                     timesUnavailableThursday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(4):
                  
                     timesUnavailableFriday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(5):
                  
                     timesUnavailableSaturday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                  case(6):
                  
                     timesUnavailableSunday[u][h] = (u + ":" + possibleMinutes[h]);
                     break;
                     
                }
                
            
            }
            
            
         
         }
         
         int difference = indexOfDayTwo - indexOfDayOne;
         
         if(difference > 1 || difference < 0) {
         
           int i = (indexOfDayOne + 1);
            
           while(i != indexOfDayTwo) {
            
               
               
               for (int j = 0; j < 24; j++) {
               
                  for(int y = 0; y < 60; y++) {
                  
                     switch(i) {
                        
                        case(0):
                           
                           timesUnavailableMonday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(1):
                           
                           timesUnavailableTuesday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(2):
                           
                           timesUnavailableWednesday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(3):
                           
                           timesUnavailableThursday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(4):
                           
                           timesUnavailableFriday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(5):
                           
                           timesUnavailableSaturday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                        case(6):
                           
                           timesUnavailableSunday[j][y] = (j + ":" + possibleMinutes[y]);
                           break;
                  
                    }
               
                }
               
               
            
              }
              i++;
              
              if(i == 7) {
               
                 i = 0;
                  
              }
         
         }

          
        }
        
        for(int y = 0; y < eH; y++) {
            
               for (int f = 0; f < 60; f++) {
               
                  switch(indexOfDayTwo) {
                  
                     case(0):
                     
                        timesUnavailableMonday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(1):
                     
                        timesUnavailableTuesday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(2):
                     
                        timesUnavailableWednesday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(3):
                     
                        timesUnavailableThursday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(4):
                     
                        timesUnavailableFriday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(5):
                     
                        timesUnavailableSaturday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                     case(6):
                     
                        timesUnavailableSunday[y][f] = (y + ":" + possibleMinutes[f]);
                        break;
                        
                   }
               
               }
            
            }
            
            for(int p = 0; p < eM + 1; p++) {
            
               switch(indexOfDayTwo) {
               
                  case(0):
                  
                     timesUnavailableMonday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(1):
                  
                     timesUnavailableTuesday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(2):
                  
                     timesUnavailableWednesday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(3):
                  
                     timesUnavailableThursday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(4):
                  
                     timesUnavailableFriday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(5):
                  
                     timesUnavailableSaturday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  case(6):
                  
                     timesUnavailableSunday[eH][p] = (eH + ":" + possibleMinutes[p]);
                     break;
                  
                }     
            
            }
      } 
      

}

}
