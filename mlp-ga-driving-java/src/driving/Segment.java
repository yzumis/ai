package driving;

import java.util.List;

/**
 * Created by Yzumi on 28/12/2017.
 */
public class Segment {

    private final Point originPoint;
    private final Point destinationPoint;

    public Segment(final Point originPoint, final Point destinationPoint) {
        this.originPoint = originPoint;
        this.destinationPoint = destinationPoint;
    }

    public List<Point> findImpactPoints(final List<Segment> trackSegments) {
        return null; // ### TODO From here
    }
}
