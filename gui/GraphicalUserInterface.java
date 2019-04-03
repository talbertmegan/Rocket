

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class GraphicalUserInterface extends JPanel
{

	////////// Declare variables////////////////////


	protected double[] position = new double[3]; //current rocket position
	protected double[] velocity = new double[3]; //current velocity


	private int shipWidth = 10;
	private int shipHeight = 25;

	private Color shipColor = new Color(250,0,0);

	private double[][] system; //defines the variables for celestial bodies

    private double scale = 0.01; //scale from distance to graphics


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

				(int) ( (double)position[0] - getCurrentSize().getWidth()/2),
				(int) ( (double)position[0] + getCurrentSize().getWidth()/2),
				(int) ( (double)position[1] - getCurrentSize().getHeight()/2),
				(int) ( (double)position[1] + getCurrentSize().getHeight()/2),
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
		//used to see what needs to be render		for(int i = 0; i < this.system.length; i++)


        int [] bounds = findBoundsOfScreen();

        //find minimum distance something can be while still being rendered
		double minDistance = Math.sqrt( Math.pow(bounds[0], 2) + Math.pow(bounds[1],2) )/2; // screen diagonal / 2

	g2d.setColor(Color.GREEN);
	for (int i = 0; i < this.system.length; i++) {
		//see if any items are within the bounds
		double xDistance = this.system[i][0] - position[0], // distance from the ship
				yDistance = this.system[i][1] - position[1];

		double radial_distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
		//System.out.println("Pre-radius adjustment: " + radial_distance);

		radial_distance = radial_distance - system[i][7]; // account for the radius of the object
		//System.out.println("Post-adjustment " + radial_distance);
		//System.out.println("Where radius = " + system[i][7]);

		//convert radial_distance to radial_distance_in_pixels
		radial_distance *= this.scale;

		//System.out.println("\nRadial Distance " + radial_distance + "\nMinimum Distance " + minDistance + "\n");

		if (radial_distance <= minDistance) {

			//System.out.println("Hello from within the system renderer!");
			//then, the object is within reason, so just render it

			//fist, convert distances to pixel numbers
			xDistance *= this.scale;
			yDistance *= this.scale;
			double radius = this.system[i][7] * this.scale;


			//because values /down/ are positive, we have to flip the y-axis
			yDistance *= -1;

			//second, convert x/y distances to distance from top-left corner of the screen!
			xDistance = xDistance + findCenterOfPanel()[0];
			yDistance = yDistance + findCenterOfPanel()[1];

			//third, convert x/y coordinates to top-left of the oval!!
			xDistance = xDistance - radius;
			yDistance = yDistance - radius;

			//System.out.println("X: " + xDistance + "\nY:" + yDistance + "\nRadius:" + radius);

			//now render
			g2d.fillOval((int) xDistance, (int) yDistance, 2 * (int) radius, 2 * (int) radius);

		}

	}


	//g2d.fillOval(-25,-25,50,50);


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
	public void setVelocity(double[] new_velocity){
		this.velocity = new_velocity;
	}

	/***
	 *
	 * @param new_position sets the new position for the Rocket
	 */
	public void setPosition(double new_position[]) {
		this.position = new_position;
	}

	public void setSystem(double[][] currentSystem){
		//TODO remove extraneous information (don't need mass for example)
		this.system = currentSystem;
		System.out.println(system);
	}

	/**
	 *
	 * @return the current position of the rocket
	 */
	public double[] getPosition(){
		return this.position;
	}


	/***
	 *
	 * @return the current velocity of the rocket
	 */
	public double[] getVelocity(){
		return this.velocity;
	}

	public Dimension getCurrentSize(){
		return this.getSize();
	}

}