package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.GameStatus.GameStates.NO_WINNER;
import static katas.learning_kata.noughtsAndCrosses.GameStatus.GameStates.WINNER;
import static katas.learning_kata.noughtsAndCrosses.GameStatusBuilder.aGameStatusBuilder;
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

	NoughtsAndCrosses() {
		grid = new Grid("---------");
		playerX = new AutomatedPlayer("x"); //new HumanPlayer(X, setupCommandPrompt());
		playerO = new HumanPlayer(O, setupCommandPrompt());
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

	public GameStatus playGame() {
		determineTheOrderOfThePlayers();

		int playersIndex = 0;
		while (gameIsInProgress()) {
			players[playersIndex].takesGo(grid);
			playersIndex = (playersIndex == 0) ? SECOND_PLAYER : FIRST_PLAYER;
		}
		System.out.println("Final Grid: \n" +  grid.toString());
		return evaluateGame();
	}

	private GameStatus evaluateGame() {
		return getWinnerFrom(grid).equals(String.valueOf(Grid.NO_MATCH_FOUND)) ?  createNoWinnerGameStatus():  createWinningStatus();
	}

	private GameStatus createNoWinnerGameStatus() {
		return aGameStatusBuilder().withAStatusOf(NO_WINNER).withAMessageOf(NO_WINNER.getStatusMessage()).build();
	}

	private GameStatus createWinningStatus() {
		return aGameStatusBuilder()
				.withAStatusOf(WINNER)
				.withAMessageOf(format(WINNER.getStatusMessage(),
									   getTheNameOfThePlayerUsingThe(grid.getWinningSymbol())))
									   .build();
	}

	private String getTheNameOfThePlayerUsingThe(String winningSymbol) {
		for (Player player : players) {
			if (player.getSymbol().equals(winningSymbol)) {
				return player.getSymbol();
			}
		}
		return "";
	}

	private boolean gameIsInProgress() {
		String winningStatus = grid.getWinningSymbol();
		return winningStatus.equals(valueOf(NO_MATCH_FOUND)) && grid.hasFreeSlot();
	}

	private String getWinnerFrom(Grid grid) {
		return grid.getWinningSymbol();
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
}
