package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.players.strategies.CornerStrategy;
import katas.learning_kata.noughtsAndCrosses.players.strategies.PlayersStrategy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CornerMoveStrategyTest {

	private PlayersStrategy cornerStrategy;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		initMocks(this);
		cornerStrategy = new CornerStrategy(grid);
	}
	
	@Test
	public void shouldReturnNoMoveWhenCornerStrategyDoesNotSuccesfullyEvaluate() {
		when(grid.getAvailableCorner()).thenReturn(NO_MATCH_FOUND);
		assertThat(cornerStrategy.move(), is(NO_MATCH_FOUND));
	}
	
	@Test
	public void shouldReturnIndexOfMoveWhenCornerStrategySuccesfullyEvaluate() {
		when(grid.getAvailableCorner()).thenReturn(1);
		assertThat(cornerStrategy.move(), is(1));
	}
}
