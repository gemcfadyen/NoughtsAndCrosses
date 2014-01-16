package katas.learning_kata.noughtsAndCrosses.symbols;

import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.EMPTY;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.O;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.X;
import static katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol.valueOfChar;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ValidSymbolTest {
	
	@Test
	public void shouldReturnTheCharValueOfAValidSymbol() {
		assertThat(X.getCharValue(), is('x'));
	}
	
	@Test
	public void shouldReturnTheEnumForAValidSymbol() {
		assertThat(valueOfChar('o'), is(O));
	}
	
	@Test
	public void shouldReturnTheEnumForAnEmptySlot() {
		assertThat(valueOfChar('-'), is(EMPTY));
	}
}