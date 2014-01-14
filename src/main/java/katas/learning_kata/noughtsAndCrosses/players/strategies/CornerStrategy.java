package katas.learning_kata.noughtsAndCrosses.players.strategies;

import katas.learning_kata.noughtsAndCrosses.Grid;

public class CornerStrategy implements PlayersStrategy {
	private Grid grid;

	public CornerStrategy(Grid grid) {
		this.grid = grid;
	}

	@Override
	public int move() {
		return grid.getAvailableCorner();
	}

}
