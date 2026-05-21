import greenfoot.*;

public class ScoreBoard extends Actor {

    private int golesJugador1 = 0;
    private int golesJugador2 = 0;

    public ScoreBoard() {
        update(0, 0);
    }

    public void update(int goles1, int goles2) {

        golesJugador1 = goles1;
        golesJugador2 = goles2;

        dibujarMarcador();
    }

    private void dibujarMarcador() {

        int ancho = 260;
        int alto = 46;

        GreenfootImage imagen =
                new GreenfootImage(ancho, alto);

        imagen.setColor(new Color(0, 0, 0, 160));
        imagen.fillRect(0, 0, ancho, alto);

        imagen.setColor(new Color(255, 255, 255, 60));
        imagen.drawRect(0, 0, ancho - 1, alto - 1);

        imagen.setColor(new Color(255, 80, 80));
        imagen.setFont(new Font("Arial", true, false, 16));

        imagen.drawString("J1", 18, 30);

        imagen.setColor(new Color(255, 255, 255));
        imagen.setFont(new Font("Arial", true, false, 26));

        imagen.drawString(String.valueOf(golesJugador1), 58, 34);

        imagen.setColor(new Color(200, 200, 200));
        imagen.setFont(new Font("Arial", true, false, 22));

        imagen.drawString("-", 116, 32);

        imagen.setColor(new Color(255, 255, 255));
        imagen.setFont(new Font("Arial", true, false, 26));

        imagen.drawString( String.valueOf(golesJugador2), 146, 34);

        imagen.setColor(new Color(80, 160, 255));
        imagen.setFont(new Font("Arial", true, false, 16));

        imagen.drawString("J2", 208, 30);

        imagen.setColor(new Color(255, 215, 0));
        imagen.setFont(new Font("Arial", false, false, 11));

        imagen.drawString( "EL PRIMERO DE 5 GANA", 30, 13);

        setImage(imagen);
    }

    public void act() {
    }
}