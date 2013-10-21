package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class HumanPlayer implements Player {
	private String symbol;
	private Prompt prompt;

	public HumanPlayer(String symbol, Prompt prompt){
		this.prompt = prompt;
		this.symbol = symbol;
	}
	
	@Override
	public Grid takesGo(Grid grid) {
		prompt.displayBoard(grid);
		int moveIndex = prompt.readNextMove();
		return grid.takeNextMove(symbol, moveIndex);
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public String getName() {
		return null;
	}

}
