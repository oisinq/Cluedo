import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@SuppressWarnings("serial")
public class Weapon extends JComponent implements BoardPiece {

    int xLocation, yLocation;//the values for the image coordinates
	BufferedImage Image;//this will hold the image of the weapon once its passed in


    public void paintComponent(Graphics g) {//this paints the image at a set size of 40 by 40 pixels 
    										//xLoxation and yLocation get filled in in the GUI class
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
    	 g2.drawImage(Image,xLocation,yLocation,40,40,null);
    }

    public void setXY(int x, int y) {//sets the XY coordinates of the Image
        xLocation = x;
        yLocation = y;
    }

   public void SetImageFile(String location){//this sets the file location of the image
	   try {
           Image = ImageIO.read(this.getClass().getResource(location));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
   }

    public int getX() {//returns the x coordinate of the image
        return xLocation;
    }

    public int getY() {//returns the y coordinate of the image
        return yLocation;
    }
    //movement for the weapons
    public void moveUp(int steps) {
        setXY(xLocation, yLocation - (23 * steps));
    }

    public void moveDown(int steps) {
        setXY(xLocation, yLocation + (23 * steps));
    }

    public void moveLeft(int steps) {
        setXY(xLocation - (23 * steps), yLocation);
    }

    public void moveRight(int steps) {
        setXY(xLocation + (23 * steps), yLocation);
    }
    
    
    
}
