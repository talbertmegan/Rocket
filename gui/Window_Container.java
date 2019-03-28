import javax.swing.*;
import java.awt.*;

public class Window_Container extends JFrame {

    private static int init_width = 1000, init_height = 1000;
    private GraphicalUserInterface game = new GraphicalUserInterface();

    public Window_Container()
    {
        super("Rocket Launch");


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
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                pack();

                //fix sizing issues again ;(
                setSize(init_width,init_height);

                   //done!
                setVisible(true);
            }
        });

    }

    public void haveFun()
    {
        int counter = 0;
        while(counter <100)
        {
            counter++;

            float [] newVel = game.getVelocity();
            newVel[0] += counter;
            newVel[1] += 20;
            newVel[2] += 20;

            game.setVelocity(newVel);
            //frame.repaint();
            game.update();
            try {
                Thread.sleep(100);
            } catch(Exception e){

            }

        }

    }

    public void update()
    {
        this.repaint();
    }

    //////////////// Getters and setters //////////////////////////

    public void setPosition(float[] newPosition)
    {
        game.setPosition(newPosition);
    }

    public void setVelocity(float[] newVelocity)
    {
        game.setVelocity(newVelocity);
    }

    public float[] getPosition()
    {
        return game.getPosition();
    }

    public float[] getVelocity( )
    {
        return game.getVelocity();
    }

}
