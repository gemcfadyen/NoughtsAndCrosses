package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	public static final char EMPTY_CELL = '-';
	public static final int CENTER_CELL = 4;
	public static final int NO_MATCH_FOUND = -1;
	public static final int GRID_DIMENSION = 3;
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
		StringBuffer boardToPrint =  new StringBuffer();
		boardToPrint.append(board.substring(0, 3));
		boardToPrint.append("       0 1 2");
		boardToPrint.append("\n");
		boardToPrint.append(board.substring(3, 6));
		boardToPrint.append("       3 4 5");
		boardToPrint.append("\n");
		boardToPrint.append(board.substring(6, 9));
		boardToPrint.append("       6 7 8");
		boardToPrint.append("\n");
		boardToPrint.append("\n");
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
		rows.addAll(getHorizontalRows(3,1));
		rows.addAll(getRows(1,3));  
		rows.addAll(getDiagonalRows(4,4));
		rows.addAll(getDiagonalForwardsRows(2,2));
		//TODO Ideally want them to share a generic method getRow(x,y)
		
		return rows;

	}

	

	private List<Row> getDiagonalRows() {
		List<Row> rows = new ArrayList<Row>();

		rows.add(new Row(getDiagonalRow(TOP_LEFT_CORNER, CENTER_CELL,
				BOTTOM_RIGHT_CORNER)));
		rows.add(new Row(getDiagonalRow(TOP_RIGHT_CORNER, CENTER_CELL,
				BOTTOM_LEFT_CORNER)));

		return rows;
	}

	private Cell[] getDiagonalRow(int... indexes) {
		Cell[] diagonalCells = new Cell[GRID_DIMENSION];

		for (int i = 0; i < diagonalCells.length; i++) {
			diagonalCells[i] = new Cell(valueOf(board.charAt(indexes[i])),
					indexes[i]);
		}

		return diagonalCells;
	}

	//TODO All the getxxxRowS methods are identical apart from the generate Row method..
	private List<Row> getHorizontalRows(int rowIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		int offset = 0;
		for (int i = offset; offset < GRID_DIMENSION * rowIncrementor ; offset += rowIncrementor) {
			rows.add(generateHorizontalRow(offset, cellIncrementor));
		}

		return rows;
	}
	
	private List<Row> getRows(int rowIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		int offset = 0;
		for (int i = offset; offset < GRID_DIMENSION * rowIncrementor; offset += rowIncrementor) {
			rows.add(generateVerticleRow(offset, cellIncrementor));
		}

		return rows;
	}
	
	private List<Row> getDiagonalRows(int rowIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		int offset = 0;
		for (int i = offset; offset < GRID_DIMENSION  ; offset += rowIncrementor) {
			rows.add(generateDiagonalRow(offset, cellIncrementor));
		}

		return rows;
	}
	
	private List<Row> getDiagonalForwardsRows(int rowIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		int offset = 0;
		for (int i = offset; offset < GRID_DIMENSION  ; offset += rowIncrementor) {
			rows.add(generateDiagonalForwardsRow(offset, cellIncrementor));
		}

		return rows;
	}
	
	//TODO The for clause in each method is different, therefore I am finding it hard to spot a generalisation here.
	
	//    \ Diagonal
	private Row generateDiagonalRow(int offset, int cellIncrementor) {
		Cell[] row = new Cell[GRID_DIMENSION];
		for (int index = offset, i=0; index < GRID_DIMENSION * GRID_DIMENSION; index+=cellIncrementor, i++) {
			row[i] = new Cell(valueOf(board.charAt(index)),	index);
		}
		return new Row(row);
	}
	
	//  / diagonal
	private Row generateDiagonalForwardsRow(int offset, int cellIncrementor) {
		Cell[] row = new Cell[GRID_DIMENSION];
		for (int index = cellIncrementor, i=0; index < GRID_DIMENSION * GRID_DIMENSION-1; index+=cellIncrementor, i++) {
			row[i] = new Cell(valueOf(board.charAt(index)),	index);
		}
		return new Row(row);
	}
	
	private Row generateHorizontalRow(int offset, int cellIncrementor) {
		Cell[] row = new Cell[GRID_DIMENSION];
		for (int index = offset; index < GRID_DIMENSION + (offset * cellIncrementor); index+=cellIncrementor) {
			row[index - offset] = new Cell(valueOf(board.charAt(index)),	index);
		}
		return new Row(row);
	}
	
	private Row generateVerticleRow(int offset, int cellIncrementor) {
		Cell[] row = new Cell[GRID_DIMENSION]; 
		for (int index = offset,  i=0; index < GRID_DIMENSION +  (cellIncrementor + offset + 1 ) ;  index+=cellIncrementor, i++) {
		    row[i] = new Cell(valueOf(board.charAt(index)),	index);
		}
		return new Row(row);
	}
	
	private List<Row> getVerticleRows() {
		
		List<Row> rows = new ArrayList<Row>();
		
		for (int verticleIndex = 0; verticleIndex < GRID_DIMENSION; verticleIndex++) {
			Cell[] cells = new Cell[GRID_DIMENSION];
			int offset = 0;
			for (int fieldIndex = 0; fieldIndex < GRID_DIMENSION; fieldIndex++) {
				int cellIndex = fieldIndex + (verticleIndex + offset);
				cells[fieldIndex] = new Cell(valueOf(board.charAt(cellIndex)), cellIndex);
				offset += (GRID_DIMENSION - 1);
			}
			rows.add(new Row(cells));
		}
		return rows;
	}

	private List<Row> getHorizontalRows() {
		List<Row> rows = new ArrayList<Row>();
		for (int horizontalIndex = 0; horizontalIndex < GRID_DIMENSION; horizontalIndex++) {
			Cell[] cells = new Cell[GRID_DIMENSION];

			for (int fieldIndex = 0; fieldIndex < GRID_DIMENSION; fieldIndex++) {
				int cellIndex = fieldIndex + horizontalIndex;
				cells[fieldIndex] = new Cell(valueOf(board.charAt(cellIndex)),
						cellIndex);
			}

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
		return board.indexOf(EMPTY_CELL);
	}

}
