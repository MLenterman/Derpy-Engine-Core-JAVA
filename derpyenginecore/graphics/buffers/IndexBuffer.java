package derpyenginecore.graphics.buffers;

import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL15.*;

public class IndexBuffer{
    private int bufferId;
    private int count;
    
    private IndexBuffer(){
        
    }
    
    public IndexBuffer(final int[] data){
        this.count = data.length;
        
        this.bufferId = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, IntBuffer.wrap(data), GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    public void bind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferId);
    }
    
    public void unbind(){
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }
    
    public int getCount(){
        return count;
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        
        glDeleteBuffers(bufferId);
    }

}
