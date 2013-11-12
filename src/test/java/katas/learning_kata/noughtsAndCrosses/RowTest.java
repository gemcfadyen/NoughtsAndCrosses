package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RowTest {
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningRow(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X);
		cells[1] = new Cell(X);
		cells[2] = new Cell(X);
				
		Row row = new Row(cells);
		
		assertThat(row.hasWinner(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfTheRowDoesNotContainAllTheSameSymbols(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X);
		cells[1] = new Cell(O);
		cells[2] = new Cell(X);
				
		Row row = new Row(cells);
		
		assertThat(row.hasWinner(), is(false));
	}
}
