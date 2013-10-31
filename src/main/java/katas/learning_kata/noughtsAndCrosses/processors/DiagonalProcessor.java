package katas.learning_kata.noughtsAndCrosses.processors;

import static java.lang.String.valueOf;
import static katas.learning_kata.noughtsAndCrosses.Grid.BOTTOM_LEFT_CORNER;
import static katas.learning_kata.noughtsAndCrosses.Grid.BOTTOM_RIGHT_CORNER;
import static katas.learning_kata.noughtsAndCrosses.Grid.CENTER_CELL;
import static katas.learning_kata.noughtsAndCrosses.Grid.O;
import static katas.learning_kata.noughtsAndCrosses.Grid.TOP_LEFT_CORNER;
import static katas.learning_kata.noughtsAndCrosses.Grid.TOP_RIGHT_CORNER;
import static katas.learning_kata.noughtsAndCrosses.Grid.X;

import java.util.ArrayList;
import java.util.List;


public class DiagonalProcessor extends Processor {
	private String board;

	public DiagonalProcessor(String board){
		this.board = board;
	}

	@Override
	public int potentialWinningMove(String playersSymbol) {
		List<int[]> diagonalIndexes = populateDiagonalIndices();
		for (int[] diagonalIndex : diagonalIndexes) {
			String diagonalRow = getDiagonalRow(diagonalIndex[0],
					diagonalIndex[1], diagonalIndex[2]);
			int position = findIndexOfWinningMoveFor(diagonalRow, playersSymbol);
			if (isAWinningMoveAt(position))
				return winningPosition(diagonalIndex, position);
		}
		return NO_MATCH_FOUND;
	}
	
	private List<int[]> populateDiagonalIndices() {
		int[] forwardSlashDiagonalIndexes = new int[] { TOP_RIGHT_CORNER, CENTER_CELL, BOTTOM_LEFT_CORNER };
		int[] backslashDiagonalIndexes = new int[] { TOP_LEFT_CORNER, CENTER_CELL, BOTTOM_RIGHT_CORNER };
		List<int[]> diagonalIndexes = new ArrayList<int[]>();
		diagonalIndexes.add(backslashDiagonalIndexes);
		diagonalIndexes.add(forwardSlashDiagonalIndexes);
		return diagonalIndexes;
	}
	
	private String getDiagonalRow(int start, int middle, int end) {
		StringBuffer diagonalRow = new StringBuffer();
		diagonalRow.append(board.charAt(start)).append(board.charAt(middle)).append(board.charAt(end));
		return diagonalRow.toString();
	}


	public String getWinningSymbol() {
		List<int[]> diagonalIndexes = populateDiagonalIndices();
		for (int[] diagonalIndex : diagonalIndexes) {
			String diagonalRow = getDiagonalRow(diagonalIndex[0], diagonalIndex[1], diagonalIndex[2]);
			if (hasWinningEntryOfNoughts(diagonalRow) ) {
				return O;
			}
			
			if(hasWinningEntryForCrosses(diagonalRow)){
				return X;
			}
		}
		return valueOf(NO_MATCH_FOUND);
	}
}
