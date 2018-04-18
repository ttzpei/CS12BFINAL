import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.geom.*;

public class FinalProject extends JPanel implements ActionListener, KeyListener{

  private ArrayList<Character> People = new ArrayList<Character>();
  private Timer t = new Timer(5, this);
  private double positionX=0;
  private double positionY=0;
  private double speedX=0;
  private double speedY=0;
  private Color characterColor = Color.white;
  private Color paintColor = Color.white;

  public static void main(String[] args){
    JFrame mainFrame = new JFrame("Game Simulator");
    JPanel mainPanel = new FinalProject();
    mainFrame.setContentPane(mainPanel);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setLocation(120,70);
    mainFrame.setSize(400,300);
    mainFrame.setVisible(true);
  }

  public FinalProject(){
    t.start();
    setBackground(paintColor);
    addKeyListener(this);
    setFocusable(true);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    Ellipse2D circle = new Ellipse2D.Double(positionX,positionY,20,20);
    g2.draw(circle);
  }

  public void actionPerformed(ActionEvent e){
    repaint();
    positionX+=speedX;
    positionY+=speedY;
  }

  public void up(){
    speedY = -1;
    speedX = 0;
  }
  public void down(){
    speedY = 1;
    speedX = 0;
  }
  public void right(){
    speedY = 0;
    speedX = 1;
  }
  public void left(){
    speedY = 0;
    speedX = -1;
  }

  public void noSpeed(){
    speedY = 0;
    speedX = 0;
  }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          right();

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          left();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          up();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          down();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          noSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          noSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
          noSpeed();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          noSpeed();
        }
    }



  public class Character{
    private int positionX;
    private int positionY;
    private double speed;
    private Color characterColor;

    public Character(int positionX, int positionY, double speed, Color characterColor){
      this.positionX = positionX;
      this.positionY = positionY;
      this.speed = speed;
      this.characterColor = characterColor;
    }

    public String toString(){
      return "X Coordinate = " + positionX + " Y Coordinate = " + positionY + " speed = " + speed;
    }
    public int getPositionX(){
      return this.positionX;
    }

    public int getPositionY(){
      return this.positionY;
    }

  }
}
