package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoughtsAndCrossesTest {
	@Mock
	private Player playerX;
	@Mock
	private Player playerO;
	@Mock
	private Grid grid;

	@InjectMocks
	private NoughtsAndCrosses noughtsAndCrosses = new NoughtsAndCrosses();

	@Test
	public void gameShouldEndIfThereIsAWinningRowInTheGrid() {
		when(grid.hasWinningRow()).thenReturn(true);

		grid = noughtsAndCrosses.startGame();

		assertThat(grid.hasWinningRow(), is(true));
		verifyNoMoreInteractions(playerX);
		verifyNoMoreInteractions(playerO);
	}

	@Test
	public void gameShouldEndIfThereAreNoFreeSlotsLeftInGrid() {
		when(grid.hasWinningRow()).thenReturn(false);
		when(grid.hasFreeSlot()).thenReturn(false);

		grid = noughtsAndCrosses.startGame();

		assertThat(grid.hasWinningRow(), is(false));
		assertThat(grid.hasFreeSlot(), is(false));
		verifyNoMoreInteractions(playerX);
		verifyNoMoreInteractions(playerO);
	}

	@Test
	public void gameShouldEndIfNineGoesHaveTakenPlaceAndThereIsNoWinningRow(){
		when(grid.hasWinningRow()).thenReturn(false);
		when(grid.hasFreeSlot()).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(false);
		
		grid = noughtsAndCrosses.startGame();
		
		assertThat(grid.hasFreeSlot(), is(false));		
		verify(playerX, times(5)).takesGo(grid);
		verify(playerO, times(4)).takesGo(grid);
	}
}
