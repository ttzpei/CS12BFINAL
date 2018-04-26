import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;
import javax.sound.sampled.*;
import java.lang.Thread;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class FinalProjectRun{

  static HashMap<String,String> ratings = readMapFromFile("scores.txt");
  static JTextArea scoreScreen = new JTextArea("Scores");

  public static void main(String[] args){

    JFrame titleScreen = new JFrame("Game Simulation");
    JPanel mainPanel2 = new JPanel();
    mainPanel2.setBackground(Color.gray);
    mainPanel2.setLayout(new BoxLayout(mainPanel2,BoxLayout.Y_AXIS));
    titleScreen.setContentPane(mainPanel2);
    JLabel gameName = new JLabel("<html><h1>Yo, it's just a game man<h1><html>");
    JButton startButton = new JButton("Start");
    mainPanel2.add(gameName);
    mainPanel2.add(startButton);

    titleScreen.setContentPane(mainPanel2);
    titleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleScreen.setLocation(120,70);
    titleScreen.setSize(500,500);
    titleScreen.setVisible(true);

    startButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
          File clap = new File("sm64_mario_lets_go.wav");
          playSound(clap);
          titleScreen.dispose();
          JFrame mainView = new JFrame("Game Simulation");
          FinalProject projectScreen = new FinalProject();
          JPanel mainPanel = new JPanel();
          mainPanel.setLayout(new BorderLayout());
          mainPanel.add(projectScreen, BorderLayout.CENTER);

          JButton finish = new JButton("End");
          JPanel sidePanel = new JPanel(new GridLayout(1,1));
          sidePanel.add(finish);
          mainPanel.add(sidePanel, BorderLayout.LINE_END);


          finish.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
              File clap2 = new File("victoryff.swf.wav");
              playSound(clap2);
              mainView.dispose();
              projectScreen.stopSound();
              JFrame finalScreen = new JFrame();
              JPanel finalPanel = new JPanel();
              JPanel finalPanel1 = new JPanel();
              JLabel finalScore = projectScreen.getLabel();
              JButton save = new JButton("save");
              JButton load = new JButton("load");
              JTextArea userSaveEntry = new JTextArea("Please put in your name!");
              JTextField fileSearch = new JTextField("scores.txt");
              finalPanel.setLayout(new BorderLayout());
              finalPanel1.add(finalScore);
              finalPanel1.add(save);
              finalPanel1.add(load);
              finalPanel1.add(userSaveEntry);
              finalPanel1.add(fileSearch);
              finalPanel.add(finalPanel1, BorderLayout.PAGE_START);
              finalPanel.add(scoreScreen, BorderLayout.CENTER);
              finalScreen.setContentPane(finalPanel);
              finalScreen.setVisible(true);
              finalScreen.setContentPane(finalPanel);
              finalScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              finalScreen.setLocation(120,70);
              finalScreen.setSize(500,500);

              save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  int scores = projectScreen.getScore();
                  String wordScore = Integer.toString(scores);
                  String name = userSaveEntry.getText();
                  String fileName = fileSearch.getText();
                  ratings.put(name,wordScore);
                  writeMapToFile(ratings,fileName);
                }
              });

              load.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  String fileName = fileSearch.getText();
                  ratings = readMapFromFile(fileName);
                  printScore(ratings);
                }
              });
            }
          });

          mainView.setContentPane(mainPanel);
          mainView.setVisible(true);
          mainView.setContentPane(mainPanel);
          mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          mainView.setLocation(120,70);
          mainView.setSize(500,500);
      }
    });

  }
  static void playSound(File sound){
    try{
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(sound));
      clip.start();

      Thread.sleep(clip.getMicrosecondLength()/1000);
    }catch(Exception e){

    }
  }

  public static void printScore(Map<String,String>d){
    scoreScreen.setText("\n\n Scorelist\n");
    Set<String> keys = d.keySet();
    for(String name: keys){
      scoreScreen.setText("\n"+scoreScreen.getText()+"The score for "+name+" is "+d.get(name)+"\n");
    }
  }

  public static void writeMapToFile(Map<String,String>d,String filename){
    try {
      PrintWriter writer = new PrintWriter(filename, "UTF-8");
      Set<String> keys = d.keySet();
      for(String name: keys){
        writer.println(name+"|"+d.get(name));
      }
      writer.close();
    } catch (Exception e){
      System.out.println("Problem writing to file: "+e);
    }
  }

  public static HashMap<String,String> readMapFromFile(String filename){
      HashMap<String,String> d = new HashMap<String,String>();
      try{
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
          String line = scanner.nextLine();
          int delimiter = line.indexOf("|");
          String key = line.substring(0,delimiter);
          String value = line.substring(delimiter+1);
          d.put(key,value);
        }
        scanner.close();
      } catch (FileNotFoundException e){
        System.out.println("Problem reading map from file "+e);
      }
      return d;
  }
}
