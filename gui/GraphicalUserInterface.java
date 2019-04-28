

import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GraphicalUserInterface extends GUI_PANEL_SUPER
{

	////////// Declare variables////////////////////

    //Ideally, this info should be passed in, but frankly this is just as easy

	private final int shipWidth = 10;
	private final int shipHeight = 25;

	// if the ship is 25 units tall, and rockets are 70 meters tall

	private final double scale =25.0/70.0;

	private Color shipColor = new Color(250,0,0);

	private BufferedImage [] textures;


	////////// Constructor(s) /////////////////////

	GraphicalUserInterface()
	{

        super(); //not really required but a nice reminder


		/*
		Time to try to import all the photos!
		Now, ideally, we would create a nomenclature for the file system
		but instead, its hardcoded here
		bc.... well its easier
		 */
        try {
            textures = new BufferedImage[2];
            textures[0] = ImageIO.read(new File("images/background.png"));
            textures[1] = ImageIO.read(new File("images/earth-texture.png"));
            textures[2] = ImageIO.read(new File("images/moon_texture.png"));


        }catch(IOException e) {
            try {

                textures = new BufferedImage[2];
                textures[0] = ImageIO.read(new File("gui/images/background.png"))
                textures[1] = ImageIO.read(new File("gui/images/earth.png"));
                textures[2] = ImageIO.read(new File("gui/images/moon.png"));

            } catch (IOException ee) {

            	//Okay, screw it. can't find images
                e.printStackTrace();
                int number_of_textures = 3;
                Color [] system_colors = {Color.BLACK, Color.GREEN, Color.WHITE};
                for(int i = 0; i < number_of_textures; i++){

					textures[i] = createBlankImage(system_colors[i], 500,500);

				}
            }

        }
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


	private BufferedImage createBlankImage(Color color, int width, int height)
	{
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = bi.createGraphics();
		g2d.setColor(color);
		g2d.fillRect(0,0,width, height);
		g2d.dispose();//stop any memory leak!
		return bi;
}

	private void drawShip(Graphics2D g2d)
	{

		//original orientation
		AffineTransform old = g2d.getTransform();

		//find center for rotation stuff
		int[] panelCenter = findCenterOfPanel();

		//find where to draw the ship
		int [] shipAdjustedCoordinates = findCenterCoordinatesForAShape(this.shipWidth, this.shipHeight);

		//assuming y is upwards on the screen :)
		double thetaOfShip = Math.atan( (double) getVelocity()[0]/getVelocity()[1] );
		if(Double.isNaN(thetaOfShip)){
			thetaOfShip = 0;
		}

		//rotate ship based on its angle of attack
		g2d.rotate(thetaOfShip, panelCenter[0], panelCenter[1]);//rotate about center

		//set color for ship and drawbounds it
		g2d.setColor(shipColor);
		g2d.fillOval(shipAdjustedCoordinates[0], shipAdjustedCoordinates[1], this.shipWidth, this.shipHeight);

		//reset rotation
		g2d.setTransform(old);
	}
	////////// Public methods ////////////////////


	@Override
	public void paintComponent(Graphics g){

		/*
		Draw background
		 */
		try {
			Image bg = textures[0]; //0th element should be the background
			bg = bg.getScaledInstance((int)getCurrentSize().getWidth(), (int)getCurrentSize().getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(bg,0,0, null);
		}catch(Exception e){
			setBackground(Color.BLACK);
		}

		//makes 2d graphics easier
		Graphics2D g2d = (Graphics2D)g;


		/*
		Draw the rocket (always in the center)
		 */
		drawShip(g2d);

		//find coordinate bounds for the screen next
		//used to see what needs to be render		for(int i = 0; i < this.system.length; i++)


		int [] bounds = findBoundsOfScreen();

		//find max distance something can be while still being rendered
		double maxDistance = Math.sqrt( Math.pow(bounds[0], 2) + Math.pow(bounds[1],2) )/2; // screen diagonal / 2

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

			//convert radial_distance to radial_distance_in_screen_units
			radial_distance *= this.scale;


			if (radial_distance <= maxDistance) {

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
				try{

					// TODO fix the scaling of the stuff

					// bc what happens now is the scaling is whack
					// so i forced it to work for earth
					// but any other size.... idk its weird
					// like the moon should be much bigger lol
					// and we need it to work for an n-body system so .... !!!

					int width = (int)(2*radius),
							height = (int)(2*radius);
					int k = 1;
					if(i == 0)
						k = 4000;
					else if(i == 1)
							k = 500;

        			TexturePaint tp = new TexturePaint( textures[i+1], new Rectangle((int)xDistance, (int)yDistance,width/k,height/k));

					g2d.setPaint(tp);

					/*
					Rectangle2D rect = new Rectangle( (int)xDistance, (int)yDistance, width, height );

					g2d.fill(rect);
					*/

					Ellipse2D ellip = new Ellipse2D.Double(xDistance, yDistance, width, height);
					g2d.fill(ellip);

					System.out.println("Hello from rendering");

				}catch(Exception e) {

					e.printStackTrace();
					g2d.setColor(Color.GREEN);
					g2d.fillOval((int) xDistance, (int) yDistance, 2 * (int) radius, 2 * (int) radius);

				}
			}

		}



	}

    /**
     * Forces the frame to update for drawing, not really used since window_container will call repaint when it updates
	 * Kept for any backwards compatibility issues that may arise !
     */
	public void update()
	{
		this.repaint();
	}


}