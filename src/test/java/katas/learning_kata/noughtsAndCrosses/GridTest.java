package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GridTest {
	@Test
	public void shouldReturnTrueIfTheCenterTaken() {
		Grid grid = new Grid("----x----");
		assertThat(grid.isCenterTaken(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfTheCenterIsNotTaken() {
		Grid grid = new Grid("---------");
		assertThat(grid.isCenterTaken(), is(false));
	}
	
//	@Test
//	public void shouldReturnTrueIfThereIsAWinningRow(){
//		Grid grid = new Grid("xxx------");
//		assertThat(grid.hasWinningRow(), is(true));
//	}
	
	@Test 
	public void shouldWriteTheGridOut(){
		Grid grid = new Grid("---xxx---");
		assertThat(grid.toString(), is("---\nxxx\n---\n"));
	}
	
	@Test
	public void shouldReturnTrueIfThereIsAFreeSlotInTheGrid(){
		Grid grid = new Grid("---------");
		assertThat(grid.hasFreeSlot(), is(true));
	}
	
	@Test
	public void shouldReturnFalseIfThereAreNoFreeSlotsInTheGrid(){
		Grid grid = new Grid("xoxoxoxoo");
		assertThat(grid.hasFreeSlot(), is(false));
	}

}
