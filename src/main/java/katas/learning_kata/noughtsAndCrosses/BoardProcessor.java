package katas.learning_kata.noughtsAndCrosses;

public interface BoardProcessor {
	int GRID_DIMENSION = 3;
	int NO_MATCH_FOUND = -1;
	char EMPTY_CELL = '-';

	int potentialWinningMove(String playersSymbol);
}
