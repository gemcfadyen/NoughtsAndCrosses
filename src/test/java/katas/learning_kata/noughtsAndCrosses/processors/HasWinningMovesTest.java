package katas.learning_kata.noughtsAndCrosses.processors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HasWinningMovesTest {

	private RowProcessor rowProcessor;
	private ColumnProcessor columnProcessor;
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningRowInTheGrid(){
		rowProcessor = new RowProcessor("xxx------");
		assertThat(rowProcessor.hasWinner(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNotAWinningRowInTheGrid(){
		rowProcessor = new RowProcessor("xx-------");
		assertThat(rowProcessor.hasWinner(), is(false));
	}
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningColumnInTheGrid(){
		columnProcessor = new ColumnProcessor("x--x--x--");
		assertThat(columnProcessor.hasWinner(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNotAWinningColumnInTheGrid(){
		columnProcessor = new ColumnProcessor("xxx------");
		assertThat(columnProcessor.hasWinner(), is(false));
	}
}
