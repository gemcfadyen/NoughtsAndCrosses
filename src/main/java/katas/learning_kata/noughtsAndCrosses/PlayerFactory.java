package katas.learning_kata.noughtsAndCrosses;

import katas.learning_kata.noughtsAndCrosses.players.AutomatedPlayer;
import katas.learning_kata.noughtsAndCrosses.players.HumanPlayer;
import katas.learning_kata.noughtsAndCrosses.players.Player;
import katas.learning_kata.noughtsAndCrosses.prompt.Prompt;

public class PlayerFactory {

	public Player createOpponentPlayer(String playerType, Symbol symbol, Prompt prompt) {
		Player opponent;

		if (playerType.equalsIgnoreCase("c")) {
			opponent = new AutomatedPlayer(symbol);
		} else {
			opponent = new HumanPlayer(symbol, prompt);
		}
		return opponent;
	}

}
