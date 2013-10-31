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
public class ColumnProcessorBlockingMoveTest {
	private String board;
	private static AutomatedPlayer playerO = new AutomatedPlayer(O);
	private static AutomatedPlayer playerX = new AutomatedPlayer(X);
	private static AutomatedPlayer player;
	private int blockingIndex;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	public ColumnProcessorBlockingMoveTest(String board, AutomatedPlayer player, int blockingIndex) {
		this.board = board;
		this.player = player;
		this.blockingIndex = blockingIndex;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] {  
				//tests for x-x
				{ "x-----x--", playerO, 3 }, 
				{ "-x-----x-", playerO, 4 },
				{ "--x-----x", playerO, 5 },
				//tests for xx-
				{ "x--x-----", playerO, 6 }, 
				{ "--x--x---", playerO, 8 },
				{ "-x--x----", playerO, 7 },
				//tests for -xx
				{ "----x--x-", playerO, 1 },
				{ "---x--x--", playerO, 0 },
				{ "-----x--x", playerO, 2 },
				//negative tests
				{ "x--o-----", playerO, -1 },
				{ "-x---o---", playerO, -1 },
				{ "--x---o--", playerO, -1 },
				{ "---------", playerO, -1 },
				{ "xx-------", playerX, -1 }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		ColumnProcessor columns = new ColumnProcessor(board);
		int index = columns.potentialWinningMove(player.opponent());

		assertThat(index, is(blockingIndex));
	}

}
