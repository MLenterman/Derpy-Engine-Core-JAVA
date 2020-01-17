package derpyenginecore.graphics.layers;

import derpyenginecore.graphics.rendering.Renderable2D;
import derpyenginecore.graphics.rendering.Renderer2D;
import derpyenginecore.math.*;
import java.util.ArrayList;

public class Group extends Renderable2D{
    private ArrayList<Renderable2D> renderables;
    private Mat4f transformationMatrix;
    
    public Group(){
        super();
        
    }
    
    public Group(final Mat4f transformationMatrix){
        super();
        this.transformationMatrix = transformationMatrix;
    }

    public void add(final Renderable2D renderable){
        renderables.add(renderable);
    }
    
    @Override
    public void submit(Renderer2D renderer){
        renderer.push(transformationMatrix);

        for(Renderable2D renderable : renderables)
            renderable.submit(renderer);

        renderer.pop();
    }

}
