package katas.learning_kata.noughtsAndCrosses.players.strategies;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class WinningMoveStrategy implements PlayersStrategy {
	private ValidSymbol symbol;
	private Grid grid;

	public WinningMoveStrategy(ValidSymbol symbol, Grid grid) {
		this.symbol = symbol;
		this.grid = grid;
	}

	@Override
	public int move() {
		return grid.potentialWinningMove(symbol);
	}
}
