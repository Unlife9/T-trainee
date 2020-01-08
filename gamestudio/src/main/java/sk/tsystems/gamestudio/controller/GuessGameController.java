package sk.tsystems.gamestudio.controller;

import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessGameController extends Interface {
	private int number;
	String game = "GuessGame";



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
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, getScore()));
			}

		} catch (Exception e) {

		}
		return "guessgame";

	}
	
	
	@RequestMapping("/guessgame/comment")
	public String comment(Comment comment){
		String content = comment.getContent();
		if (mainController.isLogged() && content.length()>=3 && content.length()<=20 ){
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName() + ": " , game,comment.getContent()));
		}
		return "guessgame";
	}
	
	@RequestMapping("/guessgame/rating1")
	public String rating1(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating2")
	public String rating2(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating3")
	public String rating3(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating4")
	public String rating4(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 4 ));
		}
		return "guessgame";
	}
	@RequestMapping("/guessgame/rating5")
	public String rating5(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 5 ));
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


	public List<Score> getScores() {
		return scoreService.getTopScores(game);

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
		return commentService.getComment(game);
		
	}

	public double getAverageRating(){
		return ratingService.getAverageRating(game);
	}


}
