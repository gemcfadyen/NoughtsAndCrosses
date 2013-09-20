package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	private Player automatedPlayer;
	
	@Before
	public void setup(){
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
		Grid startingGrid = new Grid("o-o------");
		startingGrid = automatedPlayer.takesGo(startingGrid);
		//verify(startingGrid).hasWinningMoveFor("x-x", "o");
		assertTrue(startingGrid.toString().contains("oxo"));
	}
	
}
