package katas.learning_kata.noughtsAndCrosses;

import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class Cell {
	private ValidSymbol symbol;
	private int index;

	public Cell(ValidSymbol symbol, int index) {
		this.symbol = symbol;
		this.index = index;
	}

	public ValidSymbol getSymbol() {
		return symbol;
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (index != other.index)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

}
