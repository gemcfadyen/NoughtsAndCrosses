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
public class RowProcessorWinningMoveTest {
		private String board;
		private String playersSymbol;
		private int indexOfWinningMove;

		public RowProcessorWinningMoveTest(String board, String playersSymbol, int indexOfWinningMove) {
			this.board = board;
			this.playersSymbol = playersSymbol;
			this.indexOfWinningMove = indexOfWinningMove;
		}

		@Parameters
		public static Collection<Object[]> setupParamterisedInputs() {
			return asList(new Object[][] { 
					// tests for x-x
					{ "x-x------", "x", 1 },
					{ "---x-x---", "x", 4 }, 
					{ "------x-x", "x", 7 },
					// tests for xx-
					{ "xx-------", "x", 2 },
					{ "---xx----", "x", 5 }, 
					{ "------xx-", "x", 8 },
					// tests for -xx
					{ "-xx------", "x", 0 },
					{ "-------xx", "x", 6 },
					{ "----xx---", "x", 3 },
					//negative tests
					{ "xo-------", "x", -1 },
					{ "---xo----", "x", -1 },
					{ "------ox-", "x", -1 },
					{ "---------", "x", -1 },
					{ "xx-------", "o", -1 }
			});
		}

		@Test
		public void shouldReturnTheIndexForAWinningMoveInTheGridForTheGivenPlayer() {
			RowProcessor rows = new RowProcessor(board);
			int index = rows.potentialWinningMove(playersSymbol);

			assertThat(index, is(indexOfWinningMove));
		}
}
