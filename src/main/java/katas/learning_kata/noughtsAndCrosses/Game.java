package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Symbol.O;
import static katas.learning_kata.noughtsAndCrosses.Symbol.X;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import katas.learning_kata.noughtsAndCrosses.players.HumanPlayer;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.CommandPrompt;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class Game {
	private static final int FIRST_PLAYER = 0;
	private static final int SECOND_PLAYER = 1;
	private Player playerX;
	private Player playerO;
	private Player[] players = new Player[2];
	private Grid grid;
	private Prompt commandPrompt;
	private PlayerFactory playerFactory;
	private GridFactory gridFactory;

	Game() {
		playerFactory = new PlayerFactory();
		gridFactory = new GridFactory();
		commandPrompt = setupCommandPrompt(); 
		playerO = playerFactory.createOpponentPlayer("h", O, commandPrompt);
	}
	
	private Prompt setupCommandPrompt() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
		return new CommandPrompt(bufferedReader, bufferedWriter);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}

	public void play() {
		initialiseGridToSpecifiedDimension();
		playerX = initialiseOpponent();
		determineTheOrderOfThePlayers(); 
		
		int playersIndex = 0;
		while (gameIsInProgress()) {
			players[playersIndex].takesGo(grid);
			playersIndex = (playersIndex == 0) ? SECOND_PLAYER : FIRST_PLAYER;
		}
		displayFinalGrid();
		evaluateGame();
	}

	private void initialiseGridToSpecifiedDimension() {
		commandPrompt.promptForGridDimension();
		int gridDimension = commandPrompt.readGridDimension();
		grid = gridFactory.createGridWithDimension(gridDimension);
	}
	
	private Player initialiseOpponent() {
		commandPrompt.promptForChoiceOfOpponent();
		String opponentType = commandPrompt.readChoiceOfOpponent();
		return getOpponentUsing(validated(opponentType));
	}
 
	private String validated(String opponentType) {
		while(!(hasSelectedAutomatedOpponent(opponentType) || hasSelectedHumanOpponent(opponentType))){
			commandPrompt.promptForChoiceOfOpponent();
			opponentType = commandPrompt.readChoiceOfOpponent();
		}
		return opponentType;
	}

	private boolean hasSelectedHumanOpponent(String choiceOfOpponent) {
		return choiceOfOpponent.equalsIgnoreCase("h");
	}

	private boolean hasSelectedAutomatedOpponent(String choiceOfOpponent) {
		return choiceOfOpponent.equalsIgnoreCase("c");
	}

	private Player getOpponentUsing(String opponentType) {
		return playerFactory.createOpponentPlayer(opponentType, X, commandPrompt);
	}

	private void determineTheOrderOfThePlayers() {
		players[0] = playerX;
		players[1] = playerO;
	}

	private void evaluateGame() {
		if (grid.getWinningSymbol() == null) {
			commandPrompt.printLoosingStatement();
		} else {
			commandPrompt.printWinningStatement(grid.getWinningSymbol());
		}
	}

	private boolean gameIsInProgress() {
		Symbol winningStatus = grid.getWinningSymbol();
		return winningStatus == null && grid.hasFreeSlot();
	}
	
	private void displayFinalGrid() {
		commandPrompt.displayBoard(grid.getHorizontalRows());
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

	public PlayerFactory getPlayerFactory() {
		return playerFactory;
	}

	public void setPlayerFactory(PlayerFactory playerFactory) {
		this.playerFactory = playerFactory;
	}

	public GridFactory getGridFactory() {
		return gridFactory;
	}

	public void setGridFactory(GridFactory gridFactory) {
		this.gridFactory = gridFactory;
	}
}
