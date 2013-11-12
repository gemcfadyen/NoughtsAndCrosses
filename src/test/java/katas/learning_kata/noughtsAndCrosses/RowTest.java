package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RowTest {
	
	@Test
	public void shouldReturnTrueIfThereIsAWinningRow(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X, 0);
		cells[1] = new Cell(X, 1);
		cells[2] = new Cell(X, 2);
				
		Row row = new Row(cells);
		
		assertThat(row.hasWinner(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfTheRowDoesNotContainAllTheSameSymbols(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X, 0);
		cells[1] = new Cell(O, 1);
		cells[2] = new Cell(X, 2);
				
		Row row = new Row(cells);
		
		assertThat(row.hasWinner(), is(false));
	}
	
	@Test
	public void shouldReturnTrueIfTheRowContainsTwoMatchingSymbolsAndAFreeSlot(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X, 0);
		cells[1] = new Cell(valueOf(Grid.EMPTY_CELL), 1);
		cells[2] = new Cell(X, 2);
				
		Row row = new Row(cells);
		
		assertThat(row.hasPotentialWinner(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfThereIsNoPotentialWinningMoveToBeMadeInTheRow(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X, 0);
		cells[1] = new Cell(O, 1);
		cells[2] = new Cell(X, 2);
		
		Row row = new Row(cells);
		
		assertThat(row.hasPotentialWinner(), is(false));
	}
	
	@Test
	public void shouldReturnTheIndexOfTheWinningMove(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(X, 0);
		cells[1] = new Cell(valueOf(Grid.EMPTY_CELL), 4);
		cells[2] = new Cell(X, 8);
				
		Row row = new Row(cells);
		
		assertThat(row.winningPosition(), is(4));
	}
	
	@Test
	public void shouldReturnNoMatchFoundWhenThereIsNoWinningMove(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(O, 0);
		cells[1] = new Cell(valueOf(Grid.EMPTY_CELL), 1);
		cells[2] = new Cell(X, 2);
				
		Row row = new Row(cells);
		
		assertThat(row.winningPosition(), is(NO_MATCH_FOUND));
	}
}
