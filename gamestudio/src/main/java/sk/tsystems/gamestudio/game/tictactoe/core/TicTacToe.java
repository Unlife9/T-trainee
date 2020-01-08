package sk.tsystems.gamestudio.game.tictactoe.core;

public class TicTacToe {

	private String[][] field;
	public static final int row = 3;
	public static final int column = 3;
	private String regex = "\\s{3}";

	public TicTacToe() {
		field = new String[row][column];

	}

	public void initializeBoard() {
		for (int rows = 0; rows < row; rows++) {
			for (int columns = 0; columns < column; columns++) {
				field[rows][columns] = "   ";
			}
		}

	}

	public void setPlay(int rows, int columns, String player) {
		if (this.field[rows][columns].matches(regex))
			field[rows][columns] = " " + player + " ";
	}

	public boolean isGameOver() {
		for (int rows = 0; rows < row; rows++) {
			if (!field[rows][0].matches(regex) && field[rows][0].equals(field[rows][1]) && field[rows][1].equals(field[rows][2])) {
				return true;
			}
		}
		for (int columns = 0; columns < column; columns++) {
			if (!field[0][columns].matches(regex) && field[0][columns].equals(field[1][columns]) && field[1][columns].equals(field[2][columns]))
				return true;
		}
		if (!field[0][0].matches(regex) && field[0][0].equals(field[1][1]) && field[1][1].equals(field[2][2]))
			return true;
		if (!field[0][2].matches(regex) && field[0][2].equals(field[1][1]) && field[1][1].equals(field[2][0]))
			return true;
		return false;

	}

	public String printBoard() {
		String strBoard = "";
		for (int rows = 0; rows < row; rows++) {
			for (int columns = 0; columns < column; columns++) {
				if (columns != column - 1)
					strBoard += field[rows][columns] + "|";
				else
					strBoard += field[rows][columns];
			}
			if (rows != column - 1)
				strBoard += "\n---+---+---\n";
		}
		return strBoard;
	}

}
