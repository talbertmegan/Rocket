import javax.swing.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.Graphics;


public class Map extends GUI_PANEL_SUPER { //GUI panel super adds some setters automatically
  private double position[][];

    //Constructor
    public Map(){

    }
/*
    //Sets system position
    public void setSystem(double[][] systemPosition){
      this.position = systemPosition;
    }


    //makes the drawing possible
    @Override
    public void paintComponent(Graphics g){
      Graphics2D g2d = (Graphics2D)g;
      // g2d.setColor(Color.BLUE);
      // graphics.fillRect(x,y,height);
      // graphics.fill(new Rectangle);
    }

    public void panelMaker(){
      JPanel p = new JPanel();
    }

    public void MapDraw(Double[] RocketLocation, Double[] MoonLocation){
      Graphics graphics = panel.getGraphics();
      graphics.fillEllipse(position[1][1],position[2][1],5,10);//rocket Ellipse
      graphics.fillEllipse(position[1][2],position[2][2],100,100);//moon Ellipse
      graphics.fillEllipse(0,0,200,200);//Earth ellipse
    }

    public void main(){
      JFrame frame = new JFrame();
      DrawingPanel panel = new DrawingPanel();
      panel.setPrefferedSize(new Dimension(500, 500));
      frame.add(panel);
      frame.pack();
      frame.setVisible(true);
    }
*/
}
