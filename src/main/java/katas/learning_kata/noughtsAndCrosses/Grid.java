package katas.learning_kata.noughtsAndCrosses;

public class Grid {
	private String board;

	public Grid(String board) {
		this.board = board;
	}

	public boolean hasWinningRow() {
		return false;
	}
	
	public String toString(){
		StringBuffer boardToPrint= new StringBuffer(board.substring(0, 3) + "\n" +
													board.substring(3, 6) + "\n" +
													board.substring(6, 9) + "\n");
		
		return boardToPrint.toString();
	}

	public boolean hasFreeSlot() {
		return board.contains("-");
	}

	public String getWinningSymbol() {
		return null;
	}

}
