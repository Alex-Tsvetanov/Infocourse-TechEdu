package techedu.judge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import techedu.judge.entities.Course;
import techedu.judge.entities.Track;
import techedu.judge.entities.transformers.CourseTrackUtils;
import techedu.judge.repositories.base.GenericRepository;

import java.util.stream.Collectors;


@Controller
public class HomeController {

	@Autowired
	GenericRepository<Track> trackRepo;

	@Autowired
	GenericRepository<Course> courseRepo;

	@GetMapping(value={"","/","/home"})
	public String home (Model model) {
		model = techedu.judge.controllers.BaseController.setGuest (model);
		model.addAttribute ("tracks", trackRepo.getAll ().subList (0, 3));
		model.addAttribute ("courses",
				courseRepo.getAll ().subList (0, 3)
						.stream ()
						.map (x -> CourseTrackUtils.getFullCourse (trackRepo, x))
						.collect (Collectors.toList ()));

		return "home";
	}

	@GetMapping(value={"companies"})
	public String companies (Model model) {
		model = techedu.judge.controllers.BaseController.setGuest (model);

		return "companies";
	}

}