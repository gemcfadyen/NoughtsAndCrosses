package katas.learning_kata.noughtsAndCrosses.prompt;

import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Cell;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Row;

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
	public void shouldReadTheDoubleFigureMoveFromTheCommandPrompt(){
		Reader inputReader = new StringReader("11");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);

		assertThat(prompt.readNextMove(), equalTo(11));
	}

	@Test
	public void shouldDisplayTheBoardUsingTheProvidedDisplay() {
		when(grid.getHorizontalRows()).thenReturn( horizontalRowsRepresentingAGridOfDimension3());
		Reader inputReader = new StringReader("");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.displayBoard(grid.getHorizontalRows());
		
		assertThat(outputWriter.toString(), equalTo("xxx    0 1 2 \nxxx    3 4 5 \noxo    6 7 8 \n"));

	}
	
	@Test
	public void shouldDisplayTheBoardWithADifferentDimensionUsingTheProvidedDisplay(){
		when(grid.getHorizontalRows()).thenReturn( horizontalRowsRepresentingAGridOfDimension5());
		Reader inputReader = new StringReader("");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.displayBoard(grid.getHorizontalRows());
		
		assertThat(outputWriter.toString(), equalTo("xxxxx    0 1 2 3 4 \nxxxxx    5 6 7 8 9 \noxooo    10 11 12 13 14 \noxooo    15 16 17 18 19 \noxooo    20 21 22 23 24 \n"));
	}

	private List<Row> horizontalRowsRepresentingAGridOfDimension5() {
		List<Row> horizontalRows = new ArrayList<Row>();
		Row rowOne = new Row(new Cell[]{new Cell(X, 0), new Cell(X, 1), new Cell(X, 2), new Cell(X, 3), new Cell(X, 4)});
		Row rowTwo = new Row(new Cell[]{new Cell(X, 5), new Cell(X, 6), new Cell(X, 7), new Cell(X, 8), new Cell(X, 9)});
		Row rowThree = new Row(new Cell[]{new Cell(O, 10), new Cell(X, 11), new Cell(O, 12),  new Cell(O, 13),  new Cell(O, 14)});
		Row rowFour = new Row(new Cell[]{new Cell(O, 10), new Cell(X, 11), new Cell(O, 12),  new Cell(O, 13),  new Cell(O, 14)});
		Row rowFive = new Row(new Cell[]{new Cell(O, 10), new Cell(X, 11), new Cell(O, 12),  new Cell(O, 13),  new Cell(O, 14)});
		horizontalRows.add(rowOne);
		horizontalRows.add(rowTwo);
		horizontalRows.add(rowThree);
		horizontalRows.add(rowFour);
		horizontalRows.add(rowFive);
		return horizontalRows;
	}

	private List<Row> horizontalRowsRepresentingAGridOfDimension3() {
		List<Row> horizontalRows = new ArrayList<Row>();
		Row topRow = new Row(new Cell[]{new Cell(X, 0), new Cell(X, 1), new Cell(X, 2)});
		Row middleRow = new Row(new Cell[]{new Cell(X, 3), new Cell(X, 4), new Cell(X, 5)});
		Row bottomRow = new Row(new Cell[]{new Cell(O, 6), new Cell(X, 7), new Cell(O, 8)});
		horizontalRows.add(topRow);
		horizontalRows.add(middleRow);
		horizontalRows.add(bottomRow);
		return horizontalRows;
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
	
	@Test
	public void shouldPrintALoosingStatementIfNoPlayerHasWonTheGame(){
		Reader inputReader = new StringReader("");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.printLoosingStatement();
		
		assertThat(outputWriter.toString(), is("NO_WINNER Game Over, there was no winner! \n Game Over"));
		
	}
	
	@Test
	public void shouldPrintAWinningStatementIfAPlayerHasWonTheGame(){
		Reader inputReader = new StringReader("");
		StringWriter outputWriter = new StringWriter();
		CommandPrompt prompt = new CommandPrompt(inputReader, outputWriter);
		
		prompt.printWinningStatement("x");
		
		assertThat(outputWriter.toString(), is("Congratulations [x] you have won! \n Game Over"));
		
	}
	
}
