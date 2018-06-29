package techedu.judge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import techedu.judge.entities.User;

@Controller
public class BaseController {

	public static Model setGuest (Model model) {
		if (!model.containsAttribute ("user"))
			model.addAttribute ("user", new User ());

		return model;
	}
}