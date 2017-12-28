package driving;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yzumi on 24/12/2017.
 */
public class Track implements Paintable {

    private final List<Wall> walls;

    public Track() {
        this.walls = new ArrayList<>();
        // Upper side:
        this.walls.add(new Wall(0, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(1 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(2 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(3 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(4 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(5 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(6 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(7 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(8 * Wall.WALL_X_SIZE, 9.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(9 * Wall.WALL_X_SIZE, 9 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(10 * Wall.WALL_X_SIZE, 8.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(11 * Wall.WALL_X_SIZE, 8 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(12 * Wall.WALL_X_SIZE, 7.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(13 * Wall.WALL_X_SIZE, 7 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(14 * Wall.WALL_X_SIZE, 7.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(15 * Wall.WALL_X_SIZE, 8 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(16 * Wall.WALL_X_SIZE, 8.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(17 * Wall.WALL_X_SIZE, 9 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(18 * Wall.WALL_X_SIZE, 9.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(19 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(20 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(21 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(22 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(23 * Wall.WALL_X_SIZE, 11 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(24 * Wall.WALL_X_SIZE, 12 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(25 * Wall.WALL_X_SIZE, 13 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(26 * Wall.WALL_X_SIZE, 13 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(27 * Wall.WALL_X_SIZE, 13 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(28 * Wall.WALL_X_SIZE, 12.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(29 * Wall.WALL_X_SIZE, 12 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(30 * Wall.WALL_X_SIZE, 11.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(31 * Wall.WALL_X_SIZE, 11 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(32 * Wall.WALL_X_SIZE, 10.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(33 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(34 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(35 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(36 * Wall.WALL_X_SIZE, 10 * Wall.WALL_Y_SIZE));
        // Lower side:
        this.walls.add(new Wall(0, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(1 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(2 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(3 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(4 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(5 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(6 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(7 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(8 * Wall.WALL_X_SIZE, 13.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(9 * Wall.WALL_X_SIZE, 13 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(10 * Wall.WALL_X_SIZE, 12.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(11 * Wall.WALL_X_SIZE, 12 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(12 * Wall.WALL_X_SIZE, 11.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(13 * Wall.WALL_X_SIZE, 11 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(14 * Wall.WALL_X_SIZE, 11.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(15 * Wall.WALL_X_SIZE, 12 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(16 * Wall.WALL_X_SIZE, 12.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(17 * Wall.WALL_X_SIZE, 13 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(18 * Wall.WALL_X_SIZE, 13.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(19 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(20 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(21 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(22 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(23 * Wall.WALL_X_SIZE, 15 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(24 * Wall.WALL_X_SIZE, 16 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(25 * Wall.WALL_X_SIZE, 17 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(26 * Wall.WALL_X_SIZE, 17 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(27 * Wall.WALL_X_SIZE, 17 * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(28 * Wall.WALL_X_SIZE, 16.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(29 * Wall.WALL_X_SIZE, 16 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(30 * Wall.WALL_X_SIZE, 15.5f * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(31 * Wall.WALL_X_SIZE, 15 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(32 * Wall.WALL_X_SIZE, 14.5f * Wall.WALL_Y_SIZE));
        //
        this.walls.add(new Wall(33 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(34 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(35 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
        this.walls.add(new Wall(36 * Wall.WALL_X_SIZE, 14 * Wall.WALL_Y_SIZE));
    }

    @Override
    public void paint(final Graphics graphics) {
        for(final Wall wall: this.walls) {
            wall.paint(graphics);
        }
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public List<Segment> getSegments() {
        final List<Segment> ret = new ArrayList<>();
        for(final Wall wall : this.walls) {
            ret.addAll(wall.getSegments());
        }
        return ret;
    }

}