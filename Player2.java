import greenfoot.*;

public class Player2 extends Actor {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 55;

    private static final double GRAVEDAD = 0.7;
    private static final double FUERZA_SALTO = -13.0;
    private static final double VELOCIDAD_MOVIMIENTO = 4.5;
    private static final double FRICCION = 0.8;
    private static final double FUERZA_PATADA = 13.0;
    private static final int RANGO_PATADA = 62;

    private double velocidadX = 0;
    private double velocidadY = 0;

    private boolean enSuelo = true;
    private boolean congelado = false;

    private int tiempoPatada = 0;

    public Player2() {
    }

    public void act() {

        if (congelado) {
            return;
        }

        manejarControles();
        aplicarFisica();
        limitarMovimiento();
    }

    private void manejarControles() {

        if (Greenfoot.isKeyDown("left")) {
            velocidadX = -VELOCIDAD_MOVIMIENTO; 
        }
        else if (Greenfoot.isKeyDown("right")) {
            velocidadX = VELOCIDAD_MOVIMIENTO;
        }
        else {
            velocidadX *= FRICCION;
        }

        if (Greenfoot.isKeyDown("up") && enSuelo) {

            velocidadY = FUERZA_SALTO;
            enSuelo = false;
        }

        if (Greenfoot.isKeyDown("down") && tiempoPatada == 0) {

            tiempoPatada = 15;
            patearPelota();
        }

        if (tiempoPatada > 0) {
            tiempoPatada--;
        }
    }

    private void patearPelota() {

        SoccerWorld mundo = (SoccerWorld) getWorld();

        Ball pelota = mundo.getBall();

        if (pelota == null) {
            return;
        }

        int diferenciaX = pelota.getX() - getX();
        int diferenciaY = pelota.getY() - getY();

        double distancia = Math.sqrt(diferenciaX * diferenciaX + diferenciaY * diferenciaY);

        if (distancia < RANGO_PATADA) {

            double angulo =  Math.atan2(diferenciaY, diferenciaX);
            pelota.applyForce( Math.cos(angulo) * FUERZA_PATADA,Math.sin(angulo) * FUERZA_PATADA - 3.0
            );
        }
    }

    private void aplicarFisica() {

        velocidadY += GRAVEDAD;

        int nuevaX = (int)(getX() + velocidadX);
        int nuevaY = (int)(getY() + velocidadY);
        SoccerWorld mundo =(SoccerWorld) getWorld();
        int limiteSuelo =mundo.getGroundY() - HEIGHT / 2;

        if (nuevaY >= limiteSuelo) {

            nuevaY = limiteSuelo;
            velocidadY = 0;
            enSuelo = true;
        }

        setLocation(nuevaX, nuevaY);
    }

    private void limitarMovimiento() {

        int limiteIzquierdo = GoalLeft.WIDTH + WIDTH / 2;
        int limiteDerecho = SoccerWorld.ANCHO_MUNDO - GoalRight.WIDTH- WIDTH / 2;
        int posicionX = getX();

        if (posicionX < limiteIzquierdo) {
            posicionX = limiteIzquierdo;
        }

        if (posicionX > limiteDerecho) {
            posicionX = limiteDerecho;
        }

        setLocation(posicionX, getY());
    }

    public void freeze() {
        congelado = true;
    }

    public void unfreeze() {
        congelado = false;
    }

    public void resetVelocity() {

        velocidadX = 0;
        velocidadY = 0;
        enSuelo = true;
    }
}