package katas.learning_kata.noughtsAndCrosses;

import static java.util.Arrays.asList;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class DiagonalProcessorBlockingMoveTest {
	private String board;
	@Mock private static Prompt commandPrompt;
	private static AutomatedPlayer playerO = new AutomatedPlayer(O, "fred", commandPrompt);
	private static AutomatedPlayer playerX = new AutomatedPlayer(X, "bob", commandPrompt);
	private static AutomatedPlayer player;
	private int blockingIndex;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	public DiagonalProcessorBlockingMoveTest(String board, AutomatedPlayer player, int blockingIndex) {
		this.board = board;
		this.player = player;
		this.blockingIndex = blockingIndex;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] {  
				//tests for x-x
				{ "x-------x", playerO, 4 },
				{ "--x---x--", playerO, 4 },
				//tests for xx-
				{ "--x-x----", playerO, 6 },
				{ "----x-x--", playerO, 2 },
				//tests for -xx
				{ "----x-x--", playerO, 2 },
				{ "----x---x", playerO, 0 },
				//negative tests
				{ "x---o----", playerO, -1 },
				{ "--x-o----", playerO, -1 },
				{ "---------", playerO, -1 },
				{ "xx-------", playerX, -1 }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		Grid grid = new Grid(board);
		int index = grid.potentialWinningMove(player.opponent());

		assertThat(index, is(blockingIndex));
	}

}
