package sk.tsystems.gamestudio.service;

import java.util.List;


import sk.tsystems.gamestudio.entity.Words;

public interface WordsService {

	void addWords(Words words);
	
	
	Words getWords(String slovak);
	
	public List<Words> listAllWords();

}


