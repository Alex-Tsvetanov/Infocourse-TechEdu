package techedu.judge.controllers.REST_API.Resources.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import techedu.judge.controllers.ModelObjects.ExitState;
import techedu.judge.controllers.ModelObjects.ExitStateData;
import techedu.judge.controllers.REST_API.Resources.Authentication;
import techedu.judge.entities.Course;
import techedu.judge.entities.Track;
import techedu.judge.entities.User;
import techedu.judge.entities.Video;
import techedu.judge.repositories.base.GenericRepository;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping ("/resources/courses")
public class CourseAdd {
	@Autowired
	private GenericRepository<Track> tracks;

	@Autowired
	private GenericRepository<Course> courses;

	@Autowired
	private GenericRepository<Video> videos;

	@PostMapping (value = "/add", produces = "application/json")
	public ResponseEntity addCourse (HttpSession session
			, @RequestParam("name") String name
			, @RequestParam("trackId") int trackId
	) {
		if (!Authentication.isAdmin (session))
			return new ResponseEntity ("Permission denied", HttpStatus.UNAUTHORIZED);
		if (tracks.getBy ("id", trackId).size () == 0)
			return new ResponseEntity ("Invalid track id", HttpStatus.NO_CONTENT);
		Course unit = new Course ();
		unit.setName (name);
		unit.setTrackId (trackId);
		final Course newCourse;
		try {
			newCourse = courses.create (unit);
		} catch (Exception ex) {
			return new ResponseEntity ("Cannot add this course: " + ex.getMessage (), HttpStatus.SERVICE_UNAVAILABLE);
		}
		return new ResponseEntity (newCourse, HttpStatus.CREATED);
	}
}