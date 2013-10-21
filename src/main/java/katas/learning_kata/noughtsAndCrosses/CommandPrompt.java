package katas.learning_kata.noughtsAndCrosses;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
