package techedu.judge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techedu.judge.entities.*;
import techedu.judge.entities.Details.CommentDetails;
import techedu.judge.entities.transformers.CommentUserUtils;
import techedu.judge.repositories.base.GenericRepository;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/videos")
@Controller
public class VideoController {

	@Autowired
	GenericRepository<User> users;

	@Autowired
	GenericRepository<Video> videoRepo;

	@Autowired
	GenericRepository<Course> courseRepo;

	@Autowired
	GenericRepository<Comment> commentRepo;

	@GetMapping (value = {"/{id}"})
	public String showVideo (@PathVariable (value="id") int id, Model model)
	{
		model = techedu.judge.controllers.BaseController.setGuest (model);
		List<Video> currentVideo = videoRepo.getBy ("id", id);
		model = BaseController.setGuest (model);

		if (currentVideo.size () == 0) {
			model.addAttribute ("url", "/videos/" + String.valueOf (id));
			return "404";
		}

		List <Comment> comments = commentRepo.getBy ("videoId", id);
		comments.sort ((x, y) -> (y.getTime ().compareTo (x.getTime ())));
		comments = comments.stream ().map (x -> CommentUserUtils.getPrintableComment (users, x)).collect (Collectors.toList ());
		model.addAttribute ("video", currentVideo.get (0));
		model.addAttribute ("comments", comments);
		model.addAttribute ("number", comments.size ());

		return "video";
	}
	@RequestMapping(value = {"/{id}/comment"})
	public String addComment (HttpSession session, @RequestParam(value = "text", required = true) String text, @PathVariable (value="id") int id, Model model)
	{
		User sender = (User)session.getAttribute ("user");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		commentRepo.create (new Comment (sender.getId (), id, dtf.format (now), text));
		return "redirect:/videos/" + id;
	}
}
