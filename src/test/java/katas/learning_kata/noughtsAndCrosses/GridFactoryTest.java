package katas.learning_kata.noughtsAndCrosses;

import static junit.framework.Assert.assertNotNull;

import org.junit.Test;

public class GridFactoryTest {

	@Test
	public void shouldReturnAGrid(){
		GridFactory gridFactory = new GridFactory();
		Grid grid = gridFactory.createGridWithDimension(3);
		
		assertNotNull(grid);
	}
}
