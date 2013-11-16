package katas.learning_kata.noughtsAndCrosses.players;

import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.Grid;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AutomatedPlayerTest {
	private Player automatedPlayer;
	@Mock private Grid grid;
	
	@Before 
	public void setup(){
		initMocks(this);
		automatedPlayer = new AutomatedPlayer(X);
	}
	
	@Test
	public void shouldReturnTheSymbolOfThePlayer(){
		assertThat(automatedPlayer.getSymbol(), is(X));
	}
	

	@Test
	public void shouldIdentifyThereIsABlockingMoveToBeMade(){
		when(grid.potentialWinningMove("o")).thenReturn(1);
		when(grid.isACellInTheGrid(1)).thenReturn(true);
		when(grid.takeNextMove("x", 1)).thenReturn(new Grid("oxo------"));
		
		Grid startingGrid = automatedPlayer.takesGo(grid);
		
		assertTrue(startingGrid.toString().contains("oxo"));
		verify(grid).isACellInTheGrid(1);
		verify(grid).takeNextMove(X, 1);
		
	}
	
	@Test
	public void shouldIdentifyThereIsAWinningMoveToBeMade(){
		when(grid.potentialWinningMove(X)).thenReturn(1);
		when(grid.isACellInTheGrid(1)).thenReturn(true);
		when(grid.takeNextMove(X, 1)).thenReturn(new Grid("xxx------"));
		 
		Grid startingGrid = automatedPlayer.takesGo(grid);
		assertTrue(startingGrid.toString().contains("xxx"));
		
		verify(grid).isACellInTheGrid(1);
		verify(grid).takeNextMove(X, 1);
	}
	
	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositions() {
		when(grid.potentialWinningMove("x")).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(false);
		when(grid.getCentreCell()).thenReturn(4);
		when(grid.takeNextMove(X, 4)).thenReturn(new Grid("o---x----"));
		
		Grid startingGrid = automatedPlayer.takesGo(grid);
		
		assertThat(startingGrid.toString(), is("o--       0 1 2\n-x-       3 4 5\n---       6 7 8\n\n__________________________________\n"));
		verify(grid).isCenterTaken();
		verify(grid).takeNextMove(X, 4);
	}
	
	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositionsIfCenterCellIsTaken(){
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.hasFreeCornerPosition()).thenReturn(true);
		when(grid.getAvailableCorner()).thenReturn(2);
		when(grid.takeNextMove(X, 2)).thenReturn(new Grid("x-x-o----"));
		
        Grid startingGrid = automatedPlayer.takesGo(grid);
		assertThat(startingGrid.toString(), is("x-x       0 1 2\n-o-       3 4 5\n---       6 7 8\n\n__________________________________\n"));
		verify(grid).getAvailableCorner();
	}
	
	@Test
	public void shouldMakeMoveInAnyFreeSpaceIfTheCenterCellAndCornerCellsAreTaken(){
		when(grid.potentialWinningMove(X)).thenReturn(-1);
		when(grid.potentialWinningMove(O)).thenReturn(-1);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.hasFreeCornerPosition()).thenReturn(false);
		when(grid.getFirstFreeCell()).thenReturn(3);
		when(grid.takeNextMove(X, 3)).thenReturn(new Grid("oxoxx-xox"));
	
        Grid startingGrid = automatedPlayer.takesGo(grid);
		assertThat(startingGrid.toString(), is("oxo       0 1 2\nxx-       3 4 5\nxox       6 7 8\n\n__________________________________\n"));
		verify(grid).getFirstFreeCell();
	}
	
	
	
}
