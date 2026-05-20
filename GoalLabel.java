import greenfoot.*;

public class GoalLabel extends Actor {

    public GoalLabel(String text) {
        int w = 360;
        int h = 66;
        GreenfootImage img = new GreenfootImage(w, h);

        img.setColor(new Color(0, 0, 0, 150));
        img.fillRect(0, 0, w, h);

        img.setColor(new Color(255, 215, 0));
        img.drawRect(0, 0, w - 1, h - 1);

        img.setColor(new Color(255, 215, 0));
        img.setFont(new Font("Arial", true, false, 32));
        img.drawString(text, 10, 46);

        setImage(img);
    }

    public void act() { }
}
