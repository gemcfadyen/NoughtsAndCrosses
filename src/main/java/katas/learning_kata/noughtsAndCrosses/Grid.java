package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

import katas.learning_kata.noughtsAndCrosses.processors.ColumnProcessor;
import katas.learning_kata.noughtsAndCrosses.processors.DiagonalProcessor;
import katas.learning_kata.noughtsAndCrosses.processors.Processor;
import katas.learning_kata.noughtsAndCrosses.processors.RowProcessor;

public class Grid {
	public static final char EMPTY_CELL = '-';
	public static final int CENTER_CELL = 4;
	public static final int NO_MATCH_FOUND = -1;
	public static final String O = "o";
	public static final String X = "x";
	public static final int TOP_LEFT_CORNER = 0;
	public static final int TOP_RIGHT_CORNER = 2;
	public static final int BOTTOM_LEFT_CORNER = 6;
	public static final int BOTTOM_RIGHT_CORNER = 8;
	private String board;

	public Grid(String board) {
		this.board = board;
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
		List<Row> gridRows = generateRowsFromCurrentGrid();
		for (Row row : gridRows) {
			if (row.hasWinner())
				return row.winningSymbol();
		}

		return valueOf(NO_MATCH_FOUND);
	}


	private List<Row> generateRowsFromCurrentGrid() {
		List<Row> rows = new ArrayList<Row>();
		rows.addAll(getHorizontalRows());
		rows.addAll(getVerticleRows());
		rows.addAll(getDiagonalRows());
		return rows;

	}
	
	private List<Row> getDiagonalRows(){
		List<Row> rows = new ArrayList<Row>();
		rows.add(new Row(getDiagonalRow(0, 4, 8)));
		rows.add(new Row(getDiagonalRow(2, 4, 6)));
		
		return rows;
	}
	
	private Cell[] getDiagonalRow(int... indexes){
		Cell[] diagonalCells = new Cell[Processor.GRID_DIMENSION];
		
		for(int i = 0; i < diagonalCells.length; i++){
			diagonalCells[i] = new Cell(valueOf(board.charAt(indexes[i])));
		}
	
		return diagonalCells;
	}
	
	private List<Row> getVerticleRows(){
		List<Row> rows = new ArrayList<Row>();
		for (int verticleIndex = 0; verticleIndex < Processor.GRID_DIMENSION; verticleIndex++) {
			Cell[] cells = new Cell[Processor.GRID_DIMENSION];
			
			int offset = 0;
			for (int fieldIndex = 0; fieldIndex < Processor.GRID_DIMENSION; fieldIndex++) {
				cells[fieldIndex] = new Cell(valueOf(board.charAt(fieldIndex + (verticleIndex+offset) )));
				offset+=(Processor.GRID_DIMENSION - 1);
			}

			rows.add(new Row(cells));
		}
		return rows;
	}
	
	private List<Row> getHorizontalRows(){
		List<Row> rows = new ArrayList<Row>();
		for (int horizontalIndex = 0; horizontalIndex < Processor.GRID_DIMENSION; horizontalIndex++) {
			Cell[] cells = new Cell[Processor.GRID_DIMENSION];

			for (int fieldIndex = 0; fieldIndex < Processor.GRID_DIMENSION; fieldIndex++) {
				cells[fieldIndex] = new Cell(valueOf(board.charAt(fieldIndex + horizontalIndex)));
			}

			rows.add(new Row(cells));
		}
		return rows;
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

	public int potentialWinningMove(String symbol) {
		RowProcessor horizontalRows = new RowProcessor(board);
		ColumnProcessor columnProcessor = new ColumnProcessor(board);
		DiagonalProcessor diagonals = new DiagonalProcessor(board);

		return potentialWinningPosition(
				horizontalRows.potentialWinningMove(symbol),
				columnProcessor.potentialWinningMove(symbol),
				diagonals.potentialWinningMove(symbol));
	}

	private int potentialWinningPosition(int... potentialWinningPositions) {
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
		if (board.charAt(0) == EMPTY_CELL)
			return TOP_LEFT_CORNER;
		else if (board.charAt(2) == EMPTY_CELL)
			return TOP_RIGHT_CORNER;
		else if (board.charAt(6) == EMPTY_CELL)
			return BOTTOM_LEFT_CORNER;
		else if (board.charAt(8) == EMPTY_CELL)
			return BOTTOM_RIGHT_CORNER;
		else
			return NO_MATCH_FOUND;
	}

	public int getCentreCell() {
		return CENTER_CELL;
	}

	public int getFirstFreeCell() {
		return board.indexOf('-');
	}

}
