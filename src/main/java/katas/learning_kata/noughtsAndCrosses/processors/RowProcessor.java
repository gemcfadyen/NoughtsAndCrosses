package katas.learning_kata.noughtsAndCrosses.processors;

public class RowProcessor extends Processor {
	private String board;

	public RowProcessor(String board) {
		this.board = board;
	}

	public int potentialWinningMove(String playersSymbol) {
		return potentialWinningPositionInRows(playersSymbol);
	}

	private String getRowBetween(int startOfRow, int endOfRow) {
		return board.substring(startOfRow, endOfRow);
	}

	private int potentialWinningPositionInRows(String symbol) {
		for (int rowIndex = 1; rowIndex <= GRID_DIMENSION; rowIndex++) {
			String row = getRowBetween(startingIndex(rowIndex),
					finishingIndex(rowIndex));
			int position = findIndexOfWinningMoveFor(row, symbol);
			if (isAWinningMoveAt(position))
				return winningPosition(rowIndex, position);
		}
		return NO_MATCH_FOUND;
	}

	private int finishingIndex(int rowIndex) {
		return GRID_DIMENSION * rowIndex;
	}

	public boolean hasWinner() {
		for (int rowIndex = 1; rowIndex <= GRID_DIMENSION; rowIndex++) {
			String row = getRowBetween(startingIndex(rowIndex), finishingIndex(rowIndex));
			if (hasWinnerFor(row)) {
				return true;
			}
		}
		return false;
	}

}
