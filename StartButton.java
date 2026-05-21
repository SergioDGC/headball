import greenfoot.*;

public class StartButton extends Actor {

    public StartButton() {
        dibujarBoton();
    }

    private void dibujarBoton() {

        int ancho = 200;
        int alto = 54;

        GreenfootImage imagen = new GreenfootImage(ancho, alto);

        imagen.setColor(new Color(40, 180, 80));
        imagen.fillRect(0, 0, ancho, alto);

        imagen.setColor(new Color(255, 255, 255));

        imagen.drawRect(0, 0, ancho - 1, alto - 1);
        imagen.drawRect(2, 2, ancho - 5, alto - 5);

        imagen.setColor(new Color(255, 255, 255));
        imagen.setFont(new Font("Arial", true, false, 24));

        imagen.drawString("INICIAR JUEGO", 18, 35);

        setImage(imagen);
    }

    public void act() {

        if (Greenfoot.mouseClicked(this)) {

            SoccerWorld mundo = (SoccerWorld) getWorld();

            mundo.startGame();
        }
    }
}