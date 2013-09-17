package katas.learning_kata.noughtsAndCrosses;

import static java.util.Arrays.asList;
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
	private int blockingIndex;

	public IndexOfBlockingMoveInGridTest(String board, String playersSymbol, int blockingIndex) {
		this.board = board;
		this.playersSymbol = playersSymbol;
		this.blockingIndex = blockingIndex;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] {  
				//tests for x-x
				{ "x-x------", "o", 1 },
				{ "---x-x---", "o", 4 }, 
				{ "------x-x", "o", 7 }, 
				{ "x-----x--", "o", 3 }, 
				{ "-x-----x-", "o", 4 },
				{ "--x-----x", "o", 5 },
				{ "x-------x", "o", 4 },
				{ "--x---x--", "o", 4 },
				//tests for xx-
				{ "xx-------", "o", 2 },
				{ "---xx----", "o", 5 }, 
				{ "------xx-", "o", 8 },
				{ "x--x-----", "o", 6 }, 
				{ "--x--x---", "o", 8 },
				{ "--x-x----", "o", 6 },
				{ "----x-x--", "o", 2 },
				//tests for -xx
				{ "-xx------", "o", 0 },
				{ "----x--x-", "o", 1 },
				{ "-------xx", "o", 6 },
				{ "---x--x--", "o", 0 },
				{ "-----x--x", "o", 2 },
				{ "----x-x--", "o", 2 },
				{ "----x---x", "o", 0 },
				//negative tests
				{ "xxo------", "o", -1 },
				{ "----x----", "o", -1 },
				{ "oo------x", "o", -1 },
				{ "---------", "o", -1 },
				{ "xx-------", "x", -1 }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		Grid grid = new Grid(board);
		int index = grid.getIndexOfBlockingMove(playersSymbol);

		assertThat(index, is(blockingIndex));
	}

}
