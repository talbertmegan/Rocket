import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Window_Container extends JFrame implements KeyListener {

    private static int init_width = 1000, init_height = 1000;
    private GraphicalUserInterface game = new GraphicalUserInterface();
    private double[] thrust;

    private final double acceleration_from_keypush = 10.0;

    public Window_Container()
    {
        super("Rocket Launch");

        this.thrust = new double[3];
        this.thrust[0] = 0.0;
        this.thrust[1] = 0.0;
        this.thrust[2] = 0.0;

        this.addKeyListener(this);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create frame
                //JFrame frame = new JFrame("Rocket");
                setSize(init_width,init_height); // needed twice so the frame will properly center


                //Add gui to frame
                getContentPane().add(game);
                //frame.getContentPane().add(new JLabel("TEST"));

                // some misc cleanup for the window
                setDefaultCloseOperation(HIDE_ON_CLOSE);
                setLocationRelativeTo(null);
                pack();

                //fix sizing issues again ;(
                setSize(init_width,init_height);

                   //done!
                setVisible(true);

            }
        });
    }

    public void kill()
    {
        this.dispose();
    }

    public void update()
    {
        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            //left arrow key, i think
            this.thrust[0] -= this.acceleration_from_keypush;

        }else if(key == KeyEvent.VK_RIGHT){
            this.thrust[0] += this.acceleration_from_keypush;


        }else if(key == KeyEvent.VK_UP){
            this.thrust[1] += this.acceleration_from_keypush;


        }else if(key == KeyEvent.VK_DOWN){
            this.thrust[1] -= this.acceleration_from_keypush;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_LEFT){
            //left arrow key, i think
            this.thrust[0] -= 0;

        }else if(key == KeyEvent.VK_RIGHT){
            this.thrust[0] += 0;


        }else if(key == KeyEvent.VK_UP){
            this.thrust[1] += 0;


        }else if(key == KeyEvent.VK_DOWN){
            this.thrust[1] -= 0;

        }

    }

    //////////////// Getters and setters //////////////////////////

    public void setPosition(double[] newPosition)
    {

        game.setPosition(newPosition);

    }

    public void setVelocity(double[] newVelocity)
    {
        game.setVelocity(newVelocity);
    }

    public void setSystem(double[][] newSystem)
    {
        game.setSystem(newSystem);
    }

    public double[] getThrust()
    {

        return this.thrust;
    }


}
