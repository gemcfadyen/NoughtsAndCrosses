package katas.learning_kata.noughtsAndCrosses.symbols;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InvalidSymbolTest {

	@Test
	public void shouldReturnInvalidSymbolsAsHavingNone() {
		assertThat(InvalidSymbol.NO_SYMBOL, is(None.class));
	}
}
