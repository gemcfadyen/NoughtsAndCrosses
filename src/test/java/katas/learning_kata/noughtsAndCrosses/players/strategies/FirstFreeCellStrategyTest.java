package katas.learning_kata.noughtsAndCrosses.players.strategies;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FirstFreeCellStrategyTest {
	private PlayersStrategy firstFreeCellStrategy;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		initMocks(this);
		firstFreeCellStrategy = new FirstFreeCellStrategy(grid);
	}
	
	@Test
	public void shouldReturnIndexOfMoveWhenCornerStrategySuccesfullyEvaluate() {
		when(grid.getFirstFreeCell()).thenReturn(1);
		assertThat(firstFreeCellStrategy.move(), is(1));
	}
}
