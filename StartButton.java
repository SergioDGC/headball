import greenfoot.*;

/**
 * StartButton - Boton para iniciar el juego.
 */
public class StartButton extends Actor {

    public StartButton() {
        drawButton();
    }

    private void drawButton() {
        int w = 200;
        int h = 54;
        GreenfootImage img = new GreenfootImage(w, h);

        // fondo verde
        img.setColor(new Color(40, 180, 80));
        img.fillRect(0, 0, w, h);

        // contorno blanco
        img.setColor(new Color(255, 255, 255));
        img.drawRect(0, 0, w - 1, h - 1);
        img.drawRect(2, 2, w - 5, h - 5);

        // texto
        img.setColor(new Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 24));
        img.drawString("INICIAR JUEGO", 18, 35);

        setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            SoccerWorld world = (SoccerWorld) getWorld();
            world.startGame();
        }
    }
}
