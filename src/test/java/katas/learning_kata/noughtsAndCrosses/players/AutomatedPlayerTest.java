package katas.learning_kata.noughtsAndCrosses.players;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.EMPTY_CELL;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Cell;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Row;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AutomatedPlayerTest {
	private Player automatedPlayer;
	@Mock
	private Grid grid;

	@Before
	public void setup() {
		initMocks(this);
		automatedPlayer = new AutomatedPlayer(X);
	}

	@Test
	public void shouldReturnTheSymbolOfThePlayer() {
		assertThat(automatedPlayer.getSymbol(), is(X));
	}

	@Test
	public void shouldIdentifyThereIsABlockingMoveToBeMade() {
		when(grid.potentialWinningMove("o")).thenReturn(1);
		when(grid.isACellInTheGrid(1)).thenReturn(true);
		when(grid.takeNextMove("x", 1)).thenReturn(new Grid("oxo------"));

		Grid startingGrid = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, X, O));
		
		verify(grid).isACellInTheGrid(1);
		verify(grid).takeNextMove(X, 1);

	}

	@Test
	public void shouldIdentifyThereIsAWinningMoveToBeMade() {
		when(grid.potentialWinningMove(X)).thenReturn(1);
		when(grid.isACellInTheGrid(1)).thenReturn(true);
		when(grid.takeNextMove(X, 1)).thenReturn(new Grid("xxx------"));

		Grid afterMove = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = afterMove.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, X, X, X));
		
		verify(grid).isACellInTheGrid(1);
		verify(grid).takeNextMove(X, 1);
	}
	

	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositions() {
		when(grid.potentialWinningMove("x")).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(false);
		when(grid.getCentreCell()).thenReturn(4);
		when(grid.takeNextMove(X, 4)).thenReturn(new Grid("o---x----"));

		Grid afterMove = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = afterMove.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)) );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), constructCellsFromPosition(3, valueOf(EMPTY_CELL), X, valueOf(EMPTY_CELL)));
		assertThatExpectedSymbolsAreIn( bottomRow(horizontalRows), constructCellsFromPosition(6, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)) );

		verify(grid).isCenterTaken();
		verify(grid).takeNextMove(X, 4);
	}

	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositionsIfCenterCellIsTaken() {
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.hasFreeCornerPosition()).thenReturn(true);
		when(grid.getAvailableCorner()).thenReturn(2);
		when(grid.takeNextMove(X, 2)).thenReturn(new Grid("x-x-o----"));

		Grid startingGrid = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, X, valueOf(EMPTY_CELL), X)) ;
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, valueOf(EMPTY_CELL), O, valueOf(EMPTY_CELL)));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, valueOf(EMPTY_CELL), valueOf(EMPTY_CELL), valueOf(EMPTY_CELL)));

		verify(grid).getAvailableCorner();
	}

	@Test
	public void shouldMakeMoveInAnyFreeSpaceIfTheCenterCellAndCornerCellsAreTaken() {
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.hasFreeCornerPosition()).thenReturn(false);
		when(grid.getFirstFreeCell()).thenReturn(3);
		when(grid.takeNextMove(X, 3)).thenReturn(new Grid("oxoxx-xox"));

		Grid startingGrid = automatedPlayer.takesGo(grid);
		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, X, O) );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), constructCellsFromPosition(3, X, X, valueOf(EMPTY_CELL)));
		assertThatExpectedSymbolsAreIn( bottomRow(horizontalRows), constructCellsFromPosition(6, X, O, X));

		verify(grid).getFirstFreeCell();
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
	
	private Cell[] constructCellsFromPosition(int position, String... symbols) {
		return new Cell[]{new Cell(symbols[0], position), new Cell(symbols[1], ++position), new Cell(symbols[2], ++position)};
	}

}
