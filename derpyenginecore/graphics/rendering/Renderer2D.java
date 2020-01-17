package derpyenginecore.graphics.rendering;

import derpyenginecore.math.Mat4f;
import derpyenginecore.math.MatFactory;
import java.util.Stack;

public abstract class Renderer2D{
    protected Stack<Mat4f> transformationStack;
    protected Mat4f transformationBack;
    
    public Renderer2D(){
        this.transformationStack = new Stack();
        this.transformationStack.addElement(MatFactory.indentity());
        this.transformationBack = transformationStack.lastElement();
    }
    
    public void begin(){
        
    }
    
    public abstract void submit(final Renderable2D renderable);
    
    public void end(){
        
    }
    
    public abstract void flush();
    
    public void push(final Mat4f matrix){
        transformationStack.push(transformationStack.lastElement().multiply(matrix));
        transformationBack = transformationStack.lastElement();
    }
    
    public void push(final Mat4f matrix, boolean override){
        if(override)
            transformationStack.push(matrix);
        else transformationStack.push(transformationStack.lastElement().multiply(matrix));
        
        transformationBack = transformationStack.lastElement();
    }
    
    public void pop(){
        if(transformationStack.size() > 1)
            transformationStack.pop();
        
        transformationBack = transformationStack.lastElement();
    }
}
