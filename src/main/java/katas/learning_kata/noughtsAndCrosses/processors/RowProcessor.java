package katas.learning_kata.noughtsAndCrosses.processors;

import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;

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
			if (hasWinningEntryOfNoughts(row) || hasWinningEntryForCrosses(row)) {
				return true;
			}
		}
		return false;
	}
	
	public String getWinningSymbol() {
		for (int rowIndex = 1; rowIndex <= GRID_DIMENSION; rowIndex++) {
			String row = getRowBetween(startingIndex(rowIndex), finishingIndex(rowIndex));
			if (hasWinningEntryOfNoughts(row) ) {
				return O;
			}
			
			if(hasWinningEntryForCrosses(row)){
				return X;
			}
		}
		return String.valueOf(NO_MATCH_FOUND);
	}

}
