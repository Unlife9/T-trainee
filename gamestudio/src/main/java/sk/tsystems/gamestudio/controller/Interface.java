package sk.tsystems.gamestudio.controller;

import org.springframework.beans.factory.annotation.Autowired;

import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;



public abstract class Interface {
	public String message;
	long startMillis;
	
	@Autowired
	public MainController mainController;

	@Autowired
	public ScoreService scoreService;


	@Autowired
	public CommentService commentService;

	@Autowired
	public RatingService ratingService;
	
	public String getMessage() {
		return message;
}
}
