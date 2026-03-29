package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamepanel;
import main.keyhandler;
public class player extends Entity {
    gamepanel gp;
    keyhandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey=0;

    
    public player(gamepanel gp,keyhandler keyH){
        this.gp=gp;
        this.keyH=keyH;
        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);
        solidArea=new Rectangle(8,16,28,28);//constructors accepts x,y,width,height
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
        direction="down";
    }
    public void getPlayerImage(){
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_up_1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_up_2.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_down_1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_down_2.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_left_1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_left_2.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_right_1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("../images/walk_sprites/boy_right_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed==true|| keyH.downPressed==true|| keyH.leftPressed==true|| keyH.rightPressed==true)
        {
           if(keyH.upPressed==true){
            direction="up";
        }
        else if(keyH.downPressed==true){
            direction="down";
        }
        else if(keyH.leftPressed==true){
            direction="left";
        }
        else if(keyH.rightPressed==true){
            direction="right";
        }
        collisionOn=false;
        gp.cChecker.checkTile(this);


        int objIndex=gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);
        if(collisionOn==false){
            switch (direction) {
                case "up":
                    worldY-=speed;
                    break;
            case "down":
                    worldY+=speed;
                    break;
            case "left":
                    worldX-=speed;
                    break;
            case "right":
                    worldX+=speed;
                    break;
            }
        }

        spriteCounter++;
        if(spriteCounter>10){
            if(spriteNum==1){
                spriteNum=2;}
            else if(spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
            
        }
    }
    }

    public void pickUpObject(int i){
        if(i!=999){
            String objectName=gp.obj[i].name;

            switch(objectName){
                case "key":
                    hasKey++;
                    gp.obj[i]=null;
                    gp.playSE(1);
                    gp.UI.showMessage("You got key!!!");
                    break;
                case "door":
                    if(hasKey>0){
                        gp.obj[i]=null;
                        hasKey--;
                        gp.playSE(3);
                    }
                    else{
                        gp.UI.showMessage("You don't have a key");
                    }
                    break;
                case "boots":
                    speed+=2;
                    gp.obj[i]=null;
                    gp.UI.showMessage("Speed up!!!");

                    break;
                case "chest":
                    gp.UI.gameFinished=true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
    

            }
        }
    }


    public void draw(Graphics2D g2){
        BufferedImage image=null;
        switch(direction){
            case "up":
                if(spriteNum==1){
                image=up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum==1){
                    image=down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image=left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
            case "right":
                if(spriteNum==1){
                    image=right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
