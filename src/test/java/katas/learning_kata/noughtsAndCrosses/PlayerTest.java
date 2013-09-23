package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlayerTest {
	private Player automatedPlayer;
	@Mock private Grid grid;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		automatedPlayer = new AutomatedPlayer("x", "X-Man");
	}
	
	@Test
	public void shouldReturnTheSymbolOfThePlayer(){
		assertThat(automatedPlayer.getSymbol(), is("x"));
	}
	
	@Test
	public void shouldReturnTheNameOfThePlayer(){
		assertThat(automatedPlayer.getName(), is("X-Man"));
	}
	
	@Test
	public void shouldIdentifyThereIsABlockingMoveToBeMade(){
		when(grid.potentialWinningMove("o")).thenReturn(1);
		when(grid.isAWinningMoveAt(1)).thenReturn(true);
		when(grid.takeNextMove("x", 1)).thenReturn(new Grid("oxo------"));
		
		Grid startingGrid = automatedPlayer.takesGo(grid);
		
		assertTrue(startingGrid.toString().contains("oxo"));
		verify(grid).isAWinningMoveAt(1);
		verify(grid).takeNextMove("x", 1);
		
	}
	
	@Test
	public void shouldIdentifyThereIsAWinningMoveToBeMade(){
		when(grid.potentialWinningMove("x")).thenReturn(1);
		when(grid.isAWinningMoveAt(1)).thenReturn(true);
		when(grid.takeNextMove("x", 1)).thenReturn(new Grid("xxx------"));
		
		Grid startingGrid = automatedPlayer.takesGo(grid);
		assertTrue(startingGrid.toString().contains("xxx"));
		
		verify(grid).isAWinningMoveAt(1);
		verify(grid).takeNextMove("x", 1);
	}
	
	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositions() {
		when(grid.potentialWinningMove("x")).thenReturn(-1);
		when(grid.isAWinningMoveAt(1)).thenReturn(false);
		when(grid.isCenterTaken()).thenReturn(false);
		when(grid.takeNextMove("x", 4)).thenReturn(new Grid("o---x----"));
		
		Grid startingGrid = automatedPlayer.takesGo(grid);
		
		assertThat(startingGrid.toString(), is("o--\n-x-\n---\n"));
		verify(grid).isCenterTaken();
		verify(grid).takeNextMove("x", 4);
	}
	
	@Test
	public void shouldMakeMoveWithMostPossibleWinningPositionsIfCenterCellIsTaken(){
		when(grid.potentialWinningMove("x")).thenReturn(-1);
		when(grid.isAWinningMoveAt(1)).thenReturn(false);
		when(grid.isCenterTaken()).thenReturn(true);
		when(grid.hasFreeCornerPosition()).thenReturn(true);
		when(grid.getAvailableCorner()).thenReturn(2);
		when(grid.takeNextMove("x", 2)).thenReturn(new Grid("x-x-o----"));
		
        Grid startingGrid = automatedPlayer.takesGo(grid);
		assertThat(startingGrid.toString(), is("x-x\n-o-\n---\n"));
		verify(grid).getAvailableCorner();
	}
	
	
	
}
