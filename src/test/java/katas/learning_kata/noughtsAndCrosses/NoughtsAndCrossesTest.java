package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Symbol.O;
import static katas.learning_kata.noughtsAndCrosses.Symbol.X;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

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
	@Mock 
	private Prompt commandPrompt;

	@InjectMocks
	private NoughtsAndCrosses noughtsAndCrosses = new NoughtsAndCrosses();

	@Test
	public void gameShouldEndIfThereIsAWinningRowInTheGrid() {
		when(grid.getWinningSymbol()).thenReturn(O);
		when(grid.getWinningSymbol()).thenReturn(O);
		when(grid.toString()).thenReturn("ooo\n---\n---");
		when(playerO.getSymbol()).thenReturn(O);
		when(playerX.getSymbol()).thenReturn(X);
		
		noughtsAndCrosses.playGame();

		verify(commandPrompt).printWinningStatement(O);
	}

	@Test
	public void gameShouldEndIfThereAreNoFreeSlotsLeftInGrid() {
		when(grid.getWinningSymbol()).thenReturn(null);
		when(grid.hasFreeSlot()).thenReturn(false);

		noughtsAndCrosses.playGame();

		verify(commandPrompt).printLoosingStatement();
		verifyNoMoreInteractions(playerX);
		verifyNoMoreInteractions(playerO);
	}

	@Test
	public void gameShouldEndIfNineGoesHaveTakenPlaceAndThereIsNoWinningRow(){
		when(grid.getWinningSymbol()).thenReturn(null);
		when(grid.hasFreeSlot()).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(false);
		
		noughtsAndCrosses.playGame();
		
		verify(commandPrompt).printLoosingStatement();		
		verify(playerX, times(5)).takesGo(grid);
		verify(playerO, times(4)).takesGo(grid);
	}
	
	@Test
	public void gameShouldAnnounceTheWinner(){
		when(grid.getWinningSymbol()).thenReturn(null).thenReturn(null);
		when(grid.hasFreeSlot()).thenReturn(true);
		when(grid.getWinningSymbol()).thenReturn(X);
		when(grid.toString()).thenReturn("xxx\n---\n---");
		when(playerX.getSymbol()).thenReturn(X);
		when(playerX.takesGo(grid)).thenReturn(new Grid(3, "xxx------"));
		
		noughtsAndCrosses.playGame();
	
		verify(commandPrompt).printWinningStatement(X);
	}
}
