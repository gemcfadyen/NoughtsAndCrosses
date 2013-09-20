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
	private String playersSymbol;
	private static AutomatedPlayer player;
	//private static AutomatedPlayer playerO;
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
				{ "x-x------", new AutomatedPlayer(O, "fred"), 1 },
				{ "---x-x---", new AutomatedPlayer(O, "fred"), 4 }, 
				{ "------x-x", new AutomatedPlayer(O, "fred"), 7 }, 
				{ "x-----x--", new AutomatedPlayer(O, "fred"), 3 }, 
				{ "-x-----x-", new AutomatedPlayer(O, "fred"), 4 },
				{ "--x-----x", new AutomatedPlayer(O, "fred"), 5 },
				{ "x-------x", new AutomatedPlayer(O, "fred"), 4 },
				{ "--x---x--", new AutomatedPlayer(O, "fred"), 4 },
				//tests for xx-
				{ "xx-------", new AutomatedPlayer(O, "fred"), 2 },
				{ "---xx----", new AutomatedPlayer(O, "fred"), 5 }, 
				{ "------xx-", new AutomatedPlayer(O, "fred"), 8 },
				{ "x--x-----", new AutomatedPlayer(O, "fred"), 6 }, 
				{ "--x--x---", new AutomatedPlayer(O, "fred"), 8 },
				{ "--x-x----", new AutomatedPlayer(O, "fred"), 6 },
				{ "----x-x--", new AutomatedPlayer(O, "fred"), 2 },
				//tests for -xx
				{ "-xx------", new AutomatedPlayer(O, "fred"), 0 },
				{ "----x--x-", new AutomatedPlayer(O, "fred"), 1 },
				{ "-------xx", new AutomatedPlayer(O, "fred"), 6 },
				{ "---x--x--", new AutomatedPlayer(O, "fred"), 0 },
				{ "-----x--x", new AutomatedPlayer(O, "fred"), 2 },
				{ "----x-x--", new AutomatedPlayer(O, "fred"), 2 },
				{ "----x---x", new AutomatedPlayer(O, "fred"), 0 },
				//negative tests
				{ "xxo------", new AutomatedPlayer(O, "fred"), -1 },
				{ "----x----", new AutomatedPlayer(O, "fred"), -1 },
				{ "oo------x", new AutomatedPlayer(O, "fred"), -1 },
				{ "---------", new AutomatedPlayer(O, "fred"), -1 },
				{ "xx-------", new AutomatedPlayer(X, "bob"), -1 }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		Grid grid = new Grid(board);
		int index = grid.potentialWinningMove(player.opponent());

		assertThat(index, is(blockingIndex));
	}

}
