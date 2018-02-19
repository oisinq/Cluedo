import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


public class GameSetup  {
	
	
	GameSetup() {
		playerCountSelect();
    }
	
	public static void playerCountSelect(){
			String[] playerCount =(new String[] {"2","3", "4","5", "6"});
	        String userChoice = (String) JOptionPane.showInputDialog( null, "Select How Many Players You Want","Cluedo", JOptionPane.QUESTION_MESSAGE, null,playerCount, playerCount[0]);
		
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
	
	
	public static void CharacterSelect(int PlayerCount){
		String[] Characters =(new String[] {"Plum","White", "Scarlet","Green", "Mustard","Peacock"});
		List<String> CharacterList = new ArrayList<String>(Arrays.asList(Characters));
		for(int i=0;i<PlayerCount;i++){
			String userName = JOptionPane.showInputDialog("Enter Your Username");
	        String userChoice = (String) JOptionPane.showInputDialog( null, "Player "+(i+1)+" Please Select A Character","Cluedo", JOptionPane.QUESTION_MESSAGE, null,Characters, Characters[0]);
	        CharacterList.remove(userChoice);
	        Characters = CharacterList.toArray(new String[0]);
	        Counters.createCounter(userName,userChoice);
		}
	}
	
}
