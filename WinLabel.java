import greenfoot.*;

/**
 * WinLabel - Muestra el ganador al terminar la partida.
 */
public class WinLabel extends Actor {

    public WinLabel(String text) {
        int w = 420;
        int h = 58;
        GreenfootImage img = new GreenfootImage(w, h);

        img.setColor(new Color(0, 0, 0, 180));
        img.fillRect(0, 0, w, h);

        img.setColor(new Color(100, 220, 255));
        img.drawRect(0, 0, w - 1, h - 1);

        img.setColor(new Color(100, 220, 255));
        img.setFont(new Font("Arial", true, false, 26));
        img.drawString(text, 10, 40);

        setImage(img);
    }

    public void act() { }
}
