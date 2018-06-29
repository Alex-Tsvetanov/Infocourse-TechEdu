package techedu.judge.entities.Details;

import techedu.judge.entities.Comment;
import techedu.judge.entities.Course;
import techedu.judge.entities.Track;
import techedu.judge.entities.User;

public class CommentDetails extends Comment {
	private User user;

	public CommentDetails (Comment c)
	{
		super (c.getId (), c.getUserId (), c.getVideoId (), c.getTime (), c.getText ());
	}

	public User getUser () { return this.user; }
	public void setUser (User user) { this.user = user; }
}
