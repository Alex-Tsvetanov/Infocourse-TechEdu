package techedu.judge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import techedu.judge.entities.Course;
import techedu.judge.entities.Track;
import techedu.judge.repositories.base.GenericRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/track")
@Controller
public class TrackController {

	@Autowired
	GenericRepository<Track> trackRepo;

	@Autowired
	GenericRepository<Course> courseRepo;

	@GetMapping (value = {"/{id}"})
	public String showCourses (@PathVariable (value="id") int id, Model model)
	{
		model = techedu.judge.controllers.BaseController.setGuest (model);
		List<Track> currentTrack = trackRepo.getBy ("id", id);
		model = techedu.judge.controllers.BaseController.setGuest (model);

		if (currentTrack.size () == 0) {
			model.addAttribute ("url", "/track/" + String.valueOf (id));
			return "404";
		}

		model.addAttribute ("track", currentTrack.get (0));
		model.addAttribute ("courses",
				courseRepo.getBy ("trackId", id));

		return "track";
	}
}
