package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NoughtsAndCrossesTest {
	@Mock Player playerX;
	@Mock Player playerO;
	@InjectMocks
	private NoughtsAndCrosses noughtsAndCrosses = new NoughtsAndCrosses();
	
	@Test
	public void gameShouldEndIfTherIsAWinner(){
		noughtsAndCrosses.playerXTakesTurn("0--x-x--0");
		assertThat(noughtsAndCrosses.containsWinningRow(), is(true));
		verifyNoMoreInteractions(playerX);
		verifyNoMoreInteractions(playerO);
	}
	

}
