package driving;

/**
 * Created by Yzumi on 24/12/2017.
 */
public abstract class MovableObject {

    protected float x;
    protected float y;
    protected final float xSize;
    protected final float ySize;
    protected float xVelocity;
    protected float yVelocity;
    private long lastUpdateTime;

    public MovableObject(final float x, final float y, final float xSize, final float ySize, final float xVelocity) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xVelocity = xVelocity;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void updatePosition(final long currentTimeMillis) {
        final long timeDifference = currentTimeMillis - this.lastUpdateTime;
        this.lastUpdateTime = currentTimeMillis;
        this.x = this.x + timeDifference * this.xVelocity;
        this.y = this.y + timeDifference * this.yVelocity;
    }

}
