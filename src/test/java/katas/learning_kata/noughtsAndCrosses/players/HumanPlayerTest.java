package katas.learning_kata.noughtsAndCrosses.players;

import static org.mockito.Mockito.verify;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.players.HumanPlayer;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HumanPlayerTest {
	@Mock private Grid grid;
	@Mock private Prompt prompt;
	
	@Test
	public void shouldTakeTheChosenMoveFromThePrompt(){
		HumanPlayer human = new HumanPlayer("x", prompt);
		BDDMockito.given(prompt.readNextMove()).willReturn(1);
		
		human.takesGo(grid);
		
		verify(grid).takeNextMove(human.getSymbol(), 1);
	}
	
	@Test
	public void shouldDisplayTheCurrentBoardWhenPromptingTheUserForNextMove(){
		HumanPlayer human = new HumanPlayer("x", prompt);
		
		human.takesGo(grid);
		
		verify(prompt).displayBoard(grid);
	}
}
