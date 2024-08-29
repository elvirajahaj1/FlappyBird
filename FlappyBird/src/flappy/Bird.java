package flappy;

import javax.swing.*;
import java.awt.*;

public class Bird {
    public ImageIcon bird;
    public int x;
    public int y;
    public int height=1;
    public int width=1;

    public Bird(int x,int y){
        this.x=x;
        this.y=y;

        bird = new ImageIcon("C:\\Users\\elvir\\IdeaProjects\\project1\\src\\images\\bird.png");

    }
    public void paintBird(Graphics g){

            g.drawImage(bird.getImage(), x,y+300+ Graphicss.yMotion, null);

        /*  g.drawImage(bird.getImage(),x,y+400,null);*/


    }
}
