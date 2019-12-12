package service;

import java.util.List;

import entity.Score;

public interface ScoreService {
	void addScore(Score score);
	
	List<Score> getTopScores(String game);

}
