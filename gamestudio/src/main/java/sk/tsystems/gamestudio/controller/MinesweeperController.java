package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Comment;
import sk.tsystems.gamestudio.entity.Rating;
import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesweeperController extends Interface {
	private Field field;
	private boolean marking;
	String game = "Minesweeper";
	

	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(20, 20, 3);
		return "minesweeper";
	}
	
	@RequestMapping("/minesweeper/rating1")
	public String rating1(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 1 ));
		}
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/rating2")
	public String rating2(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 2 ));
		}
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/rating3")
	public String rating3(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 3 ));
		}
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/rating4")
	public String rating4(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 4 ));
		}
		return "minesweeper";
	}
	@RequestMapping("/minesweeper/rating5")
	public String rating5(Rating rating) {
		if (mainController.isLogged()){
			ratingService.setRating(new Rating(mainController.getLoggedPlayer().getName(), game, 5 ));
		}
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/open")
	public String openTile(int row, int column) {
		if (field.getState() == GameState.PLAYING)
			if (marking)
				field.markTile(row, column);
			else
				field.openTile(row, column);
		if (field.getState() == GameState.SOLVED && mainController.isLogged()) {
			scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, field.getScore()));
		}

		return "minesweeper";
	}
	
	
	@RequestMapping("minesweeper/comment")
	public String comment(Comment comment) {
		String content = comment.getContent();
		if (mainController.isLogged()&& content.length()>=3 && content.length()<=20){
			commentService.addComment(new Comment(mainController.getLoggedPlayer().getName() + ": " , game,comment.getContent()));
		}
		return "minesweeper";
	}

	@RequestMapping("/minesweeper/change")
	public String change() {
		marking = !marking;
		return "minesweeper";
	}

	public String getHtmlField() {
		Formatter f = new Formatter();
		f.format("<table>\n");
		for (int row = 0; row < field.getRowCount(); row++) {
			f.format("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {
				f.format("<td>\n");
				Tile tile = field.getTile(row, column);
				if (tile != null) {
					if (tile.getState() == State.CLOSED) {
						f.format(
								"<a href='/minesweeper/open?row=%d&column=%d'><img src='/image/mines/closed.png'></img></a>",
								row, column);
					}
					if (tile.getState() == State.MARKED) {
						f.format(
								"<a href='/minesweeper/open?row=%d&column=%d'><img src='/image/mines/marked.png'></img></a>",
								row, column);
					}

					if (tile.getState() == State.OPEN) {
						if (tile instanceof Mine) {
							f.format("<img src='/image/mines/mine.png'></img>");
						} else if (tile instanceof Clue) {
							f.format("<img src='/image/mines/open%d.png'></img>", ((Clue) tile).getValue());
						}
					}
				}
				f.format("</td>\n");
			}
			f.format("</tr>\n");
		}
		f.format("</table>\n");
		return f.toString();

	}

	public boolean isMarking() {
		return marking;
	}

	public boolean isSolved() {
		return field.isSolved();
	}

	public List<Score> getScores() {
		return scoreService.getTopScores(game);

	}

	public List<Comment> getComment(){
		return commentService.getComment(game);
		
	}
	public double getAverageRating(){
		return ratingService.getAverageRating(game);
	}
}
