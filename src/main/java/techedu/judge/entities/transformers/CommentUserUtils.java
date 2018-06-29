package techedu.judge.entities.transformers;

import techedu.judge.entities.Comment;
import techedu.judge.entities.Course;
import techedu.judge.entities.Details.CommentDetails;
import techedu.judge.entities.Details.CourseDetails;
import techedu.judge.entities.Track;
import techedu.judge.entities.User;
import techedu.judge.repositories.base.GenericRepository;

import java.util.List;

public class CommentUserUtils {

	public static CommentDetails getPrintableComment (GenericRepository<User> users, Comment c) {
		CommentDetails answer = new CommentDetails (c);
		List<User> user = users.getBy ("id", c.getUserId ());
		if (user.size () == 0)
			answer.setUser (new User ());
		else
			answer.setUser (user.get (0));
		return answer;
	}
}