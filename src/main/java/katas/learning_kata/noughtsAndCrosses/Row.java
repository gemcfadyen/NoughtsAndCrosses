package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;

public class Row {
	private Cell[] cells;

	public Row(Cell[] cells) {
		this.cells = cells;
	}

	public boolean hasWinner() {
		String matchingSymbol = cells[0].getSymbol();

		for (Cell cell : cells) {
			if (cell.getSymbol().equals(valueOf(Grid.EMPTY_CELL))
					|| !cell.getSymbol().equals(matchingSymbol)) {
				return false;
			}
		}
		return true;
	}

	public String winningSymbol() {
		if (hasWinner()) {
			return cells[0].getSymbol();
		}
		return valueOf(Grid.NO_MATCH_FOUND);
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
			String symbol = cell.getSymbol();
			if (symbol.equals(Grid.X)) {
				numberOfXs++;
			} else if (symbol.equals(Grid.O)) {
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
			if (cell.getSymbol().equals(valueOf(Grid.EMPTY_CELL))) {
				numberOfEmptySlots++;
			}
		}
		return numberOfEmptySlots;
	}

	public int winningPosition() {
		if (hasPotentialWinner()) {
			for (Cell cell : cells) {
				if (cell.getSymbol().equals(valueOf(Grid.EMPTY_CELL))) {
					return cell.getIndex();
				}
			}
		}

		return NO_MATCH_FOUND;
	}
}
