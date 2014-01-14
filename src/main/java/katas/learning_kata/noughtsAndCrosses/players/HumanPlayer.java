package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class HumanPlayer implements Player {
	private ValidSymbol symbol;
	private Prompt prompt;

	public HumanPlayer(ValidSymbol symbol, Prompt prompt){
		this.prompt = prompt;
		this.symbol = symbol;
	}
	
	@Override
	public Grid takesGo(Grid grid) {
		prompt.displayBoard(grid.getHorizontalRows());
		prompt.promptUser();
		int moveIndex = prompt.readNextMove();
		return grid.updateGridWith(symbol, validated(moveIndex, grid));
	}
	
	private int validated(int index, Grid grid) {
		while (!grid.isEmptyCellAt(index)){
			prompt.promptUser();
			index = prompt.readNextMove();
		}
		return index;
		
	}
}
