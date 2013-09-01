package katas.learning_kata.noughtsAndCrosses;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NoughtsAndCrossesChallengeTest {
	private static final int RANDOM_SEED = 10;
	private NoughtsAndCrossesChallenge noughtsAndCrossesGame;

	@Before
	public void setup() {
		noughtsAndCrossesGame = new NoughtsAndCrossesChallenge(RANDOM_SEED);

	}

	@Test
	public void shouldChooseThePlayerToTakeTheFirstGo() {
		Player player = noughtsAndCrossesGame.determineWhichPlayerWillGoFirst();

		assertThat(player instanceof CodedPlayer, is(true));
	}
}
