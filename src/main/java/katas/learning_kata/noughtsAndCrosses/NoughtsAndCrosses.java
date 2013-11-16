package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import katas.learning_kata.noughtsAndCrosses.players.AutomatedPlayer;
import katas.learning_kata.noughtsAndCrosses.players.HumanPlayer;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.CommandPrompt;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class NoughtsAndCrosses {
	private static final int FIRST_PLAYER = 0;
	private static final int SECOND_PLAYER = 1;
	private Player playerX;
	private Player playerO;
	private Player[] players = new Player[2];
	private Grid grid;
	private Prompt commandPrompt;

	NoughtsAndCrosses() {
		commandPrompt = setupCommandPrompt();
		grid = new Grid("---------");
		playerX = new AutomatedPlayer("x"); //new HumanPlayer(X, setupCommandPrompt());
		playerO = new HumanPlayer(O, commandPrompt);
	}

	private Prompt setupCommandPrompt() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		return new CommandPrompt(bufferedReader, bufferedWriter);
	}

	private void determineTheOrderOfThePlayers() {
		// can be changed to random selection later
		players[0] = playerX;
		players[1] = playerO;
	}

	public void playGame() {
		determineTheOrderOfThePlayers();

		int playersIndex = 0;
		while (gameIsInProgress()) {
			players[playersIndex].takesGo(grid);
			playersIndex = (playersIndex == 0) ? SECOND_PLAYER : FIRST_PLAYER;
		}
		commandPrompt.displayBoard(grid);
		evaluateGame();
	}

	private void evaluateGame() {
		if(grid.getWinningSymbol().equals(valueOf(Grid.NO_MATCH_FOUND))){
			commandPrompt.printLoosingStatement();
		}
		else{
			commandPrompt.printWinningStatement(grid.getWinningSymbol());
		}
	}

	private boolean gameIsInProgress() {
		String winningStatus = grid.getWinningSymbol();
		return winningStatus.equals(valueOf(NO_MATCH_FOUND)) && grid.hasFreeSlot();
	}

	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	public Player getPlayerO() {
		return playerO;
	}

	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
	}

	public void setGrid(Grid startingGrid) {
		grid = startingGrid;
	}

	public Grid getGrid() {
		return grid;
	}

	public Prompt getCommandPrompt() {
		return commandPrompt;
	}

	public void setCommandPrompt(Prompt commandPrompt) {
		this.commandPrompt = commandPrompt;
	}
}
