package techedu.judge.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

	@GetMapping (value={"/news"})
	public String home (Model model) {
		model = techedu.judge.controllers.BaseController.setGuest (model);

		return "news";
	}
}
