package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
		//Grid updatedGrid = automatedPlayer.takesGo("x-x------");
	}
	
}
