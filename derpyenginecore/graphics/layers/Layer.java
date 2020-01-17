package derpyenginecore.graphics.layers;

import derpyenginecore.graphics.rendering.Renderable2D;
import derpyenginecore.graphics.rendering.Renderer2D;
import derpyenginecore.graphics.rendering.Shader;
import derpyenginecore.math.Mat4f;
import java.util.ArrayList;

public class Layer{
    private Renderer2D renderer;
    private ArrayList<Renderable2D> renderables;
    private Shader shader;
    private Mat4f projectionMatrix;
    
    private Layer(){
        
    }
    
    public Layer(final Renderer2D renderer, final Shader shader, final Mat4f projectionMatrix){
        this.renderer = renderer;
        this.shader = shader;
        this.projectionMatrix = projectionMatrix;
        
        this.shader.enable();
        this.shader.setUniformMat4("pr_matrix", projectionMatrix);
        
        int[] textureIds = {
            0,  1,  2,  3,  4,  5,  6,  7,  8,  9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
            30, 31};

        this.shader.setUniform1iv("textures", textureIds);
        this.shader.disable();
    }
    
    public void addRenderable(final Renderable2D renderable){
        renderables.add(renderable);
    }
    
    public void render(){
        shader.enable();
        renderer.begin();
        
        for(Renderable2D renderable : renderables)
            renderable.submit(renderer);

        renderer.end();
        renderer.flush();
    }
    
    public ArrayList<Renderable2D> getRenderables(){
        return renderables;
    }
}
