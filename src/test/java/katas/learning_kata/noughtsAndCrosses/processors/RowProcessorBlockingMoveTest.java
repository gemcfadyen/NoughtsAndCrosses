package katas.learning_kata.noughtsAndCrosses.processors;

import static java.util.Arrays.asList;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import katas.learning_kata.noughtsAndCrosses.players.AutomatedPlayer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class RowProcessorBlockingMoveTest {
	private String board;
	private static AutomatedPlayer playerO = new AutomatedPlayer(O, "fred");
	private static AutomatedPlayer playerX = new AutomatedPlayer(X, "bob");
	private static AutomatedPlayer player;
	private int blockingIndex;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	public RowProcessorBlockingMoveTest(String board, AutomatedPlayer player, int blockingIndex) {
		this.board = board;
		this.player = player;
		this.blockingIndex = blockingIndex;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] {  
				//tests for x-x
				{ "x-x------", playerO, 1 },
				{ "---x-x---", playerO, 4 }, 
				{ "------x-x", playerO, 7 }, 
				//tests for xx-
				{ "xx-------", playerO, 2 },
				{ "---xx----", playerO, 5 }, 
				{ "------xx-", playerO, 8 },
				//tests for -xx
				{ "-xx------", playerO, 0 },
				{ "----xx---", playerO, 3 },
				{ "-------xx", playerO, 6 },
				//negative tests
				{ "xxo------", playerO, -1 },
				{ "xo-------", playerO, -1 },
				{ "---xo----", playerO, -1 },
				{ "------ox-", playerO, -1 },
				{ "---------", playerO, -1 },
				{ "xx-------", playerX, -1 }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		RowProcessor rowProcessor = new RowProcessor(board);
		int index = rowProcessor.potentialWinningMove(player.opponent());

		assertThat(index, is(blockingIndex));
	}
}
