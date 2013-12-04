package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	public static final char EMPTY_CELL = '-';
	public static final int NO_MATCH_FOUND = -1;
	public static final int GRID_DIMENSION = 3;
	public static final int CENTER_CELL = (GRID_DIMENSION * GRID_DIMENSION )/2;
	public static final String O = "o";
	public static final String X = "x";
	public static final int TOP_LEFT_CORNER = 0;
	public static final int TOP_RIGHT_CORNER = GRID_DIMENSION - 1;
	public static final int BOTTOM_LEFT_CORNER = TOP_RIGHT_CORNER * GRID_DIMENSION;
	public static final int BOTTOM_RIGHT_CORNER = (GRID_DIMENSION * GRID_DIMENSION) - 1;
	private String board;

	public Grid(String board) {
		this.board = board;
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

	public List<Row> getHorizontalRows() {
		return generateRows(GRID_DIMENSION, 1);
	}

	private List<Row> generateRowsFromCurrentGrid() {
		List<Row> rows = new ArrayList<Row>();
		rows.addAll(getHorizontalRows());
		rows.addAll(getVerticalRows());
		rows.addAll(getDiagonalRowFromLeft());
		rows.addAll(getDiagonalFromRight());

		return rows;

	}

	private List<Row> getDiagonalFromRight() {
		return getDiagonalRows(TOP_RIGHT_CORNER, TOP_RIGHT_CORNER);
	}

	private List<Row> getDiagonalRowFromLeft() {
		return getDiagonalRows(0, CENTER_CELL);
	}

	private List<Row> getVerticalRows() {
		return generateRows(1, GRID_DIMENSION);
	}

	
	private List<Row> getDiagonalRows(int startingIndex, int cellIncrementor){
		List<Row> rows = new ArrayList<Row>();
		rows.add(new Row(constructCells(startingIndex, cellIncrementor)));
		return rows;
		
	}

	private Cell[] constructCells(int startingCell, int cellIncrementor){
		Cell[] cells = new Cell[GRID_DIMENSION];
		for (int cellPosition = startingCell, index = 0; 
				cellPosition < GRID_DIMENSION * GRID_DIMENSION  && index < GRID_DIMENSION; 
					cellPosition += cellIncrementor, index++) {
			cells[index] = new Cell(valueOf(board.charAt(cellPosition)), cellPosition);
		}
		
		return cells;
	}
	
	
	private List<Row> generateRows(int offsetIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		for (int offset = 0; offset < GRID_DIMENSION * offsetIncrementor; offset += offsetIncrementor) {
			Cell[] cells = constructCells(offset, cellIncrementor); 
			rows.add(new Row(cells));
		}
		return rows;
	}

	public Grid takeNextMove(String symbol, int index) {
		if (board.charAt(index) == EMPTY_CELL) {
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
		List<Row> gridRows = generateRowsFromCurrentGrid();
		for (Row row : gridRows) {
			if (row.hasPotentialWinner())
				return row.winningPosition();
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
		if (board.charAt(TOP_LEFT_CORNER) == EMPTY_CELL)
			return TOP_LEFT_CORNER;
		else if (board.charAt(TOP_RIGHT_CORNER) == EMPTY_CELL)
			return TOP_RIGHT_CORNER;
		else if (board.charAt(BOTTOM_LEFT_CORNER) == EMPTY_CELL)
			return BOTTOM_LEFT_CORNER;
		else if (board.charAt(BOTTOM_RIGHT_CORNER) == EMPTY_CELL)
			return BOTTOM_RIGHT_CORNER;
		else
			return NO_MATCH_FOUND;
	}

	public int getCentreCell() {
		return CENTER_CELL;
	}

	public int getFirstFreeCell() {
		return board.indexOf(EMPTY_CELL);
	}

}