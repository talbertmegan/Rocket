package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;


public class GraphicalUserInterface extends JPanel
{

	//Declare variables
	float[] position; //current rocket position
	float[] velocity; //current velocity

	public static void main(String args[])
	{
	//Just supposed to test this file :)

	}

	//Constructor
	public GraphicalUserInterface()
	{
		//Setup the window
		JFrame frame = new JFrame("Rocket");
		Canvas canvas = new Drawing();
		canvas.setSize(400,400);
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);

			
		this.position = new float[3];
		this.velocity = new float[3];
	}

	//Public methods
	
	/*Update
	 * updates the drawings
	 */
	public void update()
	{
	}

	/* setPosition
	 * Sets the rockets position
	 * Parameters: new position
	 * 
	 */
	public void setPosition(float new_position[])
	{

		this.position = new_position; 

	}

	/* setVelocity
	 * Sets the rocket's velocity to find its angle
	 * parameters: new velocity
	 */
	public void setVelocity(float new_velocity[])
	{
		this.velocity = new_velocity;
	}

}
