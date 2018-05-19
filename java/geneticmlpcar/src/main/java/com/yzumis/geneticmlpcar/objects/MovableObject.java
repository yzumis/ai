package com.yzumis.geneticmlpcar.objects;

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
        final long timeDifferenceMillis = currentTimeMillis - this.lastUpdateTime;
        final float timeDifferenceSeconds = timeDifferenceMillis / 1000f;
        this.lastUpdateTime = currentTimeMillis;
        this.x = this.x + timeDifferenceSeconds * this.xVelocity * 30;
        this.y = this.y + timeDifferenceSeconds * this.yVelocity * 60;
    }

}
