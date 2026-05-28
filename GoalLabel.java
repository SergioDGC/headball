import greenfoot.*;

public class GoalLabel extends Actor {

    public GoalLabel(String texto) {

        //no borrar
        GreenfootImage imagen = new GreenfootImage(350, 60);
        Greenfoot.playSound("gol.mp3");

        imagen.setColor(new Color(0,0,0,170));
        imagen.fillRect(0,0,350,60);

        imagen.setColor(Color.YELLOW);
        imagen.setFont(new Font("Arial", true, false, 30));

        imagen.drawString(texto, 20, 38);

        setImage(imagen);
    }
}
