package katas.learning_kata.noughtsAndCrosses;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol.NO_SYMBOL;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.symbols.InvalidSymbol;
import katas.learning_kata.noughtsAndCrosses.symbols.Symbol;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

import org.junit.Test;

public class GridTest {
	
	@Test
	public void shouldReturnTrueIfTheCenterTaken() {
		Grid grid = new Grid(3, "----x----");
		assertThat(grid.isCenterTaken(), is(true));
	}
	
	@Test
	public void shouldCreateAGridWithADimension(){
		Grid grid = new Grid(2);
		assertNotNull(grid);
		assertThat(grid.getHorizontalRows().size(), is(2));
	}

	@Test
	public void shouldReturnFalseIfTheCenterIsNotTaken() {
		Grid grid = new Grid(3,"---------");
		assertThat(grid.isCenterTaken(), is(false));
	}

	@Test
	public void shouldReturnTheCentreCell() {
		Grid grid = new Grid(3,"---------");
		assertThat(grid.getCenterCell(), is(grid.getCenterCell()));
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
		
		grid = grid.updateGridWith(O, 0);

		List<Row> horizontalRows = grid.getHorizontalRows();
	
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, O, EMPTY, EMPTY));
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
	public void shouldReturnTheFirstFreeIndexOnTheBoard() {
		Grid grid = new Grid(3, "---------");
		int index = grid.getFirstFreeCell();
		assertThat(index, is(0));
	}

	@Test
	public void shouldNotReturnAWinningMoveWhenThereIsNotThreeMatchingSymbolsInARow() {
		Grid grid = new Grid(3, "o-xxx-o--");
		Symbol winningSymbol = grid.getWinningSymbol();
		
		assertThat(winningSymbol, is(InvalidSymbol.class));
		assertThat((InvalidSymbol)winningSymbol, is(NO_SYMBOL));
	}


	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenTopHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "xxx------");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "---xxx---");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenBottomHorizonalRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "------xxx");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenLeftVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "o--o--o--");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenMiddleVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "-o--o--o-");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfARowWinnerWhenRightVerticleRowHasMatchingSymbols() {
		Grid grid = new Grid(3, "--o--o--o");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(O));
	}

	@Test
	public void shouldReturnTheSymbolOfADiagonalWinner() {
		Grid grid = new Grid(3, "x---x---x");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(X));
	}

	@Test
	public void shouldReturnTheSymbolOfTheOppositeDiagonalWinner() {
		Grid grid = new Grid(3, "--x-x-x--");
		Symbol winningSymbol = grid.getWinningSymbol();
		assertThat(winningSymbol, is(ValidSymbol.class));
		assertThat((ValidSymbol)winningSymbol, is(X));
	}
	
	@Test
	public void shouldReturnTrueIfAnEmptyCellIsAtGivenIndex(){
		Grid grid = new Grid(3, "--x-x-x--");
		assertTrue(grid.isEmptyCellAt(0));
	}
	
	@Test
	public void shouldReturnFalseIfNoEmptyCellIsAtGivenIndex(){
		Grid grid = new Grid(3, "--x-x-x--");
		assertFalse(grid.isEmptyCellAt(2));
	}
	
	@Test
	public void shouldReturnTrueIfIndexIsOutsideOfTheGridDimension(){
		Grid grid = new Grid(3, "--x-x-x--");
		assertFalse(grid.isEmptyCellAt(11));
	}
	
	@Test
	public void shouldReturnIndexOfTopLeftCorner(){
		Grid grid = new Grid(3, "--x-x-x--");
		assertThat(grid.getAvailableCorner(), is(0));
	}
	
	@Test
	public void shouldReturnIndexOfTopRightCorner(){
		Grid grid = new Grid(3, "x---x-x--");
		assertThat(grid.getAvailableCorner(), is(2));
	}
	
	@Test
	public void shouldReturnIndexOfBottomLeftCorner(){
		Grid grid = new Grid(3, "x-x------");
		assertThat(grid.getAvailableCorner(), is(6));
	}
	
	@Test
	public void shouldReturnIndexOfBottomRightCorner(){
		Grid grid = new Grid(3, "x-x---x--");
		assertThat(grid.getAvailableCorner(), is(8));
	}
	
	@Test
	public void shouldReturnNoCornerIndexWhenAllCornerCellsAreOccupied(){
		Grid grid = new Grid(3, "x-x---x-x");
		assertThat(grid.getAvailableCorner(), is(NO_MATCH_FOUND));
	}
	
	private Cell[] constructCellsFromPosition(int position, ValidSymbol... symbols) {
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
