package katas.learning_kata.noughtsAndCrosses;


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
			String row = getRowBetween(startingIndex(rowIndex), finishingIndex(rowIndex));
			int position = findIndexOfWinningMoveFor(row, symbol);
			if (isAWinningMoveAt(position))
				return winningPosition(rowIndex, position);
		}
		return NO_MATCH_FOUND;
	}
	
//	private int findIndexOfWinningMoveFor(String moves, String symbol) {
//		if (hasWinningMoveFor(moves, symbol)) {
//			return moves.indexOf(EMPTY_CELL);
//		}
//		return NO_MATCH_FOUND;
//	}
	
//	private boolean hasWinningMoveFor(String row, String symbol) {
//		// eg: there is a winning move to be make if any of these regex patterns
//		// are met: x-x|xx-|-xx
//		Pattern winningRowPattern = Pattern.compile(symbol + EMPTY_CELL + symbol
//				+ "|" + symbol + symbol + "-|-" + symbol + symbol);
//
//		Matcher matcher = winningRowPattern.matcher(row);
//
//		while (matcher.find()) {
//			return true;
//		}
//
//		return false;
//	}

	
	private int finishingIndex(int rowIndex) {
		return GRID_DIMENSION * rowIndex;
	}


}
