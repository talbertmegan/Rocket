//This file will test the gui
//Since running matlab is a pain
//This will simulate matlab running the program



import javax.swing.*;
import java.awt.*;

public class test_driver
{

    private static int init_width = 1000, init_height = 1000;


    public static void main(String args[])
    {

        System.out.println("hello");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create frame
                JFrame frame = new JFrame("Rocket");
                frame.setSize(init_width,init_height); // needed twice so the frame will properly center


                //Add gui to frame
                frame.setContentPane(new GraphicalUserInterface());
                //frame.getContentPane().add(new JLabel("TEST"));

                //some misc cleanup for the window
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();

                //fix sizing issues again ;(
                frame.setSize(init_width,init_height);

                //done!
                frame.setVisible(true);
            }
        });


    }

}