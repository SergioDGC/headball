import greenfoot.*;

public class Ball extends Actor {

    public static final int RADIUS = 14;

    private static final double GRAVITY    = 0.6;
    private static final double BOUNCE_Y   = -0.6;
    private static final double FRICTION_X = 0.992;
    private static final double MAX_SPEED  = 16.0;

    private double vx = 1.5, vy = 0;
    private boolean frozen = false;

    public Ball() {
    }

    public void act() {
        if (frozen) return;
        applyPhysics();
        checkWalls();
        checkPlayerCollisions();
    }

    private void applyPhysics() {
        vy += GRAVITY;
        if (vx >  MAX_SPEED) vx =  MAX_SPEED;
        if (vx < -MAX_SPEED) vx = -MAX_SPEED;
        if (vy >  MAX_SPEED) vy =  MAX_SPEED;
        if (vy < -MAX_SPEED) vy = -MAX_SPEED;

        double nx = getX() + vx;
        double ny = getY() + vy;

        SoccerWorld world = (SoccerWorld) getWorld();
        int floorLimit = world.getGroundY() - RADIUS;

        if (ny >= floorLimit) {
            ny = floorLimit;
            vy *= BOUNCE_Y;
            vx *= 0.92;
        }

        if (ny - RADIUS <= 5) {
            ny = 5 + RADIUS;
            vy *= -0.6;
        }

        vx *= FRICTION_X;
        setLocation((int) nx, (int) ny);
    }

    private void checkWalls() {
        int x = getX();
        int y = getY();
        SoccerWorld world = (SoccerWorld) getWorld();
        int groundY = world.getGroundY();

        boolean inGoalZone = (y >= groundY - GoalLeft.HEIGHT && y <= groundY);

        // Pared izquierda
        if (x - RADIUS <= GoalLeft.WIDTH) {
            if (!inGoalZone) {
                setLocation(GoalLeft.WIDTH + RADIUS, y);
                vx *= -0.65;
            }
        }
        // Pared derecha
        if (x + RADIUS >= SoccerWorld.WIDTH - GoalRight.WIDTH) {
            if (!inGoalZone) {
                setLocation(SoccerWorld.WIDTH - GoalRight.WIDTH - RADIUS, y);
                vx *= -0.65;
            }
        }
    }

    private void checkPlayerCollisions() {
        SoccerWorld world = (SoccerWorld) getWorld();
        resolveCollisionP1(world.getPlayer1());
        resolveCollisionP2(world.getPlayer2());
    }

    private void resolveCollisionP1(Player1 p) {
        if (p == null) return;
        int dx = getX() - p.getX();
        int dy = getY() - p.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        double minDist = RADIUS + Math.min(Player1.WIDTH, Player1.HEIGHT) / 2.0;

        if (dist < minDist && dist > 0) {
            double nx = dx / dist;
            double ny = dy / dist;
            setLocation((int)(p.getX() + nx * minDist),
                        (int)(p.getY() + ny * minDist));
            double relV = vx * nx + vy * ny;
            if (relV < 0) {
                vx -= 1.6 * relV * nx;
                vy -= 1.6 * relV * ny;
            }
        }
    }

    private void resolveCollisionP2(Player2 p) {
        if (p == null) return;
        int dx = getX() - p.getX();
        int dy = getY() - p.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        double minDist = RADIUS + Math.min(Player2.WIDTH, Player2.HEIGHT) / 2.0;

        if (dist < minDist && dist > 0) {
            double nx = dx / dist;
            double ny = dy / dist;
            setLocation((int)(p.getX() + nx * minDist),
                        (int)(p.getY() + ny * minDist));
            double relV = vx * nx + vy * ny;
            if (relV < 0) {
                vx -= 1.6 * relV * nx;
                vy -= 1.6 * relV * ny;
            }
        }
    }

    public void applyForce(double fx, double fy) { vx = fx; vy = fy; }
    public void freeze()        { frozen = true;  }
    public void unfreeze()      { frozen = false; }
    public void resetVelocity() {
        vx = (Math.random() > 0.5 ? 1 : -1) * 1.5;
        vy = 0;
    }
}
