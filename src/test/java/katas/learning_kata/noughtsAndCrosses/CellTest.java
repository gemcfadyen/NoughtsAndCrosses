package katas.learning_kata.noughtsAndCrosses;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

import org.junit.Test;

public class CellTest {

	@Test
	public void shouldReturnTheSymbolAndIndexContainedInTheCell() {
		Cell cell = new Cell(X, 1);
		assertThat(cell.getSymbol(), is(ValidSymbol.class));
		assertThat(cell.getSymbol(), is(X));
		assertThat(cell.getIndex(), is(1));
	}

}
