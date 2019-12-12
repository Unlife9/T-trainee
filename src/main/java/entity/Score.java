package entity;

public class Score {
	private String username;
	private String game;
	private int value;

	public Score(String username, String game, int value) {
		super();
		this.username = username;
		this.game = game;
		this.value = value;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
