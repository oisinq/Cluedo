import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

//the class allows users to input user name and pick which token they wish to play
public class GameSetup  {

    Counters counters;
	GameSetup(Counters counters) {
	    this.counters = counters;
		playerCountSelect();
    }
	
	public void playerCountSelect(){
		//the below two strings and jOptionPane are used to allow the user to pick how many users will play 
			String[] playerCount =(new String[] {"2","3", "4","5", "6"});
	        String userChoice = (String) JOptionPane.showInputDialog( null, "Select How Many Players You Want","Cluedo", JOptionPane.QUESTION_MESSAGE, null,playerCount, playerCount[0]);
		
	        if(userChoice == null){//if the user hits cancel the game is quit
	        	System.out.println("You have exited the game,Thanks For Playing!");
	        	System.exit(0);
	        }
	        
	        //depending on what they pick this switch statement sends the correct number of players to character select
	        switch (userChoice) {
            case "2":
                CharacterSelect(2);
                break;
            case "3":
            	CharacterSelect(3);
                break;	        	
			case "4":
				CharacterSelect(4);
				break;
	       	case "5":
	       		CharacterSelect(5);
	       		break;
	       	case "6":
	       		CharacterSelect(6);
                break;
	        
	        }	
		}
	
	
	public void CharacterSelect(int PlayerCount){
		//this string stores all the possible characters
		String[] Characters =(new String[] {"Plum","White", "Scarlet","Green", "Mustard","Peacock"});
		//this list will store the above string in list format , both are needed for the below loop
		List<String> CharacterList = new ArrayList<String>(Arrays.asList(Characters));
		for(int i=0;i<PlayerCount;i++){
			String userName = JOptionPane.showInputDialog("Enter Your Username");
			if(userName== null){//if the user cancels , exit the code
	        	System.out.println("You have exited the game,Thanks For Playing!");
	        	System.exit(0);
	        }
	        String userChoice = (String) JOptionPane.showInputDialog( null, "Player "+(i+1)+" Please Select A Character","Cluedo", JOptionPane.QUESTION_MESSAGE, null,Characters, Characters[0]);
	        if(userChoice== null){//if the user cancels exit the code
	        	System.out.println("You have exited the game,Thanks For Playing!");
	        	System.exit(0);
	        }
	        CharacterList.remove(userChoice);//remove the player the user chose from the list
	        Characters = CharacterList.toArray(new String[0]);//characters gets updated with the new list
	        counters.createCounter(userName,userChoice);//send the username and their character choice to be created and placed on the board
		}
	}
	
}
