package katas.learning_kata.noughtsAndCrosses.players;

import static katas.learning_kata.noughtsAndCrosses.Grid.NO_MATCH_FOUND;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Cell;
import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Row;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

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
	public void shouldIdentifyThereIsABlockingMoveToBeMade() {
		when(grid.potentialWinningMove(X)).thenReturn(Grid.NO_MATCH_FOUND);
		when(grid.potentialWinningMove(O)).thenReturn(1);
		when(grid.updateGridWith(X, 1)).thenReturn(new Grid(3, "oxo------"));

		Grid startingGrid = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, X, O));
		
		verify(grid).updateGridWith(X, 1);
 
	}

	@Test
	public void shouldIdentifyThereIsAWinningMoveToBeMade() {
		when(grid.potentialWinningMove(X)).thenReturn(1);
		when(grid.updateGridWith(X, 1)).thenReturn(new Grid(3, "xxx------"));

		Grid afterMove = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = afterMove.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, X, X, X));
		
		verify(grid).updateGridWith(X, 1);
	}
	

	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositions() {
		when(grid.potentialWinningMove(X)).thenReturn(NO_MATCH_FOUND);
		when(grid.potentialWinningMove(O)).thenReturn(NO_MATCH_FOUND);
		when(grid.isCenterTaken()).thenReturn(false);
		when(grid.getCenterCell()).thenReturn(4);
		when(grid.updateGridWith(X, 4)).thenReturn(new Grid(3, "o---x----"));

		Grid afterMove = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = afterMove.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, EMPTY, EMPTY) );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), constructCellsFromPosition(3, EMPTY, X, EMPTY));
		assertThatExpectedSymbolsAreIn( bottomRow(horizontalRows), constructCellsFromPosition(6, EMPTY, EMPTY, EMPTY) );

		verify(grid).isCenterTaken();
		verify(grid).updateGridWith(X, 4);
	}

	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositionsIfCenterCellIsTaken() {
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.isEmptyCellAt(2)).thenReturn(true);
		when(grid.getAvailableCorner()).thenReturn(2);
		when(grid.updateGridWith(X, 2)).thenReturn(new Grid(3, "x-x-o----"));

		Grid startingGrid = automatedPlayer.takesGo(grid);

		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn(topRow(horizontalRows), constructCellsFromPosition(0, X, EMPTY, X)) ;
		assertThatExpectedSymbolsAreIn(middleRow(horizontalRows), constructCellsFromPosition(3, EMPTY, O, EMPTY));
		assertThatExpectedSymbolsAreIn(bottomRow(horizontalRows), constructCellsFromPosition(6, EMPTY, EMPTY, EMPTY));

		verify(grid).getAvailableCorner();
	}

	@Test
	public void shouldMakeMoveInAnyFreeSpaceIfTheCenterCellAndCornerCellsAreTaken() {
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.getAvailableCorner()).thenReturn(-1);
		when(grid.getFirstFreeCell()).thenReturn(3);
		when(grid.updateGridWith(X, 3)).thenReturn(new Grid(3, "oxoxx-xox"));

		Grid startingGrid = automatedPlayer.takesGo(grid);
		List<Row> horizontalRows = startingGrid.getHorizontalRows();
		assertThatExpectedSymbolsAreIn( topRow(horizontalRows), constructCellsFromPosition(0, O, X, O) );
		assertThatExpectedSymbolsAreIn( middleRow(horizontalRows), constructCellsFromPosition(3, X, X, EMPTY));
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
	
	private Cell[] constructCellsFromPosition(int position, ValidSymbol... symbols) {
		return new Cell[]{new Cell(symbols[0], position), new Cell(symbols[1], ++position), new Cell(symbols[2], ++position)};
	}

}
