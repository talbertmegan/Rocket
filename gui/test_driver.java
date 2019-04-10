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

        double [][] system = {

                {0, -6.371e6, 0, 5.972e24 ,0 ,0 ,0 , 6.371e6},

                {0,383.4e6,0,7.327e22,-1.022e3,0,0,1e6}

        };

        System.out.println(system[0][7]);


        double [] temp = {0,0,0};

        test.setPosition(temp);

        test.setSystem(system);

    }

}