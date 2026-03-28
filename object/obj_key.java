package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class obj_key extends superobject {
    public obj_key()
    {
        name="key";
        try{
            image=ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
