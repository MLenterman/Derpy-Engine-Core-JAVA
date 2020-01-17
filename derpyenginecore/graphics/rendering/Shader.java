package derpyenginecore.graphics.rendering;

import derpyenginecore.math.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader{
    private final String vertShaderPath;
    private final String fragShaderPath;
    public final int shaderId;
    
    public Shader(){
        this.vertShaderPath = null;
        this.fragShaderPath = null;
        this.shaderId = 0;
    }
    
    public Shader(final String vertShaderPath, final String fragShaderPath){
        this.vertShaderPath = vertShaderPath;
        this.fragShaderPath = fragShaderPath;
        this.shaderId = loadShader();
    }
    
    private int loadShader(){
        int prog = glCreateProgram();

        int vert = glCreateShader(GL_VERTEX_SHADER);
        int frag = glCreateShader(GL_FRAGMENT_SHADER);
        

        CharSequence vertSource = readShaderSource(vertShaderPath);
        CharSequence fragSource = readShaderSource(fragShaderPath);

        glShaderSource(vert, vertSource);
        glCompileShader(vert);

        if(glGetShaderi(vert, GL_COMPILE_STATUS) == GL_FALSE){
            String error = glGetShaderInfoLog(vert);
            glDeleteShader(vert);
            throw new RuntimeException("OpenGL failed to compile vertex shader: " + error);
        }
        
        glShaderSource(frag, fragSource);
        glCompileShader(frag);

        if(glGetShaderi(frag, GL_COMPILE_STATUS) == GL_FALSE){
            String error = glGetShaderInfoLog(frag);
            glDeleteShader(frag);
            throw new RuntimeException("OpenGL failed to compile fragment shader: " + error);
        }
        
        glAttachShader(prog, vert);
        glAttachShader(prog, frag);

        glLinkProgram(prog);
        glValidateProgram(prog);

        glDeleteShader(vert);
        glDeleteShader(frag);

        return prog;
    }
    
    private CharSequence readShaderSource(String path){
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        String line;
        
        try{
            reader = new BufferedReader(new FileReader(path));
            
            while((line = reader.readLine()) != null)
                builder.append(line).append('\n');
            
            builder.deleteCharAt(builder.length() - 1);
            reader.close();
        }catch(FileNotFoundException ex){
            Logger.getLogger(Shader.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            Logger.getLogger(Shader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return builder;
    }
    
    private int getUniformLocation(final String name){
        return glGetUniformLocation(shaderId, name);
    }
    
    public void setUniform1f(final String name, final float value){
        glUniform1f(getUniformLocation(name), value);
    }
    
    public void setUniform1fv(final String name, final float[] value){
        glUniform1fv(getUniformLocation(name), FloatBuffer.wrap(value));
    }

    public void setUniform1i(final String name, final int value){
        glUniform1i(getUniformLocation(name), value);
    }
    
    public void setUniform1iv(final String name, final int[] value){
        glUniform1iv(getUniformLocation(name), IntBuffer.wrap(value));
    }
    
    public void setUniform2f(final String name, final Vec2f vector){
        glUniform2f(getUniformLocation(name), vector.x, vector.y);
    }
    
    public void setUniform3f(final String name, final Vec3f vector){
        glUniform3f(getUniformLocation(name), vector.x, vector.y, vector.z);
    }
    
    public void setUniform4f(final String name, final Vec4f vector){
        glUniform4f(getUniformLocation(name), vector.x, vector.y, vector.z, vector.w);
    }

    public void setUniformMat4(final String name, final Mat4f matrix){
        glUniformMatrix4fv(getUniformLocation(name), false, FloatBuffer.wrap(matrix.elements));
    }
    
    public void enable(){
        glUseProgram(shaderId);
    }
    
    public void disable(){
        glUseProgram(0);
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize();
        glDeleteProgram(shaderId);
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.vertShaderPath);
        hash = 97 * hash + Objects.hashCode(this.fragShaderPath);
        hash = 97 * hash + this.shaderId;
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;

        final Shader other = (Shader)obj;
        
        if(!Objects.equals(this.vertShaderPath, other.vertShaderPath))
            return false;
        if(!Objects.equals(this.fragShaderPath, other.fragShaderPath))
            return false;
        if(this.shaderId != other.shaderId)
            return false;

        return true;
    }

    @Override
    public String toString(){
        return "Shader{" + "vertShaderPath=" + vertShaderPath + ", fragShaderPath=" + fragShaderPath + ", shaderId=" + shaderId + '}';
    }

}
