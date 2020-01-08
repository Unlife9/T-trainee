package sk.tsystems.gamestudio.game.npuzzle;

import sk.tsystems.gamestudio.game.npuzzle.Console.*;

public class Main {

	public static void main(String[] args) {
		Console ui = new Console();
		Field field = new Field(3, 3);
		ui.play(field);
	}
}
