package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.npuzzle.Field;
import sk.tsystems.gamestudio.game.npuzzle.Tile;
import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;



@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController extends Interface {
	private Field field = new Field(4,4);
	String game = "Npuzzle";
	
	
	@RequestMapping("/puzzle")
	public String index() {
		return "puzzle";
	}
	
	@RequestMapping("/puzzle/generated")
	public String generate() {
		field.shuffle();
		return "puzzle";
	}

	@RequestMapping("/puzzle/move")
	public String move(int tile) {
		field.move(tile);
		if (isSolved() && mainController.isLogged()){
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, field.getScore()));
		}
		return "puzzle";
	}
	
	@RequestMapping("/puzzle/comment")
	public String comment(Comment comment) {
		String content = comment.getContent();
		if (mainController.isLogged() && content.length()>=3 && content.length()<=20){
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName() + ": " , game,comment.getContent()));
		}
		return "puzzle";
	}
	
	@RequestMapping("/puzzle/rating1")
	public String rating1(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1 ));
		}
		return "puzzle";
	}
	@RequestMapping("/puzzle/rating2")
	public String rating2(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2 ));
		}
		return "puzzle";
	}
	@RequestMapping("/puzzle/rating3")
	public String rating3(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3 ));
		}
		return "puzzle";
	}
	@RequestMapping("/puzzle/rating4")
	public String rating4(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 4 ));
		}
		return "puzzle";
	}
	@RequestMapping("/puzzle/rating5")
	public String rating5(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 5 ));
		}
		return "puzzle";
	}

	
	
 
	public String getMessage() {
		return "Hello from Java";
	}
 
	public String getHtmlField() {
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null)
					f.format("<a href='/puzzle/move?tile=%d'><img src='/image/puzzle/img%d.jpg'></img></a>", tile.getValue(),tile.getValue());
				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();
 
	}
	
	public boolean isSolved() {
		return field.isSolved();
	}
	
	public List<Score> getScores(){
		return scoreService.getTopScores(game);
		
	}
	
	public List<Comment> getComment(){
		return commentService.getComment(game);
		
	}
	public double getAverageRating(){
		return ratingService.getAverageRating(game);
	}
	
	
	
}

