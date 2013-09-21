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
		
		int blockingIndex = grid.potentialWinningMove(opponent());
		if(grid.isAWinningMoveAt(blockingIndex)){
			return grid.takeNextMove(symbol, blockingIndex);
		}
		else{
			if(grid.isCenterTaken() && grid.hasFreeCornerPosition()) {
				takeACornerPosition(grid);
			}
			return grid.takeNextMove(symbol, 4);
		}
	}

	private void takeACornerPosition(Grid grid) {
		grid.takeNextMove(symbol, grid.getAvailableCorner());
	}

	protected String opponent() {
		return (symbol.equals(X)) ? O : X;
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
