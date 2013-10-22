package katas.learning_kata.noughtsAndCrosses.prompt;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandPromptTest {
	@Mock private Grid grid;

	@Test
	public void shouldReadTheMoveFromTheCommandPrompt() {
		Reader inputReader = new StringReader("1");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);

		assertThat(prompt.readNextMove(), equalTo(1));
	}

	@Test
	public void shouldDisplayTheBoard() {
		when(grid.toString()).thenReturn("xxx\nxxx\noxo\n");
		Reader inputReader = new StringReader("1");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.displayBoard(grid);
		
		assertThat(outputWriter.toString(), equalTo("xxx\nxxx\noxo\n"));

	}
	
	@Test
	public void shouldPromptTheUserToTakeAGo(){
		when(grid.toString()).thenReturn("xxx\nxxx\noxo\n");
		Reader inputReader = new StringReader("1");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.promptUser();
		
		assertThat(outputWriter.toString(), equalTo("Enter the index of your next move:\n"));
	}
	
	@Test
	public void shouldIgnoreNewLineCharacters(){
		Reader inputReader = new StringReader("1\n2");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		int firstValue = prompt.readNextMove();
		int secondValue = prompt.readNextMove();
		
		assertThat(firstValue, is(1));
		assertThat(secondValue, is(2));
		
		
	}
}
