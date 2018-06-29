package techedu.judge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import techedu.judge.entities.Course;
import techedu.judge.entities.Track;
import techedu.judge.entities.Video;
import techedu.judge.repositories.base.GenericRepository;

import java.util.List;

@RequestMapping("/courses")
@Controller
public class CourseController {

	@Autowired
	GenericRepository<Video> videoRepo;

	@Autowired
	GenericRepository<Course> courseRepo;

	@GetMapping (value = {"/{id}"})
	public String showVideos (@PathVariable (value="id") int id, Model model)
	{
		model = techedu.judge.controllers.BaseController.setGuest (model);
		List<Course> currentCourse = courseRepo.getBy ("id", id);
		model = BaseController.setGuest (model);

		if (currentCourse.size () == 0) {
			model.addAttribute ("url", "/courses/" + String.valueOf (id));
			return "404";
		}

		model.addAttribute ("course", currentCourse.get (0));
		model.addAttribute ("videos", videoRepo.getBy ("courseId", id));

		return "course";
	}
}
