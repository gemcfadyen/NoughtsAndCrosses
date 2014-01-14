package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import katas.learning_kata.noughtsAndCrosses.Grid;

public class CenterMoveStrategy implements PlayersStrategy {
	private Grid grid;

	public CenterMoveStrategy(Grid grid) {
		this.grid = grid;
	}

	@Override
	public int move() {
		return grid.isCenterTaken() ? NO_MATCH_FOUND: grid.getCenterCell();
	}

}
