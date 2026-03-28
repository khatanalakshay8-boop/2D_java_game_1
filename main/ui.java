package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.obj_key;

public class ui {
    gamepanel gp;
    int n;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int messageCounter=0;

    public ui(gamepanel gp){
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        obj_key key=new obj_key();
        keyImage=key.image;
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("X "+gp.Player.hasKey,74,60);

    if(messageOn){
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
        messageCounter++;
        if(messageCounter>120){
            messageCounter=0;
            messageOn=false;
        }
    }

    }
}
