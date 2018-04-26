import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.*;
import java.lang.Thread;

public class FinalProject extends JPanel implements ActionListener, KeyListener{

  private Timer t = new Timer(5, this);
  private double positionX=30;
  private double positionY=260;
  private double speedY=0;
  private double posY2;
  private double posX2=490;
  private double speedX2 = -1;
  private Color characterColor = Color.orange;
  private Color paintColor = Color.cyan;
  private boolean pointTest = true;
  private boolean stopNoise = false;
  private int score = 0;
  private JLabel label = new JLabel("Score: 0");
  private ImageIcon image = new ImageIcon("pointImageFinal2.png");
  private File clap = new File("smw_coin.wav");
  private File clappy = new File("failure.wav");


  public FinalProject(){
    t.start();
    setBackground(paintColor);
    addKeyListener(this);
    setFocusable(true);
  }

  public void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(characterColor);
    Rectangle2D rectangle = new Rectangle2D.Double(positionX,positionY,10,60);
    g2.draw(rectangle);
    g2.setPaint(characterColor);
    g2.fill(new Rectangle2D.Double(positionX,positionY,10,60));
    if(pointTest == false){
      image.paintIcon(this,g2, 300,0);
    }

    g2.fill(new Ellipse2D.Double(posX2,posY2,10,10));
    Ellipse2D circle2 = new Ellipse2D.Double(posX2,posY2,10,10);
    g2.draw(circle2);


    if(posX2 <= 0){
      posX2 = 490;
      posY2 = Math.random()*400;
    }

  }

  public void actionPerformed(ActionEvent e){
    positionY+=speedY;
    posX2 += speedX2;
    if(posX2<=40 && posX2>=30 && posY2 <= (positionY+60) && posY2 >= (positionY)){
      pointTest = false;
      characterColor = Color.red;
      repaint();
    }else{
      characterColor = Color.orange;
      repaint();
    }
    if(stopNoise == true){
      this.clap = null;
      this.clappy = null;
    }else if(posX2 <= 0 && pointTest == false){
      score++;
      this.clap = new File("smw_coin.wav");
      playSound(clap);
      pointTest = true;
    }else if(posX2<=0 && pointTest == true){
      this.clappy = new File("failure.wav");
      playSound(clappy);
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

  static void playSound(File sound){
    try{
      Clip clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(sound));
      clip.start();
      Thread.sleep(clip.getMicrosecondLength()/1000);
    }catch(Exception e){

    }
  }

  public void stopSound(){
    this.stopNoise = true;
  }

  public int getScore(){
    return this.score;
  }

  public JLabel getLabel(){
    return this.label;
  }

}
