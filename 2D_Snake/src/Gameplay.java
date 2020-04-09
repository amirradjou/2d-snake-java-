import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JPanel implements KeyListener , ActionListener {

    private int[] snakeXlength = new int[750];
    private int[] snakeYlength = new int[750];

    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightmouth;
    private ImageIcon leftmouth;
    private ImageIcon upmouth;
    private ImageIcon downmouth;

    private int moves = 0;

    private int score = 0;

    private int lengthofsnake = 3;

    private Timer timer;
    private int delay = 100;

    private ImageIcon snakeimage;
    private ImageIcon titleImage;
    private ImageIcon enemyimage;

    private int [] enemyxpos = {25 ,50 ,75 ,100 ,125 ,150 ,175 ,200 ,225
            ,250 ,275 ,300 ,325 ,350 ,375 ,400 ,425 ,450 ,475 ,500 ,525 ,
            550 ,575 ,600 ,625 ,650 ,675 ,700 ,725 ,750 ,775 ,800 ,825 ,850};

    private int[] enemyypos={75 ,100 ,125 ,150 ,175 ,200 ,225 ,250 ,275 ,300 ,325 ,350 ,375 ,400 ,425 ,450 ,475 ,500 ,525 ,550 ,575 ,600 ,625};

    private Random random = new Random();

    private int xpos = random.nextInt(34);
    private int ypos = random.nextInt(23);

    public Gameplay()
    {
        addKeyListener(this); // harchi inja ham hast nemidonam chie
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }


    public void paint(Graphics g)
    {

        if (moves==0){
            snakeXlength[2] = 50;
            snakeXlength[1] = 75;
            snakeXlength[0] = 100;

            snakeYlength[2] = 100;
            snakeYlength[1] = 100;
            snakeYlength[0] = 100;
        }


        //draw title image border
        g.setColor(Color.white);
        g.drawRect(24,10,851,55);


        //draw the title image
        /*titleImage = new ImageIcon("snaketitle.jpg");
        titleImage.paintIcon(this, g , 25 , 11);*/

        //draw border for gameplay
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,577);

        //draw background for the gameplay
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        //draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("scores: " + score , 780 , 30);

        //draw len of snake
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,14));
        g.drawString("length: " + lengthofsnake , 780 , 50);

        rightmouth = new ImageIcon("rightmouth.png");
        rightmouth.paintIcon(this , g , snakeXlength[0], snakeYlength[0]);

        for (int a = 0; a < lengthofsnake ; a++) {
           if (a==0 && right){
               rightmouth = new ImageIcon("rightmouth.png");
               rightmouth.paintIcon(this , g , snakeXlength[a], snakeYlength[a]);
           }

            if (a==0 && left){
                leftmouth = new ImageIcon("leftmouth.png");
                leftmouth.paintIcon(this , g , snakeXlength[a], snakeYlength[a]);
            }

            if (a==0 && up){
                upmouth = new ImageIcon("upmouth.png");
                upmouth.paintIcon(this , g , snakeXlength[a], snakeYlength[a]);
            }

            if (a==0 && down){
                downmouth = new ImageIcon("downmouth.png");
                downmouth.paintIcon(this , g , snakeXlength[a], snakeYlength[a]);
            }

            if (a!=0){
                snakeimage = new ImageIcon("snakeimage.png");
                snakeimage.paintIcon(this,g,snakeXlength[a],snakeYlength[a]);
            }

        }

        enemyimage = new ImageIcon("enemy.png");

        if (enemyxpos[xpos]==snakeXlength[0] && enemyypos[ypos]==snakeYlength[0])
        {
            lengthofsnake++;
            score++;
            xpos = random.nextInt(34);
            ypos = random.nextInt(23);
        }

        enemyimage.paintIcon(this , g , enemyxpos[xpos], enemyypos[ypos]);

        for (int i = 1; i < lengthofsnake; i++) {
            if (snakeYlength[i]==snakeYlength[0] && snakeXlength[i] == snakeXlength[0])
            {
                up = false;
                down = false;
                right = false;
                left = false;

                g.setColor(Color.RED);
                g.setFont(new Font("arial", Font.BOLD , 50));
                g.drawString("GAME OVER",300,300);

                g.setFont(new Font("arial", Font.BOLD , 20));
                g.drawString("Press Space To Restart",350,340);

            }
        }

        g.dispose(); //nemidonam chieeee
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (right){
            for (int i = lengthofsnake - 1 ; i >=0 ; i--) {
                snakeYlength[i+1] = snakeYlength[i];
            }
            for (int i = lengthofsnake; i >= 0 ; i--) {
                if (i==0)
                    snakeXlength[i]+=25;
                else
                    snakeXlength[i]= snakeXlength[i-1];
                if (snakeXlength[i]>850)
                    snakeXlength[i]=25;
            }
            repaint();
        }

        if (left){
            for (int i = lengthofsnake-1; i >= 0 ; i--) {
                snakeYlength[i+1]=snakeYlength[i];
            }
            for (int i = lengthofsnake; i >=0 ; i--) {
                if (i==0)
                    snakeXlength[i]-=25;
                else
                    snakeXlength[i]=snakeXlength[i-1];
                if (snakeXlength[i]<25)
                    snakeXlength[i]=850;
            }
            repaint();
        }

        if (up){
            for (int i = lengthofsnake-1; i >= 0 ; i--) {
                snakeXlength[i+1]=snakeXlength[i];
            }
            for (int i = lengthofsnake; i >=0 ; i--) {
                if (i==0)
                    snakeYlength[i]-=25;
                else
                    snakeYlength[i]=snakeYlength[i-1];
                if (snakeYlength[i]<75)
                    snakeYlength[i]=625;
            }
            repaint();
        }

        if (down){
            for (int i = lengthofsnake-1; i >= 0 ; i--) {
                snakeXlength[i+1]=snakeXlength[i];
            }
            for (int i = lengthofsnake; i >=0 ; i--) {
                if (i==0)
                    snakeYlength[i]+=25;
                else
                    snakeYlength[i]=snakeYlength[i-1];
                if (snakeYlength[i]>625)
                    snakeYlength[i]=75;
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            moves = 0;
            score = 0;
            lengthofsnake = 3;
            repaint();
        }

        if (e.getKeyCode()==KeyEvent.VK_RIGHT){
            moves++;
            right=true;
            if (!left)
                right=true;
            else{
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_LEFT){
            moves++;
            left=true;
            if (!right)
                left=true;
            else{
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_UP){
            moves++;
            up=true;
            if(!down)
                up=true;
            else{
                up=false;
                down=true;
            }
            right=false;
            left=false;
        }

        if (e.getKeyCode()==KeyEvent.VK_DOWN){
            moves++;
            down=true;
            if(!up)
                down=true;
            else{
                down=false;
                up=true;
            }
            right=false;
            left=false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
