package main;
import javax.swing.JPanel;

import entity.player;
import object.superobject;
import tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class gamepanel extends JPanel implements Runnable {
    //Screen settings
    final int originalTileSize=16; //16X16
    final int scale=3;
    public final int tileSize=originalTileSize*scale;//48X48
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth=tileSize* maxScreenCol; //768 pixels
    public final int screenHeight=tileSize*maxScreenRow; //576 pixels

    //world settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    //FPS
    int fps=60;

    public TileManager tilemanager=new TileManager(this);
    keyhandler keyH=new keyhandler();
    sound sou=new sound();
    sound sou2=new sound();
    public ui UI=new ui(this);
    

    Thread gameThread;
    public CollisionChecker cChecker=new CollisionChecker(this);
    public player Player=new player(this, keyH);
    public superobject obj[]=new superobject[10];
    public objplace place=new objplace(this);
    


    public gamepanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
    }

    public void setupGame(){
        place.setObject();
        playmusic(0);
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    public void run(){

        double drawInterval=1000000000/fps;
        double nextDrawTime= System.nanoTime()+drawInterval;
        


        while (gameThread!=null) {
        

            update();
            repaint();
            try {
                double remainingTime=(nextDrawTime-System.nanoTime())/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime+=drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           
        }
    }
    public void update(){
     Player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); //clear old frame(gives a clean canvas) also creates a temp drawing context g
        Graphics2D g2= (Graphics2D)g;
        tilemanager.draw(g2);
        for(int i=0;i<obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        Player.draw(g2);
        UI.draw(g2);
        
        g2.dispose();

       
    }
     public void playmusic(int i){
            sou.setFile(i);
            sou.play();
            sou.loop();
        }
    public void stopMusic(){
        sou.stop();
    }
    public void playSE(int i){
        sou2.setFile(i);
        sou2.play();
    }
}
