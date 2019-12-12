package sk.tsystems.gamestudio.game.tictactoe.consoleui;

import sk.tsystems.gamestudio.game.tictactoe.core.*;
import java.util.Scanner;

public class ConsoleUII {
	public static void newGame() {
		Scanner input = new Scanner(System.in);
		TicTacToe game = new TicTacToe();
		long startMillis = System.currentTimeMillis();
		game.initializeBoard();
		String player = "X";
		do {
			System.out.println(game.printBoard());

			switch (player) {
			case "O": {
				extracted(input, game, player);
				player = "X";
				break;
			}
			case "X": {
				extracted(input, game, player);
				player = "O";
				break;

			}
			}

			if (game.isGameOver()) {
				System.out.println(game.printBoard() + "\n" + " You Win! " + (System.currentTimeMillis() - startMillis) / 1000 + " sekund");
				break;
			}
		} while (true);

	}

	private static void extracted(Scanner input, TicTacToe game, String player) {
		System.out.println("Player " + player + " Zadaj suradnice pre riadky(0-2): ");
		int row = input.nextInt();
		System.out.println("Player " + player + " Zadaj suradnice pre stlpce(0-2): ");
		int column = input.nextInt();
		game.setPlay(row, column, player);

	}
}
