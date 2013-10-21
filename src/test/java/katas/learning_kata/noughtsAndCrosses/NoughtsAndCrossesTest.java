package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import katas.learning_kata.noughtsAndCrosses.GameStatus.GameStates;
import katas.learning_kata.noughtsAndCrosses.players.Player;

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
		when(grid.getWinningSymbol()).thenReturn("o");
		when(grid.toString()).thenReturn("ooo\n---\n---");
		when(playerO.getSymbol()).thenReturn("o");
		when(playerX.getSymbol()).thenReturn("x");
		when(playerO.getName()).thenReturn("O-Man"); 
		
		GameStatus gameOverMessage =  noughtsAndCrosses.playGame();

		assertThat(gameOverMessage.getStatus(), is(GameStates.WINNER));		
		assertThat(gameOverMessage.getMessage(), is("Congratulations [O-Man] you have won! \n [ooo\n---\n---]"));	
	}

	@Test
	public void gameShouldEndIfThereAreNoFreeSlotsLeftInGrid() {
		when(grid.hasWinningRow()).thenReturn(false);
		when(grid.hasFreeSlot()).thenReturn(false);

		GameStatus gameStatus =  noughtsAndCrosses.playGame();

		assertThat(gameStatus.getStatus(), is(GameStates.NO_WINNER));		
		assertThat(gameStatus.getMessage(), is("Game Over, there was no winner!"));	
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
		
		GameStatus gameStatus = noughtsAndCrosses.playGame();
		
		assertThat(gameStatus.getStatus(), is(GameStates.NO_WINNER));		
		assertThat(gameStatus.getMessage(), is("Game Over, there was no winner!"));		
		verify(playerX, times(5)).takesGo(grid);
		verify(playerO, times(4)).takesGo(grid);
	}
	
	@Test
	public void gameShouldAnnounceTheWinner(){
		when(grid.hasWinningRow()).thenReturn(false).thenReturn(true);
		when(grid.hasFreeSlot()).thenReturn(true);
		when(grid.getWinningSymbol()).thenReturn("x");
		when(grid.toString()).thenReturn("xxx\n---\n---");
		when(playerX.getSymbol()).thenReturn("x");
		when(playerX.getName()).thenReturn("X-Man");
		when(playerX.takesGo(grid)).thenReturn(new Grid("xxx------"));
		
		GameStatus winningMessage = noughtsAndCrosses.playGame();
		assertThat(winningMessage.getStatus(), is(GameStates.WINNER));		
		assertThat(winningMessage.getMessage(), is("Congratulations [X-Man] you have won! \n [xxx\n---\n---]"));
	}
}
