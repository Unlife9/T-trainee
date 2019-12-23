package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.guessnumber.GuessGame;
import sk.tsystems.gamestudio.game.guessnumber.console.ConsoleUI;
import sk.tsystems.gamestudio.service.CommentService;
import sk.tsystems.gamestudio.service.PlayerServiceJPA;
import sk.tsystems.gamestudio.service.RatingService;
import sk.tsystems.gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessGameController {
	private int number;
	private String message;
	long startMillis;

	@Autowired
	private MainController mainController;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private PlayerServiceJPA playerService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private RatingService ratingService;

	@RequestMapping("/guessgame")
	public String index() {
		message = "";
		number = (int) (Math.random() * 100 + 1);
		startMillis = System.currentTimeMillis();
		return "guessgame";
	}

	@RequestMapping("/guessgame/guess")
	public String guess(String value) {
		try {
			int parseValue = Integer.parseInt(value);
			evaluate(parseValue);
			if (getSolved(message) && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), "GuessGame", getScore()));
			}

		} catch (Exception e) {

		}
		return "guessgame";

	}
	
	
	@RequestMapping("/guessgame/comment")
	public String comment(Comment comment) {
		if (mainController.isLogged()){
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName() + ": " , "GuessGame",comment.getContent()));
		}
		return "guessgame";
	}
	
	@RequestMapping("/guessgame/rating1")
	public String rating1(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessGame", 1 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating2")
	public String rating2(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessGame", 2 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating3")
	public String rating3(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessGame", 3 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating4")
	public String rating4(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessGame", 4 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating5")
	public String rating5(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), "GuessGame", 5 ));
		}
		return "guessgame";
	}

	public void evaluate(int value) {
		if (value < number) {
			message = "Thats too small";

		} else if (value > number) {
			message = "Thats too big";

		} else {

			message = "You won";

		}
	}

	public String getMessage() {
		return message;
	}

	public List<Score> getScores() {
		return scoreService.getTopScores("GuessGame");

	}

	public int getScore() {
		int time = (int) ((System.currentTimeMillis() - startMillis) / 1000);
		int number = 500;
		int score = number - time;
		return score;

	}

	public boolean getSolved(String message) {
		if (message.equals("You won"))
			return true;
		else {
			return false;
		}
	}
	
	public List<Comment> getComment(){
		return commentService.getComment("GuessGame");
		
	}

	public double getAverageRating(){
		return ratingService.getAverageRating("GuessGame");
	}


}
