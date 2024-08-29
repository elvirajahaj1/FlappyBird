
package flappy;

import javax.swing.*;
import java.awt.*;

public class Pipes {
    ImageIcon pipe;
     public  int x;
     public   int y;
     public static  int width;
     public  static int height;
   public  int yy;

    public Pipes(){
    }

    public Pipes(boolean state,int x,int y){
        this.x=x;
        this.y=y;

        if(state)

                 pipe = new ImageIcon("C:\\Users\\elvir\\IdeaProjects\\project1\\src\\images\\pipes1.png");
          else
              pipe = new ImageIcon("C:\\Users\\elvir\\IdeaProjects\\project1\\src\\images\\pipes2.png");;


    }
    public void paint(Graphics g){

       g.drawImage(pipe.getImage(),x,y,null);



    }


}
