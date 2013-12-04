package katas.learning_kata.noughtsAndCrosses.prompt;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Row;

public interface Prompt {
	int readNextMove();
	void promptUser();
	void displayBoard(List<Row> board);
	void printLoosingStatement();
	void printWinningStatement(String winningSymbol);

}
