package sk.tsystems.gamestudio.consoleui.Menu;

import java.util.Scanner;

import sk.tsystems.gamestudio.game.guessnumber.console.ConsoleUI;
import sk.tsystems.gamestudio.game.npuzzle.Field;
import sk.tsystems.gamestudio.game.npuzzle.Console.Console;
import sk.tsystems.gamestudio.game.tictactoe.consoleui.*;



public class ConsoleMenu {

	private static final String[] games = {"Minesweeper","Puzzle","GuessGame","TicTacToe"};
	public void run() {
		while (true) {
			try {
				
				System.out.println("Welcome to the GAMESTUDIO, Choose your game:");
 
				Scanner scanner = new Scanner(System.in);
				for (int i = 0; i < games.length; i++) {
					System.out.println((i + 1) + ". " + games[i]);
				}
 
				int input = Integer.parseInt(scanner.nextLine());
				if (input == 00) {
 
				}
				if (input > 0 && input <= games.length) {
				} else {
					System.out.println("Game Doesnt Exist");
				}
				switch (input) {
				case 1:
					System.out.println("Minesweepers starting...");

		
					break;
				case 2:
					System.out.println("Puzzle");
					Console ui1 = new Console();
					Field field = new Field(4, 4);
					ui1.play(field);
					
					break;
				case 3:
					System.out.println("GuessGame");
					ConsoleUI ui = new ConsoleUI();
					ui.game();
					break;
				case 4:
					System.out.println("TicTacToe");
					ConsoleUII ui2= new ConsoleUII();
					ui2.newGame();
					
					break;
				}return;
				
 
			} catch (NumberFormatException e) {
				System.err.println("Wrong input, Enter game number");
			}
		}

			
		
	}
}
