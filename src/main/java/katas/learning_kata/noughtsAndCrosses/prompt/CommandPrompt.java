package katas.learning_kata.noughtsAndCrosses.prompt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import katas.learning_kata.noughtsAndCrosses.Cell;
import katas.learning_kata.noughtsAndCrosses.Row;

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
	public void displayBoard(List<Row> rows) {
		try {
			StringBuffer board = drawBoard(rows);
			outputWriter.write(board.toString());
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private StringBuffer drawBoard(List<Row> rows) {
		StringBuffer board = new StringBuffer();
		int position = -1;
		for (Row horizontalRow : rows) {
			Cell[] cells = horizontalRow.getCells();
			board.append(addSymbolIn(cells) + "    " + (++position) + " " + (++position) + " " + (++position) + "\n");
		}
		return board;
	}

	private StringBuffer addSymbolIn(Cell[] cells) {
		StringBuffer board = new StringBuffer();
		for (Cell cell : cells) {
			board.append(cell.getSymbol());
		}
		return board;
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
			outputWriter
					.write("NO_WINNER Game Over, there was no winner! \n Game Over");
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void printWinningStatement(String winningSymbol) {
		try {
			outputWriter.write("Congratulations [" + winningSymbol
					+ "] you have won! \n Game Over");
			outputWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
