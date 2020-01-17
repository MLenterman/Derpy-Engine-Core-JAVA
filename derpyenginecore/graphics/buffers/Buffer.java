package derpyenginecore.graphics.buffers;

import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL15.*;

public class Buffer{
    private int bufferId;
    private int componentCount;
    
    private Buffer(){
        
    }
    
    public Buffer(final float[] data, final int componentCount){
        this.componentCount = componentCount;
        
        this.bufferId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, bufferId);
        glBufferData(GL_ARRAY_BUFFER, FloatBuffer.wrap(data), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }
    
    public int getComponentCount(){
        return componentCount;
    }
    
    public void bind(){
        glBindBuffer(GL_ARRAY_BUFFER, bufferId);
    }
    
    public void unbind(){
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        glDeleteBuffers(bufferId);
    }

}
