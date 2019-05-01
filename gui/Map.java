import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.Graphics;


public class Map extends GUI_PANEL_SUPER { //GUI panel super adds some setters automatically


    //Constructor
    Map(){
      super();
      setBackground(Color.GREEN);


    }

  ////////// Private methods ///////////////////

  /***
   * This function finds the coordinates to center an arbitrary shape in the panel
   * @return x, y coordinates to set for shape top-left corner
   */
  private int[] findCenterOfPanel()
  {

    //find current panel info
    double panelHeight = this.getCurrentSize().getHeight();
    double panelWidth = this.getCurrentSize().getWidth();

    //Center coordinates
    int centerY = (int)panelHeight/2;
    int centerX = (int)panelWidth/2;

    int coor[] = {centerX, centerY};

    return coor;



  }

  @Override
  public void paintComponent(Graphics g){
    System.out.println("WHAT?");
    Graphics2D g2d = (Graphics2D)g;


    this.getGraphics().setColor(Color.GREEN);
    this.getGraphics().fillRect(100,100,100,100);

    setBackground(Color.BLACK);

    Dimension bounds = getCurrentSize();

    double scale;

    double max_dist = 0, distance=0;
    int max_dist_index;


    for(int i = 0; i< this.system.length; i++ )
    {

        distance = Math.sqrt( Math.pow( this.getPosition()[0] - this.system[i][0] ,2) + Math.pow( this.getPosition()[1] - this.system[i][1] ,2) + Math.pow( this.getPosition()[2] - this.system[i][2] ,2) );

        if(distance > max_dist)
        {
          max_dist = distance;
          max_dist_index = i;

        }

    }

    scale = bounds.getHeight()/max_dist;

    for(int i = 0; i<this.system.length; i++)
    {


      double xDistance = scale*this.system[i][0],
              yDistance = scale*this.system[i][1],
              width = scale*this.system[i][7],
              height = scale*this.system[i][7];



      g2d.setPaint(Color.GREEN);

      Ellipse2D ellip = new Ellipse2D.Double(xDistance, yDistance, width, height);
      g2d.fill(ellip);

    }

  }

  public void update()
  {
    this.repaint();
  }

}
