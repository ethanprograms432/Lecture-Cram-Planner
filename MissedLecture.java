public class MissedLecture {

   private String eT;
   private String day;
   private String lectureName;
   public static int lecturesPerWeek = 0;
   public static int checkCounter = 0;
   
   public MissedLecture(String eT, String day, String lectureName) {
      
      this.eT = eT;
      this.day = day;
      this.lectureName = lectureName + " Online Watch";
      findGap(this.eT,this.day,this.lectureName,50);
      
   }
   
   public static void findGap(String eT,String day,String name,int lectureMinutes) {
      
      /* MAKE IT ABLE TO FIND A NEW DAY */
      int counter = 0;
      int postCounter = 0;
      int timeCounter = 0;
      String startTime = "";
      String endTime = "";
      int FACTOR = LectureCramPlanner.factor;
      int eH = Integer.valueOf(eT.substring(0,eT.indexOf(":")));
      String endMinute = eT.substring(eT.indexOf(":") + 1, eT.length());
      
      int eM = Integer.valueOf(endMinute);
      
   
      int indexOfDay = 0;
      
      for (int i = 0; i < LectureCramPlanner.weekDays.length; i++) {
      
         if(day.equals(LectureCramPlanner.weekDays[i])) {
         
            indexOfDay = i;
            
         }
      
      }

      switch(indexOfDay) {
      
         
         case(0):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableMonday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);

                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR + 10) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                     
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                        
                     }
                  
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
                  
                 
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableMonday[i][j] == null) {
               
                     if(counter == 10*FACTOR) {
                     
                        startTime = (i + ":" + Activity.possibleMinutes[j]);
                     }
                        
                     counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR)) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        if(timeCounter == 0) {
                        
                           Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        }
                        if(name.contains("Lecture Catch Up")) {
                           
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                              
                              
                           }
                  
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }
            break;
            
            case(1):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableTuesday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                     
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                        
                     }
                  
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableTuesday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                           
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                              
                           }
                  
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }
            break;
            
            case(2):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableWednesday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                  
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                        
                     }
                  
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableWednesday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                           
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                              
                           }
                  
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }            
            break;
            
            case(3):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableThursday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                  
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                        
                     }
                  
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableThursday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                           
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                           }
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }
            break;
            
            case(4):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableFriday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                  
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                     }
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableFriday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                  
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                              
                           }
                  
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }
            break;
            
            case(5):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableSaturday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                  
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                        
                     }
                  
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableSaturday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                  
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                           }
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }
            break;
            
            case(6):
         
            
            for (int i = eM; i < 60; i++) {
            
               if(Activity.timesUnavailableSunday[eH][i] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (eH + ":" + Activity.possibleMinutes[i]);
                     
                  }
                     
                  counter++;
               
               } else {
               
                  counter = 0;
                  postCounter = 0;
                  
               }
               
               if(counter == (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
               
                  endTime = (eH + ":" + Activity.possibleMinutes[i-10]);
                  Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                  if(name.contains("Lecture Catch Up")) {
                  
                     if(checkCounter == 0) {
                     
                        lecturesPerWeek++;
                     }
                  }
                  counter = 0;
                  timeCounter++;
                  
               }
            
            }
            
            for(int i = eH + 1; i < 24; i++) {
            
               for (int j = 0; j < 60; j++) {
               
                  if(Activity.timesUnavailableSunday[i][j] == null) {
               
                  if(counter == 10*FACTOR) {
                  
                     startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                  }
                     
                  counter++;
               
                  } else {
               
                     counter = 0;
                     postCounter = 0;
                  
                  }
               
                  if(counter >= (lectureMinutes + 10*FACTOR) && timeCounter == 0) {
                  
                     if(postCounter == 0) {
                     
                        endTime = (i + ":" + Activity.possibleMinutes[j]);
                        
                     }
                     postCounter++;
                     if(postCounter == 10) {
                     
                        Activity catchUpLecture = new Activity(startTime,endTime,day,day,name);
                        if(name.contains("Lecture Catch Up")) {
                  
                           if(checkCounter == 0) {
                           
                              lecturesPerWeek++;
                           }
                        }
                        counter = 0;
                        postCounter = 0;
                        timeCounter++;
                        
                     }
                  
                  }
               
               }
            
            }            
            break;
      
      }
      

         
         indexOfDay++;
         
         while(indexOfDay < 7) {
            
            boolean previousDay = false;
            switch(indexOfDay) {
            
              case(0):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableMonday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                                 
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                    
                                 }
                  
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break;
              case(1):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableTuesday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                           
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;

                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                    
                                 }
                  
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break;
               
               case(2):
                
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableWednesday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                    
                                 }
                  
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break; 
               
               case(3):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableThursday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                 }
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break; 
               
               case(4):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableFriday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                 }
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break; 
               
               case(5):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableSaturday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                                 
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                 }
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break;
               
               case(6):
              
                previousDay = false;
                
                if(postCounter > 0) {
                
                  previousDay = true;
                  
                }
                for(int i = 0; i < 24; i++) {
                
                  for(int j = 0; j < 60; j++) {
                  
                     if(Activity.timesUnavailableSunday[i][j] == null) {
               
                        if(counter == 10*FACTOR) {
                  
                           startTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                        }
                     
                           counter++;
               
                      } else {
               
                           counter = 0;
                           postCounter = 0;
                  
                      }
               
                      if(counter >= (lectureMinutes + 10*FACTOR)) {
               
                           if(postCounter == 0) {
                           
                              endTime = (i + ":" + Activity.possibleMinutes[j]);
                     
                           }
                           postCounter++;
                           
                           if(postCounter == 10) {
                           
                              double sTime = Calculations.convertToDecimal(startTime);
                              double eTime = Calculations.convertToDecimal(endTime);
                              
                              if(previousDay == true) {
                              
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay-1],name);
                                 }
                              
                              } else if(sTime > eTime) {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay-1],LectureCramPlanner.weekDays[indexOfDay],name);
                                 }
                                 
                              } else {
                           
                                 if(timeCounter == 0) {
                                 
                                    Activity catchUpLecture = new Activity(startTime,endTime,LectureCramPlanner.weekDays[indexOfDay],LectureCramPlanner.weekDays[indexOfDay],name);
                              
                                 }
                              }
                           
                              if(name.contains("Lecture Catch Up")) {
                  
                                 if(checkCounter == 0) {
                                 
                                    lecturesPerWeek++;
                                 }
                              }
                              counter = 0;
                              postCounter = 0;
                              timeCounter++;
                              
                           }
                        
                      }
                  
                  }
                
                }
                
               break;  
      
            }
            indexOfDay++;
            
            
         }

   
      if(name.contains("Lecture Catch Up")) {
      
         checkCounter++;

         
      }
   }

}

