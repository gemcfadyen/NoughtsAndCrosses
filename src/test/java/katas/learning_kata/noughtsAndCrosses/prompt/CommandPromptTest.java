package katas.learning_kata.noughtsAndCrosses.prompt;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
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
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandPromptTest {
	@Mock private Grid grid;

	@Test
	public void shouldReadTheMoveFromTheCommandPrompt() {
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("1"), getStringWriter());
		assertThat(prompt.readNextMove(), equalTo("1"));
	}
	
	@Test
	public void shouldReadTheDoubleFigureMoveFromTheCommandPrompt(){
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("11"), getStringWriter());
		assertThat(prompt.readNextMove(), equalTo("11"));
	}
	
	@Test
	public void shouldReadTheMoveEvenIfItIsNotANumberFromTheCommandPrompt() {
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("z"), getStringWriter());
		assertThat(prompt.readNextMove(), equalTo("z"));
	}

	@Test
	public void shouldDisplayA3By3Board() {
		when(grid.getHorizontalRows()).thenReturn( horizontalRowsRepresentingAGridOfDimension3());
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		
		prompt.displayBoard(grid.getHorizontalRows());
		
		assertThat(outputWriter.toString(), equalTo("xxx    0 1 2 \nxxx    3 4 5 \noxo    6 7 8 \n"));

	}
	
	@Test
	public void shouldDisplayA5By5Board(){
		when(grid.getHorizontalRows()).thenReturn( horizontalRowsRepresentingAGridOfDimension5());
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		
		prompt.displayBoard(grid.getHorizontalRows());
		
		assertThat(outputWriter.toString(), equalTo("xxxxx    0 1 2 3 4 \nxxxxx    5 6 7 8 9 \noxooo    10 11 12 13 14 \noxooo    15 16 17 18 19 \noxooo    20 21 22 23 24 \n"));
	}
	
	@Test
	public void shouldPromptTheUserToTakeAGo(){
		when(grid.toString()).thenReturn("xxx\nxxx\noxo\n");
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("1"), outputWriter);
		
		prompt.promptUser();
		
		assertThat(outputWriter.toString(), equalTo("Enter the index of your next move:\n"));
	}
	
	@Test
	public void shouldIgnoreNewLineCharacters(){
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("1\n2"), getStringWriter());
		
		String firstValue = prompt.readNextMove();
		String secondValue = prompt.readNextMove();
		
		assertThat(firstValue, is("1"));
		assertThat(secondValue, is("2"));
	}
	
	@Test
	public void shouldPrintALoosingStatementIfNoPlayerHasWonTheGame(){
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		
		prompt.printGameOverStatement(null);
		
		assertThat(outputWriter.toString(), is("NO_WINNER Game Over, there was no winner! \n Game Over"));
		
	}
	
	@Test
	public void shouldPrintAWinningStatementIfAPlayerHasWonTheGame() {
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		prompt.printGameOverStatement(ValidSymbol.X);
		
		assertThat(outputWriter.toString(), is("Congratulations [X] you have won! \n Game Over"));
	}
	
	@Test
	public void shouldPromptTheUserToEnterAGridDimension(){
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		
		prompt.promptForGridDimension();
		
		assertThat(outputWriter.toString(), is("Please enter the grid dimension"));
	}
	
	@Test
	public void shouldReadInTheGridDimesionEnteredByThePlayer(){
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("4"), getStringWriter());
		assertThat(prompt.readGridDimension(), is("4"));
	}
	
	@Test
	public void shouldPromptPlayerToChooseOpponent(){
		StringWriter outputWriter = getStringWriter();
		Prompt prompt = initialisePromptWith(getInputReaderWithContent(""), outputWriter);
		
		prompt.promptForChoiceOfOpponent();
		
		assertThat(outputWriter.toString(), is("Enter 'h' to play against another human, Enter 'c' to play against the computer"));
	}
	
	@Test
	public void shouldReadInPlayersChoiceOfOpponent(){
		Prompt prompt = initialisePromptWith(getInputReaderWithContent("h"), getStringWriter());
		assertThat(prompt.readChoiceOfOpponent(), is("h"));
	}
	
	private Prompt initialisePromptWith(Reader reader, StringWriter writer){
		CommandPrompt prompt = new CommandPrompt(reader, writer);
		return prompt;
	}
	
	private Reader getInputReaderWithContent(String dataReadIn){
		return new StringReader(dataReadIn);
	}
	
	private StringWriter getStringWriter(){
		return new StringWriter();
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

}
