import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class GUI extends JFrame {

    private Counter redCounter, yellowCounter, blueCounter, cyanCounter, greenCounter, whiteCounter;
    private Weapon Gun, Rope,Wrench,Dagger,LeadPipe,CandleStick;
    JPanel board;
    JTextArea infoField;
    JTextField userInput;
    JScrollPane scrollPane;
    BufferedImage boardImage;


    // This method creates the graphic interface for the program
    public GUI() {

        board = new JPanel();

        // We use BorderLayout to easily have multiple components in the same panel
        setLayout(new BorderLayout());
        setSize(835, 690);
        setTitle("Cluedo");
        // Places the frame in the centre of the screen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boardImage = null;
        try {
            boardImage= ImageIO.read(this.getClass().getResource("cluedo_board.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        infoField = new JTextArea(10, 15);
        // I setEditable to false so that the user can't edit the text on the right-hand size
        infoField.setEditable(false);
        infoField.setLineWrap(true);
        // I place the infoField inside a scrollpane so that the textArea doesn't fill up
        scrollPane = new JScrollPane(infoField);

        userInput = new JTextField();
        userInput.setText("Help");

        initialiseCounters();
        initialiseWeapons();

        WeaponLocationAssigner();

        addComponents();

        setVisible(true);


        //all this is text manipulation that will need to be moved
        @SuppressWarnings("serial")//come back to this maybe
    	Action action = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                interpretInput();
            }
        };
        userInput.addActionListener(action); //Sets a button(enter) to activate the above listener
    }

    /**
     * This method runs when we setVisible(true) and when we repaint()
     */
    public void paint(Graphics g){
        // This line insures that the other components of the JFrame are visible
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(boardImage, 0, 25,this);


        redCounter.paintComponent(g);
        yellowCounter.paintComponent(g);
        blueCounter.paintComponent(g);
        cyanCounter.paintComponent(g);
        greenCounter.paintComponent(g);
        whiteCounter.paintComponent(g);


        drawWeapons(g);
    }

    public void drawWeapons(Graphics g) {

    	Gun.paintComponent(g);
    	Rope.paintComponent(g);
    	Dagger.paintComponent(g);
    	Wrench.paintComponent(g);
    	CandleStick.paintComponent(g);
    	LeadPipe.paintComponent(g);
    }


    public void WeaponLocationAssigner(){//this randomly allocates a location to weapons on each game launch
    	//array of valid locations of weapons each array will contain an XY coordinate
    	int[][] wepLocation=new int [] []{
    				{120,150},
    				{500,120},
    				{172,303},
    				{470,300},
    				{120,500},
    				{438,550},
    				{550,410},
    				{300,100},
    				{250,500}};

    	Random rnd = ThreadLocalRandom.current();//creates random numbers
        for (int i = wepLocation.length -1; i > 0; i--)//run this loop for the length of the array
        {
          int index = rnd.nextInt(i+1);
          int a = wepLocation[index][0];//a and b are placeholders for values in the original array
          int b = wepLocation[index][1];
          wepLocation[index][0] = wepLocation[i][0];//the original values get replaced with a random other value
          wepLocation[index][1]= wepLocation[i][1];
          wepLocation[i][0] = a;//the replaced values move to where their replacement had been
          wepLocation[i][1] = b;
        }
        //allocate the new random positions to the items
        LeadPipe.setXY(wepLocation[0][0],wepLocation[0][1]);
 	   	CandleStick.setXY(wepLocation[1][0],wepLocation[1][1]);
        Wrench.setXY(wepLocation[2][0],wepLocation[2][1]);
        Dagger.setXY(wepLocation[3][0], wepLocation[3][1]);
        Rope.setXY(wepLocation[4][0], wepLocation[4][1]);
        Gun.setXY(wepLocation[5][0], wepLocation[5][1]);

      }

      public void interpretInput() {
          String inputtedText =userInput.getText();//takes info from the field
          userInput.setText("");//wipes the field after

          infoField.append(">" +inputtedText+"\n");//puts it into the panel
          String[] splitStr = inputtedText.split("\\s+");// Splits the inputted string into an array based spaces
          if(splitStr[0].toLowerCase().equals("move"))// If the first word is move in any format
          {
              Counter temp = null;// Holds the name of the player counter chosen
              Weapons hold = null;// Holds the name of the weapon chosen
              int check = 2;// When check is set to 1 it specifies that a player counter has been chosen and when it had been set to zero it specifies a weapon has been chosen
              switch (splitStr[1].toLowerCase()) {// Checks the counter or weapon chosen
                  case "red":
                      temp = redCounter;
                      check=1;
                      break;
                  case "yellow":
                      temp = yellowCounter;
                      check=1;
                      break;
                  case "blue":
                      temp = blueCounter;
                      check=1;
                      break;
                  case "cyan":
                      temp = cyanCounter;
                      check=1;
                      break;
                  case "green":
                      temp = greenCounter;
                      check=1;
                      break;
                  case "white":
                      temp = whiteCounter;
                      check=1;
                      break;
                  case "dagger":
                      hold = Dagger;
                      check=0;
                      break;
                  case "candlestick":
                	  hold = CandleStick;
                	  check=0;
                      break;
                  case "gun":
                      hold = Gun;
                      check=0;
                      break;
                  case "leadpipe":
                      hold = LeadPipe;
                      check=0;
                      break;
                  case "rope":
                      hold = Rope;
                      check=0;
                      break;
                  case "wrench":
                      hold = Wrench;
                      check=0;
                      break;
                  default:
                	  infoField.append("\nInvalid item chosen\n");
              }
              if(check==1)// If player counter is chosen 
              {
              switch (splitStr[2].toLowerCase()) {// Checks the movement command entered 
                  case "up":
                      temp.moveUp(Integer.parseInt(splitStr[3]));
                      break;
                  case "down":
                      temp.moveDown(Integer.parseInt(splitStr[3]));
                      break;
                  case "left":
                      temp.moveLeft(Integer.parseInt(splitStr[3]));
                      break;
                  case "right":
                      temp.moveRight(Integer.parseInt(splitStr[3]));
                      break;
                  default:
                	  infoField.append("\nInvalid direction chosen\n");
                  
              }
              }
              if(check==0)// If weapons are chosen
              {
              switch (splitStr[2].toLowerCase()) {// Checks the movement command entered 
                  case "up":
                      hold.moveUp(Integer.parseInt(splitStr[3]));
                      break;
                  case "down":
                      hold.moveDown(Integer.parseInt(splitStr[3]));
                      break;
                  case "left":
                      hold.moveLeft(Integer.parseInt(splitStr[3]));
                      break;
                  case "right":
                      hold.moveRight(Integer.parseInt(splitStr[3]));
                      break;
                  default:
                	  infoField.append("\nInvalid direction chosen\n");
              }
              }

              repaint();// Repaints the board with the new location of the pieces
          }
          else if(inputtedText.toLowerCase().equals("help"))
          {
              infoField.setText("Commands: \nMove Player Piece\n - move (colour) (direction) (steps)\n\nMove Weapons\n - move (weapon name) (direction) (steps)\n\nItem Names\n-Dagger, CandleStick, Gun, Rope, Wrench, LeadPipe");
          }
      }

      public void initialiseWeapons() {
          //weapon objects are created below
          Gun = new Weapon();
          Gun.SetImageFile("/revolver.PNG");
          board.add(Gun);

          Rope = new Weapon();
          Rope.SetImageFile("/rope.PNG");
          board.add(Rope);

          Dagger = new Weapon();
          Dagger.SetImageFile("/dagger.PNG");
          board.add(Dagger);

          Wrench = new Weapon();
          Wrench.SetImageFile("/wrench.PNG");
          board.add(Wrench);

          CandleStick = new Weapon();
          CandleStick.SetImageFile("/candlestick.PNG");
          board.add(CandleStick);

          LeadPipe = new Weapon();
          LeadPipe.SetImageFile("/lead_pipe.PNG");
          board.add(LeadPipe);
      }

      public void initialiseCounters() {
          redCounter = new Counter();
          redCounter.setXY(204, 601);
          redCounter.setColor(Color.RED);
          board.add(redCounter);

          yellowCounter = new Counter();
          yellowCounter.setXY(44, 440);
          yellowCounter.setColor(Color.YELLOW);
          board.add(yellowCounter);

          blueCounter = new Counter();
          blueCounter.setXY(572, 487);
          blueCounter.setColor(Color.BLUE);
          board.add(blueCounter);

          cyanCounter = new Counter();
          cyanCounter.setXY(572, 188);
          cyanCounter.setColor(Color.CYAN);
          board.add(cyanCounter);

          greenCounter = new Counter();
          greenCounter.setXY(250, 50);
          greenCounter.setColor(Color.GREEN);
          board.add(greenCounter);

          whiteCounter = new Counter();
          whiteCounter.setXY(365, 50);
          whiteCounter.setColor(Color.WHITE);
          board.add(whiteCounter);
      }

      public void addComponents() {

          add(scrollPane, "East");
          add(userInput, "South");
          add(board, "Center");
      }
}