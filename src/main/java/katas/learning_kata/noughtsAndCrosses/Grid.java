package katas.learning_kata.noughtsAndCrosses;

import katas.learning_kata.noughtsAndCrosses.processors.ColumnProcessor;
import katas.learning_kata.noughtsAndCrosses.processors.DiagonalProcessor;
import katas.learning_kata.noughtsAndCrosses.processors.RowProcessor;

public class Grid {
	public static final char EMPTY_CELL = '-';
	public static final int CENTER_CELL = 4;
	public static final int NO_MATCH_FOUND = -1;
	public static final String O = "o";
	public static final String X = "x";
	private String board;

	public Grid(String board) {
		this.board = board;
	}

	public boolean hasWinningRow() {
		RowProcessor rowProcessor = new RowProcessor(board);
		ColumnProcessor columnProcessor = new ColumnProcessor(board);
		DiagonalProcessor diagonalProcessor = new DiagonalProcessor(board);
		return rowProcessor.hasWinner() || columnProcessor.hasWinner() || diagonalProcessor.hasWinner();
	}

	public String toString() {
		StringBuffer boardToPrint = new StringBuffer(board.substring(0, 3)
				+ "\n" + board.substring(3, 6) + "\n" + board.substring(6, 9)
				+ "\n");
		boardToPrint.append("__________________________________\n");

		return boardToPrint.toString();
	}

	public boolean hasFreeSlot() {
		return board.contains(String.valueOf(EMPTY_CELL));
	}

	public String getWinningSymbol() {
		RowProcessor rowProcessor = new RowProcessor(board);
		String winningSymbolFromRows = rowProcessor.getWinningSymbol();
		ColumnProcessor columnProcessor = new ColumnProcessor(board);
		String winningSymbolFromColumns = columnProcessor.getWinningSymbol();
		DiagonalProcessor diagonalProcessor = new DiagonalProcessor(board);
		String winningSymbolFromDiagonal = diagonalProcessor.getWinningSymbol();
		
		if(winningSymbolFromRows != "-1"){
			return winningSymbolFromRows;
		}
		
		if(winningSymbolFromColumns != "-1"){
			return winningSymbolFromColumns;
		}
		
		if(winningSymbolFromDiagonal != "-1"){
			return winningSymbolFromDiagonal;
		}
		return "-1";
	}

	public Grid takeNextMove(String symbol, int index) {
		if (board.charAt(index) == '-') {
			board = board.substring(0, index) + symbol + board.substring(index + 1, board.length());
		}

		return this;
	}

	public int getIndexOfBlockingMove(String symbol) {
		String opponentsSymbol = (symbol.equals(X)) ? O : X;
		return potentialWinningMove(opponentsSymbol);
	}



	public int potentialWinningMove(String symbol) {
		RowProcessor horizontalRows = new RowProcessor(board);
		ColumnProcessor columnProcessor = new ColumnProcessor(board);
		DiagonalProcessor diagonals = new DiagonalProcessor(board);
		
		return potentialWinningPosition(
				horizontalRows.potentialWinningMove(symbol), 
				columnProcessor.potentialWinningMove(symbol),
				diagonals.potentialWinningMove(symbol)
				);
	}

	private int potentialWinningPosition(int...potentialWinningPositions) {
		for (int position : potentialWinningPositions)
			if (position != NO_MATCH_FOUND) {
				return position;
		}
		return NO_MATCH_FOUND;
	}

	public boolean isACellInTheGrid(int position) {
		return position != NO_MATCH_FOUND;
	}

	public boolean isCenterTaken() {
		return !(board.charAt(CENTER_CELL) == EMPTY_CELL);
	}

	public boolean hasFreeCornerPosition() {
		return getAvailableCorner() != NO_MATCH_FOUND;
	}

	public int getAvailableCorner() {
		if (board.charAt(0) == EMPTY_CELL) return 0;
		else if (board.charAt(2) == EMPTY_CELL) return 2;
		else if (board.charAt(6) == EMPTY_CELL) return 6;
		else if (board.charAt(8) == EMPTY_CELL) return 8;
		else return NO_MATCH_FOUND;
	}

	public int getCentreCell() {
		return CENTER_CELL;
	}

	public int getFirstFreeCell() {
		return board.indexOf('-');
	}

}
