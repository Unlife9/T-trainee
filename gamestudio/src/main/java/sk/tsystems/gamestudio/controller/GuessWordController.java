package sk.tsystems.gamestudio.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import sk.tsystems.gamestudio.entity.Score;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessWordController extends Interface {
	String[] words = { "water", "dog", "car","window","tea","computer","cat","light","house","movie", "voda", "pes", "auto","okno","caj","pocitac","macka","svetlo","dom","film" };
	String word = words[(int) (Math.random() * words.length * 1)];
	int count;
	int message1;
	String message2;
	String game = "GuessWord";


	


	@RequestMapping("/guessword")
	public String index() {
		message="translate word";
		message = word;
		if (word == "water")
			message = "voda";
		if (word == "dog")
			message = "pes";
		if (word == "car")
			message = "auto";
		if (word == "window")
			message = "okno";
		if (word == "tea")
			message = "caj";
		if (word == "computer")
			message = "pocitac";
		if (word == "cat")
			message = "macka";
		if (word == "light")
			message = "svetlo";
		if (word == "house")
			message = "dom";
		if (word == "movie")
			message = "film";
		if (word == "voda")
			message = "water";
		if (word == "pes")
			message = "dog";
		if (word == "auto")
			message = "car";
		if (word == "okno")
			message = "window";
		if (word == "caj")
			message = "tea";
		if (word == "pocitac")
			message = "computer";
		if (word == "macka")
			message = "cat";
		if (word == "svetlo")
			message = "light";
		if (word == "dom")
			message = "house";
		if (word == "film")
			message = "movie";
		return "guessword";

	}

	@RequestMapping("/guessword/word")
	public String term(String value) {
		String parseValue = String.format(value, word);
		evaluate(parseValue);
		try {
			if (message2.equals("Congratulation you win") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, getScore()));
			}
			if (message2.equals("You lose") && mainController.isLogged()) {
				scoreService.addScore(new Score(mainController.getLoggedPlayer().getName(), game, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "guessword";
	}

	@RequestMapping("/guessword/generate")
	public String generated() {

		word = words[(int) (Math.random() * words.length * 1)];
		message = word;
		int count = 0;
		message1 = count;
		message2 = "";
		if (word == "water")
			message = "voda";
		if (word == "dog")
			message = "pes";
		if (word == "car")
			message = "auto";
		if (word == "window")
			message = "okno";
		if (word == "tea")
			message = "caj";
		if (word == "computer")
			message = "pocitac";
		if (word == "cat")
			message = "macka";
		if (word == "light")
			message = "svetlo";
		if (word == "house")
			message = "dom";
		if (word == "movie")
			message = "film";
		if (word == "voda")
			message = "water";
		if (word == "pes")
			message = "dog";
		if (word == "auto")
			message = "car";
		if (word == "okno")
			message = "window";
		if (word == "caj")
			message = "tea";
		if (word == "pocitac")
			message = "computer";
		if (word == "macka")
			message = "cat";
		if (word == "svetlo")
			message = "light";
		if (word == "dom")
			message = "house";
		if (word == "film")
			message = "movie";

		return "guessword";
	}

	private void evaluate(String guess) {
		
		if (guess.equals(word)) {
			message2 = "Congratulation you win";
		}
		else count++; { message1 = count;
		}
		if (count < 6 && message2 !="Congratulation you win" );
		
		else if (count >= 7) {message2="You lose";
		message1= 7;
		}

	}
	
	public int getMessage1() {
		return message1;
	}
	
	public String getMessage2() {
		return message2;
	}
	
	public int getScore() {
		int start = 100;
		int score = start - count * 14;
		return score;
	}


}