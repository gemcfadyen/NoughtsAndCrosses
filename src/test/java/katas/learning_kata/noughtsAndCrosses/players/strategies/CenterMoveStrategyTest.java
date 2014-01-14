package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CenterMoveStrategyTest {
	private PlayersStrategy centerMoveStrategy;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		initMocks(this);
		centerMoveStrategy = new CenterMoveStrategy(grid);
	}
	
	@Test
	public void shouldReturnNoMoveWhenCornerStrategyDoesNotSuccesfullyEvaluate() {
		when(grid.getCenterCell()).thenReturn(NO_MATCH_FOUND);
		assertThat(centerMoveStrategy.move(), is(NO_MATCH_FOUND));
	}
	
	@Test
	public void shouldReturnIndexOfMoveWhenCornerStrategySuccesfullyEvaluate() {
		when(grid.getCenterCell()).thenReturn(1);
		assertThat(centerMoveStrategy.move(), is(1));
	}
}
