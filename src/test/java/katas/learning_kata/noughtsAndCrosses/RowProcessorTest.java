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
public class RowProcessorTest {
		private String board;
		private String playersSymbol;
		private int indexOfWinningMove;

		public RowProcessorTest(String board, String playersSymbol, int indexOfWinningMove) {
			this.board = board;
			this.playersSymbol = playersSymbol;
			this.indexOfWinningMove = indexOfWinningMove;
		}

		@Parameters
		public static Collection<Object[]> setupParamterisedInputs() {
			return asList(new Object[][] { 
					//tests for x-x
					{ "x-x------", "x", 1 },
					{ "---x-x---", "x", 4 }, 
					{ "------x-x", "x", 7 }
			});
		}

		@Test
		public void shouldReturnTheIndexForAWinningMoveInTheGridForTheGivenPlayer() {
			RowProcessor rows = new RowProcessor(board);
			int index = rows.potentialWinningMove(playersSymbol);

			assertThat(index, is(indexOfWinningMove));
		}
}
