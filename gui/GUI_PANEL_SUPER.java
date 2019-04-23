import javax.swing.*;
import java.awt.*;

/*
If you inherit from this class, and add it to window container, it will honestly handle 99% of the work
well, thats the hope anyways
 */
public class GUI_PANEL_SUPER extends JPanel{

    /*
    Class variables
     */
    double[][] system;
    double[] position = new double[3]; //current rocket position
    private double[] velocity = new double[3]; //current velocity


    /**
     * A function called by window to automatically update the window and draw on it
     * @param g contains the graphics stuff for the window
     */
    @Override
    public void paintComponent(Graphics g){
        setBackground(Color.BLACK);
    }


    /**
     * Sets the system of planets for graphics
     * @param newSystem contains all the information of the planets
     */
    void setSystem(double[][] newSystem){
        this.system = newSystem;
    }

    /**
     * Sets the position of the rocket in meters
     * @param newPos the position of the rocket at time t
     */
    void setPosition(double[] newPos){
        this.position = newPos;
    }

    /**
     * sets the velocity of the rocket, used to caclculate angle
     * @param newVel the velocity of the rocket at time t
     */
    void setVelocity(double[] newVel){
        this.velocity = newVel;
    }

    /**
     * returns the position of the rocket
     * @return the current position of the rocket
     */
    double[] getPosition(){
        return this.position;
    }

    /**
     * returns the velocity of the rocket
     * @return velocity of the rocket
     */
    double[] getVelocity(){
        return this.velocity;
    }

    /**
     * Return the current size of the panel
     * @return Dimension of the panel
     */
    Dimension getCurrentSize(){
        return this.getSize();
    }


}
