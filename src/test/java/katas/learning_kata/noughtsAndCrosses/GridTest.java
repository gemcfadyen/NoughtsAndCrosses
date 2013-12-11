package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.Symbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.Symbol.O;
import static katas.learning_kata.noughtsAndCrosses.Symbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class GridTest {
	@Test
	public void shouldReturnTrueIfTheCenterTaken() {
		Grid grid = new Grid(3, "----x----");
		assertThat(grid.isCenterTaken(), is(true));
	}

	@Test
	public void shouldReturnFalseIfTheCenterIsNotTaken() {
		Grid grid = new Grid(3,"---------");
		assertThat(grid.isCenterTaken(), is(false));
	}

	@Test
	public void shouldReturnTheCentreCell() {
		Grid grid = new Grid(3,"---------");
		assertThat(grid.getCentreCell(), is(grid.getCentreCell()));
	}

	@Test
	public void shouldReturnTrueIfTopLeftCornerIsFree() {
		Grid grid = new Grid(3,"-xxxxxxxx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfTopRightCornerIsFree() {
		Grid grid = new Grid(3,"xx-xxxxxx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfBottomRightCornerIsFree() {
		Grid grid = new Grid(3,"xxxxxxxx-");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnTrueIfBottomLeftCornerIsFree() {
		Grid grid = new Grid(3,"xxxxxx-xx");
		assertThat(grid.hasFreeCornerPosition(), is(true));
	}

	@Test
	public void shouldReturnFalseIfNoCornerIsFree() {
		Grid grid = new Grid(3,"x-x---x-x");
		assertThat(grid.hasFreeCornerPosition(), is(false));
	}


	@Test
	public void shouldWriteTheGridOut() {
		Grid grid = new Grid(3,"---xxx---");
		
		List<Row> horizontalRows = grid.getHorizontalRows();

		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, EMPTY, EMPTY, EMPTY)); 
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows),constructCellsFromPosition(3, X, X, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows),constructCellsFromPosition(6,  EMPTY, EMPTY, EMPTY));
	}

	@Test
	public void shouldReturnTrueIfThereIsAFreeSlotInTheGrid() {
		Grid grid = new Grid(3,"---------");
		assertThat(grid.hasFreeSlot(), is(true));
	}

	@Test
	public void shouldReturnFalseIfThereAreNoFreeSlotsInTheGrid() {
		Grid grid = new Grid(3, "xoxoxoxoo");
		assertThat(grid.hasFreeSlot(), is(false));
	}

	@Test
	public void shouldPlaceThePlayersSymbolAtTheSpecifiedIndexInTheGrid() {
		Grid grid = new Grid(3, "---xox---");
		
		grid = grid.takeNextMove(O, 0);

		List<Row> horizontalRows = grid.getHorizontalRows();
	
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, O, EMPTY, EMPTY));
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, X, O, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, EMPTY, EMPTY, EMPTY));
	}

	@Test
	public void shouldNotPlaceThePlayersSymbolInAnOccupiedCell() {
		Grid grid = new Grid(3, "---xox---");
		grid.takeNextMove(O, 3);

		List<Row> horizontalRows = grid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, EMPTY, EMPTY, EMPTY));
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, X, O, X));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, EMPTY, EMPTY, EMPTY));
	}

	@Test
	public void shouldReturnTheIndexOfTheCellUsedToBlockOpponentFromWinning() {
		Grid grid = new Grid(3, "xx-------");
		int index = grid.getIndexOfBlockingMove(O);

		assertThat(index, is(2));
	}

	@Test
	public void shouldReturnNoMatchIfThereAreNoPotentialWinsForOpponent() {
		Grid grid = new Grid(3, "o--------");
		int index = grid.getIndexOfBlockingMove(X);

		assertThat(index, is(-1));
	}

	@Test
	public void shouldReturnTrueIfTheIndexIsACellInTheGrid() {
		Grid grid = new Grid(3, "---------");
		boolean isFree = grid.isACellInTheGrid(0);
		assertTrue(isFree);
	}

	@Test
	public void shouldReturnFalseIfTheIndexIsNoMatchFound() {
		Grid grid = new Grid(3, "---------");
		boolean isFree = grid.isACellInTheGrid(NO_MATCH_FOUND);
		assertFalse(isFree);
	}

	@Test
	public void shouldReturnTheFirstFreeIndexOnTheBoard() {
		Grid grid = new Grid(3, "---------");
		int index = grid.getFirstFreeCell();
		assertThat(index, is(0));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow() {
		Grid grid = new Grid(3, "o-xxx-o--");
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow2() {
		Grid grid = new Grid(3, "o-o-o----");
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow3() {
		Grid grid = new Grid(3, "---o-o-o-");
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow4() {
		Grid grid = new Grid(3, "--o-xxx-o");
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow5() {
		Grid grid = new Grid(3, "--ooxxx-o"); // 0, 2, 8
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow6() {
		Grid grid = new Grid(3, "o-xxx-oxo"); // 6, 8, 0
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow7() {
		Grid grid = new Grid(3, "----x-x-x"); // 6, 8, 0
		Symbol winningSymbol = grid.getWinningSymbol();
		Symbol expectedResult = null;
		assertThat(winningSymbol, is(expectedResult));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenTopHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "xxx------");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "---xxx---");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenBottomHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "------xxx");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenLeftVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "o--o--o--");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "-o--o--o-");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenRightVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "--o--o--o");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfADiagonalWinner() {
		Grid grid = new Grid(3, "x---x---x");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfTheOppositeDiagonalWinner() {
		Grid grid = new Grid(3, "--x-x-x--");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(X));
	}
	
	private Cell[] constructCellsFromPosition(int position, Symbol... symbols) {
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
