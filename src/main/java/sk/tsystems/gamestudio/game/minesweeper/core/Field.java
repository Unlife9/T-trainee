package sk.tsystems.gamestudio.game.minesweeper.core;

import java.util.Random;

/**
 * Field represents playing field and game logic.
 */
public class Field {
	/**
	 * Playing field tiles.
	 */
	private final Tile[][] tiles;

	/**
	 * Field row count. Rows are indexed from 0 to (rowCount - 1).
	 */
	private final int rowCount;

	/**
	 * Column count. Columns are indexed from 0 to (columnCount - 1).
	 */
	private final int columnCount;

	/**
	 * Mine count.
	 */
	private final int mineCount;

	/**
	 * Game state.
	 */
	private GameState state = GameState.PLAYING;

	/**
	 * Constructor.
	 *
	 * @param rowCount    row count
	 * @param columnCount column count
	 * @param mineCount   mine count
	 */
	public Field(int rowCount, int columnCount, int mineCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.mineCount = mineCount;
		tiles = new Tile[rowCount][columnCount];

		// generate the field content
		generate(mineCount);
	}

	public GameState getState() {
		return state;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public Tile getTile(int row, int column) {
		return tiles[row][column];
	}
	private final long startMillis = System.currentTimeMillis();
	
	public int getScore() {
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000 );
		int number = 1000;
		int score = number - time;
		return score;

	}


	public void openTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.OPEN);
			if (tile instanceof Mine) {
				state = GameState.FAILED;
				return;
			}else if(tile instanceof Clue) {
				Clue clue = (Clue)tiles[row][column];
				if(clue.getValue() == 0 ) {
					openAdjacentMines(row, column);
				}
			}

			if (isSolved()) {
				state = GameState.SOLVED;
				return;
			}
		}
	}


	public void markTile(int row, int column) {
		Tile tile = tiles[row][column];
		if (tile.getState() == Tile.State.CLOSED) {
			tile.setState(Tile.State.MARKED);
		} else if (tile.getState() == Tile.State.MARKED) {
			tile.setState(Tile.State.CLOSED);
		}
	}

	/**
	 * Generates playing field, generate mines based on set mineCount iterates
	 * through field and add Clue when they dont have Mine
	 * 
	 * @return
	 */
	private void generate(int n) {
		Random r = new Random();
		int counter = 0;
		while (counter < mineCount) {
			int randomRow = r.nextInt(rowCount);
			int randomColumn = r.nextInt(columnCount);
			if (tiles[randomRow][randomColumn] == null) {
				tiles[randomRow][randomColumn] = new Mine();
				counter++;
			}
		}
		for (int f = 0; f < rowCount; f++) {
			for (int s = 0; s < columnCount; s++) {
				if (tiles[f][s] == null) {
					tiles[f][s] = new Clue(countAdjacentMines(f, s));
				}

			}
		}
	}

	/**
	 * Returns true if game is solved, false otherwise.
	 *
	 * @return true if game is solved, false otherwise
	 */
	public boolean isSolved() {
		if ((rowCount * columnCount) - getNumberOf(Tile.State.OPEN) == mineCount)
			return true;
		else
			return false;

	}
	



	private int getNumberOf(Tile.State state) {
		int count = 0;
		for (int row = 0; row < rowCount; row++) {
			for (int columnx = 0; columnx < columnCount; columnx++) {
				if (tiles[row][columnx].getState() == state) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Returns number of adjacent mines for a tile at specified position in the
	 * field.
	 *
	 * @param row    row number.
	 * @param column column number.
	 * @return number of adjacent mines.
	 */
	private int countAdjacentMines(int row, int column) {
		int count = 0;
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < rowCount) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < columnCount) {
						if (tiles[actRow][actColumn] instanceof Mine) {
							count++;
						}
					}
				}
			}
		}

		return count;
	}
	
	private void openAdjacentMines(int row, int column) {
		for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
			int actRow = row + rowOffset;
			if (actRow >= 0 && actRow < rowCount) {
				for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
					int actColumn = column + columnOffset;
					if (actColumn >= 0 && actColumn < columnCount) {
						
						if(tiles[actRow][actColumn] instanceof Clue) {
							Clue clue = (Clue)tiles[actRow][actColumn];
							if(clue.getValue() == 0 || clue.getValue() == 1 || clue.getValue() == 2 || clue.getValue() == 3 )
							openTile(actRow, actColumn);
							
						}
						}
					}
				}
			}
		}


}
