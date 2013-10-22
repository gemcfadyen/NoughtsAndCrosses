package katas.learning_kata.noughtsAndCrosses.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Processor implements BoardProcessor {

	protected int findIndexOfWinningMoveFor(String moves, String symbol) {
		if (hasWinningMoveFor(moves, symbol)) {
			return moves.indexOf(EMPTY_CELL);
		}
		return NO_MATCH_FOUND;
	}
	
	private boolean hasWinningMoveFor(String row, String symbol) {
		// eg: there is a winning move to be make if any of these regex patterns
		// are met: x-x|xx-|-xx
		Pattern winningRowPattern = Pattern.compile(symbol + EMPTY_CELL + symbol
				+ "|" + symbol + symbol + "-|-" + symbol + symbol);

		return matchPattern(row, winningRowPattern);
	}
	
	protected boolean hasWinnerFor(String row){
		Pattern winningPattern = Pattern.compile("xxx|ooo");
		return matchPattern(row, winningPattern);
	}

	private boolean matchPattern(String row, Pattern winningRowPattern) {
		Matcher matcher = winningRowPattern.matcher(row);

		while (matcher.find()) {
			return true;
		}

		return false;
	}
	
	
	protected int winningPosition(int rowIndex, int position) {
		return startingIndex(rowIndex) + position;
	}

	protected boolean isAWinningMoveAt(int position) {
		return position != NO_MATCH_FOUND;
	}
	
	protected int startingIndex(int rowIndex) {
		return GRID_DIMENSION * (rowIndex - 1);
	}
	
	protected int winningPosition(int[] indexesOfLeftColumn, int position) {
		return indexesOfLeftColumn[position];
	}
}
