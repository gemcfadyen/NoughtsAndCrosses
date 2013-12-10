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
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), new Cell[] { new Cell(O, 0), new Cell(X, 1), new Cell(O, 2)} );
		
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
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), new Cell[] { new Cell(X, 0), new Cell(X, 1), new Cell(X, 2)} );
		
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
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), new Cell[] { new Cell(Grid.O, 0), new Cell(valueOf(EMPTY_CELL), 1), new Cell(valueOf(EMPTY_CELL), 2)} );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), new Cell[] { new Cell(valueOf(EMPTY_CELL), 3), new Cell(X, 4), new Cell(valueOf(EMPTY_CELL), 5) } );
		assertThatExpectedSymbolsAreIn( bottomRow(horizontalRows), new Cell[] { new Cell(valueOf(EMPTY_CELL), 6), new Cell(valueOf(EMPTY_CELL), 7), new Cell(valueOf(EMPTY_CELL), 8) } );

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
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), new Cell[] { new Cell(Grid.X, 0), new Cell(valueOf(EMPTY_CELL), 1), new Cell(X, 2) }) ;
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), new Cell[] { new Cell(valueOf(EMPTY_CELL), 3), new Cell(O, 4), new Cell(valueOf(EMPTY_CELL), 5) });
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), new Cell[] { new Cell(valueOf(EMPTY_CELL), 6), new Cell(valueOf(EMPTY_CELL), 7), new Cell(valueOf(EMPTY_CELL), 8) });

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
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), new Cell[] { new Cell(Grid.O, 0), new Cell(X, 1), new Cell(O, 2)} );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), new Cell[] { new Cell(X, 3), new Cell(X, 4), new Cell(valueOf(EMPTY_CELL), 5) } );
		assertThatExpectedSymbolsAreIn( bottomRow(horizontalRows), new Cell[] { new Cell(X, 6), new Cell(O, 7), new Cell(X, 8) } );

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

}
