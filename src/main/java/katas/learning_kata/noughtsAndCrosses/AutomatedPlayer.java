package katas.learning_kata.noughtsAndCrosses;

public class AutomatedPlayer implements Player {
	private String symbol;
	private String name;

	public AutomatedPlayer(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}

	@Override
	public Grid takesGo(Grid grid) {
		String opponentsSymbol = (symbol.equals("x")) ? "o" : "x";
		int blockingIndex = grid.getIndexOfWinningMove(opponentsSymbol);
		if(grid.getIndexOfWinningMove(opponentsSymbol) != -1){
			return grid.takeNextMove(symbol, blockingIndex);
		}
		else{
			return null;
		}
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public String getName() {
		return name;
	}
}
