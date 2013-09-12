package katas.learning_kata.noughtsAndCrosses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
	private String board;

	public Grid(String board) {
		this.board = board;
	}

	public boolean hasWinningRow() {
		return false;
	}

	public String toString() {
		StringBuffer boardToPrint = new StringBuffer(board.substring(0, 3)
				+ "\n" + board.substring(3, 6) + "\n" + board.substring(6, 9)
				+ "\n");

		return boardToPrint.toString();
	}

	public boolean hasFreeSlot() {
		return board.contains("-");
	}

	public String getWinningSymbol() {
		return null;
	}

	public Grid takeNextMove(String symbol) {
		// not sure this is requird anymore. the responsability of the move
		// isthe player... he can query the grid for its status but the player
		// must decide on their own strategy.
		// update the board by taking winning move
		// update the board by taking a blocking move
		// update the board by take any move in a free slot
		return this;
	}

	public boolean hasWinningMoveFor(String symbol) {
		// eg: there is a winning move to be make if any of these regex patterns
		// are met: x-x|xx-|-xx
		Pattern winningRowPattern = Pattern.compile(symbol + "-" + symbol + "|"
				+ symbol + symbol + "-|-" + symbol + symbol);
		Matcher[] allPossibleRows = getMatchersForAllRowsInGridUsing(winningRowPattern);

		for (Matcher matcher : allPossibleRows) {
			while (matcher.find()) {
				return true;
			}
		}

		return false;
	}

	private Matcher[] getMatchersForAllRowsInGridUsing(Pattern pattern) {
		String[] allRows = { getHorizontalRowsAtIndexes(0, 3),
				getHorizontalRowsAtIndexes(3, 6),
				getHorizontalRowsAtIndexes(6, 9),
				getVerticleRowsStartAtIndex(0), getVerticleRowsStartAtIndex(1),
				getVerticleRowsStartAtIndex(2), getBackslashDiagonalRow(),
				getForwardslashDiagonalRow() };

		Matcher[] matchersForAllRows = new Matcher[allRows.length];
		for (int i = 0; i < allRows.length; i++) {
			Matcher matcher = pattern.matcher(allRows[i]);
			matchersForAllRows[i] = matcher;

		}
		return matchersForAllRows;
	}

	private String getBackslashDiagonalRow() {
		StringBuffer diagonalRow = new StringBuffer();
		diagonalRow.append(board.charAt(0)).append(board.charAt(4))
				.append(board.charAt(8));
		return diagonalRow.toString();
	}

	private String getForwardslashDiagonalRow() {
		StringBuffer diagonalRow = new StringBuffer();
		diagonalRow.append(board.charAt(2)).append(board.charAt(4))
				.append(board.charAt(6));
		return diagonalRow.toString();
	}

	private String getVerticleRowsStartAtIndex(int startOfVerticleRow) {
		StringBuffer verticleRow = new StringBuffer();
		verticleRow.append(board.charAt(startOfVerticleRow))
				.append(board.charAt(startOfVerticleRow + 3))
				.append(board.charAt(startOfVerticleRow + 6));
		return verticleRow.toString();
	}

	private String getHorizontalRowsAtIndexes(int startOfRow, int endOfRow) {
		return board.substring(startOfRow, endOfRow);
	}

	public Grid takeWinningMoveWith(String symbol) {
		return null;
	}

}
