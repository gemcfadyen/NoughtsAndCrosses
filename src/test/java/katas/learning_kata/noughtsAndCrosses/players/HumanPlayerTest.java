package katas.learning_kata.noughtsAndCrosses.players;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HumanPlayerTest {
	@Mock private Grid grid;
	@Mock private Prompt prompt;
	
	private HumanPlayer human;
	
	@Before
	public void setup() {
		 human = new HumanPlayer(X, prompt);
	}
	
	@Test
	public void shouldTakeTheChosenMoveFromThePrompt() {
		given(prompt.readNextMove()).willReturn("1");
		given(grid.isEmptyCellAt(1)).willReturn(true);
		
		human.takesGo(grid);
		
		verify(grid).updateGridWith(X, 1);
	}
	
	@Test
	public void shouldDisplayTheCurrentBoardWhenPromptingTheUserForNextMove() {
		given(prompt.readNextMove()).willReturn("1");
		given(grid.isEmptyCellAt(1)).willReturn(true);
		
		human.takesGo(grid);
		
		verify(prompt).displayBoard(grid.getHorizontalRows());
	}
	
	@Test
	public void shouldRepromptUserForAnotherChoiceIfCellTheyChooseIsAlreadyOccupied() {
		given(prompt.readNextMove()).willReturn("1").willReturn("2");
		given(grid.isEmptyCellAt(1)).willReturn(false);
		given(grid.isEmptyCellAt(2)).willReturn(true);
		
		
		human.takesGo(grid);
		
		verify(prompt, times(2)).promptUser();
	}
	
	@Test
	public void shouldRepromptTheUserIfTheyEnterANonNumericCellPosition() {
		given(prompt.readNextMove()).willReturn("a").willReturn("2");
		given(grid.isEmptyCellAt(2)).willReturn(true);
		
		human.takesGo(grid);
		
		verify(prompt, times(2)).promptUser();
	}
	
	@Test
	public void shouldRepromptTheUserUntilAValidCellPositionIsReceived() {
		given(prompt.readNextMove()).willReturn("a").willReturn("1").willReturn("2");
		given(grid.isEmptyCellAt(1)).willReturn(false);
		given(grid.isEmptyCellAt(2)).willReturn(true);
		
		human.takesGo(grid);
		
		verify(prompt, times(3)).promptUser();
	}
}
