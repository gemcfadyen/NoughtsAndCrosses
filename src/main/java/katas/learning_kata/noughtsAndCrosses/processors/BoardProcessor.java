package katas.learning_kata.noughtsAndCrosses.processors;

public interface BoardProcessor {
	int GRID_DIMENSION = 3;
	int NO_MATCH_FOUND = -1;
	char EMPTY_CELL = '-';

	int potentialWinningMove(String playersSymbol);
	boolean hasWinner();
}
