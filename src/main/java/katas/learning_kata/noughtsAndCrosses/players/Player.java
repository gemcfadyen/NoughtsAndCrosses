package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;

public interface Player {
	 Grid takesGo(Grid grid);
	 String getSymbol();
}

