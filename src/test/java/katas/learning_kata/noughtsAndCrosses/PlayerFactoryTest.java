package katas.learning_kata.noughtsAndCrosses;

import static junit.framework.Assert.assertTrue;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.mockito.MockitoAnnotations.initMocks;
import katas.learning_kata.noughtsAndCrosses.players.AutomatedPlayer;
import katas.learning_kata.noughtsAndCrosses.players.HumanPlayer;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class PlayerFactoryTest {
	@Mock private Prompt prompt;
	
	@Before
	public void setup(){
		initMocks(this);
	}

	@Test
	public void shouldCreateAHumanPlayer(){
		PlayerFactory factory = new PlayerFactory();
		Player player = factory.createOpponentPlayer("h", X, prompt);
		assertTrue(player instanceof HumanPlayer);
	}
	
	@Test
	public void shouldCreateAnAutomatedPlayer(){
		PlayerFactory factory = new PlayerFactory();
		Player player = factory.createOpponentPlayer("c", X, prompt);
		assertTrue(player instanceof AutomatedPlayer);
	}

}
