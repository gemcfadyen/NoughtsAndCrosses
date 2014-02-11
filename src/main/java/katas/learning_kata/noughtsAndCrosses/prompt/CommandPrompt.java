package katas.learning_kata.noughtsAndCrosses.prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Cell;
import katas.learning_kata.noughtsAndCrosses.Row;
import katas.learning_kata.noughtsAndCrosses.symbols.ValidSymbol;


public class CommandPrompt implements Prompt {
	private BufferedReader inputReader;
	private Writer outputWriter;

	public CommandPrompt(Reader inputReader, Writer outputWriter) {
		this.inputReader = new BufferedReader(inputReader);
		this.outputWriter = outputWriter;
	}
	
	@Override
	public void displayBoard(List<Row> rows) {
		StringBuffer board = drawBoard(rows);
		write(board.toString());
	}

	private StringBuffer drawBoard(List<Row> rows) {
		StringBuffer board = new StringBuffer();
		int position = -1;
		for (Row horizontalRow : rows) {
			Cell[] cells = horizontalRow.getCells();
			board.append(addSymbolIn(cells) + "    "
					+ addPositionsFrom(position, cells.length));
			position += cells.length;
		}
		return board;
	}

	private StringBuffer addPositionsFrom(int position, int dimension) {
		StringBuffer displayThePositions = new StringBuffer();
		for (int i = 0; i < dimension; i++) {
			displayThePositions.append(++position + " ");
		}
		displayThePositions.append("\n");
		return displayThePositions;
	}

	private StringBuffer addSymbolIn(Cell[] cells) {
		StringBuffer board = new StringBuffer();
		for (Cell cell : cells) {
			board.append(cell.getSymbol().getCharValue());
		}
		return board;
	}

	@Override
	public void promptUser() {
		write("Enter the index of your next move:\n");
	}

	@Override
	public void printGameOverStatement(ValidSymbol winningSymbol) {
		if(winningSymbol != null){
			write("Congratulations [" + winningSymbol + "] you have won! \n Game Over");
		}
		else { 
			write("No Winner! \n Game Over");
		}
	}
	
	@Override
	public void promptForGridDimension() {
		write("Please enter the grid dimension");
	}

	@Override
	public void promptForChoiceOfOpponent() {
		write("Enter 'h' to play against another human, Enter 'c' to play against the computer");
	}
	
	@Override
	public String readNextMove() {
		return readLine();
	}
	
	@Override
	public String readChoiceOfOpponent() {
		return readLine();
	}
	
	@Override
	public String readGridDimension() {
		return readLine();
	}
	
	private String readLine() {
		try {
			return inputReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	private void write(String message){
		try {
			outputWriter.write(message);
			outputWriter.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
