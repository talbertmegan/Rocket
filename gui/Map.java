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
    //System.out.println("WHAT?");


    Graphics2D g2d = (Graphics2D)g;


    /*

    Graphics2D g2d = (Graphics2D)g;


    g2d.setColor(Color.RED);
    g2d.fillRect(100,100,100,100);


    setBackground(Color.BLACK);

    Dimension bounds = getCurrentSize();

    double scale;

    double max_dist = 0, distance=0;
    int max_dist_index;


    double[][] soi = getSystem();

    soi[0][0] = getPosition()[0];
    soi[0][1] = getPosition()[1];
    soi[0][2] = getPosition()[2];
    soi[0][7] =1000;


    //find max distance from the earth
    for(int i = 0; i<this.getSystem().length; i++)
    {

      distance = Math.sqrt(  Math.pow(getSystem()[0][0] - soi[i][0], 2) +  Math.pow(getSystem()[0][0] - soi[i][0], 2) +  Math.pow(getSystem()[0][0] - soi[i][0], 2) );
      if (distance >=max_dist)
            max_dist = distance;

    }


    scale = bounds.getHeight()/max_dist;


    //manually draw the earth

    Ellipse2D ellip = new Ellipse2D.Double(findCenterOfPanel()[0]+scale*getSystem()[0][7], findCenterOfPanel()[1]+scale*getSystem()[0][7], scale*getSystem()[0][7], scale*getSystem()[0][7] );
    g2d.fill(ellip);


    for(int i = 0; i<soi.length; i++)
    {
        double xDistance = scale * ( soi[i][0] - getSystem()[0][0] ),
                yDistance = scale * ( soi[i][1] - getSystem()[0][1]),
                radius = soi[i][7];

        yDistance *=-1;

        yDistance += findCenterOfPanel()[1];


      System.out.println("YDistance: " + yDistance + " XDistance: " + xDistance + " Radius: " + radius);

      g2d.setColor(Color.GREEN);

      ellip = new Ellipse2D.Double(xDistance, yDistance, radius, radius);
      g2d.fill(ellip);
      //g2d.fillOval((int)xDistance, (int)yDistance, (int)width, (int)height);


      //g2d.fillOval(0,0,1000,1000);

   */

  }

  public void update()
  {
    this.repaint();
  }

}
