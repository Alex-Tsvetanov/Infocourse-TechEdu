package techedu.judge.entities.transformers;

import techedu.judge.entities.Course;
import techedu.judge.entities.Details.CourseDetails;
import techedu.judge.entities.Track;
import techedu.judge.repositories.base.GenericRepository;

import java.util.List;

public class CourseTrackUtils {
	public static CourseDetails getFullCourse (GenericRepository<Track> tracks, Course c) {
		CourseDetails answer = new CourseDetails (c);
		List <Track> track = tracks.getBy ("id", c.getTrackId ());
		if (track.size () == 0)
			answer.setTrack (new Track ());
		else
			answer.setTrack (track.get (0));
		return answer;
	}
}
