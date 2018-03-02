import java.awt.Color;
import java.util.Random;

/*  Cluedo - Sprint 2
    Team: auroraBorealis
    Members: Oisin Quinn (16314071), Darragh Clarke (16387431), Charlie Kelly (16464276)
    "Aurora Borealis! At this time of year? At this time of day? In this part of the country? Localized entirely within your kitchen?" */




public class Cards  {
	private Counters counters;
	String[] CardList =(new String[] {"Scarlet","Mustard", "Green","Peacock", "White", "Plum",
			"Pistol", "Dagger", "Lead Pipe", "Candle Stick", "Rope", "Wrench",
			"Ballroom", "Library", "Hall", "Conservatory", "Billiard Room", "Study", "Lounge", "Dining Room", "Kitchen"});
	String[] Scarlet =new String[9];
	String[] Mustard =new String[9];
	String[] Green =new String[9];
	String[] Peacock =new String[9];
	String[] White =new String[9];
	String[] Plum =new String[9];
	int[] Given = (new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});
	  
	  Cards( Counters counters)
	  { 
		  Envelope();
		  
		  int Players=0;
		  for (Counter currentCounter : counters) {
          System.out.println("yes");
          Players++;
         
      }
		 
		  for (Counter currentCounter : counters) {
	         CardHolder(currentCounter.getCharacterName(), 18/Players);
	      }
	  int a=0;
	  while(a<18/Players)
	  {
		  System.out.println(Plum[a]);
		  a++;
	  }
	  }
	  public Random Random()
	  {
		  Random rand;
		  rand = new Random();
		  return rand;
	  }
	  public void Envelope()
	  {
	  	String[] MurderFile = new String[3];
	  	int temp=Random().nextInt(6);
	  	MurderFile[0]=CardList[temp ];
	  	Given[temp]=1;
	  	temp=Random().nextInt(6)+6;
	  	MurderFile[1]=CardList[temp ];
	  	Given[temp]=1;
	  	temp=Random().nextInt(8)+12;
	  	MurderFile[2]=CardList[temp ];
	  	Given[temp]=1;
	  	System.out.print(MurderFile[0]+MurderFile[1]+MurderFile[2]);
	  }
	  public void CardHolder(String character, int amount )
	  {
		  int track=0;
		  int temp=Random().nextInt(18);
		  switch (character) {
          case "Plum":
              while(track<amount)
              {
            	  if(Given[temp]==0)
            	  {
            		  System.out.println(track);
            	  Plum[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
          case "White":
        	  while(track<amount)
              {
        		  System.out.println(temp);
            	  if(Given[temp]==0)
            	  {
            		  
            	  White[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
          case "Scarlet":
        	  while(track<amount)
              {
            	  if(Given[temp]==0)
            	  {
            	  Scarlet[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
          case "Green":
        	  while(track<amount)
              {
            	  if(Given[temp]==0)
            	  {
            	  Green[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
          case "Mustard":
        	  while(track<amount)
              {
            	  if(Given[temp]==0)
            	  {
            	  Mustard[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
          case "Peacock":
        	  while(track<amount)
              {
            	  if(Given[temp]==0)
            	  {
            	  Peacock[track]=CardList[temp];
            	  Given[temp]=1;
            	  track++;
            	  }
            	  temp=Random().nextInt(21);
              }
              break;
	        }	
	  }
}
