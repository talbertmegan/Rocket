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


        /*
        double [][] test = new double[1][1];

        //System.out.println(test);

        test[0][0] = 10;

        for(int i = 0; i < test.length; i++)
        {
            System.out.println(test[0][0]);
        }

        System.out.println(test[0][0]);
*/



        Window_Container test = new Window_Container();

        double [][] system = {

                {0, -6.371e6,0,5.972e24,0,0,0,6.371e6},

                {0,383.4e6,0,7.327e22,-1.022e3,0,0,1e6}

        };

        test.setSystem(system);

        test.haveFun();

        /*
        system = [...
        [0,-6.371e6,0],5.972e24, [0,0,0], 6.371e6;... %earth
        [0,383.4e6,0],7.3276e22, [-1.022e3,0,0], 1e6... %moo
    ];
         */

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