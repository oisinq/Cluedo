import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class GUI extends JFrame  implements MouseListener {

    Counter redCounter, yellowCounter, blueCounter, cyanCounter, greenCounter, whiteCounter;
    Weapons Gun, Rope,Wrench,Dagger,LeadPipe,CandleStick;
    int dagger;
    /*
    g.drawImage(dagger, 120,150,40,40, null);
    g.drawImage(rope, 500,120,40,40, null);
    g.drawImage(wrench, 172,303,40,40, null);
    g.drawImage(gun, 470,300,40,40, null);
    g.drawImage(lead_Pipe, 470,375,40,40, null);
    g.drawImage(candlestick, 250,500,40,40, null);
}
    */
    int[] wepLoc= {120,150} ;
    Random rand= new Random();
    int chance = rand.nextInt(60)%6+1;{
    //System.out.println("");
    
    
    //whiteCounter.setXY(365, 48);
    //char grade = 'C';{

    switch(chance) {
       case 1 :
          
          break;
       case 2 :
       case 3 :
          System.out.println("Well done");
          break;
       case 4 :
          System.out.println("You passed");
       case 5 :
          System.out.println("Better try again");
          break;
       case 6 :
           System.out.println("Better try again");
           break;
       default :
          System.out.println("Invalid grade");
    }
    }

    // This method creates the graphic interface for the program
    public GUI() {

        JPanel board = new JPanel();

        // We use BorderLayout to easily have multiple components in the same panel
        setLayout(new BorderLayout());
        setSize(835, 690);
        setTitle("Cluedo");
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea infoField = new JTextArea(10, 15);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        JScrollPane scrollPane = new JScrollPane(infoField);

        JTextField userInput = new JTextField();
        userInput.setText("Enter messages here!");

        redCounter = new Counter();
        redCounter.setXY(204, 598);
        board.add(redCounter);

        yellowCounter = new Counter();
        yellowCounter.setXY(44, 437);
        board.add(yellowCounter);

        blueCounter = new Counter();
        blueCounter.setXY(572, 484);
        board.add(blueCounter);

        cyanCounter = new Counter();
        cyanCounter.setXY(572, 185);
        board.add(cyanCounter);

        greenCounter = new Counter();
        greenCounter.setXY(250, 47);
        board.add(greenCounter);

        whiteCounter = new Counter();
        whiteCounter.setXY(365, 47);
        board.add(whiteCounter);
        
        //weapon objects are created below
        Gun = new Weapons();
        Gun.SetImageFile("resources/revolver.png");
        Gun.setXY(500, 150);
        board.add(Gun);
        
        Rope = new Weapons();
        Rope.SetImageFile("resources/rope.png");
        Rope.setXY(150, 150);
        board.add(Rope);
        
        Dagger = new Weapons();
        Dagger.SetImageFile("resources/dagger.png");
        Dagger.setXY(200, 200);
        board.add(Dagger);
        
        Wrench = new Weapons();
        Wrench.SetImageFile("resources/wrench.png");
        Wrench.setXY(150, 20);
        board.add(Wrench);
        
        CandleStick = new Weapons();
        CandleStick.SetImageFile("resources/candlestick.png");
        CandleStick.setXY(0, 0);
        board.add(CandleStick);
        
        LeadPipe = new Weapons();
        LeadPipe.SetImageFile("resources/lead_pipe.png");
        LeadPipe.setXY(200, 400);
        board.add(LeadPipe);
        
        add(scrollPane, "East");
        add(userInput, "South");
        add(board, "Center");


        addMouseListener(this);

        setVisible(true);


        //all this is text manipulation that will need to be moved
        @SuppressWarnings("serial")//come back to this maybe
    	Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
            
            	 String inputtedText =userInput.getText();//takes info from the field
                 userInput.setText("");//wipes the field after 
                 
                 infoField.append(">" +inputtedText + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");//puts it into the panel
                 String[] splitStr = inputtedText.split("\\s+");
                 if(splitStr[0].equals("Move"))
                 {
                	 Counter temp = null;
                	
                	 if(splitStr[1].equals("Red"))
                	 {
                    	 temp=redCounter;
                	 }

                	 else if(splitStr[1].equals("Yellow"))
                	 {
                    	
                		 temp = yellowCounter;
                	 }

                	 else if(splitStr[1].equals("Blue"))
                	 {
                		 temp = blueCounter;
                	 }

                	 else if(splitStr[1].equals("Cyan"))
                	 {
                		 temp = cyanCounter;
                	 }

                	 else if(splitStr[1].equals("Green"))
                	 {
                    	temp = greenCounter;
                	 }

                	 else if(splitStr[1].equals("White"))
                	 {
                		 temp = whiteCounter;
                	 }
                	 if(splitStr[2].equals("Up"))
                	 {
                		
						temp.moveUp(Integer.parseInt(splitStr[3]));
                	 }
                	 else if(splitStr[2].equals("Down"))
                	 {
                		 temp.moveDown(Integer.parseInt(splitStr[3]));
                	 }
                	 else if(splitStr[2].equals("Left"))
                	 {
                		 temp.moveLeft(Integer.parseInt(splitStr[3]));
                	 }
                	 else if(splitStr[2].equals("Right"))
                	 {
                		 temp.moveRight(Integer.parseInt(splitStr[3]));
                	 }
                	 repaint();
                 }
                 else if(inputtedText.equals("Help"))
                 {
                	 infoField.setText("Commands: \nMove\n ");
                 }
               
            }
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
    }

    public void paint(Graphics g){
        super.paint(g);
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("resources/cluedo_board.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        g.drawImage(myPicture, 0, 22, null);


        redCounter.setColor(Color.RED);
        redCounter.paintComponent(g);
        yellowCounter.setColor(Color.YELLOW);
        yellowCounter.paintComponent(g);
        blueCounter.setColor(Color.BLUE);
        blueCounter.paintComponent(g);
        cyanCounter.setColor(Color.CYAN);
        cyanCounter.paintComponent(g);
        greenCounter.setColor(Color.GREEN);
        greenCounter.paintComponent(g);
        whiteCounter.setColor(Color.WHITE);
        whiteCounter.paintComponent(g);
        
        
        drawImage(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // These get the location of where the mouse was clicked and prints it
        int xLocation = e.getX();
        int yLocation = e.getY();
        System.out.println("(" + xLocation + ", " + yLocation + ")");
        //    redCounter.setXY(redCounter.getX(), redCounter.getY()-23);
        redCounter.moveUp(1);
        whiteCounter.moveDown(1);
        cyanCounter.moveLeft(1);
        yellowCounter.moveRight(1);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void drawImage(Graphics g) {

    	Gun.paintComponent(g);
    	Rope.paintComponent(g);
    	Dagger.paintComponent(g);
    	Wrench.paintComponent(g);
    	CandleStick.paintComponent(g);
    	LeadPipe.paintComponent(g);
    }
	
    public static void main (String args[]) {
        new GUI();
    }
}