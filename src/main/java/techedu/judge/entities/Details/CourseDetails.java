package techedu.judge.entities.Details;

import techedu.judge.entities.Course;
import techedu.judge.entities.Track;

public class CourseDetails extends Course {
	private Track track;
	private int videos;

	public CourseDetails (Course c)
	{
		super (c.getId (), c.getName (), c.getTrackId ());
	}

	public Track getTrack () { return this.track; }
	public void setTrack (Track track) { this.track = track; }

	public int getVideos () { return this.videos; }
	public void setVideos (int videos) { this.videos = videos; }
}
