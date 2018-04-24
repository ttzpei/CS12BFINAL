import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class FinalProject extends JPanel implements ActionListener, KeyListener{

  private Timer t = new Timer(5, this);
  private double positionX=30;
  private double positionY=260;
  private double speedY=0;
  private Color characterColor = Color.white;
  private Color paintColor = Color.cyan;
  private double posY2;
  private double posX2=490;
  private double speedX2 = -1;
  private boolean pointTest = true;
  public static int score = 0;
  public static JLabel label = new JLabel("Score: 0");
  private ImageIcon image = new ImageIcon("pointImageFinal2.png");

  public FinalProject(){
    t.start();
    setBackground(paintColor);
    addKeyListener(this);
    setFocusable(true);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    Rectangle2D rectangle = new Rectangle2D.Double(positionX,positionY,10,40);
    g2.setBackground(characterColor);
    g2.draw(rectangle);
    if(pointTest == false){
      image.paintIcon(this,g2, 0,0);
    }

    Ellipse2D circle2 = new Ellipse2D.Double(posX2,posY2,10,10);
    g2.draw(circle2);


    if(posX2 <= 0){
      posX2 = 490;
      posY2 = Math.random()*490;
    }

  }

  public void actionPerformed(ActionEvent e){
    positionY+=speedY;
    posX2 += speedX2;
    if(posX2<=40 && posX2>=30 && posY2 <= (positionY+20) && posY2 >= (positionY-20)){
      pointTest = false;
      characterColor = Color.red;
      repaint();
    }else{
      characterColor = Color.white;
      repaint();
    }

    if(posX2 <= 0 && pointTest == false){
      score++;
      pointTest = true;
    }

    label.setText("Score: " + score);
    repaint();
  }

  public void up(){
    speedY = -1;
  }
  public void down(){
    speedY = 1;
  }

  public void pause(){
    speedY = 0;
  }

  public void keyTyped(KeyEvent e) {

  }

  public void keyPressed(KeyEvent e) {

    if (e.getKeyCode() == KeyEvent.VK_UP) {
      up();
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      down();
    }

    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      pause();
    }
  }

    public void keyReleased(KeyEvent e) {

    }

}
