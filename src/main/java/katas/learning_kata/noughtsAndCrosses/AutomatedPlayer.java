package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;

public class AutomatedPlayer implements Player {
	private String symbol;
	private String name;

	public AutomatedPlayer(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}

	@Override
	public Grid takesGo(Grid grid) {
		int potentialWinningMove = grid.potentialWinningMove(symbol);
		if(grid.isAWinningMoveAt(potentialWinningMove)){
			return grid.takeNextMove(symbol, potentialWinningMove);
		}
		
		String opponentsSymbol = (symbol.equals(X)) ? O : X;
		int blockingIndex = grid.potentialWinningMove(opponentsSymbol);
		if(grid.isAWinningMoveAt(blockingIndex)){
			return grid.takeNextMove(symbol, blockingIndex);
		}
		else{
			return null;
		}
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public String getName() {
		return name;
	}
}
