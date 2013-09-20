package katas.learning_kata.noughtsAndCrosses;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
	private static final String SPACE = "-";
	private static final String O = "o";
	private static final String X = "x";
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
		return board.contains(SPACE);
	}

	public String getWinningSymbol() {
		return null;
	}

	public Grid takeNextMove(String symbol) {
		// not sure this is required anymore. the responsibility of the move
		// is the player... he can query the grid for its status but the player
		// must decide on their own strategy.
		// update the board by taking winning move
		// update the board by taking a blocking move
		// update the board by take any move in a free slot
		return this;
	}

	private int calculateIndexOfNextMove(String row, int offset) {
		return row.indexOf(SPACE) + offset;
	}

	private int calculateIndexOfNextMove(String row, int[] positions) {
		return positions[row.indexOf(SPACE)];
	}

	public int getIndexOfBlockingMove(String symbol) {
		String opponentsSymbol = (symbol.equals(X)) ? O : X;
		return getIndexOfWinningMove(opponentsSymbol);
	}

	public int getIndexOfWinningMove(String symbol) {

		for (int rowIndex = 1; rowIndex <= 3; rowIndex++) {
			String row = getRowAtIndexes(3 * (rowIndex - 1), 3 * rowIndex);
			int position = getIndexOfWinningMoveFor(row, symbol);
			if (position != -1)
				return 3 * (rowIndex -1) + position;
		}
		
		int columnIndex = 0;

		String leftColumn = getColumnStartingAtIndex(columnIndex);
		int[] indexesOfLeftColumn = new int[] { columnIndex, 3 + columnIndex, 3 * 2 + columnIndex };
		int position = getIndexOfWinningMoveFor(leftColumn, symbol);
		if(position != -1){
			return indexesOfLeftColumn[position];
		}

		columnIndex=1;
		String middleColumn = getColumnStartingAtIndex(columnIndex);
		int[] indexesOfMiddleColumn = new int[] { columnIndex, 3 + columnIndex, 3 * 2 + columnIndex };
		position = getIndexOfWinningMoveFor(middleColumn, symbol);
		if(position != -1){
			return indexesOfMiddleColumn[position];
		}

		columnIndex = 2;
		String lastColumn = getColumnStartingAtIndex(columnIndex);
		int[] indexesOfLastColumn = new int[] { columnIndex, 3 + columnIndex, 3 * 2 + columnIndex };
		position = getIndexOfWinningMoveFor(lastColumn, symbol);
		if(position != -1){
			return indexesOfLastColumn[position];
		}

		String backslashDiagonalRow = getBackslashDiagonalRow();
		if (hasWinningMoveFor(backslashDiagonalRow, symbol)) {
			return calculateIndexOfNextMove(backslashDiagonalRow, new int[] {
					0, 4, 8 });
		}

		String forwardslashDiagonalRow = getForwardslashDiagonalRow();
		if (hasWinningMoveFor(forwardslashDiagonalRow, symbol)) {
			return calculateIndexOfNextMove(forwardslashDiagonalRow, new int[] {
					2, 4, 6 });
		}

		return -1;
	}

	private int getIndexOfWinningMoveFor(String moves, String symbol) {
		if (hasWinningMoveFor(moves, symbol)) {
			return moves.indexOf('-');
		}
		return -1;
	}

	private int indexOfWinningPositionOnLine(String symbol, int index) {
		String row = getRowAtIndexes(3 * (index - 1), 3 * index);
		if (hasWinningMoveFor(row, symbol)) {
			return calculateIndexOfNextMove(row, 3 * (index - 1));
		}
		return -1;
	}

	public boolean hasWinningMoveFor(String row, String symbol) {
		// eg: there is a winning move to be make if any of these regex patterns
		// are met: x-x|xx-|-xx
		Pattern winningRowPattern = Pattern.compile(symbol + SPACE + symbol
				+ "|" + symbol + symbol + "-|-" + symbol + symbol);

		Matcher matcher = winningRowPattern.matcher(row);

		while (matcher.find()) {
			return true;
		}

		return false;
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

	private String getColumnStartingAtIndex(int startOfVerticleRow) {
		StringBuffer verticleRow = new StringBuffer();
		verticleRow.append(board.charAt(startOfVerticleRow))
				.append(board.charAt(startOfVerticleRow + 3))
				.append(board.charAt(startOfVerticleRow + 6));
		return verticleRow.toString();
	}

	private String getRowAtIndexes(int startOfRow, int endOfRow) {
		return board.substring(startOfRow, endOfRow);
	}

	public Grid takeWinningMoveWith(String symbol) {
		return null;
	}

}
