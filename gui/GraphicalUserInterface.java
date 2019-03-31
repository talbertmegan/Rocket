

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;


public class GraphicalUserInterface extends JPanel
{

	////////// Declare variables////////////////////


	protected float[] position = new float[3]; //current rocket position
	protected float[] velocity = new float[3]; //current velocity


	private int shipWidth = 20;
	private int shipHeight = 50;

	private Color shipColor = new Color(250,0,0);

	protected double[][] system; //defines the variables for celestial bodies


	////////// Constructor(s) /////////////////////

	public GraphicalUserInterface()
	{


		//TODO add listeners here
		//TODO allocate UI components


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

	/***
	 *
	 * @param shapeWidth width of the shape
	 * @param shapeHeight height of the shape
	 * @return (x,y) the coordinates for the top left corner
	 */

	private int[] findCenterCoordinatesForAShape(int shapeWidth, int shapeHeight)
	{

		int [] centerCoordinates = findCenterOfPanel();

		int x = centerCoordinates[0] - shapeWidth;
		int y = centerCoordinates[1] - shapeHeight;

		int coor[] = {x,y};

		return coor;
	}

	/***
	 *
	 * @return (minX, maxX, minY, maxY)
	 */
	private int[] findBoundsOfScreen()
	{
		int[] bounds = {

				(int) ( (double)findCenterOfPanel()[0] - getCurrentSize().getWidth()/2),
				(int) ( (double)findCenterOfPanel()[0] + getCurrentSize().getWidth()/2),
				(int) ( (double)findCenterOfPanel()[1] - getCurrentSize().getHeight()/2),
				(int) ( (double)findCenterOfPanel()[1] + getCurrentSize().getHeight()/2),


		};

		return bounds;


	}

	////////// Public methods ////////////////////


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.BLACK);

		//makes 2d graphics easier
		Graphics2D g2d = (Graphics2D)g;

		//original orientation
		AffineTransform old = g2d.getTransform();

		//TODO add drawing code here


		//TODO add angling to the rocket (so if it turns, the rocket will move)
		//Draw the rocket (always in the center

		/* tests velocity angling for the drawing
		float[] vel = new float[] {0,50,0};
		setVelocity( vel);
		*/

		//find center for rotation stuff
		int[] panelCenter = findCenterOfPanel();

		///////DRAW SHIP

		//find where to draw the ship
		int [] shipAdjustedCoordinates = findCenterCoordinatesForAShape(this.shipWidth, this.shipHeight);

		//assuming y is upwards on the screen :)
		double thetaOfShip = Math.atan( (double) getVelocity()[0]/getVelocity()[1] );
		if(Double.isNaN(thetaOfShip)){
			thetaOfShip = 0;
		}
		//System.out.println(thetaOfShip);

		//rotate ship based on its angle of attack
		g2d.rotate(thetaOfShip, panelCenter[0], panelCenter[1]);//rotate about center

		//set color for ship and draw it
		g2d.setColor(shipColor);
		g2d.fillOval(shipAdjustedCoordinates[0], shipAdjustedCoordinates[1], this.shipWidth, this.shipHeight);

		//reset rotation
		g2d.setTransform(old);
		//find coordinate bounds for the screen next
		//used to see what needs to be rendered
		int[] bounds = findBoundsOfScreen();


	}

	public void update()
	{
		this.repaint();
	}

	////////// GETTERS AND SETTERS ///////////////

	/***
	 *
	 * @param new_velocity sets the new velocity for the Rocket
	 */
	public void setVelocity(float[] new_velocity){
		this.velocity = new_velocity;
	}

	/***
	 *
	 * @param new_position sets the new position for the Rocket
	 */
	public void setPosition(float new_position[]) {
		this.position = new_position;
	}

	public void setSystem(double[][] currentSystem){
		//TODO remove extraneous information (don't need mass for example)
		this.system = currentSystem;
	}

	/**
	 *
	 * @return the current position of the rocket
	 */
	public float[] getPosition(){
		return this.position;
	}


	/***
	 *
	 * @return the current velocity of the rocket
	 */
	public float[] getVelocity(){
		return this.velocity;
	}


	public Dimension getCurrentSize(){
		return this.getSize();
	}

}