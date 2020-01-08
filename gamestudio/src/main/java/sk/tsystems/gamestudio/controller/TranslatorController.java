package sk.tsystems.gamestudio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;


import sk.tsystems.gamestudio.entity.Words;
import sk.tsystems.gamestudio.service.WordsService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TranslatorController extends Interface {
	@Autowired
	public WordsService wordsService;
	
	@RequestMapping("/translator")
	public String translator() {
		return "/translator";
	}
	
	@RequestMapping("translator/add")
	public String addwords(Words words) {
		String slovak = words.getSlovak();
		String english = words.getEnglish();
		List<Words> text = listAllWords();
		boolean wordsexist = false;
		for(Words u: text ) {
			if(u.getSlovak().equals(slovak)) {message="word exist in db";
			wordsexist = true;
			}
		}
		try {
			if(!wordsexist && slovak!="" && english!="") {message="Add completed";
			wordsService.addWords(new Words( words.getSlovak(), words.getEnglish()));
			}
			else {message="Wrong input";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/translator";
	}

	public List<Words> listAllWords(){
		return wordsService.listAllWords();
	}

	
}
