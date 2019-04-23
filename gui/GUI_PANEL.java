import java.awt.Graphics;

public interface GUI_PANEL {

        public void paintComponent(Graphics g);
        public void kill();

        public void setSystem(double[][] system);
        public void setPosition(double[] newPos);
        public void setVelocity(double[] newVel);

        public double[] getPosition();
        public double[] getVelocity();



}
