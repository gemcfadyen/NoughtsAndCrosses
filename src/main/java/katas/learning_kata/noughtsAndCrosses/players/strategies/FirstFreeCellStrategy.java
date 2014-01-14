package katas.learning_kata.noughtsAndCrosses.players.strategies;

import katas.learning_kata.noughtsAndCrosses.Grid;

public class FirstFreeCellStrategy implements PlayersStrategy {
	private Grid grid;

	public FirstFreeCellStrategy(Grid grid) {
		this.grid = grid;
	}

	@Override
	public int move() {
		return grid.getFirstFreeCell();
	}

}
