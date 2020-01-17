package derpyenginecore.graphics.rendering;

import derpyenginecore.graphics.textures.Texture;
import derpyenginecore.math.Vec2f;
import derpyenginecore.math.Vec3f;
import derpyenginecore.math.Vec4f;
import java.util.ArrayList;

public class Renderable2D{
    public class VertexData{
        Vec3f vertex;
        Vec2f uvMap;
        float textureid;
        int color;
    }
    
    protected Vec3f position;
    protected Vec2f size;
    protected int color;
    protected ArrayList<Vec2f> uvMap;
    protected Texture texture;
    
    public Renderable2D(){
        
    }
    
    public Renderable2D(final Vec3f position, final Vec2f size, int color){
        this.position = position;
        this.size = size;
        this.color = color;
        
        this.uvMap = new ArrayList();
        setUVMapDefaults();
        
        this.texture = null; 
    }
    
    public Renderable2D(final Vec3f position, final Vec2f size, Texture texture){
        this.position = position;
        this.size = size;
        this.color = 0;
        
        this.uvMap = new ArrayList();
        setUVMapDefaults();
        
        this.texture = texture;
    }
    
    public void submit(Renderer2D renderer){
        renderer.submit(this);
    }

    public void setColor(int color){
        this.color = color;
    }

    public void setColor(Vec4f color){                    
        this.color = (int)(color.w * 255F) << 24 | (int)(color.z * 255F) << 16 | (int)(color.y * 255F) << 8 | (int)(color.x * 255F);
    }

    public int getColor(){
        return color;
    }

    public Vec3f getPosition(){
        return position;
    }

    public Vec2f getSize(){
        return size;
    }

    public ArrayList<Vec2f> getUV(){
        return uvMap;
    }

    public int getTextureId(){
        return texture.getId();
    }
    
    private void setUVMapDefaults(){
        uvMap.add(new Vec2f(0, 0));
        uvMap.add(new Vec2f(0, 1));
        uvMap.add(new Vec2f(1, 1));
        uvMap.add(new Vec2f(1, 0));
    }
    
}
