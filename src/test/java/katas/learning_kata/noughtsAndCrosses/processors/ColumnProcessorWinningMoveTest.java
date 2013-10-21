package katas.learning_kata.noughtsAndCrosses.processors;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import katas.learning_kata.noughtsAndCrosses.processors.ColumnProcessor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ColumnProcessorWinningMoveTest {
	private String board;
	private String playersSymbol;
	private int indexOfWinningMove;

	public ColumnProcessorWinningMoveTest(String board, String playersSymbol, int indexOfWinningMove) {
		this.board = board;
		this.playersSymbol = playersSymbol;
		this.indexOfWinningMove = indexOfWinningMove;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] { 
				//tests for x-x
				{ "x-----x--", "x", 3 }, 
				{ "-x-----x-", "x", 4 },
				{ "--x-----x", "x", 5 },
				//tests for xx-
				{ "x--x-----", "x", 6 }, 
				{ "--x--x---", "x", 8 },
				{ "-x--x----", "x", 7 },
				//tests for -xx
				{ "----x--x-", "x", 1 },
				{ "---x--x--", "x", 0 },
				{ "-----x--x", "x", 2 },
				//negative tests
				{ "x--o-----", "x", -1 },
				{ "-x---o---", "x", -1 },
				{ "--x---o--", "x", -1 },
				{ "---------", "x", -1 },
				{ "xx-------", "o", -1 }
		});
	}

	@Test
	public void shouldReturnTheIndexForAWinningMoveInTheGridForTheGivenPlayer() {
		ColumnProcessor columnProcessor = new ColumnProcessor(board);
		int index = columnProcessor.potentialWinningMove(playersSymbol);

		assertThat(index, is(indexOfWinningMove));
	}
}
