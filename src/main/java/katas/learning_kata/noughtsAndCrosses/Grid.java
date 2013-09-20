package katas.learning_kata.noughtsAndCrosses;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
	private static final int GRID_DIMENSION = 3;
	private static final int NO_MATCH_FOUND = -1;
	private static final String SPACE = "-";
	public static final String O = "o";
	public static final String X = "x";
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

	public Grid takeNextMove(String symbol, int index) {
		if (board.charAt(index) == '-') {
			board = board.substring(0, index) + symbol
					+ board.substring(index + 1, board.length());
		}

		return this;
	}

	public int getIndexOfBlockingMove(String symbol) {
		String opponentsSymbol = (symbol.equals(X)) ? O : X;
		return potentialWinningMove(opponentsSymbol);
	}

	protected int potentialWinningPositionInRows(String symbol) {
		for (int rowIndex = 1; rowIndex <= GRID_DIMENSION; rowIndex++) {
			String row = getRowBetween(startingIndex(rowIndex),
					finishingIndex(rowIndex));
			int position = findIndexOfWinningMoveFor(row, symbol);
			if (isAWinningMoveAt(position))
				return winningPosition(rowIndex, position);
		}
		return NO_MATCH_FOUND;
	}

	protected int potentialWinningPositionInColumns(String symbol) {
		for (int columnIndex = 0; columnIndex < GRID_DIMENSION; columnIndex++) {
			String column = getColumnStartingAtIndex(columnIndex);
			int[] cells = calculateColumnCells(columnIndex);
			int position = findIndexOfWinningMoveFor(column, symbol);
			if (isAWinningMoveAt(position))
				return winningPosition(cells, position);
		}
		return NO_MATCH_FOUND;
	}

	protected int potentialWinningPositionInDiagonals(String symbol) {
		List<int[]> diagonalIndexes = populateDiagonalIndices();
		for (int[] diagonalIndex : diagonalIndexes) {
			String backslashDiagonalRow = getDiagonalRow(diagonalIndex[0],
					diagonalIndex[1], diagonalIndex[2]);
			int position = findIndexOfWinningMoveFor(backslashDiagonalRow,
					symbol);
			if (isAWinningMoveAt(position))
				return winningPosition(diagonalIndex, position);
		}
		return NO_MATCH_FOUND;
	}

	public int potentialWinningMove(String symbol) {
		return potentialWinningPosition(
				potentialWinningPositionInRows(symbol), 
				potentialWinningPositionInColumns(symbol),
				potentialWinningPositionInDiagonals(symbol));
	}

	private int potentialWinningPosition(int...potentialWinningPositions) {
		for (int position : potentialWinningPositions)
			if (position != NO_MATCH_FOUND) {
				return position;
		}
		return NO_MATCH_FOUND;
	}

	private int[] calculateColumnCells(int columnIndex) {
		return new int[] { columnIndex, GRID_DIMENSION + columnIndex,
				GRID_DIMENSION * 2 + columnIndex };
	}

	private int winningPosition(int[] indexesOfLeftColumn, int position) {
		return indexesOfLeftColumn[position];
	}

	private int winningPosition(int rowIndex, int position) {
		return startingIndex(rowIndex) + position;
	}

	public boolean isAWinningMoveAt(int position) {
		return position != NO_MATCH_FOUND;
	}

	private int finishingIndex(int rowIndex) {
		return GRID_DIMENSION * rowIndex;
	}

	private int startingIndex(int rowIndex) {
		return GRID_DIMENSION * (rowIndex - 1);
	}

	private List<int[]> populateDiagonalIndices() {
		int[] forwardSlashDiagonalIndexes = new int[] { 2, 4, 6 };
		int[] backslashDiagonalIndexes = new int[] { 0, 4, 8 };
		List<int[]> diagonalIndexes = new ArrayList<int[]>();
		diagonalIndexes.add(backslashDiagonalIndexes);
		diagonalIndexes.add(forwardSlashDiagonalIndexes);
		return diagonalIndexes;
	}

	private int findIndexOfWinningMoveFor(String moves, String symbol) {
		if (hasWinningMoveFor(moves, symbol)) {
			return moves.indexOf(SPACE);
		}
		return NO_MATCH_FOUND;
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

	private String getDiagonalRow(int start, int middle, int end) {
		StringBuffer diagonalRow = new StringBuffer();
		diagonalRow.append(board.charAt(start)).append(board.charAt(middle))
				.append(board.charAt(end));
		return diagonalRow.toString();
	}

	private String getColumnStartingAtIndex(int startOfVerticleRow) {
		StringBuffer verticleRow = new StringBuffer();
		verticleRow.append(board.charAt(startOfVerticleRow))
				.append(board.charAt(startOfVerticleRow + 3))
				.append(board.charAt(startOfVerticleRow + 6));
		return verticleRow.toString();
	}

	private String getRowBetween(int startOfRow, int endOfRow) {
		return board.substring(startOfRow, endOfRow);
	}

	public Grid takeWinningMoveWith(String symbol) {
		return null;
	}

}
