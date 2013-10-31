package katas.learning_kata.noughtsAndCrosses.processors;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class HasWinningMovesTest {
	private RowProcessor rowProcessor;
	private ColumnProcessor columnProcessor;
	private DiagonalProcessor diagonalProcessor;
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningRowInTheGrid(){
		rowProcessor = new RowProcessor("xxx------");
		assertThat(rowProcessor.getWinningSymbol(), is(X));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNotAWinningRowInTheGrid(){
		rowProcessor = new RowProcessor("xx-------");
		assertThat(rowProcessor.getWinningSymbol(), is(valueOf(NO_MATCH_FOUND)));
	}
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningColumnInTheGrid(){
		columnProcessor = new ColumnProcessor("x--x--x--");
		assertThat(columnProcessor.getWinningSymbol(), is(X));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNotAWinningColumnInTheGrid(){
		columnProcessor = new ColumnProcessor("xxx------");
		assertThat(columnProcessor.getWinningSymbol(), is(valueOf(NO_MATCH_FOUND)));
	}
	
	@Test
	public void shouldReturnTrueIfThereIsABackSlashDiagonalWinningRow(){
		diagonalProcessor = new DiagonalProcessor("x---x---x");
		assertThat(diagonalProcessor.getWinningSymbol(), is(X));
	}
	
	@Test
	public void shouldReturnTrueIfThereIsAForwardSlashDiagonalWinningRow(){
		diagonalProcessor = new DiagonalProcessor("--o-o-o--");
		assertThat(diagonalProcessor.getWinningSymbol(), is(O));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNoDiagonalWinningRow(){
		diagonalProcessor = new DiagonalProcessor("---xx----");
		assertThat(diagonalProcessor.getWinningSymbol(), is(valueOf(NO_MATCH_FOUND)));
	}
}
