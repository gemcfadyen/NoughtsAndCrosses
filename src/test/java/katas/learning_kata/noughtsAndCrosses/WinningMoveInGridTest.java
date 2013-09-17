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
public class WinningMoveInGridTest {

	private String board;
	private String playersSymbol;
	private boolean foundWinningMove;

	public WinningMoveInGridTest(String board, String playersSymbol, boolean foundWinningMove) {
		this.board = board;
		this.playersSymbol = playersSymbol;
		this.foundWinningMove = foundWinningMove;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] { 
				//tests for x-x
				{ "x-x------", "x", true },
				{ "---x-x---", "x", true }, 
				{ "------x-x", "x", true }, 
				{ "x-----x--", "x", true }, 
				{ "-x-----x-", "x", true },
				{ "--x-----x", "x", true },
				{ "x-------x", "x", true },
				{ "--x---x--", "x", true },
				//tests for xx-
				{ "xx-------", "x", true },
				{ "---xx----", "x", true }, 
				{ "------xx-", "x", true },
				{ "x--x-----", "x", true }, 
				{ "--x--x---", "x", true },
				{ "--x--x---", "x", true },
				{ "--x-x----", "x", true },
				{ "----x-x--", "x", true },
				//tests for -xx
				{ "-xx------", "x", true },
				{ "----x--x-", "x", true },
				{ "-------xx", "x", true },
				{ "---x--x--", "x", true },
				{ "-----x--x", "x", true },
				{ "----x-x--", "x", true },
				{ "----x---x", "x", true },
				//negative tests
				{ "xxo------", "x", false },
				{ "----x----", "x", false },
				{ "oo------x", "x", false },
				{ "---------", "x", false },
				{ "xx-------", "o", false }
		});
	}

	@Test
	public void shouldUnderstandIfThereIsAWinningMoveInTheGridForTheGivenPlayer() {
		Grid grid = new Grid(board);
		boolean hasWinningMove = grid.hasWinningMoveFor(playersSymbol);

		assertThat(hasWinningMove, is(foundWinningMove));
	}

}
