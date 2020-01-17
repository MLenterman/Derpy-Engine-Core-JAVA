package derpyenginecore;

/*
import derpyenginecore.graphics.MonitorUtils;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetMonitorName;
import static org.lwjgl.glfw.GLFW.glfwGetMonitorPhysicalSize;
import static org.lwjgl.glfw.GLFW.glfwGetMonitors;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.glfw.GLFWvidmode.HEIGHT;
import static org.lwjgl.glfw.GLFWvidmode.WIDTH;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glGetString;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;
*/

import derpyenginecore.graphics.Window;
import derpyenginecore.graphics.rendering.Shader;

public class Test{

    public static void main(String[] args){
        Window window = new Window("Derpy Engine Test", 800, 600);
        window.setVisible(true);
        
        //window.printOpenGLInfo();
        //Shader shader = new Shader("C:/Users/MARCEL-MSI/Dropbox/Hobby/Programmeren/Java/DerpyEngineCore/src/derpyenginecore/graphics/shaders/Basic.vert", "C:/Users/MARCEL-MSI/Dropbox/Hobby/Programmeren/Java/DerpyEngineCore/src/derpyenginecore/graphics/shaders/Basic.frag");

        while(!window.isClosed()){
            window.update();

        }
    }
    
}
