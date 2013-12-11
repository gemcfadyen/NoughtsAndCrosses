package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Symbol;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class HumanPlayer implements Player {
	private Symbol symbol;
	private Prompt prompt;

	public HumanPlayer(Symbol symbol, Prompt prompt){
		this.prompt = prompt;
		this.symbol = symbol;
	}
	
	@Override
	public Grid takesGo(Grid grid) {
		prompt.displayBoard(grid.getHorizontalRows());
		prompt.promptUser();
		int moveIndex = prompt.readNextMove();
		return grid.takeNextMove(symbol, moveIndex);
	}

	@Override
	public Symbol getSymbol() {
		return symbol;
	}

}
