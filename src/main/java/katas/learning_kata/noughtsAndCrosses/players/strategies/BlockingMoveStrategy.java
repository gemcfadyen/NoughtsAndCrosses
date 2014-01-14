package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class BlockingMoveStrategy implements PlayersStrategy {
	private ValidSymbol symbol;
	private Grid grid;
	
	public BlockingMoveStrategy(ValidSymbol symbol, Grid grid) {
		this.symbol = symbol;
		this.grid = grid;
	}

	@Override
	public int move() {
		return grid.potentialWinningMove(opponent());
	}
	
	public ValidSymbol opponent() {
		return (symbol.equals(X)) ? O : X;
	}

}
