package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GridTest {

	//Testing RegEx X-X
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInFirstRowX_X(){
		Grid grid = new Grid("x-x------");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleRowX_X(){
		Grid grid = new Grid("---x-x---");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInBottomRowX_X(){
		Grid grid = new Grid("------x-x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInLeftHandVerticleRowX_X(){
		Grid grid = new Grid("x-----x--");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
		
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleVerticleRowX_X(){
		Grid grid = new Grid("-x-----x-");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInRightHandVerticleRowX_X(){
		Grid grid = new Grid("--x-----x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheDiagonalX_X(){
		Grid grid = new Grid("x-------x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheOppositeDiagonalX_X(){
		Grid grid = new Grid("--x---x--");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	
	//Testing RegEx XX-
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInFirstRowXX_(){
		Grid grid = new Grid("xx-------");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleRowXX_(){
		Grid grid = new Grid("---xx----");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInBottomRowXX_(){
		Grid grid = new Grid("------xx-");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInLeftHandVerticleRowXX_(){
		Grid grid = new Grid("x--x-----");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
		
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleVerticleRowXX_(){
		Grid grid = new Grid("--x--x---");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInRightHandVerticleRowXX_(){
		Grid grid = new Grid("--x--x---");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheDiagonalXX_(){
		Grid grid = new Grid("--x-x----");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheOppositeDiagonalXX_(){
		Grid grid = new Grid("----x-x--");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	
	
	//Testing RegEx -XX
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInFirstRow_XX(){
		Grid grid = new Grid("-xx------");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleRow_XX(){
		Grid grid = new Grid("----x--x-");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInBottomRow_XX(){
		Grid grid = new Grid("-------xx");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInLeftHandVerticleRow_XX(){
		Grid grid = new Grid("---x--x--");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
		
	}
	
	@Test
	public void shouldReasliseThereIsAWinningMoveToBeMadeInMiddleVerticleRow_XX(){
		Grid grid = new Grid("-----x--x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInRightHandVerticleRow_XX(){
		Grid grid = new Grid("-----x--x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheDiagonal_XX(){
		Grid grid = new Grid("----x-x--");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
	
	@Test
	public void shouldRealiseThereIsAWinningMoveToBeMadeInTheOppositeDiagonal_XX(){
		Grid grid = new Grid("----x---x");
		boolean hasWinningMove = grid.hasWinningMoveFor("x");
		assertThat(hasWinningMove, is(true));
	}
}
