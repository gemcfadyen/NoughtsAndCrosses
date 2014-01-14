package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class BlockingMoveStrategyTest {
	private PlayersStrategy blockingMoveStrategy;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		initMocks(this);
		blockingMoveStrategy = new BlockingMoveStrategy(X, grid);
	}
	
	@Test
	public void shouldReturnTheIndexOfAMoveToBlockYourOpponent() {
		when(grid.potentialWinningMove(O)).thenReturn(1);
		assertThat(blockingMoveStrategy.move(), is(1));
	}
	
	@Test
	public void shouldReturnNoMoveWhenThereIsNoPotentialBlockingMove() {
		when(grid.potentialWinningMove(O)).thenReturn(NO_MATCH_FOUND);
		assertThat(blockingMoveStrategy.move(), is(NO_MATCH_FOUND));
	}
}
