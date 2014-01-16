package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.players.strategies.BlockingMoveStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.CenterMoveStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.CornerStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.FirstFreeCellStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.PlayersStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.WinningMoveStrategy;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public class AutomatedPlayer implements Player {
	private ValidSymbol symbol;

	public AutomatedPlayer(ValidSymbol symbol) {
		this.symbol = symbol;
	}

	@Override
	public Grid takesGo(Grid grid) {
		int indexOfNextMove = getIndexOfNextMove(grid);
		return grid.updateGridWith(symbol, indexOfNextMove);

	}

	private int getIndexOfNextMove(Grid grid) {
		PlayersStrategy[] strategies = prioritisedGamePlan(grid);
		
		int nextMove = Grid.NO_MATCH_FOUND;
		for (int i = 0; i < strategies.length; i++) {
			nextMove = strategies[i].move();
			
			if(nextMove != Grid.NO_MATCH_FOUND)
				return nextMove;
		}
		return nextMove;
	}

	private PlayersStrategy[] prioritisedGamePlan(Grid grid) {
		return new PlayersStrategy[] {   new WinningMoveStrategy(symbol, grid),
										 new BlockingMoveStrategy(symbol, grid),
         								 new CenterMoveStrategy(grid), 
         								 new CornerStrategy(grid),
										 new FirstFreeCellStrategy(grid)
									   };
	}

}
