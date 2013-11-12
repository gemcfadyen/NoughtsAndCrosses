package katas.learning_kata.noughtsAndCrosses;

public class Cell {
	private String symbol;
	private int index;

	public Cell(String symbol, int index) {
		this.symbol = symbol;
		this.index = index;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public int getIndex() {
		return index;
	}

}
