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

  static HashMap<String,String > ratings = new HashMap<String,String>();
  static HashMap<String,String> ratings2 = new HashMap<String,String>();



  public static void main(String[] args){

    JFrame titleScreen = new JFrame("Game Simulation");
    JPanel mainPanel2 = new JPanel();
    mainPanel2.setLayout(new GridLayout(2,1));
    titleScreen.setContentPane(mainPanel2);
    JLabel gameName = new JLabel("<html><h1>Gucci Game!<h1><html>");
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
          File clap = new File("Burr.wav");
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
              File explosion = new File("Explosion.mp3");
          	  playSound(explosion);
              mainView.dispose();
              JFrame finalScreen = new JFrame();
              JPanel finalPanel = new JPanel();
              JLabel finalScore = FinalProject.label;
              JButton save = new JButton("save");
              JButton load = new JButton("load");
              JTextArea userSaveEntry = new JTextArea("Please put in your name!");
              finalPanel.setLayout(new FlowLayout());
              finalPanel.add(finalScore);
              finalPanel.add(save);
              finalPanel.add(load);
              finalPanel.add(userSaveEntry);
              finalScreen.setContentPane(finalPanel);
              finalScreen.setVisible(true);
              finalScreen.setContentPane(finalPanel);
              finalScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              finalScreen.setLocation(120,70);
              finalScreen.setSize(500,500);

              save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  int scores = FinalProject.score;
                  String wordScore = Integer.toString(scores);
                  String nameText = userSaveEntry.getText();
                  ratings.put(nameText, wordScore);
                  writeMapToFile(ratings, "ratings.txt");
                }
              });

              load.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  HashMap<String,String > d = readMapFromFile("ratings.txt");
                  printScore(d);
                  
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
    System.out.println("\n\n ScoreList:\n");
    Set<String> keys = d.keySet();
    for(String name: keys){
      System.out.println("The grade for "+name+" is "+d.get(name));
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
