package katas.learning_kata.noughtsAndCrosses.prompt;

import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Row;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;

public interface Prompt {
	String readNextMove();
	String readGridDimension();
	String readChoiceOfOpponent();
	void promptForGridDimension();
	void promptUser();
	void promptForChoiceOfOpponent();
	void displayBoard(List<Row> board);
	void printGameOverStatement(ValidSymbol winningSymbol);

}
