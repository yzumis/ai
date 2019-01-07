package com.yzumis.ai.applications.geneticmlpcar.object;

public abstract class MovableObject {

    protected double x;
    protected double y;
    protected final double xSize;
    protected final double ySize;
    protected double xVelocity;
    protected double yVelocity;
    private long lastUpdateTime;

    public MovableObject(final double x, final double y, final double xSize, final double ySize, final double xVelocity) {
        this.x = x;
        this.y = y;
        this.xSize = xSize;
        this.ySize = ySize;
        this.xVelocity = xVelocity;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public void updatePosition(final long currentTimeMillis) {
        final long timeDifferenceMillis = currentTimeMillis - this.lastUpdateTime;
        final double timeDifferenceSeconds = timeDifferenceMillis / 1000d;
        this.lastUpdateTime = currentTimeMillis;
        this.x = this.x + timeDifferenceSeconds * this.xVelocity * 30;
        this.y = this.y + timeDifferenceSeconds * this.yVelocity * 60;
    }

}

