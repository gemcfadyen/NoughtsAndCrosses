package katas.learning_kata.noughtsAndCrosses;

import static java.util.Arrays.asList;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IndexOfBlockingMoveInGridTest {
	private String board;
	private static AutomatedPlayer playerO = new AutomatedPlayer(O, "fred");
	private static AutomatedPlayer playerX = new AutomatedPlayer(X, "bob");
	private static AutomatedPlayer player;
	private int blockingIndex;

	public IndexOfBlockingMoveInGridTest(String board, AutomatedPlayer player, int blockingIndex) {
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
				{ "x-----x--", playerO, 3 }, 
				{ "-x-----x-", playerO, 4 },
				{ "--x-----x", playerO, 5 },
				{ "x-------x", playerO, 4 },
				{ "--x---x--", playerO, 4 },
				//tests for xx-
				{ "xx-------", playerO, 2 },
				{ "---xx----", playerO, 5 }, 
				{ "------xx-", playerO, 8 },
				{ "x--x-----", playerO, 6 }, 
				{ "--x--x---", playerO, 8 },
				{ "--x-x----", playerO, 6 },
				{ "----x-x--", playerO, 2 },
				//tests for -xx
				{ "-xx------", playerO, 0 },
				{ "----x--x-", playerO, 1 },
				{ "-------xx", playerO, 6 },
				{ "---x--x--", playerO, 0 },
				{ "-----x--x", playerO, 2 },
				{ "----x-x--", playerO, 2 },
				{ "----x---x", playerO, 0 },
				//negative tests
				{ "xxo------", playerO, -1 },
				{ "----x----", playerO, -1 },
				{ "oo------x", playerO, -1 },
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
