package driving;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 02/01/2018.
 */
public class TestApplication {

    public static void main(final String[] args) {
        final Track track = new Track();
        final Segment segment1 = new Segment(new Point2d(0, 0), new Point2d(1, 1));
        final Segment segment2 = new Segment(new Point2d(0, 10), new Point2d(0, 11));
        final List<Segment> segments = new ArrayList<>();
        segments.add(segment2);
        final List<Segment> trackSegments = track.getSegments();
        final List<Point2d> impactPoints = segment1.findImpactPoints(trackSegments);
        for(final Point2d point2d : impactPoints) {
            System.out.println("Impact point = (" + point2d.getX() + ", " + point2d.getY() + ")");
        }
    }

}
