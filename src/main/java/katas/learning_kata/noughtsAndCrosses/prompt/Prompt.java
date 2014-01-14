package katas.learning_kata.noughtsAndCrosses.prompt;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Row;
import katas.learning_kata.noughtsAndCrosses.symbols.Symbol;

public interface Prompt {
	int readNextMove();
	int readGridDimension();
	String readChoiceOfOpponent();
	void promptForGridDimension();
	void promptUser();
	void promptForChoiceOfOpponent();
	void displayBoard(List<Row> board);
	void printGameOverStatement(Symbol winningSymbol);

}
