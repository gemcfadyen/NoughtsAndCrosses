package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class WinningMoveStrategyTest {
	private PlayersStrategy winningMoveStrategy;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		initMocks(this);
		winningMoveStrategy = new WinningMoveStrategy(X, grid);
	}
	
	@Test
	public void shouldReturnTheIndexOfAWinningMove() {
		when(grid.potentialWinningMove(X)).thenReturn(1);
		assertThat(winningMoveStrategy.move(), is(1));
	}
	
	@Test
	public void shouldReturnNoMoveWhenThereIsNoPotentialWinningMove() {
		when(grid.potentialWinningMove(X)).thenReturn(NO_MATCH_FOUND);
		assertThat(winningMoveStrategy.move(), is(NO_MATCH_FOUND));
	}
}
