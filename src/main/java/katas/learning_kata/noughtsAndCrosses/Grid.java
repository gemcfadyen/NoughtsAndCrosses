package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;

import java.util.ArrayList;
import java.util.List;

import katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol;
import katas.learning_kata.noughtsAndCrosses.symbols.Symbol;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

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

	public List<Row> getHorizontalRows() {
		return generateRows(dimension, 1);
	}

	public boolean hasFreeSlot() {
		return contains(EMPTY);
	}

	public Grid updateGridWith(ValidSymbol symbol, int index) {
		board = board.substring(0, index) + symbol
				+ board.substring(index + 1, board.length());
		return this;
	}

	public boolean isEmptyCellAt(int index) {
		return hasACellAt(index) &&  board.charAt(index) == charValueOf(EMPTY);
	}

	private String initialiseBoard(int dimension) {
		StringBuffer initialiseGrid = new StringBuffer();

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				initialiseGrid.append(EMPTY.getCharValue());
			}

		}
		return initialiseGrid.toString();
	}

	private List<Row> generateRowsFromCurrentGrid() {
		List<Row> rows = new ArrayList<Row>();
		rows.addAll(getHorizontalRows());
		rows.addAll(getVerticalRows());
		rows.addAll(getDiagonalRowFromLeft());
		rows.addAll(getDiagonalFromRight());

		return rows;

	}

	private List<Row> generateRows(int offsetIncrementor, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		for (int offset = 0; offset < dimension * offsetIncrementor; offset += offsetIncrementor) {
			Cell[] cells = constructCells(offset, cellIncrementor);
			rows.add(new Row(cells));
		}
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

	private List<Row> getDiagonalRows(int startingIndex, int cellIncrementor) {
		List<Row> rows = new ArrayList<Row>();
		rows.add(new Row(constructCells(startingIndex, cellIncrementor)));
		return rows;

	}

	private Cell[] constructCells(int startingCell, int cellIncrementor) {
		Cell[] cells = new Cell[dimension];
		for (int cellPosition = startingCell, index = 0; 
				cellPosition < dimension * dimension && index < dimension; 
					cellPosition += cellIncrementor, index++) {
			cells[index] = new Cell(getSymbolAt(cellPosition), cellPosition);
		}

		return cells;
	}

	private boolean contains(ValidSymbol symbol) {
		return board.indexOf(charValueOf(symbol)) > NO_MATCH_FOUND;
	}

	private ValidSymbol getSymbolAt(int cellPosition) {
		char charValueOfSymbol = board.charAt(cellPosition);
		return ValidSymbol.valueOfChar(charValueOfSymbol);
	}

	public boolean isCenterTaken() {
		return !(board.charAt(getCenterCell()) == charValueOf(EMPTY));
	}

	public int getAvailableCorner() {
		int[] corners = cornerIndexes(); 
		
		for (int i = 0; i < corners.length; i++) {
			if(isEmptyCellAt(corners[i])) {
				return corners[i];
			}
		}
		return Grid.NO_MATCH_FOUND;
	}

	private int[] cornerIndexes() {
		return new int[]{ topLeftCorner(), 
						  topRightCorner(), 
						  bottomLeftCorner(),	
						  bottomRightCorner()
						 };
	} 

	private int bottomRightCorner() {
		return (dimension * dimension) - 1;
	}

	private int bottomLeftCorner() {
		return topRightCorner() * dimension;
	}

	private int topRightCorner() {
		return dimension - 1;
	}

	private int topLeftCorner() {
		return 0;
	}

	public int getCenterCell() {
		return (dimension * dimension) / 2;
	}

	public int getFirstFreeCell() {
		return board.indexOf(charValueOf(EMPTY));
	}

	public Symbol getWinningSymbol() {
		List<Row> gridRows = generateRowsFromCurrentGrid();
		for (Row row : gridRows) {
			if (row.hasWinner())
				return row.winningSymbol();
		}

		return InvalidSymbol.NO_SYMBOL;
	}

	private char charValueOf(ValidSymbol symbol) {
		return symbol.getCharValue();
	}

	public int getIndexOfBlockingMove(ValidSymbol symbol) {
		ValidSymbol opponentsSymbol = (symbol == X) ? O : X;
		return potentialWinningMove(opponentsSymbol);
	}

	public int potentialWinningMove(ValidSymbol symbol) {
		List<Row> gridRows = generateRowsFromCurrentGrid();
		for (Row row : gridRows) {
			if (row.hasPotentialWinner())
				return row.winningPosition();
		}
		return NO_MATCH_FOUND;
	}
	
	private boolean hasACellAt(int index) {
		if(index < (dimension * dimension))
			return true;
		else
			return false;
	}
}