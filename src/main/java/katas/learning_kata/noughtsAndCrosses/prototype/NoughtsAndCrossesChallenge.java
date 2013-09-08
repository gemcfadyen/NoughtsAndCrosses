package katas.learning_kata.noughtsAndCrosses.prototype;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

	public String getCodedPlayersFirstMove() {
		return "x--------";
	}

	// This method is the one that actually kicks off a game of noughts and
	// crosses.
	// Each time the human player is required to make a move they will be
	// prompted for their input in the console
	public void playAGame() {
		Player startingPlayer = determineWhichPlayerWillGoFirst();
		if (startingPlayer instanceof CodedPlayer) {
			BufferedWriter bufferedWriter = new BufferedWriter(
					new OutputStreamWriter(System.out));
			try {

				// while there is not a winning streak in the game and so long
				// as there is a free space to make a move..
				promptyCodedUserForInput(bufferedWriter,
						getCodedPlayersFirstMove());
				promptHumanUserForInput();
				promptyCodedUserForInput(bufferedWriter, determineNextMove());
				promptHumanUserForInput();
				bufferedWriter.close();

			} catch (IOException e) {
				System.out.println("Error in writing output");
				e.printStackTrace();
			}
		} else {
			//Will come later... lets get the first scenario when the computer starts going first...
			promptHumanUserForInput();

		}
	}

	//this will need making 'smart' so that it carefully selects what move to make next
	protected String determineNextMove() {
		return "x-o---x---";
	}

	protected void promptyCodedUserForInput(BufferedWriter bufferedWriter,
			String nextMove) {
		try {
			bufferedWriter.write(nextMove);
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected String promptHumanUserForInput() {
		String humanPlayersInput = "";
		System.out.println("Player, please take your go...\n");

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(System.in));

		try {
			humanPlayersInput = bufferedReader.readLine();
			System.out.println("You chose: " + humanPlayersInput + "\n");

		} catch (IOException e) {
			System.out.println("Error in reading input");
			e.printStackTrace();
		}
		return humanPlayersInput;
	}
}
