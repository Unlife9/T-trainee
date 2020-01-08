package sk.tsystems.gamestudio.controller;

import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tsystems.gamestudio.entity.Player;
import sk.tsystems.gamestudio.service.PlayerService;
import sk.tsystems.gamestudio.service.PlayerServiceJPA;


@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MainController {
	private Player loggedPlayer;
	private String message;

	@Autowired
	private PlayerServiceJPA playerService;
	
	
	
	@RequestMapping("/")
public String index() {
	return "index";
	}
	
	
	@RequestMapping("/signin")
	public String signin(Player player) {
		String name = player.getName();
		String password = player.getPassword();
		List<Player> users = listAllUsers();
		boolean userExist = false;
		for(Player u: users ) {
			if(u.getName().equals(name)) {message="user name exists";
				userExist = true;
			}
		}
		if(!userExist && name!="" && password!="" && password.length()>= 5 && name.length()>= 5) {message="Registration completed";
			Player p = new Player(player.getName(), player.getPassword());
			playerService.addPlayer(p);
		}
		else {message="Wrong input";
			
		}
		return "redirect:/";
	}



	@RequestMapping("/login")
	public String login(String name, String password) {
		Player userInDB = playerService.getPlayer(name);
		if (userInDB != null) {
			if (password.equals(userInDB.getPassword())){
				loggedPlayer = userInDB;
			} else{message="wrong password";

			}
		}
		return "redirect:/";

	}

	@RequestMapping("/logout")
	public String logout() {
		loggedPlayer = null;
		return "redirect:/";
	}


	public boolean isLogged() {
		return loggedPlayer != null;

	}

	public Player getLoggedPlayer() {
		return loggedPlayer;
	}
	
	public List<Player> listAllUsers(){
		return playerService.listAllUsers();
	}
	public String getMessage(){
		return message;
	}
}
