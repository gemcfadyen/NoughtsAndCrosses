package katas.learning_kata.noughtsAndCrosses;

import java.util.Random;

public class NoughtsAndCrossesChallenge {
	int randomSeed;
	Player codedPlayer = new CodedPlayer();
	Player humanPlayer = new HumanPlayer();

	NoughtsAndCrossesChallenge(int randomSeed) {
		this.randomSeed = randomSeed;
	}

	public Player determineWhichPlayerWillGoFirst() {
		if (generateRandomSeedToSelectStartingPlayer() <= 4) {
			codedPlayer.setIsPlayersTurn(true);
			return codedPlayer;
		} else {
			return humanPlayer;
		}
	}

	private int generateRandomSeedToSelectStartingPlayer() {
		Random initalPlayerGenerator = new Random(randomSeed);

		int randomNumberToDetermineStartingPlayer = initalPlayerGenerator.nextInt(randomSeed);
		return randomNumberToDetermineStartingPlayer;
	}
	
	//building up the actual game of noughts and crosses
	public void playAGame(){
		Player startingPlayer = determineWhichPlayerWillGoFirst();
		if(startingPlayer instanceof CodedPlayer){
			
		}
		else{
			promptHumanUserForInput();
		}
	}

	private void promptHumanUserForInput() {
		//go to the console and read in what the human wants to start with
	}
}
