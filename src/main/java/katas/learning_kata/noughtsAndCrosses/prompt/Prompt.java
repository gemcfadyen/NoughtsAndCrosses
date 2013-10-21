package katas.learning_kata.noughtsAndCrosses.prompt;

import katas.learning_kata.noughtsAndCrosses.Grid;

public interface Prompt {

	int readNextMove();

	void displayBoard(Grid grid);

}
