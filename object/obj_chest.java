package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_chest extends superobject{
    public obj_chest()
    {
        name="chest";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/chest (OLD).png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
