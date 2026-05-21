import greenfoot.*;

public class WinLabel extends Actor {

    public WinLabel(String texto) {

        GreenfootImage imagen = new GreenfootImage(400, 60);

        imagen.setColor(new Color(0,0,0,170));
        imagen.fillRect(0,0,400,60);

        imagen.setColor(Color.CYAN);
        imagen.setFont(new Font("Arial", true, false, 28));

        imagen.drawString(texto, 20, 38);

        setImage(imagen);
    }
}