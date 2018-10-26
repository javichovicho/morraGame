/*
 * OddEven.java
 * The instantiable class basically compares the user's input and determines who wins a round (either the computer or the player)
 * @author G. Gomes, I. Shaikh, J.Gonzales
 * 14th April 2018
 * The code below was written by G. Gomes
 */

import java.lang.Math;

public class OddEven{

	//data members
	private int playerChoice;		//Player choice between 1 for Odd or 2 for Even
	private int playerFingers;		//Player number of fingers between 1 and 10
	private int computerFingers;	//Random number generated for the fingers the computer will "hold out"
	private int sum;				//Sum of the fingers held out by both the player and the computer
	private String roundWinner;
	private final int ODD = 1;
	private final int EVEN = 2;

	//constructor
	public OddEven(){
		playerChoice = 0;
		playerFingers = 0;
		computerFingers = 0;
		sum = 0;
		roundWinner = "";
	}

	//set methods one for every input
	public void setPlayerChoice(int playerChoice){		//Brings the player's choice from the app class
		this.playerChoice = playerChoice;
	}
	public void setPlayerFingers(int playerFingers){	//Brings the player's fingers from the app class
		this.playerFingers = playerFingers;
	}

	//compute method
	public void compute(){
		computerFingers = (int) (Math.random() * 10) + 1;	//generates a random number between 1 and 10 to be assigned to the computer's fingers
		sum = playerFingers + computerFingers;

		//The following finds out who the winner of the round is
		if(((sum % 2 == 0)&&(playerChoice == EVEN))||((sum % 2 != 0)&&(playerChoice == ODD))){
			//(if sum is even AND player is the "Evens" player) OR (sum is odd AND player is the "Odds" player)
			roundWinner = "Player";
		}else{
			//(sum is odd AND player is the "Evens" player) OR (sum is even AND player is the "Odds" player)
			roundWinner = "Computer";
		}
	}

	//get methods one for every output
	public String getRoundWinner(){
		return roundWinner;
		//Sends the winner of the round to the App class in order to be displayed
	}
	public int getComputerFingers(){
		return computerFingers;
		//Sends the number of fingers generated for the computer to the App class in order to be displayed
	}
}
