import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Messy_test {

    public void main(){

        window w = new window();

    }


    protected class window extends JFrame{


        panel p = new panel();

        window()
        {
            super("Rocket Launch");


            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    //Create frame
                    //JFrame frame = new JFrame("Rocket");
                    setSize(500,500); // needed twice so the frame will properly center


                    //Add gui to frame
                    getContentPane().add(p);
                    //frame.getContentPane().add(new JLabel("TEST"));

                    // some misc cleanup for the window
                    setDefaultCloseOperation(HIDE_ON_CLOSE);
                    setLocationRelativeTo(null);
                    pack();

                    //fix sizing issues again ;(
                    setSize(500,500);

                    //done!
                    setVisible(true);

                }
            });
        }

    }

    protected  class panel extends JPanel {

        BufferedImage texture;

        public panel()
        {
            try{
                texture = ImageIO.read(new File("earth.png"));
            }catch(IOException e){}
        }

        public void paintComponent(Graphics g)
        {
            Graphics2D g2d = (Graphics2D)g;

            Rectangle2D rect = new Rectangle(50,50,200,200);

            TexturePaint tp = new TexturePaint(texture, new Rectangle(50,50,100,100));
            g2d.setPaint(tp);

            g2d.fill(rect);
                    /*
            TexturePaint tp = new TexturePaint( textures[i], new Rectangle((int)xDistance, (int)yDistance,(int)(2*radius), (int)(2*radius)  ));

            g2d.setPaint(tp);

            Rectangle2D rect = new Rectangle( (int)xDistance, (int)yDistance, (int)(2*radius), (int)(2*radius) );

            g2d.fill(rect);

            System.out.println("Hello from rendering");
            */

        }

    }

}
