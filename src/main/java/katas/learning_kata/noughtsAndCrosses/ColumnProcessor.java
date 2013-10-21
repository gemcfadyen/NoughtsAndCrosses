package katas.learning_kata.noughtsAndCrosses;

public class ColumnProcessor extends Processor {

	private String board;

	public ColumnProcessor(String board) {
		this.board = board;
	}

	public int potentialWinningMove(String playersSymbol) {
		for (int columnIndex = 0; columnIndex < GRID_DIMENSION; columnIndex++) {
			String column = getColumnStartingAtIndex(columnIndex);
			int[] cells = calculateColumnCells(columnIndex);
			int position = findIndexOfWinningMoveFor(column, playersSymbol);
			if (isAWinningMoveAt(position))
				return winningPosition(cells, position);
		}
		return NO_MATCH_FOUND;
	}
	

	private String getColumnStartingAtIndex(int startOfVerticleRow) {
		StringBuffer verticleRow = new StringBuffer();
		verticleRow.append(board.charAt(startOfVerticleRow))
				.append(board.charAt(startOfVerticleRow + 3))
				.append(board.charAt(startOfVerticleRow + 6));
		return verticleRow.toString();
	}
	
	private int[] calculateColumnCells(int columnIndex) {
		return new int[] { columnIndex, GRID_DIMENSION + columnIndex,
				GRID_DIMENSION * 2 + columnIndex };
	}
	

}
