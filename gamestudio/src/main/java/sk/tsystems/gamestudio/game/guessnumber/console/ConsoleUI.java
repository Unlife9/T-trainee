package sk.tsystems.gamestudio.game.guessnumber.console;

import java.util.Scanner;

public class ConsoleUI {

	final long startMillis = System.currentTimeMillis();

	public void game() {
		processInput();
		
	}

	public void processInput() {

		int number;

		number = (int) (Math.random() * 100 + 1);

		Scanner scanner = new Scanner(System.in);
		
		final long startMillis = System.currentTimeMillis();

		while (true) {
			try {
				int guess;
				System.out.print("Vloz cislo (1-100): ");

				guess = Integer.parseInt(scanner.nextLine());

				if (guess == number) {
					System.out.println("Uhadol si, Gratulujem! Vyherne cislo je: " + number + " Za cas: "
							+ (System.currentTimeMillis() - startMillis) / 1000 + " sekund");
					return;
				} else if (guess < number)
					System.out.println("Tvoje cislo je vacsie ako skryte cislo!");
				else if (guess > number)
					System.out.println("Tvoje cislo je mensie ako skryte cislo!");

			} catch (Exception e) {
				System.err.println("Zly vstup, pouzij cislo!");
			}
		}
	

	}

}