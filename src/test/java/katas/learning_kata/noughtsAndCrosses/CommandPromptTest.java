package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

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
		when(grid.toString()).thenReturn("xxx\nxxx\noxo");
		Reader inputReader = new StringReader("1");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.displayBoard(grid);
		
		assertThat(outputWriter.toString(), equalTo("xxx\nxxx\noxo"));

	}
}
