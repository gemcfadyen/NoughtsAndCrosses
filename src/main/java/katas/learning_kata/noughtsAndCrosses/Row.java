package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol;
import katas.learning_kata.noughtsAndCrosses.symbols.Symbol;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class Row {
	private Cell[] cells;

	public Row(Cell[] cells) {
		this.cells = cells;
	}

	public boolean hasWinner() {
		ValidSymbol matchingSymbol = cells[0].getSymbol();

		for (Cell cell : cells) {
			if (cell.getSymbol().equals(EMPTY)
					|| !cell.getSymbol().equals(matchingSymbol)) {
				return false;
			}
		}
		return true;
	}

	public Symbol winningSymbol() {
		if (hasWinner()) {
			return cells[0].getSymbol();
		}
		return InvalidSymbol.NO_SYMBOL;
	}

	public boolean hasPotentialWinner() {
		if (numberOfEmptySlots() == 1 && allOccupiedCellsContainTheSameSymbol()) {
			return true;
		} else {
			return false;
		}
	}

	private boolean allOccupiedCellsContainTheSameSymbol() {
		int numberOfXs = 0;
		int numberOfOs = 0;

		for (Cell cell : cells) {
			Symbol symbol = cell.getSymbol();
			if (symbol.equals(X)) {
				numberOfXs++;
			} else if (symbol.equals(O)) {
				numberOfOs++;
			}
		}

		if (numberOfXs == 0 || numberOfOs == 0) {
			return true;
		} else {
			return false;
		}
	}

	private int numberOfEmptySlots() {
		int numberOfEmptySlots = 0;
		for (Cell cell : cells) {
			if (cell.getSymbol() == (EMPTY)) {
				numberOfEmptySlots++;
			}
		}
		return numberOfEmptySlots;
	}

	public int winningPosition() {
		if (hasPotentialWinner()) {
			for (Cell cell : cells) {
				if (cell.getSymbol().equals(EMPTY)) {
					return cell.getIndex();
				}
			}
		}

		return NO_MATCH_FOUND;
	}

	public Cell[] getCells() {
		return cells;
	}
}
