import greenfoot.*;

public class ScoreBoard extends Actor {

    private int score1 = 0;
    private int score2 = 0;

    public ScoreBoard() {
        update(0, 0);
    }

    public void update(int s1, int s2) {
        score1 = s1;
        score2 = s2;
        drawBoard();
    }

    private void drawBoard() {
        int w = 260;
        int h = 46;
        GreenfootImage img = new GreenfootImage(w, h);

        // Fondo
        img.setColor(new Color(0, 0, 0, 160));
        img.fillRect(0, 0, w, h);
        img.setColor(new Color(255, 255, 255, 60));
        img.drawRect(0, 0, w - 1, h - 1);

        // P1
        img.setColor(new Color(255, 80, 80));
        img.setFont(new Font("Arial", true, false, 16));
        img.drawString("J1", 18, 30);

        // puntaje P1
        img.setColor(new Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 26));
        img.drawString(String.valueOf(score1), 58, 34);

        // Separador
        img.setColor(new Color(200, 200, 200));
        img.setFont(new Font("Arial", true, false, 22));
        img.drawString("-", 116, 32);

        // puntaje P2
        img.setColor(new Color(255, 255, 255));
        img.setFont(new Font("Arial", true, false, 26));
        img.drawString(String.valueOf(score2), 146, 34);

        // P2
        img.setColor(new Color(80, 160, 255));
        img.setFont(new Font("Arial", true, false, 16));
        img.drawString("J2", 208, 30);

        // texto
        img.setColor(new Color(255, 215, 0));
        img.setFont(new Font("Arial", false, false, 11));
        img.drawString("PRIMERO EN 5 GOLES GANA", 30, 13);

        setImage(img);
    }

    public void act() { }
}
