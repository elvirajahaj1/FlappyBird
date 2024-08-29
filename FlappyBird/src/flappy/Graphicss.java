package flappy;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.ArrayList;


public class Graphicss extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;

    public  Bird bird;
    public boolean gameStarted=false;
   /* public int x=10;
    public int y=20;*/
    public ArrayList<Pipes> pipes;
    public ImageIcon imageBackground;
    public static int  yMotion;

    public Graphicss( ArrayList<Pipes> pipes) {

        this.pipes = pipes;
        imageBackground = new ImageIcon("C:\\Users\\elvir\\IdeaProjects\\project1\\src\\images\\background.png");

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        imageBackground.paintIcon(this, g, 0, 0);
        if (FlappyBird.started) {
            bird = new Bird(10, 20 + (yMotion++));
        } else {
            bird = new Bird(10, 20);
        }

       /* bird=new Bird(10,20+(yMotion++));*/

          bird.paintBird(g);

        for (Pipes pipe : pipes) {
            pipe.paint(g);
        }

        FlappyBird.flappyBird.repaint(g);
    }

}