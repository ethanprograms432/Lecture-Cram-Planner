import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LectureCramPlannerGUI extends JPanel {

    private JButton[] days = {new JButton("Monday"),new JButton("Tuesday"),new JButton("Wednesday"),
    new JButton("Thursday"),new JButton("Friday"),new JButton("Saturday"),new JButton("Sunday"),
    new JButton("Everyday")};
    
    private boolean everyday,mon,tue,wed,thu,fri,sat,sun,YES,NO,FINISHED,CANCEL;
    private JButton start,instructions,background, back, confirm, finished,yes,no,cancel;
    private JTextField numAnswer;
    private JLabel question;
    private JRadioButton am,pm;
    private static int stage = 1;
    private static int subStage = 1;
    private static int backCounter = 0;
    private static int instructionCounter = 0;
    private static int activityCounter = 0;
    private static int actualActivityCounter = 1;
    private static int optionCounter = 0;
    
    JPanel mainScreen,instructionScreen,backgroundScreen,numQuestionScreen,optionQuestionScreen;
    JPanel questionPanel,answerPanel,confirmPanel;
    private static String activityName,endDay,startTime,endTime;
    private static ArrayList<String> startDays = new ArrayList<String>();
    
    public LectureCramPlannerGUI() {
    
       this.setPreferredSize(new Dimension(600,600));
       ButtonListener bL = new ButtonListener();
       AnswerListener aL = new AnswerListener();
       
       JPanel mS = new JPanel();
       this.mainScreen = mS;
       add(this.mainScreen);
       
       JPanel iS = new JPanel();
       this.instructionScreen = iS;
       
       JPanel bS = new JPanel();
       this.backgroundScreen = bS;
       
       JPanel OqS = new JPanel();
       this.optionQuestionScreen = OqS;
       
       JPanel nqS = new JPanel();
       this.numQuestionScreen = nqS;
       
       days[0].setBackground(Color.white);
       days[1].setBackground(Color.white);
       days[2].setBackground(Color.white);
       days[3].setBackground(Color.white);
       days[4].setBackground(Color.white);
       days[5].setBackground(Color.white);
       days[6].setBackground(Color.white);
       days[7].setBackground(Color.white);
       
       
       instructions = new JButton("Instructions");
       instructions.setBackground(Color.yellow);
       instructions.setPreferredSize(new Dimension(200,200));
       instructions.addActionListener(bL);
               
       start = new JButton("Start");
       start.setBackground(Color.green);
       start.setPreferredSize(new Dimension(200,200));
       start.addActionListener(bL);
               
       background = new JButton("Background");
       background.setBackground(Color.red);
       background.setPreferredSize(new Dimension(200,200));
       background.addActionListener(bL);
       
       back = new JButton("Back");
       back.setBackground(Color.cyan);
       back.addActionListener(bL);
       
       confirm = new JButton("Confirm");
       confirm.setBackground(Color.green);
       confirm.addActionListener(aL);
       
       finished = new JButton("Finished?");
       finished.setBackground(Color.red);
       finished.addActionListener(aL);
       
       question = new JLabel(" ");
       
       numAnswer = new JTextField(10);
       
       cancel = new JButton("Back To Start");
       cancel.setBackground(Color.red);
       cancel.addActionListener(aL);
       
       manageStages();
    }
    
        
    public void manageStages() {
    
       switch(stage) {
       
         case(1):
         
            if(subStage == 1) {
            
               ButtonListener bL = new ButtonListener();
               
               
               mainScreen.setLayout(new BorderLayout());
               
               
               
               JLabel title = new JLabel("                                                                    Welcome To Lecture Cram Planner");
               title.setPreferredSize(new Dimension(600,200));
               
               
               
               mainScreen.add(title,BorderLayout.NORTH);
               mainScreen.add(background,BorderLayout.WEST);
               mainScreen.add(start,BorderLayout.CENTER);
               mainScreen.add(instructions,BorderLayout.EAST);
               mainScreen.add(new JLabel("Created by Ethan James"),BorderLayout.SOUTH);
               
            } else if(subStage == 2) {
            
               instructionCounter++;
               ButtonListener bL = new ButtonListener();
               
               add(instructionScreen);
               instructionScreen.setVisible(true);
               instructionScreen.setLayout(new GridLayout(28,1));
               
               int counter = 0;
               
               if(instructionCounter <= 1) {
               
                  try {
                  
                     Scanner fileScanner = new Scanner(new File("instructions"));
                     
                     while(fileScanner.hasNextLine()) {
                     
                        JLabel currLabel = new JLabel(fileScanner.nextLine());
                        currLabel.setPreferredSize(new Dimension(600,20));
                        counter++;
                        instructionScreen.add(currLabel);
                     
                     }
                     
                  } catch(FileNotFoundException e) {
                  
                     e.printStackTrace();
                     
                  }
                  
                  
                  
                  
               }
               
               instructionScreen.add(back);
            
            } else if(subStage == 3) {
            
               backCounter++;
               ButtonListener bL = new ButtonListener();
               
               add(backgroundScreen);
               backgroundScreen.setVisible(true);
               backgroundScreen.setLayout(new GridLayout(14,1));
               
               int counter = 0;
               
               if(backCounter <= 1) {
               
                  try {
                  
                     Scanner fileScanner = new Scanner(new File("backgroundInfo"));
                     
                     while(fileScanner.hasNextLine()) {
                     
                        JLabel currLabel = new JLabel(fileScanner.nextLine());
                        currLabel.setPreferredSize(new Dimension(600,20));
                        counter++;
                        backgroundScreen.add(currLabel);
                     
                     }
                     
                  } catch(FileNotFoundException e) {
                  
                     e.printStackTrace();
                     
                  }

                  
               }
               
               backgroundScreen.add(back);
            
            }
            
            break;
            
         case(2):
         
            add(numQuestionScreen);
            numQuestionScreen.setLayout(new GridLayout(10,1));
            
            question.setText("How many lectures are you behind on?");
            numQuestionScreen.add(question);
            
            numQuestionScreen.add(numAnswer);
            numQuestionScreen.add(confirm);
            numQuestionScreen.add(finished);
            finished.setVisible(false);
            
            
            break;
            
         case(3):
            
            question.setText("Stress Rating (1 - 5)");
            numQuestionScreen.remove(back);
            break;
            
         case(4):
         

            
            if(subStage == 1) {
               
               startDays.clear();
               resetColors();
               if(CANCEL == true) {
               
                  numQuestionScreen.setVisible(true);
                  
                  numQuestionScreen.removeAll();
                  numQuestionScreen.repaint();
                  numQuestionScreen.revalidate();
                  
                  numQuestionScreen.add(question);
                  question.setText("Weekly Activity " + actualActivityCounter + " Name: ");
                  
                  numQuestionScreen.add(numAnswer);
                  numQuestionScreen.add(confirm);
                  numQuestionScreen.add(finished);
                  finished.setVisible(false);
                  
                  optionQuestionScreen.setVisible(false);
                  
               } else {
               
                  question.setText("Weekly Activity " + actualActivityCounter + " Name: ");
               
               }
               
               
            } else if(subStage == 2) {
            
                  if(activityCounter == 0) {
                  
                     AnswerListener aL = new AnswerListener();
                     add(optionQuestionScreen);
                     optionQuestionScreen.setLayout(new GridLayout(3,1));
                              
                     questionPanel = new JPanel();
                     questionPanel.setPreferredSize(new Dimension(600,200));
                     optionQuestionScreen.add(questionPanel);
                              
                     answerPanel = new JPanel();
                     answerPanel.setLayout(new GridLayout(4,2));
                     answerPanel.setPreferredSize(new Dimension(600,200));
                     optionQuestionScreen.add(answerPanel);
                              
                     confirmPanel = new JPanel();
                     confirmPanel.setPreferredSize(new Dimension(600,200));
                     optionQuestionScreen.add(confirmPanel);
                              
                     questionPanel.add(question);
                     question.setText("Start Day (can select multiple days): ");
                              
                     for(JButton button: days) {
                              
                          answerPanel.add(button);
                          button.addActionListener(aL);
                              
                     }
                     
                     answerPanel.add(cancel);
                              
                     confirmPanel.add(confirm);
                     activityCounter++;
                     
                  } else {

                     optionQuestionScreen.setVisible(true);
                     
                     answerPanel.removeAll();
                     answerPanel.repaint();
                     answerPanel.revalidate();
                     
                     for(JButton button: days) {
                              
                          answerPanel.add(button);
                              
                     }
                     
                     answerPanel.add(cancel);

                     confirmPanel.add(confirm);
                     questionPanel.add(question);
                     question.setText("Start Day (can select multiple days): ");
                     
                  }
               
               
            
            } else if(subStage == 3) {
            
               AnswerListener aL = new AnswerListener();
               question.setText("Is the end day the same as the start day?");
               
               answerPanel.removeAll();
               answerPanel.repaint();
               answerPanel.revalidate();
               
               yes = new JButton("Yes");
               yes.setBackground(Color.white);
               yes.addActionListener(aL);
               
               no = new JButton("No");
               no.setBackground(Color.white);
               no.addActionListener(aL);
               
               answerPanel.add(yes);
               answerPanel.add(no);
               answerPanel.add(cancel);
               
            } else if(subStage == 4) {
            
               answerPanel.remove(yes);
               answerPanel.remove(no);
               answerPanel.remove(cancel);
               answerPanel.repaint();
               answerPanel.revalidate();
               
               question.setText("End Day:");
               
               for (JButton button: days) {
               
                  if(!button.getText().equals("Everyday")) {
                  
                     answerPanel.add(button);
                     
                  }
               
               }
               resetColors();
               answerPanel.add(cancel);
            
            } else if(subStage == 5) {
            
               optionQuestionScreen.setVisible(false);
               numQuestionScreen.remove(numAnswer);
               numQuestionScreen.setVisible(true);
               
               numQuestionScreen.add(question);
               numQuestionScreen.add(numAnswer);
               numQuestionScreen.add(confirm);
               
               question.setText("Start Time (i.e 20:30):");
            
            } else if(subStage == 6) {
            
               question.setText("End Time:");
               finished.setVisible(true);
            
            }
            break;
            
         case(5):
         
            if(subStage == 1) {
            
               startDays.clear();
               resetColors();
               AnswerListener aL = new AnswerListener();
               
               numQuestionScreen.setVisible(false);
               optionQuestionScreen.setVisible(true);
               
               answerPanel.removeAll();
               answerPanel.repaint();
               answerPanel.revalidate();
               
               yes = new JButton("Yes");
               yes.addActionListener(aL);
               yes.setBackground(Color.white);
               no = new JButton("No");
               no.addActionListener(aL);
               no.setBackground(Color.white);
               
               answerPanel.add(yes);
               answerPanel.add(no);
               
               
               question.setText("Do your current commitments cause you to miss lectures during the week?");
               
               questionPanel.add(question);
               
               confirmPanel.add(confirm);
               
            } else if(subStage == 2) {
            
               numQuestionScreen.setVisible(true);
               optionQuestionScreen.setVisible(false);
               
               numQuestionScreen.remove(numAnswer);
               
               numQuestionScreen.add(question);
               numQuestionScreen.add(numAnswer);
               numQuestionScreen.add(confirm);
               question.setText("How many lectures do you miss per week?");
               
            } else if(subStage == 3) {
            
               startDays.clear();
               resetColors();
               if(CANCEL == true) {
               
                  numQuestionScreen.setVisible(true);
                  optionQuestionScreen.setVisible(false);
                  
                  numQuestionScreen.removeAll();
                  numQuestionScreen.repaint();
                  numQuestionScreen.revalidate();
                  
                  numQuestionScreen.add(question);
                  question.setText("Lecture " + (optionCounter+1) + " Subject: ");
                  
                  numQuestionScreen.add(numAnswer);
                  numQuestionScreen.add(confirm);
                  
               } else {
               
                  question.setText("Lecture " + (optionCounter+1) + " Subject: ");
                  
               }
                  
               
               
            } else if(subStage == 4) {
            
               numQuestionScreen.setVisible(false);
               optionQuestionScreen.setVisible(true);
               
               answerPanel.remove(yes);
               answerPanel.remove(no);
               
               for (JButton button: days) {
               
                  if(!button.getText().equals("Everyday")) {
                  
                     answerPanel.add(button);
                     
                  }
               
               }
               answerPanel.add(cancel);
               
               questionPanel.add(question);
               question.setText("Day Of Lecture");
               confirmPanel.add(confirm);
            
            } else if(subStage == 5) {
            
               optionQuestionScreen.setVisible(false);
               numQuestionScreen.setVisible(true);
               
               numQuestionScreen.remove(numAnswer);
               
               numQuestionScreen.add(question);
               question.setText("Lecture End Time");
               numQuestionScreen.add(numAnswer);
               numQuestionScreen.add(confirm);
            
            }
            break;
               
               


         case(6):
         
            numQuestionScreen.remove(numAnswer);
            numQuestionScreen.remove(confirm);
            
            question.setText("Generating catch-up schedule...");
            LectureCramPlanner.getScheduleReady();
            break;      
       }   
    
    }
    
    private class ButtonListener implements ActionListener {
    
       public void actionPerformed(ActionEvent ae) {
       
          JButton b = (JButton)ae.getSource();
          
          if(b == instructions) {
          
            subStage = 2;
            mainScreen.setVisible(false);
            manageStages();
          
          } else if(b == back) {
          
            if(subStage == 2) {
            
               subStage = 1;
               mainScreen.setVisible(true);
               instructionScreen.setVisible(false);
               manageStages();
               
            } else if(subStage == 3) {
            
               subStage = 1;
               mainScreen.setVisible(true);
               backgroundScreen.setVisible(false);
               manageStages();
               
            }
               
            
          } else if(b == background) {
          
            subStage = 3;
            mainScreen.setVisible(false);
            manageStages();
          
          } else if(b == start) {
          
            stage = 2;
            mainScreen.setVisible(false);
            manageStages();
            
          }
       
       }
    
    }
    
    private class AnswerListener implements ActionListener {
    
      public void actionPerformed(ActionEvent ae) {
      
         JButton b = (JButton)ae.getSource();
         
         if(b == confirm) {
         
            
            if(stage == 2) {
            
               try {
               
                  int lectures = Integer.valueOf(numAnswer.getText());
                  
                  if(lectures >= 0) {
                  
                     LectureCramPlanner.minutesBehind = 50*lectures;
                     stage = 3;
                     
                  }
                  
               } catch(InputMismatchException ime) {
               
                  String x = numAnswer.getText();
                  numAnswer.setText("");
                  
               } catch(NumberFormatException ime) {
               
                  String x = numAnswer.getText();
                  numAnswer.setText("");
               
               } catch(NoSuchElementException e) {
               
                  String x = numAnswer.getText();
                  numAnswer.setText("");
               
               }
               manageStages();
               
            } else if(stage == 3) {
            
               try {
               int rating = Integer.valueOf(numAnswer.getText());
            
                  switch(rating) {
                  
                     case(1):
                     
                        LectureCramPlanner.factor = 5;
                        stage = 4;
                        subStage = 1;
                        break;
                        
                     case(2):
                     
                        LectureCramPlanner.factor = 4;
                        stage = 4;
                        subStage = 1;
                        break;
                        
                     case(3):
                     
                        LectureCramPlanner.factor = 3;
                        stage = 4;
                        subStage = 1;
                        break;
                        
                     case(4):
                     
                        LectureCramPlanner.factor = 2;
                        stage = 4;
                        subStage = 1;
                        break;
                        
                     case(5):
                     
                        LectureCramPlanner.factor = 1;
                        stage = 4;
                        subStage = 1;
                        break;
                        
                     default:
                     
                        numAnswer.setText("");
                        break;
                        
                  }
                  
               } catch(NumberFormatException ime) {
               
                  String x = numAnswer.getText();
                  
                  if(x.equals("CANCEL")) {
                  
                     stage = 2;
                  
                  }
                  numAnswer.setText("");
                  
               } catch(NoSuchElementException e) {
               
                  String x = numAnswer.getText();
                  numAnswer.setText("");
               
               }
               manageStages();
            
            } else if(stage == 4) {
            
            
               if(subStage == 1) {
               
                  String response = numAnswer.getText();
                  activityName = response;
                  subStage = 2;
                  manageStages();
                  numQuestionScreen.setVisible(false);
                  
               } else if(subStage == 2) {
               
                  if(mon == true) {
                  
                     subStage = 3;
                     startDays.add("Monday");
                     
                  } 
                  
                  if(tue == true) {
                  
                     subStage = 3;
                     startDays.add("Tuesday");
                     
                  } 
                  
                  if(wed == true) {
                  
                     subStage = 3;
                     startDays.add("Wednesday");
                     
                  } 
                  
                  if(thu == true) {
                  
                     subStage = 3;
                     startDays.add("Thursday");
                     
                  } 
                  
                  if(fri == true) {
                  
                     subStage = 3;
                     startDays.add("Friday");
                     
                  } 
                  
                  if(sat == true) {
                  
                     subStage = 3;
                     startDays.add("Saturday");
                     
                  } 
                  
                  if(sun == true) {
                  
                     subStage = 3;
                     startDays.add("Sunday");
                     
                  } 
                  
                  if(everyday == true) {
                  
                     subStage = 5;
                     startDays.clear();
                     startDays.add("everyday");
                     endDay = "everyday";
                     
                  } 
                  
                  if(startDays.size() > 1) {
                  
                     subStage = 5;
                  
                  }
                  
                  if(CANCEL == true) {
                  
                     subStage = 1;
                  
                  }
                  
                  manageStages();
               
               } else if(subStage == 3) {
               
                  if(YES == true) {
                  
                     subStage = 5;
                     endDay = startDays.get(0);
                     manageStages();
                     
                  } else if(NO == true) {
                  
                     subStage = 4;
                     manageStages();
                     
                  } else if(CANCEL == true) {
                  
                     subStage = 1;
                     manageStages();
                     
                  }
                     
               
               } else if(subStage == 4) {
               
                  if(mon == true) {
                  
                     subStage = 5;
                     endDay = "Monday";
                     manageStages();
                     
                  } else if(tue == true) {
                  
                     subStage = 5;
                     endDay = "Tuesday";
                     manageStages();
                     
                  } else if(wed == true) {
                  
                     subStage = 5;
                     endDay = "Wednesday";
                     manageStages();
                     
                  } else if(thu == true) {
                  
                     subStage = 5;
                     endDay = "Thursday";
                     manageStages();
                     
                  } else if(fri == true) {
                  
                     subStage = 5;
                     endDay = "Friday";
                     manageStages();
                     
                  } else if(sat == true) {
                  
                     subStage = 5;
                     endDay = "Saturday";
                     manageStages();
                     
                  } else if(sun == true) {
                  
                     subStage = 5;
                     endDay = "Sunday";
                     manageStages();
                     
                  } else if(CANCEL == true) {
                  
                     subStage = 1;
                     manageStages();
                  
                  }
               
               } else if(subStage == 5) {
               
                  startTime = numAnswer.getText();
                  int colonCounter = 0;
                  
                  for (int f = 0; f < startTime.length(); f++) {
                  
                     if(startTime.charAt(f) == ':') {
                     
                        colonCounter++;
                     
                     }
                  
                  }
                  
                  if(colonCounter == 1 && startTime.length() > 3 && startTime.length() < 6) {
                  
                     // MAKE SURE THAT U CANNOT ENTER 2 COLONS 
                     boolean valid = true;
                     int hour = Integer.valueOf(startTime.substring(0,startTime.indexOf(":")));
                     int minute = Integer.valueOf(startTime.substring(startTime.indexOf(":") + 1));
                     
                     if(hour < 0 || hour > 23) {
                     
                        valid = false;
                     
                     }
                     
                     if(minute < 0 || minute > 59) {
                     
                        valid = false;
                        
                     }
                     
                     if(valid == true) {
                     
                        subStage = 6;
                        manageStages();
                     
                     } else {
                     
                        manageStages();
                        
                     }
                     
                  } else if(startTime.equals("CANCEL")) {
                  
                     subStage = 1;
                     manageStages();
           
                     
                  }
               
               } else if(subStage == 6) {
               

                  boolean valid = true;
                  endTime = numAnswer.getText();
                  int colonCounter = 0;
                  
                  for (int f = 0; f < endTime.length(); f++) {
                  
                     if(endTime.charAt(f) == ':') {
                     
                        colonCounter++;
                     
                     }
                  
                  }
                  
                  
                  if(FINISHED == true && colonCounter == 1 && endTime.length() > 3 && endTime.length() < 6) {
                  

                     int hour = Integer.valueOf(endTime.substring(0,endTime.indexOf(":")));
                     int minute = Integer.valueOf(endTime.substring(endTime.indexOf(":") + 1));
                     
                     if(hour < 0 || hour > 23) {
                     
                        valid = false;
                     
                     }
                     
                     if(minute < 0 || minute > 59) {
                     
                        valid = false;
                        
                     }
                     
                     if(valid == true) {
                     
                        stage = 5;
                        subStage = 1;
                        
                        if(startDays.size() > 1) {
                        
                           for (int i = 0; i < startDays.size(); i++) {
                           
                              Activity activity = new Activity(startTime,endTime,startDays.get(i),startDays.get(i),activityName);
                           
                           }
                           
                        } else {
                        
                           Activity activity = new Activity(startTime,endTime,startDays.get(0),endDay,activityName);
                           
                        }
          
                        actualActivityCounter++;
                        manageStages();
                        finished.setVisible(false);
                        
                     } else {
                     
                        FINISHED = false;
                        manageStages();
                        
                     }
                     
                  } else if(colonCounter == 1 && endTime.length() > 3 && endTime.length() < 6) {
                  
                     int hour = Integer.valueOf(endTime.substring(0,endTime.indexOf(":")));
                     int minute = Integer.valueOf(endTime.substring(endTime.indexOf(":") + 1));
                     
                     if(hour < 0 || hour > 23) {
                     
                        valid = false;
                     
                     }
                     
                     if(minute < 0 || minute > 59) {
                     
                        valid = false;
                        
                     }
                     
                     if(valid == true) {
                     
                        subStage = 1;
                        
                        if(startDays.size() > 1) {
                        
                           for (int i = 0; i < startDays.size(); i++) {
                           
                              Activity activity = new Activity(startTime,endTime,startDays.get(i),startDays.get(i),activityName);
                           
                           }
                           startDays.clear();
                           
                        } else {
                        
                           Activity activity = new Activity(startTime,endTime,startDays.get(0),endDay,activityName);
                           startDays.clear();
                           
                        }
                        
                        actualActivityCounter++;
                        manageStages();
                        finished.setVisible(false);
                        
                     } else {
                     
                        manageStages();
                        
                     }
                     
                  } else if(endTime.equals("CANCEL")) {
                  
                     subStage = 1;
                  
                     manageStages();
                     finished.setVisible(false);
                  
                  }
               
               }
               
            } else if(stage == 5) {
            
               if(subStage == 1) {

                  if(YES == true) {
                  
                     subStage = 2;
                     manageStages();
                     
                 } else if(NO == true) {
                 
                     stage = 6;
                     manageStages();
                     
                 }
               
               } else if(subStage == 2) {
               
                  try {
                  
                     int lecturesMissed = Integer.valueOf(numAnswer.getText());
                     
                     if(lecturesMissed > 0) {
                     
                        LectureCramPlanner.minutesMissedPerWeek = 50*lecturesMissed;
                        subStage = 3;
                        
                     } else {
                     
                        numAnswer.setText("");
                     
                     }
                     
                  } catch(NumberFormatException e) {
                  
                     String x = numAnswer.getText();
                     
                     if(x.equals("CANCEL")) {
                     
                        subStage = 1;
                        manageStages();
                        numAnswer.setText("");
                     
                     }
                     numAnswer.setText("");
                  
                  } catch(NoSuchElementException e) {
                  
                     numAnswer.setText("");
                  
                  }
                  manageStages();
               
               } else if(subStage == 3) {
               
                  activityName = numAnswer.getText();
                  
                  subStage = 4;
                  manageStages();
               
               } else if(subStage == 4) {
               
                  int dayCounter = 0;
                  int lectures = (LectureCramPlanner.minutesMissedPerWeek/50);
                  
                  if(mon == true) {
                  
                     subStage = 5;
                     startDays.add("Monday");
                     dayCounter++;
                     
                  } 
                  
                  if(tue == true) {
                  
                     subStage = 5;
                     startDays.add("Tuesday");
                     dayCounter++;
                     
                  } 
                  
                  if(wed == true) {
                  
                     subStage = 5;
                     startDays.add("Wednesday");
                     dayCounter++;
                     
                  } 
                  
                  if(thu == true) {
                  
                     subStage = 5;
                     startDays.add("Thursday");
                     dayCounter++;
                     
                  } 
                  
                  if(fri == true) {
                  
                     subStage = 5;
                     startDays.add("Friday");
                     dayCounter++;
                     
                  } 
                  
                  if(sat == true) {
                  
                     subStage = 5;
                     startDays.add("Saturday");
                     dayCounter++;
                     
                  } 
                  
                  if(sun == true) {
                  
                     subStage = 5;
                     startDays.add("Sunday");
                     dayCounter++;
                     
                     
                  }
                  
                  if(dayCounter > lectures) {
                  
                     subStage = 4;
                     startDays.clear();
                  
                  } 
                  
                  if(CANCEL == true) {
                  
                     startDays.clear();
                     subStage = 3;
                     
                  
                  }
                  manageStages();
               
               } else if(subStage == 5) {
               
                  
                  endTime = numAnswer.getText();
                  int colonCounter = 0;
                  
                  for (int f = 0; f < endTime.length(); f++) {
                  
                     if(endTime.charAt(f) == ':') {
                     
                        colonCounter++;
                     
                     }
                  
                  }
                  
                  int lectures = (LectureCramPlanner.minutesMissedPerWeek/50);
                  
                  
                  
                  if(colonCounter == 1 && endTime.length() > 3 && endTime.length() < 6) {
                  
                     boolean valid = true;
                     int hour = Integer.valueOf(endTime.substring(0,endTime.indexOf(":")));
                     int minute = Integer.valueOf(endTime.substring(endTime.indexOf(":") + 1));
                     
                     if(hour < 0 || hour > 23) {
                     
                        valid = false;
                     
                     }
                     
                     if(minute < 0 || minute > 59) {
                     
                        valid = false;
                        
                     }
  
                     if((optionCounter + 1) >= lectures && valid == true) {
                     
                        stage = 6;
                        
                        if (startDays.size() > 1) {
                        
                           for (int i = 0; i < startDays.size(); i++) {
                           
                              MissedLecture mS = new MissedLecture(endTime,startDays.get(i),activityName);
                              startDays.clear();
                              resetColors();
                           
                           }
                           
                        } else {
                        
                           MissedLecture mS = new MissedLecture(endTime,startDays.get(0),activityName);
                           startDays.clear();
                           resetColors();
                        
                        }
                        
                        manageStages();
                     
                     } else if(valid == true) { 
                     
                        subStage = 3;
                        if (startDays.size() > 1) {
                        
                           for (int i = 0; i < startDays.size(); i++) {
                           
                              MissedLecture mS = new MissedLecture(endTime,startDays.get(i),activityName);
                              resetColors();
                              optionCounter++;
                           
                           }
                           
                        } else {
                        
                           MissedLecture mS = new MissedLecture(endTime,startDays.get(0),activityName);
                           resetColors();
                           optionCounter++;
                        
                        }
                        
                        if (optionCounter >= lectures) {
                        
                           stage = 6;
                           manageStages();
                           
                        } else {
                        
                           manageStages();
                        
                        }
                     
                     }
                     
                  } else if(endTime.contains("CANCEL")) {
                  
                     subStage = 3;
                     manageStages();
                     startDays.clear();
                  
                  }
                  manageStages();
                  
               
               }
            
            }
            numAnswer.setText("");
         } else if(b == days[0]) {
         
            if(stage == 4 && subStage == 4) {
            
               mon = true;
               tue = false;
               wed = false;
               thu = false;
               fri = false;
               sat = false;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.green);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(mon == false) {
               
                  mon = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[0].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  mon = false;
                  CANCEL = false;
                  days[0].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[1]) {
         

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = true;
               wed = false;
               thu = false;
               fri = false;
               sat = false;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.green);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(tue == false) {
               
                  tue = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[1].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  tue = false;
                  CANCEL = false;
                  days[1].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[2]) {
         

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = false;
               wed = true;
               thu = false;
               fri = false;
               sat = false;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.green);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(wed == false) {
               
                  wed = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[2].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  wed = false;
                  CANCEL = false;
                  days[2].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[3]) {
         

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = false;
               wed = false;
               thu = true;
               fri = false;
               sat = false;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.green);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(thu == false) {
               
                  thu = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[3].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  thu = false;
                  CANCEL = false;
                  days[3].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[4]) {
         

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = false;
               wed = false;
               thu = false;
               fri = true;
               sat = false;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.green);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(fri == false) {
               
                  fri = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[4].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  fri = false;
                  CANCEL = false;
                  days[4].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[5]) {
         

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = false;
               wed = false;
               thu = false;
               fri = false;
               sat = true;
               sun = false;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.green);
               days[6].setBackground(Color.white);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(sat == false) {
               
                  sat = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[5].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  sat = false;
                  CANCEL = false;
                  days[5].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[6]) {

            if(stage == 4 && subStage == 4) {
            
               mon = false;
               tue = false;
               wed = false;
               thu = false;
               fri = false;
               sat = false;
               sun = true;
               CANCEL = false;
               
               days[0].setBackground(Color.white);
               days[1].setBackground(Color.white);
               days[2].setBackground(Color.white);
               days[3].setBackground(Color.white);
               days[4].setBackground(Color.white);
               days[5].setBackground(Color.white);
               days[6].setBackground(Color.green);
               days[7].setBackground(Color.white);
               
            } else {
            
               if(sun == false) {
               
                  sun = true;
                  everyday = false;
                  CANCEL = false;
                  
                  days[6].setBackground(Color.green);
                  days[7].setBackground(Color.white);
                  
               } else {
               
                  sun = false;
                  CANCEL = false;
                  days[6].setBackground(Color.white);
               
               }
               
            }
         
         } else if(b == days[7]) {

            everyday = true;
            mon = false;
            tue = false;
            wed = false;
            thu = false;
            fri = false;
            sat = false;
            sun = false;
            CANCEL = false;
            
            days[0].setBackground(Color.white);
            days[1].setBackground(Color.white);
            days[2].setBackground(Color.white);
            days[3].setBackground(Color.white);
            days[4].setBackground(Color.white);
            days[5].setBackground(Color.white);
            days[6].setBackground(Color.white);
            days[7].setBackground(Color.green);
         
         } else if(b == yes) {
         

            YES = true;
            NO = false;
            CANCEL = false;
            no.setBackground(Color.white);
            yes.setBackground(Color.green);
            
         } else if(b == no) {
         

            NO = true;
            YES = false;
            CANCEL = false;
            no.setBackground(Color.green);
            yes.setBackground(Color.white);
         
         } else if(b == finished) {
         
            FINISHED = true;
            CANCEL = false;
         
         } else if(b == cancel) {
         
            CANCEL = true;
            everyday = false;
            mon = false;
            tue = false;
            wed = false;
            thu = false;
            fri = false;
            sat = false;
            sun = false;
            
            resetColors();
            
            if(no != null && yes != null) {
            
               no.setBackground(Color.white);
               yes.setBackground(Color.white);
               NO = false;
               YES = false;
               
            }
            
         
         }
      
      }
      
    }
    
    public void resetColors() {
    
            days[0].setBackground(Color.white);
            days[1].setBackground(Color.white);
            days[2].setBackground(Color.white);
            days[3].setBackground(Color.white);
            days[4].setBackground(Color.white);
            days[5].setBackground(Color.white);
            days[6].setBackground(Color.white);
            days[7].setBackground(Color.white);
            
                  mon = false;
                  tue = false;
                  wed = false;
                  thu = false;
                  fri = false;
                  sat = false;
                  sun = false;
                  everyday = false;
    
    }
   

}