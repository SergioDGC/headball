import greenfoot.*;

public class SoccerWorld extends World {

    public static final int WIDTH        = 800;
    public static final int HEIGHT       = 500;
    public static final int GROUND_Y     = 420;
    public static final int GOALS_TO_WIN = 5;

    private int score1 = 0;
    private int score2 = 0;

    private boolean gameStarted = false;
    private boolean gameOver    = false;

    private Player1     player1;
    private Player2     player2;
    private Ball        ball;
    private GoalLeft    goalLeft;
    private GoalRight   goalRight;
    private ScoreBoard  scoreBoard;
    private StartButton startButton;

    private int goalCooldown = 0;

    public SoccerWorld() {
        super(WIDTH, HEIGHT, 1);
        prepare();
    }

    private void prepare() {
        // Porterias
        goalLeft  = new GoalLeft();
        goalRight = new GoalRight();
        addObject(goalLeft,  GoalLeft.WIDTH / 2,          GROUND_Y - GoalLeft.HEIGHT / 2);
        addObject(goalRight, WIDTH - GoalRight.WIDTH / 2, GROUND_Y - GoalRight.HEIGHT / 2);

        // Jugadores
        player1 = new Player1();
        player2 = new Player2();
        addObject(player1, WIDTH / 4,     GROUND_Y - Player1.HEIGHT / 2);
        addObject(player2, WIDTH * 3 / 4, GROUND_Y - Player2.HEIGHT / 2);

        // bochita
        ball = new Ball();
        addObject(ball, WIDTH / 2, GROUND_Y - Ball.RADIUS - -14);

        // Marcador
        scoreBoard = new ScoreBoard();
        addObject(scoreBoard, WIDTH / 2, 28);

        // Boton inicio
        startButton = new StartButton();
        addObject(startButton, WIDTH / 2, HEIGHT / 2);

        Greenfoot.setSpeed(50);
    }

    public void act() {
        if (!gameStarted || gameOver) return;

        if (goalCooldown > 0) {
            goalCooldown--;
            if (goalCooldown == 0) resetAfterGoal();
            return;
        }

        checkGoal();
    }

    private void checkGoal() {
        int bx = ball.getX();
        int by = ball.getY();

        // Porteria izquierda: gol para P2
        if (bx - Ball.RADIUS <= GoalLeft.WIDTH
            && by >= GROUND_Y - GoalLeft.HEIGHT
            && by <= GROUND_Y) {
            registerGoal(2);
        }
        // Porteria derecha: gol para P2
        else if (bx + Ball.RADIUS >= WIDTH - GoalRight.WIDTH
                 && by >= GROUND_Y - GoalRight.HEIGHT
                 && by <= GROUND_Y) {
            registerGoal(1);
        }
    }

    private void registerGoal(int scorer) {
        if (scorer == 1) score1++;
        else             score2++;

        scoreBoard.update(score1, score2);
        goalCooldown = 90;

        player1.freeze();
        player2.freeze();
        ball.freeze();

        GoalLabel lbl = new GoalLabel("  GOOOL!  -  Jugador " + scorer);
        addObject(lbl, WIDTH / 2, HEIGHT / 2);

        if (score1 >= GOALS_TO_WIN || score2 >= GOALS_TO_WIN) {
            gameOver = true;
            int winner = (score1 >= GOALS_TO_WIN) ? 1 : 2;
            WinLabel win = new WinLabel("JUGADOR " + winner + " GANA! (Reset para repetir)");
            addObject(win, WIDTH / 2, HEIGHT / 2 + 70);
        }
    }

    private void resetAfterGoal() {
        removeObjects(getObjects(GoalLabel.class));

        player1.unfreeze();
        player2.unfreeze();
        ball.unfreeze();

        player1.setLocation(WIDTH / 4,     GROUND_Y - Player1.HEIGHT / 2);
        player2.setLocation(WIDTH * 3 / 4, GROUND_Y - Player2.HEIGHT / 2);
        player1.resetVelocity();
        player2.resetVelocity();

        ball.setLocation(WIDTH / 2, GROUND_Y - Ball.RADIUS - -14);
        ball.resetVelocity();
    }

    public void startGame() {
        removeObject(startButton);
        gameStarted = true;
    }

    public int      getGroundY()  { return GROUND_Y; }
    public Player1  getPlayer1()  { return player1;  }
    public Player2  getPlayer2()  { return player2;  }
    public Ball     getBall()     { return ball;      }
}
