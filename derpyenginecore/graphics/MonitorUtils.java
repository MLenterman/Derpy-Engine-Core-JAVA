package derpyenginecore.graphics;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.Callbacks;
import static org.lwjgl.glfw.GLFW.glfwGetMonitorName;
import static org.lwjgl.glfw.GLFW.glfwGetMonitorPhysicalSize;
import static org.lwjgl.glfw.GLFW.glfwGetMonitorPos;
import static org.lwjgl.glfw.GLFW.glfwGetMonitors;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetVideoModes;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.system.MemoryUtil;

public final class MonitorUtils{
    private static GLFWErrorCallback errorCallback = Callbacks.errorCallbackPrint(System.err);
    
    public static void getPrimaryMonitor(){
        glfwSetErrorCallback(errorCallback);

        if (glfwInit() == GL_FALSE)
            throw new IllegalStateException("GLFW initialization failed");
    
        PointerBuffer monitors = glfwGetMonitors();
        long monitorId = monitors.get(0);
        
        System.out.println(glfwGetMonitorName(monitorId));
        printMonitorVideoModes(monitorId);
    }
    
    public static void printMonitorInfo(long monitorId){
        
        
        printMonitorVideoModes(monitorId);
        
    }
    
    public static void printMonitorVideoModes(long monitorId){
        //IntBuffer count = IntBuffer.allocate(4);
        ByteBuffer videoModes = glfwGetVideoModes(monitorId, IntBuffer.allocate(1));
        //System.out.println("Video mode width: " + GLFWvidmode.width(videoModes));
        //System.out.println("Video mode height: " + GLFWvidmode.height(videoModes));
    }
}
