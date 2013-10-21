package katas.learning_kata.noughtsAndCrosses.players;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class AutomatedPlayer implements Player {
	private String symbol;
	private String name;
	private Prompt commandPrompt;

	public AutomatedPlayer(String symbol, String name, Prompt prompt) {
		this.symbol = symbol;
		this.name = name;
		this.commandPrompt = prompt;
	}

	@Override
	public Grid takesGo(Grid grid) {
		commandPrompt.displayBoard(grid);
		
		int indexOfNextMove = getIndexOfNextMove(grid);
		return grid.takeNextMove(symbol, indexOfNextMove);

	}
	
	private int getIndexOfNextMove(Grid grid){		
		return automatedPlayersNextMove(indexOfWinningMoveFor(symbol, grid), 
										indexOfWinningMoveFor(opponent(), grid), 
										centerCell(grid), 
										cornerCell(grid),
										anyFreePosition(grid));
	}

	private int anyFreePosition(Grid grid) {
		return grid.getFirstFreeCell();
	}

	private int cornerCell(Grid grid) {
		if(grid.hasFreeCornerPosition())
			return grid.getAvailableCorner();
		else
			return NO_MATCH_FOUND;
	}

	private int centerCell(Grid grid) {
		if(!grid.isCenterTaken())
			return grid.getCentreCell();
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
	
	private int indexOfWinningMoveFor(String symbol, Grid grid){
		int potentialWinningMove = grid.potentialWinningMove(symbol);
		if(grid.isAWinningMoveAt(potentialWinningMove)){
			return potentialWinningMove;
		}
		return NO_MATCH_FOUND;
	}
	
	public String opponent() {
		return (symbol.equals(X)) ? O : X;
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
