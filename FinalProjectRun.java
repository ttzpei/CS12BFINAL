import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class FinalProjectRun{

  public static void main(String[] args){

    JFrame titleScreen = new JFrame("Game Simulation");
    JPanel mainPanel2 = new JPanel();
    mainPanel2.setLayout(new GridLayout(2,1));
    titleScreen.setContentPane(mainPanel2);
    JLabel gameName = new JLabel("<html><h1>GAME TIME!!!!!!<h1><html>");
    JButton startButton = new JButton("Start");
    mainPanel2.add(gameName);
    mainPanel2.add(startButton);

    titleScreen.setContentPane(mainPanel2);
    titleScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleScreen.setLocation(120,70);
    titleScreen.setSize(400,300);
    titleScreen.setVisible(true);

    startButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
          titleScreen.dispose();
          JFrame mainView = new JFrame("Game Simulation");
          FinalProject projectScreen = new FinalProject();
          JPanel mainPanel = new JPanel();
          mainPanel.setLayout(new BorderLayout());
          mainPanel.add(projectScreen, BorderLayout.CENTER);
          mainView.setContentPane(mainPanel);
          mainView.setVisible(true);
          mainView.setContentPane(mainPanel);
          mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          mainView.setLocation(120,70);
          mainView.setSize(400,300);

      }
    });

  }
}
