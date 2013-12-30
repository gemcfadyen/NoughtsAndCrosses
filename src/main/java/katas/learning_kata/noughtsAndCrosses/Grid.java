package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Symbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.Symbol.O;
import static katas.learning_kata.noughtsAndCrosses.Symbol.X;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	public static final int NO_MATCH_FOUND = -1;

	private int dimension;
	private String board;

	public Grid(int dimension, String board) {
		this.dimension = dimension;
		this.board = board;
	}

	public Grid(int dimension) {
		this.dimension = dimension;
		this.board = initialiseBoard(dimension);
	}

	private String initialiseBoard(int dimension) {
		StringBuffer initialiseGrid = new StringBuffer();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				initialiseGrid.append(Symbol.EMPTY.getCharValue());
			}

		}
		return initialiseGrid.toString();
	}
	public boolean hasFreeSlot() {
		return contains(EMPTY);
	}
	
	private boolean contains(Symbol symbol) {
		return board.indexOf(charValueOf(symbol)) > -1;
	}

	public Symbol getWinningSymbol() {
		List<Row> gridRows = generateRowsFromCurrentGrid();
		for (Row row : gridRows) {
			if (row.hasWinner())
				return row.winningSymbol();
		}

		return null;//valueOf(NO_MATCH_FOUND);
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
			cells[index] = new Cell(getSymbolAt(cellPosition), cellPosition);
		}
		
		return cells;
	}
	
	
	private Symbol getSymbolAt(int cellPosition) {
		char charValueOfSymbol = board.charAt(cellPosition);
		return  Symbol.valueOfChar(charValueOfSymbol);
	}

	private List<Row> generateRows(int offsetIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		for (int offset = 0; offset < dimension * offsetIncrementor; offset += offsetIncrementor) {
			Cell[] cells = constructCells(offset, cellIncrementor); 
			rows.add(new Row(cells));
		}
		return rows;
	}

	public Grid takeNextMove(Symbol symbol, int index) {
		if (board.charAt(index) == charValueOf(EMPTY)) {
			board = board.substring(0, index) + symbol
					+ board.substring(index + 1, board.length());
		}

		return this;
	}

	private char charValueOf(Symbol symbol) {
		return symbol.getCharValue();
	}

	public int getIndexOfBlockingMove(Symbol symbol) {
		Symbol opponentsSymbol = (symbol == X) ? O : X;
		return potentialWinningMove(opponentsSymbol);
	}

	public int potentialWinningMove(Symbol symbol) {
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
		return !(board.charAt(getCentreCell()) == charValueOf(EMPTY));
	}

	public boolean hasFreeCornerPosition() {
		return getAvailableCorner() != NO_MATCH_FOUND;
	}

	public int getAvailableCorner() {
		if (board.charAt(topLeftCorner()) == charValueOf(EMPTY))
			return topLeftCorner();
		else if (board.charAt(topRightCorner()) == charValueOf(EMPTY))
			return topRightCorner();
		else if (board.charAt(bottomLeftCorner()) == charValueOf(EMPTY))
			return bottomLeftCorner();
		else if (board.charAt(bottomRightCorner()) == charValueOf(EMPTY))
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
		return board.indexOf(charValueOf(EMPTY));
	}
}