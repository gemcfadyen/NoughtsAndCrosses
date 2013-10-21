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
public class DiagonalProcessorWinningMoveTest {
	private String board;
	private String playersSymbol;
	private int indexOfWinningMove;

	public DiagonalProcessorWinningMoveTest(String board, String playersSymbol, int indexOfWinningMove) {
		this.board = board;
		this.playersSymbol = playersSymbol;
		this.indexOfWinningMove = indexOfWinningMove;
	}

	@Parameters
	public static Collection<Object[]> setupParamterisedInputs() {
		return asList(new Object[][] { 
				//tests for x-x,
				{ "x-------x", "x", 4 },
				{ "--x---x--", "x", 4 },
				//tests for xx-
				{ "--x-x----", "x", 6 },
				{ "----x-x--", "x", 2 },
				//tests for -xx
				{ "----x-x--", "x", 2 },
				{ "----x---x", "x", 0 },
				//negative tests
				{ "x---o----", "x", -1 },
				{ "--x-o----", "x", -1 },
				{ "---------", "x", -1 },
				{ "xx-------", "o", -1 }
		});
	}

	@Test
	public void shouldReturnTheIndexForAWinningMoveInTheGridForTheGivenPlayer() {
		DiagonalProcessor diagonalProcessor = new DiagonalProcessor(board);
		int index = diagonalProcessor.potentialWinningMove(playersSymbol);

		assertThat(index, is(indexOfWinningMove));
	}
}
