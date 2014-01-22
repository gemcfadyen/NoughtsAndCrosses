package katas.learning_kata.noughtsAndCrosses.players;

import static java.lang.Integer.valueOf;
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
		String moveIndex = prompt.readNextMove();
		return grid.updateGridWith(symbol, validated(moveIndex, grid));
	}
	
	private int validated(String index, Grid grid) {
		int indexOfNextMove = onlyNumbersInIndex(index);
		
		while (!grid.isEmptyCellAt(indexOfNextMove)){
			prompt.promptUser();
			indexOfNextMove = onlyNumbersInIndex(prompt.readNextMove());
		}
		return indexOfNextMove;
		
	}
	
	private boolean isOnlyNumbers(String input) {
		return input.matches("\\d+");
	}
	
	private int onlyNumbersInIndex(String input) {
		while(!isOnlyNumbers(input)){
			prompt.promptUser();
			input = prompt.readNextMove();
		}
		
		return valueOf(input);
	}
	
}
