import greenfoot.*;

public class Player2 extends Actor {

    public static final int WIDTH  = 40;
    public static final int HEIGHT = 55;

    private static final double GRAVITY    = 0.7;
    private static final double JUMP_VY    = -13.0;
    private static final double MOVE_SPEED = 4.5;
    private static final double FRICTION   = 0.8;
    private static final double KICK_FORCE = 13.0;
    private static final int    KICK_RANGE = 62;

    private double  vx = 0, vy = 0;
    private boolean onGround = true;
    private boolean frozen   = false;
    private int     kickTimer = 0;

    public Player2() {
    }

    public void act() {
        if (frozen) return;
        handleInput();
        applyPhysics();
        constrainToWorld();
    }

    private void handleInput() {
        if (Greenfoot.isKeyDown("left"))
            vx = -MOVE_SPEED;
        else if (Greenfoot.isKeyDown("right"))
            vx = MOVE_SPEED;
        else
            vx *= FRICTION;

        if (Greenfoot.isKeyDown("up") && onGround) {
            vy = JUMP_VY;
            onGround = false;
        }

        if (Greenfoot.isKeyDown("down") && kickTimer == 0) {
            kickTimer = 15;
            kickBall();
        }
        if (kickTimer > 0) kickTimer--;
    }

    private void kickBall() {
        SoccerWorld world = (SoccerWorld) getWorld();
        Ball ball = world.getBall();
        if (ball == null) return;

        int dx = ball.getX() - getX();
        int dy = ball.getY() - getY();
        double dist = Math.sqrt(dx * dx + dy * dy);

        if (dist < KICK_RANGE) {
            double angle = Math.atan2(dy, dx);
            ball.applyForce(
                Math.cos(angle) * KICK_FORCE,
                Math.sin(angle) * KICK_FORCE - 3.0
            );
        }
    }

    private void applyPhysics() {
        vy += GRAVITY;
        int nx = (int)(getX() + vx);
        int ny = (int)(getY() + vy);

        SoccerWorld world = (SoccerWorld) getWorld();
        int floorLimit = world.getGroundY() - HEIGHT / 2;

        if (ny >= floorLimit) {
            ny = floorLimit;
            vy = 0;
            onGround = true;
        }
        setLocation(nx, ny);
    }

    private void constrainToWorld() {
        int minX = GoalLeft.WIDTH + WIDTH / 2;
        int maxX = SoccerWorld.WIDTH - GoalRight.WIDTH - WIDTH / 2;
        int cx = getX();
        if (cx < minX) cx = minX;
        if (cx > maxX) cx = maxX;
        setLocation(cx, getY());
    }

    public void freeze()        { frozen = true;  }
    public void unfreeze()      { frozen = false; }
    public void resetVelocity() { vx = 0; vy = 0; onGround = true; }
}
