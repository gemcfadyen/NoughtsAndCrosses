package katas.learning_kata.noughtsAndCrosses.prompt;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import katas.learning_kata.noughtsAndCrosses.Grid;

public class CommandPrompt implements Prompt {

	private Reader inputReader;
	private Writer outputWriter;

	public CommandPrompt(Reader inputReader, Writer outputWriter) {
		this.inputReader = inputReader;
		this.outputWriter = outputWriter;
	}

	@Override
	public int readNextMove() {
		try {
			return Character.getNumericValue(inputReader.read());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	@Override
	public void displayBoard(Grid grid) {
		try {
			outputWriter.write(grid.toString());
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void promptUser() {
		try {
			outputWriter.write("Enter the index of your next move:\n");
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
