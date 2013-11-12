package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;

public class Row {
	private Cell[] cells;

	public Row(Cell[] cells) {
		this.cells = cells;
	}

	public boolean hasWinner() {
		String matchingSymbol = cells[0].getSymbol();

		for (Cell cell : cells) {
			System.out.println("Symbol is: " + matchingSymbol);
			System.out.println("cell.getSymbol() is: " + cell.getSymbol());
			
			if (cell.getSymbol().equals(valueOf(Grid.EMPTY_CELL)) || 
					!cell.getSymbol().equals(matchingSymbol)) {
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
}
