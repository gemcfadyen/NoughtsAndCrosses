package katas.learning_kata.noughtsAndCrosses.players;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Symbol.O;
import static katas.learning_kata.noughtsAndCrosses.Symbol.X;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Symbol;

public class AutomatedPlayer implements Player {
	private Symbol symbol;

	public AutomatedPlayer(Symbol symbol) {
		this.symbol = symbol;
	}

	@Override
	public Grid takesGo(Grid grid) {
		int indexOfNextMove = getIndexOfNextMove(grid);
		return grid.updateGridWith(symbol, indexOfNextMove);

	}
	
	
	private int getIndexOfNextMove(Grid grid){
		return automatedPlayersNextMove(indexOfWinningMoveFor(symbol, grid), 
										indexOfWinningMoveFor(opponent(), grid), 
										centerCell(grid), 
										getAvailableCorner(grid),
										anyFreePosition(grid));
	}

	private int anyFreePosition(Grid grid) {
		return grid.getFirstFreeCell();
	}
	
	private int getAvailableCorner(Grid grid) {
		return grid.getAvailableCorner();
	}
	
	private int centerCell(Grid grid) {
		if(!grid.isCenterTaken())
			return grid.getCenterCell();
		else
			return NO_MATCH_FOUND;
	}
	
	private int automatedPlayersNextMove(int... potentialMoves){
		for(int move : potentialMoves){
			if(move != NO_MATCH_FOUND){
				return move;
			}
		}
		return NO_MATCH_FOUND;
	}
	
	private int indexOfWinningMoveFor(Symbol symbol, Grid grid){
		int potentialWinningMove = grid.potentialWinningMove(symbol);
			return potentialWinningMove;
	}
	
	public Symbol opponent() {
		return (symbol.equals(X)) ? O : X;
	}

	@Override
	public Symbol getSymbol() {
		return symbol;
	}
}
