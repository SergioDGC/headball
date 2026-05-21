import greenfoot.*;

public class Ball extends Actor {

    public static final int RADIUS = 14;

    private static final double GRAVEDAD = 0.3;
    private static final double REBOTE_Y = -1;
    private static final double FRICCION_X = 1;
    private static final double VELOCIDAD_MAXIMA = 16.0;

    private double velocidadX = 1.5;
    private double velocidadY = 0;

    private boolean congelada = false;

    public Ball() {
    }

    public void act() {

        if (congelada) {
            return;
        }

        aplicarFisica();
        verificarParedes();
        verificarColisionesJugadores();
    }

    private void aplicarFisica() {

        velocidadY += GRAVEDAD;

        if (velocidadX > VELOCIDAD_MAXIMA) velocidadX = VELOCIDAD_MAXIMA;
        if (velocidadX < -VELOCIDAD_MAXIMA) velocidadX = -VELOCIDAD_MAXIMA;

        if (velocidadY > VELOCIDAD_MAXIMA) velocidadY = VELOCIDAD_MAXIMA;
        if (velocidadY < -VELOCIDAD_MAXIMA) velocidadY = -VELOCIDAD_MAXIMA;

        double nuevaX = getX() + velocidadX;
        double nuevaY = getY() + velocidadY;

        SoccerWorld mundo = (SoccerWorld) getWorld();

        int limiteSuelo = mundo.getGroundY() - RADIUS;

        if (nuevaY >= limiteSuelo) {
            nuevaY = limiteSuelo;
            velocidadY *= REBOTE_Y;
            velocidadX *= 0.92;
        }

        if (nuevaY - RADIUS <= 5) {
            nuevaY = 5 + RADIUS;
            velocidadY *= -0.6;
        }

        velocidadX *= FRICCION_X;
        setLocation((int) nuevaX, (int) nuevaY);
    }

    private void verificarParedes() {

        int posicionX = getX();
        int posicionY = getY();

        SoccerWorld mundo = (SoccerWorld) getWorld();

        int sueloY = mundo.getGroundY();

        boolean dentroPorteria =(posicionY >= sueloY - GoalLeft.HEIGHT && posicionY <= sueloY);

        if (posicionX - RADIUS <= GoalLeft.WIDTH) {

            if (!dentroPorteria) {
                setLocation(GoalLeft.WIDTH + RADIUS, posicionY);
                velocidadX *= -0.65;
            }
        }

        if (posicionX + RADIUS >= SoccerWorld.ANCHO_MUNDO - GoalRight.WIDTH) {

            if (!dentroPorteria) {
                setLocation(  SoccerWorld.ANCHO_MUNDO - GoalRight.WIDTH - RADIUS,posicionY);
                velocidadX *= -0.65;
            }
        }
    }

    private void verificarColisionesJugadores() {

        SoccerWorld mundo = (SoccerWorld) getWorld();
        resolverColisionJugador1(mundo.getPlayer1());
        resolverColisionJugador2(mundo.getPlayer2());
    }

    private void resolverColisionJugador1(Player1 jugador) {

        if (jugador == null) {
            return;
        }

        int diferenciaX = getX() - jugador.getX();
        int diferenciaY = getY() - jugador.getY();

        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);

        double distanciaMinima =  RADIUS + Math.max(Player1.WIDTH, Player1.HEIGHT) / 2.0;

        if (distancia < distanciaMinima && distancia > 0) {

            double normalX = diferenciaX / distancia;
            double normalY = diferenciaY / distancia;

            setLocation( (int)(jugador.getX() + normalX * distanciaMinima),(int)(jugador.getY() + normalY * distanciaMinima));
            double velocidadRelativa = velocidadX * normalX + velocidadY * normalY;

            if (velocidadRelativa < 0) {
                velocidadX -= 1.6 * velocidadRelativa * normalX;
                velocidadY -= 1.6 * velocidadRelativa * normalY;
            }
        }
    }

    private void resolverColisionJugador2(Player2 jugador) {

        if (jugador == null) {
            return;
        }

        int diferenciaX = getX() - jugador.getX();
        int diferenciaY = getY() - jugador.getY();

        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);

        double distanciaMinima = RADIUS + Math.max(Player2.WIDTH, Player2.HEIGHT) / 2.0;

        if (distancia < distanciaMinima && distancia > 0) {

            double normalX = diferenciaX / distancia;
            double normalY = diferenciaY / distancia;

            setLocation(
                    (int)(jugador.getX() + normalX * distanciaMinima),
                    (int)(jugador.getY() + normalY * distanciaMinima));

            double velocidadRelativa = velocidadX * normalX + velocidadY * normalY;

            if (velocidadRelativa < 0) {

                velocidadX -= 1.6 * velocidadRelativa * normalX;
                velocidadY -= 1.6 * velocidadRelativa * normalY;
            }
        }
    }

    public void applyForce(double fuerzaX, double fuerzaY) {

        velocidadX = fuerzaX;
        velocidadY = fuerzaY;
    }

    public void freeze() {
        congelada = true;
    }

    public void unfreeze() {
        congelada = false;
    }

    public void resetVelocity() {
        velocidadX =(Math.random() > 0.5 ? 1 : -1) * 1.5;
        velocidadY = 0;
    }
}