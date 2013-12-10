package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	public static final char EMPTY_CELL = '-';
	public static final int NO_MATCH_FOUND = -1;
//	public static final int GRID_DIMENSION = dimension;
//	public static final int CENTER_CELL = (GRID_DIMENSION * GRID_DIMENSION )/2;
	public static final String O = "o";
	public static final String X = "x";
//	public static final int TOP_LEFT_CORNER = 0;
//	public static final int TOP_RIGHT_CORNER = GRID_DIMENSION - 1;
//	public static final int BOTTOM_LEFT_CORNER = TOP_RIGHT_CORNER * GRID_DIMENSION;
//	public static final int BOTTOM_RIGHT_CORNER = (GRID_DIMENSION * GRID_DIMENSION) - 1;
	private int dimension;
	private String board;

	public Grid(int dimension, String board) {
		this.dimension = dimension;
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
		return generateRows(dimension, 1);
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
		return getDiagonalRows(topRightCorner(), topRightCorner());
	}

	private List<Row> getDiagonalRowFromLeft() {
		return getDiagonalRows(0, dimension + 1);
	}

	private List<Row> getVerticalRows() {
		return generateRows(1, dimension);
	}

	
	private List<Row> getDiagonalRows(int startingIndex, int cellIncrementor){
		List<Row> rows = new ArrayList<Row>();
		rows.add(new Row(constructCells(startingIndex, cellIncrementor)));
		return rows;
		
	}

	private Cell[] constructCells(int startingCell, int cellIncrementor){
		Cell[] cells = new Cell[dimension];
		for (int cellPosition = startingCell, index = 0; 
				cellPosition < dimension * dimension  && index < dimension; 
					cellPosition += cellIncrementor, index++) {
			cells[index] = new Cell(valueOf(board.charAt(cellPosition)), cellPosition);
		}
		
		return cells;
	}
	
	
	private List<Row> generateRows(int offsetIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		for (int offset = 0; offset < dimension * offsetIncrementor; offset += offsetIncrementor) {
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
		return !(board.charAt(getCentreCell()) == EMPTY_CELL);
	}

	public boolean hasFreeCornerPosition() {
		return getAvailableCorner() != NO_MATCH_FOUND;
	}

	public int getAvailableCorner() {
		if (board.charAt(topLeftCorner()) == EMPTY_CELL)
			return topLeftCorner();
		else if (board.charAt(topRightCorner()) == EMPTY_CELL)
			return topRightCorner();
		else if (board.charAt(bottomLeftCorner()) == EMPTY_CELL)
			return bottomLeftCorner();
		else if (board.charAt(bottomRightCorner()) == EMPTY_CELL)
			return bottomRightCorner();
		else
			return NO_MATCH_FOUND;
	}
	
	private int bottomRightCorner(){
		return (dimension * dimension) - 1;
	}
	
	private int bottomLeftCorner(){
		return topRightCorner() * dimension;
	}
	
	private int topRightCorner(){
		 return dimension - 1;
	}
	
	private int topLeftCorner(){
		return 0;
	}

	public int getCentreCell() {
		return (dimension * dimension)/2;
	}

	public int getFirstFreeCell() {
		return board.indexOf(EMPTY_CELL);
	}
	

}