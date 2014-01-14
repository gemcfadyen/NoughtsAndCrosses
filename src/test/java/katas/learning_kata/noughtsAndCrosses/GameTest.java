package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol.NO_SYMBOL;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;
import katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameTest {
	@Mock
	private Player playerX;
	@Mock
	private Player playerO;
	@Mock
	private Grid grid;
	@Mock private Prompt commandPrompt;
	@Mock private PlayerFactory playersFactory;
	@Mock private GridFactory gridFactory;

	@InjectMocks
	private Game noughtsAndCrosses = new Game();
	
	@Test
	public void gameShouldEndIfThereIsAWinningRowInTheGrid() {		
		when(commandPrompt.readChoiceOfOpponent()).thenReturn("c");
		when(commandPrompt.readGridDimension()).thenReturn(3);
		when(playersFactory.createOpponentPlayer("c", X, commandPrompt)).thenReturn(playerX);
		when(gridFactory.createGridWithDimension(3)).thenReturn(grid);
		when(grid.getWinningSymbol()).thenReturn(O);
		
		noughtsAndCrosses.play();

		verify(commandPrompt).printGameOverStatement(O);
	}

	@Test
	public void gameShouldEndIfThereAreNoFreeSlotsLeftInGrid() {
		when(commandPrompt.readChoiceOfOpponent()).thenReturn("c");
		when(commandPrompt.readGridDimension()).thenReturn(3);
		when(playersFactory.createOpponentPlayer("c", X, commandPrompt)).thenReturn(playerX);
		when(gridFactory.createGridWithDimension(3)).thenReturn(grid);
		when(grid.getWinningSymbol()).thenReturn(NO_SYMBOL);
		when(grid.hasFreeSlot()).thenReturn(false);

		noughtsAndCrosses.play();

		verify(commandPrompt).printGameOverStatement(NO_SYMBOL);
		verifyNoMoreInteractions(playerX);
		verifyNoMoreInteractions(playerO);
	} 

	@Test
	public void gameShouldEndIfNineGoesHaveTakenPlaceInA3By3GridAndThereIsNoWinningRow(){
		when(commandPrompt.readChoiceOfOpponent()).thenReturn("c");
		when(commandPrompt.readGridDimension()).thenReturn(3);
		when(playersFactory.createOpponentPlayer("c", X, commandPrompt)).thenReturn(playerX);
		when(gridFactory.createGridWithDimension(3)).thenReturn(grid);
		when(grid.getWinningSymbol()).thenReturn(InvalidSymbol.NO_SYMBOL);
		when(grid.hasFreeSlot()).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(true).thenReturn(true)
		.thenReturn(true).thenReturn(false);
		
		noughtsAndCrosses.play();
		
		verify(commandPrompt).printGameOverStatement(NO_SYMBOL);		
		verify(playerX, times(5)).takesGo(grid);
		verify(playerO, times(4)).takesGo(grid);
	}	
	
	@Test
	public void gameShouldRepromptUserIfTheySelectAnInvalidOptionForOpponent() {
		when(commandPrompt.readChoiceOfOpponent()).thenReturn("e").thenReturn("h");
		when(commandPrompt.readGridDimension()).thenReturn(3);
		when(playersFactory.createOpponentPlayer("h", X, commandPrompt)).thenReturn(playerX);
		when(gridFactory.createGridWithDimension(3)).thenReturn(grid);
		
		noughtsAndCrosses.play();
		
		verify(commandPrompt, times(2)).promptForChoiceOfOpponent();
	}
}
