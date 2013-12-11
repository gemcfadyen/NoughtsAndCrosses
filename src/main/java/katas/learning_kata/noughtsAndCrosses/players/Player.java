package katas.learning_kata.noughtsAndCrosses.players;

import katas.learning_kata.noughtsAndCrosses.Grid;
import katas.learning_kata.noughtsAndCrosses.Symbol;

public interface Player {
	 Grid takesGo(Grid grid);
	 Symbol getSymbol();
}

