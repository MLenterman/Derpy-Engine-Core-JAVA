package derpyenginecore.graphics.sprites;

import derpyenginecore.graphics.rendering.Renderable2D;
import derpyenginecore.graphics.textures.Texture;
import derpyenginecore.math.*;

public class Sprite extends Renderable2D{  
    public Vec3f positionP;
    
    public Sprite(final float posX, final float posY, final float width, final float height, final int color){
        super(new Vec3f(posX, posY, 0), new Vec2f(width, height), color);
        
        this.positionP = position;
    }
    
    public Sprite(final float posX, final float posY, final float width, final float height, final Texture texture){
        super(new Vec3f(posX, posY, 0), new Vec2f(width, height), texture);
        
        this.positionP = position;
    }
 
}
