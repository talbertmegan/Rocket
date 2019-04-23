import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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



}
