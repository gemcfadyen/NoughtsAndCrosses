package katas.learning_kata.noughtsAndCrosses;

public class NoughtsAndCrosses {
	private static final int FIRST_PLAYER = 0;
	private static final int SECOND_PLAYER = 1;
	private Player playerX;
	private Player playerO;
	private Player[] players = new Player[2];
	private Grid grid;

	NoughtsAndCrosses() {
		grid = new Grid("---------");
	}

	private void determineTheOrderOfThePlayers() {
		// can be changed to random selection later
		players[0] = playerX;
		players[1] = playerO;
	}

	public Grid startGame() {
		determineTheOrderOfThePlayers();

		int playersIndex = 0;
		while (gameIsInProgress()) {
			players[playersIndex].takesGo(grid);
			playersIndex = (playersIndex == 0) ? SECOND_PLAYER : FIRST_PLAYER;
		}
		return grid;
	}

	private boolean gameIsInProgress() {
		return !containsWinningRow(grid) && grid.hasFreeSlot();
	}

	private boolean containsWinningRow(Grid grid) {
		return grid.hasWinningRow();
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
