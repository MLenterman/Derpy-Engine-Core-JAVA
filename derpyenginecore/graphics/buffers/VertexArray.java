package derpyenginecore.graphics.buffers;

import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray{
    private int arrayId;
    private ArrayList<Buffer> buffers;
    
    public VertexArray(){
        this.buffers = new ArrayList();
        
        this.arrayId = glGenVertexArrays();
    }
    
    public void addBuffer(final Buffer buffer, final int index){
        glBindVertexArray(arrayId);
        buffer.bind();
        
        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, buffer.getComponentCount(), GL_FLOAT, false, 0, 0);
        
        buffer.unbind();
        glBindVertexArray(0);

        buffers.add(buffer);
    }
    
    public void bind(){
        glBindVertexArray(arrayId);
    }
    
    public void unbind(){
        glBindVertexArray(0);
    }
 
    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        glDeleteVertexArrays(arrayId);
    }

}
