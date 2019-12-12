package sk.tsystems.gamestudio.controller;

import java.util.Formatter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;

@Controller
public class MinesweeperController {
	private Field field;

	@RequestMapping("/minesweeper")
	public String index() {
		field = new Field(10, 10, 10);
		return "minesweeper";
	}
		@RequestMapping("/minesweeper/open")
		public String openTile(int row, int column) {
			if(field.getState()==GameState.PLAYING)
				field.openTile(row, column);
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
					if(tile.getState()== State.CLOSED) {
						f.format("<a href='/minesweeper/open?row=%d&column=%d'><img src='/image/mines/closed.png'></img></a>",row, column);
				}
				
				if(tile.getState()==State.OPEN) {
					if(tile instanceof Mine) {
						f.format("<img src='/image/mines/mine.png'></img>");
					} else if(tile instanceof Clue) {
						f.format("<img src='/image/mines/open%d.png'></img>",((Clue) tile).getValue());
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

//	public String getHtmlField() {
//		Formatter sb = new Formatter();
//		sb.format("<table class='mines_field'>\n");
//		for (int row = 0; row < field.getRowCount(); row++) {
//			sb.format("<tr>\n");
//			for (int column = 0; column < field.getColumnCount(); column++) {
//				Tile tile = field.getTile(row, column);
//				sb.format("<td>\n");
//				sb.format("<a href='/mines?row=%d&column=%d'>", row, column);
//				sb.format("<img src='/images/mines/" + ".png'>\n");
//				sb.format("</a>");
//			}
//		}
//		sb.format("</table>\n");
//		return sb.toString();
//	}
}
