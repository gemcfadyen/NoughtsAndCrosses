package katas.learning_kata.noughtsAndCrosses;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.CENTER_CELL;
import static katas.learning_kata.noughtsAndCrosses.Grid.EMPTY_CELL;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

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

	@Test
	public void shouldReturnTheCentreCell() {
		Grid grid = new Grid("---------");
		assertThat(grid.getCentreCell(), is(CENTER_CELL));
	}

	@Test
	public void shouldReturnTrueIfTopLeftCornerIsFree() {
		Grid grid = new Grid("-xxxxxxxx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfTopRightCornerIsFree() {
		Grid grid = new Grid("xx-xxxxxx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfBottomRightCornerIsFree() {
		Grid grid = new Grid("xxxxxxxx-");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfBottomLeftCornerIsFree() {
		Grid grid = new Grid("xxxxxx-xx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnFalseIfNoCornerIsFree() {
		Grid grid = new Grid("x-x---x-x");
		assertThat(grid.hasFreeCornerPosition(), is(false));
	}


	@Test
	public void shouldWriteTheGridOut() {
		Grid grid = new Grid("---xxx---");
		
		List<Row> horizontalRows = grid.getHorizontalRows();

		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL))); 
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows),constructCellsFromPosition(3, X, X, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows),constructCellsFromPosition(6,  valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)));
	}

	@Test
	public void shouldReturnTrueIfThereIsAFreeSlotInTheGrid() {
		Grid grid = new Grid("---------");
		assertThat(grid.hasFreeSlot(), is(true));
	}

	@Test
	public void shouldReturnFalseIfThereAreNoFreeSlotsInTheGrid() {
		Grid grid = new Grid("xoxoxoxoo");
		assertThat(grid.hasFreeSlot(), is(false));
	}

	@Test
	public void shouldPlaceThePlayersSymbolAtTheSpecifiedIndexInTheGrid() {
		Grid grid = new Grid("---xox---");
		
		grid = grid.takeNextMove("o", 0);

		List<Row> horizontalRows = grid.getHorizontalRows();
	
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, O, valueOf(EMPTY_CELL),valueOf(EMPTY_CELL)));
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, X, O, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)));
	}

	@Test
	public void shouldNotPlaceThePlayersSymbolInAnOccupiedCell() {
		Grid grid = new Grid("---xox---");
		grid.takeNextMove("o", 3);

		List<Row> horizontalRows = grid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)));
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, X, O, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)));
	}

	@Test
	public void shouldReturnTheIndexOfTheCellUsedToBlockOpponentFromWinning() {
		Grid grid = new Grid("xx-------");
		int index = grid.getIndexOfBlockingMove("o");

		assertThat(index, is(2));
	}

	@Test
	public void shouldReturnNoMatchIfThereAreNoPotentialWinsForOpponent() {
		Grid grid = new Grid("o--------");
		int index = grid.getIndexOfBlockingMove("x");

		assertThat(index, is(-1));
	}

	@Test
	public void shouldReturnTrueIfTheIndexIsACellInTheGrid() {
		Grid grid = new Grid("---------");
		boolean isFree = grid.isACellInTheGrid(0);
		assertTrue(isFree);
	}

	@Test
	public void shouldReturnFalseIfTheIndexIsNoMatchFound() {
		Grid grid = new Grid("---------");
		boolean isFree = grid.isACellInTheGrid(NO_MATCH_FOUND);
		assertFalse(isFree);
	}

	@Test
	public void shouldReturnTheFirstFreeIndexOnTheBoard() {
		Grid grid = new Grid("---------");
		int index = grid.getFirstFreeCell();
		assertThat(index, is(0));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow() {
		Grid grid = new Grid("o-xxx-o--");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow2() {
		Grid grid = new Grid("o-o-o----");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow3() {
		Grid grid = new Grid("---o-o-o-");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow4() {
		Grid grid = new Grid("--o-xxx-o");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow5() {
		Grid grid = new Grid("--ooxxx-o"); // 0, 2, 8
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow6() {
		Grid grid = new Grid("o-xxx-oxo"); // 6, 8, 0
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow7() {
		Grid grid = new Grid("----x-x-x"); // 6, 8, 0
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("-1"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenTopHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid("xxx------");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("x"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid("---xxx---");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("x"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenBottomHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid("------xxx");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("x"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenLeftVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid("o--o--o--");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("o"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid("-o--o--o-");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("o"));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenRightVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid("--o--o--o");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("o"));
	}

	@Test
	public void shouldReturnTheSymbolOfADiagonalWinner() {
		Grid grid = new Grid("x---x---x");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("x"));
	}

	@Test
	public void shouldReturnTheSymbolOfTheOppositeDiagonalWinner() {
		Grid grid = new Grid("--x-x-x--");
		String winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is("x"));
	}
	
	private Cell[] constructCellsFromPosition(int position, String... symbols) {
		return new Cell[]{new Cell(symbols[0], position), new Cell(symbols[1], ++position), new Cell(symbols[2], ++position)};
	}
	
	private void assertThatExpectedSymbolsAreIn(Row row, Cell[] cells){
		assertThat(row.getCells(), is(cells));
	}
	
	private Row bottomRow(List<Row> horizontalRows) {
		return horizontalRows.get(2);
	}

	private Row middleRow(List<Row> horizontalRows) {
		return horizontalRows.get(1);
	}

	private Row topRow(List<Row> horizontalRows) {
		return horizontalRows.get(0);
	}
}
