package katas.learning_kata.noughtsAndCrosses;

import katas.learning_kata.noughtsAndCrosses.Player;

public class NoughtsAndCrosses {
	private Player playerX;
	private Player playerO;
	private Grid grid;

	public boolean containsWinningRow(Grid grid) {
		return grid.hasWinningRow();
	}

	public Grid playerXTakesTurn(Grid startingGrid) {
		return grid;
	}

	public Grid playerOTakesTurn(Grid startingGrid) {
		return grid;
	}

	public Player getPlayerX() {
		return playerX;
	}

	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	public Player getPlayerO() {
		return playerO;
	}

	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
	}

	public void setGrid(Grid startingGrid) {
		grid = startingGrid;
	}

	public Grid getGrid() {
		return grid;
	}
}
