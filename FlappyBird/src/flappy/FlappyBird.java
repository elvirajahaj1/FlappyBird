package  flappy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class FlappyBird extends JFrame implements ActionListener, MouseListener, KeyListener {
    public static FlappyBird flappyBird;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public Random random;
    public int ticks,score;
    public ArrayList<Pipes> pipes;
    public Graphicss graph;
    public boolean gameOver;
    public  static boolean started;

    public FlappyBird() {


        JFrame frame = new JFrame();
        Timer timer = new Timer(20, this);

        random = new Random();


        pipes = new ArrayList<>();
        addPipes(true);
        addPipes(true);
        addPipes(true);
        addPipes(true);

        graph = new Graphicss(pipes);
        frame.add(graph);
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("FlappyBird Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(this);
        frame.addKeyListener(this);
        frame.setVisible(true);
        frame.setResizable(false);

        timer.start();

    }

    public void addPipes(boolean start) {
        int distance = 200;
        int space = 100;
        int height = 50 + random.nextInt(100);
        if (start) {
            pipes.add(new Pipes(true, +pipes.size() * 300, 300 + space + height + 20));
            pipes.add(new Pipes(false, (pipes.size() - 1) * 300, -350 - space + height - 20));
        } else {
            pipes.add(new Pipes(true, pipes.get(pipes.size() - 1).x + distance + 500, 400 + space + height + 20));
            pipes.add(new Pipes(false, (pipes.get(pipes.size() - 1).x + distance - 200), -300 - space + height - 20));
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 12;
        ticks++;
        if (started) {

            for (int i = 0; i < pipes.size(); i++) {
                Pipes pipe = pipes.get(i);
                pipe.x -= speed;
            }

            if (ticks % 2 == 0 && graph.yMotion < 15) {

                graph.yMotion += 2;
            }


            for (int i = 0; i < pipes.size(); i++) {
                Pipes pipe = pipes.get(i);

                if ((pipe.x + pipe.width) < 0) {

                    if (pipe.yy == 0) {
                        addPipes(false);
                    }
                }
            }

            for (Pipes pipe : pipes) {
                if (pipe.yy == 0 && graph.bird.x + graph.bird.width / 2 > pipe.x + pipe.width / 2 - 10 && graph.bird.x / 2 < pipe.x + pipe.width / 2 + 10) {
                    score++;
                }
              /*  if (hasIntersection(pipe, graph.bird)) {
                    System.out.println("test1");
                    gameOver = true;
                }*/

                    if (graph.bird.x <= pipe.x) {
                        graph.bird.x = pipe.x - graph.bird.width;
                    } else {
                        if (pipe.y != 0) {
                            graph.bird.y = pipe.y - graph.bird.height;
                        } else if (graph.bird.y < pipe.height) {
                            graph.bird.y = pipe.height;

                        }


                }
                if (graph.bird.y > HEIGHT - 150 || graph.bird.y < 0) {

                    gameOver = true;

                }
                if (graph.bird.y + graph.yMotion >= HEIGHT - 120) {

                    graph.bird.y = HEIGHT - 120 - graph.bird.height;


                }}
        }
        graph.repaint();

    }


    public void repaint(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));

        if (!started) {
            g.drawString("Click to Start!", 350, HEIGHT / 2 - 100);

        }
        if (gameOver) {
            g.drawString("Game Over!", 350, HEIGHT / 2 - 100);
        }
        if (!gameOver && started)
        {
            g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);
        }
    }
    public void jump () {
        if (gameOver) {
            graph.bird = new Bird(WIDTH / 2 - 10, HEIGHT / 2 - 10);
            pipes.clear();
            graph.yMotion = 0;
            score = 0;

            addPipes(true);
            addPipes(true);
            addPipes(true);
            addPipes(true);

            gameOver = false;
        }
        if (!started) {

            started = true;

        }
        else if (!gameOver) {

            if (graph.yMotion > 0) {
                graph.yMotion = 0;
            }
            graph.yMotion -= 10;
        }
    }

    public static boolean hasIntersection(Pipes pipe, Bird bird) {

        Rectangle pipe1Bounds = new Rectangle(0, 0, pipe.width, pipe.height);
        Rectangle pipe2Bounds = new Rectangle(0, pipe.yy, pipe.width, HEIGHT - pipe.yy);
        Rectangle birdBounds = new Rectangle(bird.x, bird.y, bird.width, bird.height);

        return birdBounds.intersects(pipe1Bounds) || birdBounds.intersects(pipe2Bounds);
    }

    public static void main (String[]args){
        flappyBird = new FlappyBird();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            jump();
        }
    }}