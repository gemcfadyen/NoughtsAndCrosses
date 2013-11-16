package katas.learning_kata.noughtsAndCrosses.prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import katas.learning_kata.noughtsAndCrosses.Grid;

public class CommandPrompt implements Prompt {

	private BufferedReader inputReader;
	private Writer outputWriter;

	public CommandPrompt(Reader inputReader, Writer outputWriter) {
		this.inputReader = new BufferedReader(inputReader);
		this.outputWriter = outputWriter;
	}

	@Override
	public int readNextMove() {
		return Integer.parseInt(readLine());
	}

	private String readLine() {
		try {
			return inputReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
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

	@Override
	public void printLoosingStatement() {
		try {
			outputWriter.write("NO_WINNER Game Over, there was no winner! \n Game Over");
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void printWinningStatement(String winningSymbol) {
		try {
			outputWriter.write("Congratulations [" + winningSymbol + "] you have won! \n Game Over");
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
