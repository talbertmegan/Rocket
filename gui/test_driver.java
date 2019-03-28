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

        Window_Container test = new Window_Container();

        test.haveFun();

        /*
        GraphicalUserInterface GUI = new GraphicalUserInterface();
        JFrame frame = new JFrame("Rocket");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create frame
                //JFrame frame = new JFrame("Rocket");
                frame.setSize(init_width,init_height); // needed twice so the frame will properly center


                //Add gui to frame
                frame.setContentPane(GUI);
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
        int counter = 0;
        while(counter <100)
        {
            float [] newVel = GUI.getVelocity();
            newVel[0] += counter;
            newVel[1] += 20;
            newVel[2] += 20;

            GUI.setVelocity(newVel);
            //frame.repaint();
            GUI.update();
            try {
                Thread.sleep(100);
            } catch(Exception e){

            }
            counter ++;
        }
        */
    }

}