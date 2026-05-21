import greenfoot.*;

public class SoccerWorld extends World {

    public static final int ANCHO_MUNDO = 800;
    public static final int ALTO_MUNDO = 500;
    public static final int SUELO_Y = 420;
    public static final int GOLES_PARA_GANAR = 5;

    private int golesJugador1 = 0;
    private int golesJugador2 = 0;

    private boolean juegoIniciado = false;
    private boolean juegoTerminado = false;

    private Player1 jugador1;
    private Player2 jugador2;
    private Ball pelota;
    private GoalLeft porteriaIzquierda;
    private GoalRight porteriaDerecha;
    private ScoreBoard marcador;
    private StartButton botonInicio;

    private int tiempoEsperaGol = 0;

    public SoccerWorld() {
        super(ANCHO_MUNDO, ALTO_MUNDO, 1);
        preparar();
    }

    private void preparar() {

        porteriaIzquierda = new GoalLeft();
        porteriaDerecha = new GoalRight();

        addObject(porteriaIzquierda, GoalLeft.WIDTH / 2,  SUELO_Y - GoalLeft.HEIGHT / 2);
        addObject(porteriaDerecha, ANCHO_MUNDO - GoalRight.WIDTH / 2,  SUELO_Y - GoalRight.HEIGHT / 2);

        jugador1 = new Player1();
        jugador2 = new Player2();

        addObject(jugador1,ANCHO_MUNDO / 4, SUELO_Y - Player1.HEIGHT / 2);

        addObject(jugador2, ANCHO_MUNDO * 3 / 4,SUELO_Y - Player2.HEIGHT / 2);

        pelota = new Ball();
        addObject(pelota, ANCHO_MUNDO / 2, SUELO_Y - Ball.RADIUS - -14);

        marcador = new ScoreBoard();
        addObject(marcador, ANCHO_MUNDO / 2, 28);

        botonInicio = new StartButton();
        addObject(botonInicio, ANCHO_MUNDO / 2, ALTO_MUNDO / 2);

        Greenfoot.setSpeed(50);
    }

    public void act() {

        if (!juegoIniciado || juegoTerminado) {
            return;
        }
        if (tiempoEsperaGol > 0) {

            tiempoEsperaGol--;
            if (tiempoEsperaGol == 0) {
                reiniciarDespuesGol();
            }
            return;
        }

        verificarGol();
    }

    private void verificarGol() {

        int posicionX = pelota.getX();
        int posicionY = pelota.getY();

        if (posicionX - Ball.RADIUS <= GoalLeft.WIDTH
                && posicionY >= SUELO_Y - GoalLeft.HEIGHT
                && posicionY <= SUELO_Y) {

            registrarGol(2);
        }

        else if (posicionX + Ball.RADIUS >= ANCHO_MUNDO - GoalRight.WIDTH
                && posicionY >= SUELO_Y - GoalRight.HEIGHT
                && posicionY <= SUELO_Y) {

            registrarGol(1);
        }
    }

    private void registrarGol(int jugadorAnotador) {

        if (jugadorAnotador == 1) {
            golesJugador1++;
        } else {
            golesJugador2++;
        }

        marcador.update(golesJugador1, golesJugador2);

        tiempoEsperaGol = 90;

        jugador1.freeze();
        jugador2.freeze();
        pelota.freeze();

        GoalLabel etiquetaGol = new GoalLabel("  GOOOLAZO!  -  Jugador " + jugadorAnotador);

        addObject(etiquetaGol, ANCHO_MUNDO / 2, ALTO_MUNDO / 2);

        if (golesJugador1 >= GOLES_PARA_GANAR
                || golesJugador2 >= GOLES_PARA_GANAR) {

            juegoTerminado = true;
            int jugadorGanador =(golesJugador1 >= GOLES_PARA_GANAR) ? 1 : 2;
            WinLabel etiquetaGanador = new WinLabel("JUGADOR " + jugadorGanador + " GANA! (Reset para repetir)");
            addObject(etiquetaGanador, ANCHO_MUNDO / 2,ALTO_MUNDO / 2 + 70);
        }
    }

    private void reiniciarDespuesGol() {

        removeObjects(getObjects(GoalLabel.class));

        jugador1.unfreeze();
        jugador2.unfreeze();
        pelota.unfreeze();

        jugador1.setLocation(
                ANCHO_MUNDO / 4,
                SUELO_Y - Player1.HEIGHT / 2);

        jugador2.setLocation(
                ANCHO_MUNDO * 3 / 4,
                SUELO_Y - Player2.HEIGHT / 2);

        jugador1.resetVelocity();
        jugador2.resetVelocity();

        pelota.setLocation(
                ANCHO_MUNDO / 2,
                SUELO_Y - Ball.RADIUS - -14);

        pelota.resetVelocity();
    }

    public void startGame() {
        removeObject(botonInicio);
        juegoIniciado = true;
    }

    public int getGroundY() {
        return SUELO_Y;
    }

    public Player1 getPlayer1() {
        return jugador1;
    }

    public Player2 getPlayer2() {
        return jugador2;
    }

    public Ball getBall() {
        return pelota;
    }
}