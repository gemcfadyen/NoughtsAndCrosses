package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.Grid.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CellTest {

	@Test
	public void shouldReturnTheSymbolContainedInTheCell() {
		Cell cell = new Cell(X);
		assertThat(cell.getSymbol(), is(X));

	}
}
