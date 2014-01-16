package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

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
		cells[1] = new Cell(EMPTY, 1);
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
		cells[1] = new Cell(EMPTY, 4);
		cells[2] = new Cell(X, 8);
				
		Row row = new Row(cells);
		
		assertThat(row.winningPosition(), is(4));
	}
	
	@Test
	public void shouldReturnNoMatchFoundWhenThereIsNoWinningMove(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(O, 0);
		cells[1] = new Cell(EMPTY, 1);
		cells[2] = new Cell(X, 2);
				
		Row row = new Row(cells);
		
		assertThat(row.winningPosition(), is(NO_MATCH_FOUND));
	}
	
	@Test
	public void shouldReturnTheWinningSymbolIfThereIsAWinningRow(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(O, 0);
		cells[1] = new Cell(O, 1);
		cells[2] = new Cell(O, 2);
		
		Row row = new Row(cells);
		assertThat(row.winningSymbol(), is(ValidSymbol.class));
		assertThat((ValidSymbol)row.winningSymbol(), is(O));
	}
	
	@Test
	public void shouldReturnAnInvalidSymbolIfThereIsNoWinningRow(){
		Cell[] cells = new Cell[3];
		cells[0] = new Cell(O, 0);
		cells[1] = new Cell(X, 1);
		cells[2] = new Cell(O, 2);
		
		Row row = new Row(cells);
		assertThat(row.winningSymbol(), nullValue());
	}
	
}
