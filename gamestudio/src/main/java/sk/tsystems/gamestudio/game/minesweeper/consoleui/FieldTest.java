package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.RepeatedTest;

import sk.tsystems.gamestudio.game.minesweeper.core.*;

class FieldTest {
	private int fieldMineCount = 10;
	private Field field;

	@BeforeEach
	void init() {
		field = new Field(10, 10, fieldMineCount);
	}

	@RepeatedTest(10)
	void fieldShouldContainCorrectNumberOfMines() {
		int mineCount = 0;
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				if (field.getTile(row, column) instanceof Mine) {
					mineCount++;
				}
			}
		}
		assertEquals(10, mineCount, String.format(" There shoul be 10 mines in the field", fieldMineCount));

	}
}