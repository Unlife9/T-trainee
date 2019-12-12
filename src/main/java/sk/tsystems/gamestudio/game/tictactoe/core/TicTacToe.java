package sk.tsystems.gamestudio.game.tictactoe.core;

public class TicTacToe {

	private String[][] board;
	public static final int row = 3;
	public static final int column = 3;
	private String regex = "\\s{3}";

	public TicTacToe() {
		board = new String[row][column];

	}

	public void initializeBoard() {
		for (int rows = 0; rows < row; rows++) {
			for (int columns = 0; columns < column; columns++) {
				board[rows][columns] = "   ";
			}
		}

	}

	public void setPlay(int rows, int columns, String player) {
		if (this.board[rows][columns].matches(regex))
			board[rows][columns] = " " + player + " ";
	}

	public boolean isGameOver() {
		for (int rows = 0; rows < row; rows++) {
			if (!board[rows][0].matches(regex) && board[rows][0].equals(board[rows][1]) && board[rows][1].equals(board[rows][2])) {
				return true;
			}
		}
		for (int columns = 0; columns < column; columns++) {
			if (!board[0][columns].matches(regex) && board[0][columns].equals(board[1][columns]) && board[1][columns].equals(board[2][columns]))
				return true;
		}
		if (!board[0][0].matches(regex) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
			return true;
		if (!board[0][2].matches(regex) && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
			return true;
		return false;

	}

	public String printBoard() {
		String strBoard = "";
		for (int rows = 0; rows < row; rows++) {
			for (int columns = 0; columns < column; columns++) {
				if (columns != column - 1)
					strBoard += board[rows][columns] + "|";
				else
					strBoard += board[rows][columns];
			}
			if (rows != column - 1)
				strBoard += "\n---+---+---\n";
		}
		return strBoard;
	}

}
