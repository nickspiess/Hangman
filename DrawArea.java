import java.awt.*;
import javax.swing.*;

/** The program program will perform the following steps
 * 1. Creates booleans for different drawings
 * 2. If methods are called, booleans are turned true
 * 3. True booleans will print out desired image
 */

public class DrawArea extends JComponent {
    // Booleans to switch on painting
    private boolean gallows;
    private boolean head;
    private boolean body;
    private boolean leftArm;
    private boolean rightArm;
    private boolean leftLeg;
    private boolean rightLeg;
    private boolean win;
    // boolean to display word
    private String word;

    /**
     * Constructor for Draw Area
     */
    public DrawArea() {

    }

    /**
     * Method for activating gallows drawing
     */
    public void drawGallows() {
        gallows = true;
        //System.out.println("Drawing Gallows");
    }

    /**
     * Method for activating head drawing
     */
    public void drawHead() {
        head = true;
        //System.out.println("Drawing Head");
        repaint();
    }

    /**
     * Method for activating body drawing
     */
    public void drawBody() {
        body = true;
        //System.out.println("Drawing body");
        repaint();
    }

    /**
     * Method for activating left arm drawing
     */
    public void drawLeftArm() {
        leftArm = true;
        //System.out.println("Drawing left arm");
        repaint();
    }

    /**
     * Method for activating right arm drawing
     */
    public void drawRightArm() {
        rightArm = true;
        //System.out.println("Drawing right arm");
        repaint();
    }

    /**
     * Method for activating left leg drawing
     */
    public void drawLeftLeg() {
        leftLeg = true;
        //System.out.println("Drawing left leg");
        repaint();
    }

    /**
     * Method for activating right leg drawing
     */
    public void drawRightLeg(String word) {
        this.word = word;
        rightLeg = true;
        //System.out.println("Drawing right leg");
        repaint();
    }

    /**
     * Method for activating win drawing
     */
    public void win() {
        win = true;
    }

    /**
     * Paint component that contains a boolean activation for each drawing
     */
    public void paintComponent(Graphics g) {
        // Creates a line that is thick to create limbs
        BasicStroke line = new BasicStroke(12f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f);

        if(gallows){
            g.fillRect(100, 500, 400, 10);// Base
            g.fillRect(300, 100, 10, 400);// Pole
            g.fillRect(100, 100, 200, 10);// Top
            g.fillRect(100, 100, 15, 25);// Little Drop
            repaint();
        }
        if(head){
            g.fillOval(73, 123, 75, 75);
        }
        if(body) {
            g.fillRect(102, 198, 18, 120);
        }
        if(leftArm) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(line);
            g2.drawLine(60,250, 120, 175);
        }
        if(rightArm) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(line);
            g2.drawLine(90,150, 160, 255);
        }
        if(leftLeg) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(line);
            g2.drawLine(60,400, 108, 312);
        }
        if(rightLeg) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(line);
            g2.drawLine(110,312, 148, 400);
            g2.setFont(new Font("default", Font.BOLD, 30));
            g2.drawString("You lose! The word was " + word, 60, 565);
        }
        if(win) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setFont(new Font("default", Font.BOLD, 30));
            g2.drawString("You won!", 180, 565);
        }

    }

}
