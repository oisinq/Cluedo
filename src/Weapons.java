import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Weapons extends JComponent {

    int xLocation, yLocation;
   
    
    //BufferedImage gun = null;
    BufferedImage rope = null;
    BufferedImage wrench=null;
    BufferedImage lead_Pipe= null;
    BufferedImage candlestick=null;
    BufferedImage dagger=null;


	BufferedImage Image;

 //   @Override
    public void paintComponent(Graphics g) {
    	 //try {
            // dagger = ImageIO.read(new File("resources/dagger.png"));
             //rope = ImageIO.read(new File("resources/rope.png"));
             //wrench = ImageIO.read(new File("resources/wrench.png"));
             //lead_Pipe =  ImageIO.read(new File("resources/lead_pipe.png"));
             //BufferedImage gun = ImageIO.read(new File("resources/revolver.png"));
             //candlestick = ImageIO.read(new File("resources/candlestick.png"));
        // } catch (IOException e){

         //}
    	 //g.drawImage(dagger, x,y,40,40, null);
         //g.drawImage(rope, 500,120,40,40, null);
         //g.drawImage(wrench, 172,303,40,40, null);
         //g.drawImage(gun, 470,300,40,40, null);
         //g.drawImage(lead_Pipe, 470,375,40,40, null);
         //g.drawImage(candlestick, 250,500,40,40, null);
    	 g.drawImage(Image,xLocation,yLocation,40,40,null);
    }

    public void setXY(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

   public void SetImageFile(String location){
	   try {
		this.Image=ImageIO.read(new File(location));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
	}
   }

    public int getX() {
        return xLocation;
    }

    public int getY() {
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
