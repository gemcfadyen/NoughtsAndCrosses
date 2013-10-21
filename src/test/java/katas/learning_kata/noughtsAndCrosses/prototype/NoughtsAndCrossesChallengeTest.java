package katas.learning_kata.noughtsAndCrosses.prototype;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import katas.learning_kata.noughtsAndCrosses.prototype.CodedPlayer;
import katas.learning_kata.noughtsAndCrosses.prototype.NoughtsAndCrossesChallenge;
import katas.learning_kata.noughtsAndCrosses.prototype.Player;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
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

	@Test
	public void theCodedPlayerShouldTakeTheFirstTurn() {
		String initialMove = noughtsAndCrossesGame.getCodedPlayersFirstMove();
		assertThat(initialMove, is("x--------"));
	}
	
	@Test
	public void shouldDetermineTheNextMoveToMake() {
		String nextMove = noughtsAndCrossesGame.determineNextMove();
		assertThat(nextMove, is("x-o---x---"));
	}

	@Ignore  //how to imitate a user taking a go?
	public void askTheUserToTakeAGo() throws IOException {
		String humansInput = noughtsAndCrossesGame.promptHumanUserForInput();
	}

	@Test
	public void goAheadAndPlayTheGame() {
		noughtsAndCrossesGame.playAGame();
	}
}
