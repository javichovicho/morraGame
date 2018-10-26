/*
 * OddEvenApp.java
 * @author G. Gomes, I. Shaikh, J.Gonzales
 * 14th April 2018
 */

import javax.swing.JOptionPane;

public class OddEvenApp{
	public static void main(String[] args){

		//declare variables (initial variables)
		//*Variables were modified by J. Gonzales*
		final int ODD = 1;
		final int EVEN = 2;
		final int MIN_FINGERS = 1;				//This could change to 0 if there's an option to hold out no fingers
		final int MAX_FINGERS = 10;
		final int MAX_SCORE = 6;				//Could be changed if a higher score is required to win a game
		int playerChoice;						//Player choice between 1 for Odd or 2 for Even
		int playerFingers;						//Player number of fingers between 1 and 10
		int computerFingers;					//Random number generated in the instantiable class
		String ans = "Yes";						//Responsible for starting the loop for a game - returns True as long as the user
		String computerChoice = "";				//Either Even or Odd - depending on the player's choice
		String roundWinner;						//Either the player or the computer
												//keeps entering "Yes" to whether he/she would like to play again

		//Game History - group of integers and arrays
		//The following three integers start at 0 before all games
		int gamesWon = 0;
		int gamesLost = 0;
		int gameCounter = 0;
		int playerGameScore;
		int computerGameScore;
		int extraPoints;
		int roundCounter;
		int playerEvenFingers;
		int playerOddFingers;
		int computerEvenFingers;
		int computerOddFingers;
		int roundsWon;
		int roundsLost;

		String firstMessage;
		String firstHistory;					//History of numbers of fingers per round
		String secondHistory;					//History of games played
		StringBuffer strBuff = new StringBuffer();

		boolean condition;

		//*Second History arrays were written by M.Imran Shaikh*
		int[] roundsWonHistory = new int[99];	//99 but it could be anything as long as its greater than the number of rounds which is later specified
		int[] roundsLostHistory = new int[99];
		int[] playerEvenFingersHistory = new int[99];
		int[] computerEvenFingersHistory = new int[99];
		int[] playerOddFingersHistory = new int[99];
		int[] computerOddFingersHistory = new int[99];
		int[] extraPointsHistory = new int [99];
		int[] playerFingersHistory;
		int[] computerFingersHistory;

		//Object
		OddEven myObject = new OddEven();

		/*	*	*	*	*		Start of outmost loop		*	*	*	*	*/
		//*Outer loop written by M.Imran Shaikh*
		//If the String ans (for answer) is not "Yes", the loop below won't run
		while (ans.equalsIgnoreCase("Yes")) {

			condition = true;
			playerGameScore = 0;		//starts at 0 before each game
			computerGameScore = 0;
			extraPoints = 0;

			//*First History arrays were written by J.Gonzales*
			roundCounter = 0;
			playerFingersHistory = new int[99];
			computerFingersHistory = new int[99];

			//For the fingers history to be printed using JOptionPane
			firstMessage = "";
			firstHistory = "";

			//Game History - Second set of arrays
			playerEvenFingers = 0;
			playerOddFingers = 0;
			computerEvenFingers = 0;
			computerOddFingers = 0;

			roundsWon = 0;
			roundsLost = 0;

			gameCounter++;		//Increments by 1 at the start of each game

			//input - prompts the user to choose between being the "odds" player if 1 is entered or being the "even" player if 2 is entered
			//Also prints the respective game number
			playerChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Game " + gameCounter + "\n\nPlease type 1 for Odds or 2 for Even: "));

			/*
			 * Input Correction
			 * The following code makes sure correct data is being input - the correct choice between 1 for Odds and 2 for Even
			 * Records the computer's choice and sends the player's choice to the instantiable class to be processed
			 * Code written by G. Gomes
			 */

			while ((playerChoice < ODD)||(playerChoice > EVEN)) {	//Keeps prompting if the input is invalid
				playerChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid option. Please enter 1 for Odds or 2 for Even"));
			}
			myObject.setPlayerChoice(playerChoice);

			if (playerChoice == ODD) {
				computerChoice = "Even";
			}else if (playerChoice == EVEN) {
				computerChoice = "Odds";
			}

			/*	*	*	*	*		Start of inner loop		*	*	*	*	*/
			//If the score from the previous round for either player is 6 or more the loop below won't run
			//*The code below was written by J.Gonzales*
			while ((playerGameScore < MAX_SCORE)&&(computerGameScore < MAX_SCORE)) {

				/*
				 * The code below uses a string buffer and a conditional statement to print the computer's choice once, at round 1
				 * This has been done so that instead of displaying two windows at the start of each round, only one is displayed
				 * Two pieces of information get inserted into the string buffer which is later 'transformed' into a string
				 * Written by J. Gonzales
				 */

				roundCounter++;		//Increments by 1 at the start of each round
				strBuff.append("Round " + roundCounter + "\n");

				if (condition) {
					strBuff.append("The computer chose: " + computerChoice);
				}
				condition = false;	//the condition is changed so that it is only true at the start of each game

				firstMessage = strBuff.toString();		//character sequence in String Buffer inserted into String firstMessage
				//The method below deletes the content in the string buffer so that it can be used again later
				strBuff.setLength(0);

				//firstMessage has the number of round every round and the computer's choice once at the start of round 1 every game

				//input - prompting the user to insert a number of fingers between 1 and 10
				playerFingers = Integer.parseInt(JOptionPane.showInputDialog(null, firstMessage + "\nHow many fingers from 1 to 10 will you display"));

				/*
				 * The code below makes sure correct data is being input
				 * Sends the number of fingers entered by the player to the instantiable class to be processed
				 * It also records the number of even and odd fingers entered by the player
				 * Code written by G. Gomes
				 */

				while ((playerFingers < MIN_FINGERS)||(playerFingers > MAX_FINGERS)) {
					playerFingers = Integer.parseInt(JOptionPane.showInputDialog(null, "Invalid option. How many fingers from 1 to 10 will you display"));
				}
				myObject.setPlayerFingers(playerFingers);

				if (playerFingers % 2 != 0) {			//player entered an odd number
					playerOddFingers++;
				}else if (playerFingers % 2 == 0) {		//player entered an even number
					playerEvenFingers++;
				}

				//process
				//The code below was written by G. Gomes
				myObject.compute(); // This retrieves the the round winner and the computerFingers too

				//Get Methods
				roundWinner = myObject.getRoundWinner();
				computerFingers = myObject.getComputerFingers();

				//Computer fingers
				//The code below was written by J.Gonzales
				if (computerFingers % 2 == 0) {
					computerEvenFingers++;//Computer displays an even number
				}else{
					computerOddFingers++;//Computer displays an odd number
				}

				//Points counter
				if (roundWinner.equals("Player")) {
					playerGameScore += 2;
					roundsWon++;
				}else{
					computerGameScore += 2;
					roundsLost++;
				}

				//Extra points
				//if player Fingers is closest to the sum (biggest number will always be closest to the sum)
				if (playerFingers > computerFingers) {
					playerGameScore++;//player receives an extra point
					extraPoints++;//extra point counter
				}else if (playerFingers < computerFingers) {	//if computer Fingers is closest to the sum
					computerGameScore++;//computer receives an extra point
					//no extra points for player
				}

				//Required Output after each round (Round winner and scores)
				//Code below was written by G. Gomes
				JOptionPane.showMessageDialog(null, "Computer held out " + computerFingers +
					" finger(s)\n\nThe " + roundWinner +
					" wins the round\nPlayer score: " + playerGameScore +
					"\nComputer score: " + computerGameScore);

				//Required Output after each game (Game winner)
				if (playerGameScore >= MAX_SCORE) {
					JOptionPane.showMessageDialog(null, "You win the game !");
					gamesWon++;
				}else if (computerGameScore >= MAX_SCORE) {
					JOptionPane.showMessageDialog(null, "The computer wins the game...");
					gamesLost++;

				}else if ((computerGameScore == MAX_SCORE)&&(playerGameScore == MAX_SCORE)) {
					JOptionPane.showMessageDialog(null, "Its a draw !");
				}

				//Store a history of the number of fingers shown by both players per round
				playerFingersHistory[roundCounter] = playerFingers;
				computerFingersHistory[roundCounter] = computerFingers;

			} /*	*	*	*	*		End of inner loop		*	*	*	*	*/

			/*
			 * Required Output after each game (Fingers history)
			 * The following code uses the append method
			 * It inserts data relating the history of fingers into a string buffer
			 * Written by J. Gonzales
			 */

			strBuff.append("Fingers history:\n");
			for (int i = 1; i <= roundCounter; i++) {
				strBuff.append("Round " + i + ":\nPlayer held out " +
				playerFingersHistory[i] + " finger(s)\nComputer held out " +
				computerFingersHistory[i] + " finger(s)\n");
			}

			//The following returns a string representation of the string buffer from before
			firstHistory = strBuff.toString();
			//The method below deletes the content in the string buffer so that it can be used again later
			strBuff.setLength(0);
			//The program then prints the string
			JOptionPane.showMessageDialog(null, firstHistory);

			//condition - outmost loop
			ans = JOptionPane.showInputDialog(null, "Would you like to play again? Yes or No.");

			//Rounds won / lost history storing
			//Code below was written by I. Shaikh
			roundsWonHistory[gameCounter] = roundsWon;
			roundsLostHistory[gameCounter] = roundsLost;

			//Even Odd fingers history storing
			playerEvenFingersHistory[gameCounter] = playerEvenFingers;
			playerOddFingersHistory[gameCounter] = playerOddFingers;
			computerEvenFingersHistory[gameCounter] = computerEvenFingers;
			computerOddFingersHistory[gameCounter] = computerOddFingers;

			//Extra points history storing
			extraPointsHistory[gameCounter] = extraPoints;

		} /*	*	*	*	*		End of outmost loop		*	*	*	*	*/

		//History of rounds won and lost even odd fingers and extra points per game using JOptionPane
		//History storing: (If we were using System.out.println() we wouldnt need to store the history like this)
		//Written by J. Gonzales
		for (int i = 1; i <= gameCounter; i++) {
			strBuff.append("For game " + i + ":\nPlayer won " + roundsWonHistory[i] + " round(s)" +
			"\nPlayer lost " + roundsLostHistory[i] + " round(s)" +
			"\n" +
			"\nPlayer displayed " + playerEvenFingersHistory[i] + " even finger(s)" +
			"\nand " + playerOddFingersHistory[i] + " odd finger(s)" +
			"\nComputer displayed " + computerEvenFingersHistory[i] + " even finger(s)" +
			"\nand " + computerOddFingersHistory[i] + " odd finger(s)" +
			"\n" + extraPointsHistory[i] + " extra point(s) was/were awarded to the player\n\n");
		}

		secondHistory = strBuff.toString();

		JOptionPane.showMessageDialog(null, "Game History\n" + secondHistory); //Displays the history

	}
}