package derpyenginecore.graphics.rendering;

import derpyenginecore.graphics.buffers.IndexBuffer;
import derpyenginecore.math.*;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.ArrayList;
import static org.lwjgl.opengl.ARBVertexBufferObject.glBindBufferARB;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class BatchRenderer2D extends Renderer2D{
    public final int RENDERER_MAX_SPRITES = 60000;
    public final int RENDERER_VERTEX_SIZE = 24;
    public final int RENDERER_SPRITE_SIZE = RENDERER_VERTEX_SIZE * 4;
    public final int RENDERER_BUFFER_SIZE = RENDERER_SPRITE_SIZE * RENDERER_MAX_SPRITES;

    public final int RENDERER_MAX_TEXTURES = 32;
    public final int RENDERER_INDICES_SIZE = RENDERER_MAX_SPRITES * 6;

    public final int SHADER_VERTEX_INDEX = 0;
    public final int SHADER_UV_INDEX = 1;
    public final int SHADER_TID_INDEX = 2;
    public final int SHADER_COLOR_INDEX = 3;

    private int vao;
    private int vbo;
    private IndexBuffer ibo;
    private int indexCount;
    private ByteBuffer buffer;
    private ArrayList<Integer> textureSlots;
    
    public BatchRenderer2D(){
        this.textureSlots = new ArrayList();
        init();
    }
    
    private void init(){
        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, RENDERER_BUFFER_SIZE, GL_DYNAMIC_DRAW);

        glEnableVertexAttribArray(SHADER_VERTEX_INDEX);
        glEnableVertexAttribArray(SHADER_UV_INDEX);
        glEnableVertexAttribArray(SHADER_TID_INDEX);
        glEnableVertexAttribArray(SHADER_COLOR_INDEX);

        glVertexAttribPointer(SHADER_VERTEX_INDEX, 3, GL_FLOAT, false, RENDERER_VERTEX_SIZE, 0);
        glVertexAttribPointer(SHADER_UV_INDEX, 2, GL_FLOAT, false, RENDERER_VERTEX_SIZE, RENDERER_VERTEX_SIZE + 8);
        glVertexAttribPointer(SHADER_TID_INDEX, 1, GL_FLOAT, false, RENDERER_VERTEX_SIZE, RENDERER_VERTEX_SIZE + 4);
        glVertexAttribPointer(SHADER_COLOR_INDEX, 4, GL_UNSIGNED_BYTE, true, RENDERER_VERTEX_SIZE, RENDERER_VERTEX_SIZE + 4);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        
        int[] indices = new int[RENDERER_INDICES_SIZE];
        int offset = 0;
        for (int i = 0; i < RENDERER_INDICES_SIZE; i += 6){
            indices[  i  ] = offset + 0;
            indices[i + 1] = offset + 1;
            indices[i + 2] = offset + 2;

            indices[i + 3] = offset + 2;
            indices[i + 4] = offset + 3;
            indices[i + 5] = offset + 0;

            offset += 4;
        }

        ibo = new IndexBuffer(indices);
        glBindVertexArray(0);
    }

    @Override
    public void begin(){
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        
        buffer = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY);
    }

    @Override
    public void submit(Renderable2D renderable){
        Vec3f position = renderable.getPosition();
        Vec2f size = renderable.getSize();
        int color = renderable.getColor();
        ArrayList<Vec2f> uv = renderable.getUV();
        int textureId = renderable.getTextureId();

        float ts = 0F;
        if(textureId > 0){
            boolean found = false;
            for(int i = 0; i < textureSlots.size(); i++){
                if(textureSlots.get(i) == textureId){
                    ts = (float)(i + 1);
                    found = true;
                    break;
                }
            }

        if(!found){
            if(textureSlots.size() >= RENDERER_MAX_TEXTURES){
                end();
                flush();
                begin();
            }
            
            textureSlots.add(textureId);
            ts = (float)(textureSlots.size());
            }
        }
        /*
        buffer.vertex = transformationBack.multiply(position);
        buffer.uv = uv[0];
        buffer.textureId = ts;
        buffer.color = color;
        buffer++;

        buffer.vertex = *m_TransformationBack * maths::vec3(position.x, position.y + size.y, position.z);
        buffer.uv = uv[1];
        buffer.textureId = ts;
        buffer.color = color;
        buffer++;

        buffer.vertex = *m_TransformationBack * maths::vec3(position.x + size.x, position.y + size.y, position.z);
        buffer.uv = uv[2];
        buffer.textureId = ts;
        buffer.color = color;
        buffer++;

        buffer.vertex = *m_TransformationBack * maths::vec3(position.x + size.x, position.y, position.z);
        buffer.uv = uv[3];
        buffer.textureId = ts;
        buffer.color = color;
        buffer++;

        indexCount += 6;
        */
    }

    @Override
    public void flush(){
        
    }

}
