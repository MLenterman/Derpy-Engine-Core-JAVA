package derpyenginecore.graphics;

import java.nio.ByteBuffer;
import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWvidmode.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.system.MemoryUtil;

public class Window{
    private GLFWErrorCallback errorCallback = Callbacks.errorCallbackPrint(System.err);
    private String title;
    private int width;
    private int height;
    private boolean fullscreen;
    private long windowId;
    
    public Window(){
        this.title = "";
        this.width = 800;
        this.height = 600;
        this.fullscreen = false;
        this.windowId = MemoryUtil.NULL;
        
        init();
    }
    
    public Window(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        this.fullscreen = false;
        this.windowId = MemoryUtil.NULL;
        
        init();
    }
    
    public Window(String title, int width, int height, boolean fullscreen){
        this.title = title;
        this.width = width;
        this.height = height;
        this.fullscreen = fullscreen;
        this.windowId = MemoryUtil.NULL;
        
        init();
    }
    
    protected void init(){
        glfwSetErrorCallback(errorCallback);
        
        if(glfwInit() == GL_FALSE)
            throw new IllegalStateException("GLFW failed to initialize");
        
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        
        long monitorId = MemoryUtil.NULL;
        if(fullscreen)
            monitorId = glfwGetPrimaryMonitor();
        
        windowId = glfwCreateWindow(width, height, "Derpy Engine Test", monitorId, MemoryUtil.NULL);
        
        if(windowId == MemoryUtil.NULL)
            throw new RuntimeException("GLFW failed to create window");
        
        if(!fullscreen){
            ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(windowId, (GLFWvidmode.width(vidmode) - width) / 2, (GLFWvidmode.height(vidmode) - height) / 2);
        }
        
        glfwMakeContextCurrent(windowId);
        GLContext.createFromCurrent();
        glfwSwapInterval(1);
    }
    
    public void enableVSync(){
        glfwSwapInterval(1);
    }
    
    public void disableVSync(){
        glfwSwapInterval(0);
    }
    
    public void setVisible(boolean visible){
        if(visible)
            glfwShowWindow(windowId);
        else glfwHideWindow(windowId);
    }
    
    public boolean isClosed(){
        return glfwWindowShouldClose(windowId) == 1;
    }
    
    public String getOpenGLVersion(){
        return glGetString(GL_VERSION);
    }
    
    public void printOpenGLInfo(){
        System.out.println("OpenGL Info:");
        System.out.println("Vendor: " + glGetString(GL_VENDOR));
        System.out.println("Renderer: " + glGetString(GL_RENDERER));
        System.out.println("Version: " + glGetString(GL_VERSION));
        
        String[] splittedExtensions = glGetString(GL_EXTENSIONS).split(" ");
        System.out.println("Extensions:");
        
        for(String string : splittedExtensions)
            System.out.println(" - " + string);
    }
    
    public void update(){
        GLContext.createFromCurrent();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glfwPollEvents();
	glfwSwapBuffers(windowId);
    }

    @Override
    protected void finalize() throws Throwable{
        super.finalize(); 
        
        glfwDestroyWindow(windowId);
        glfwTerminate();
        errorCallback.release();
    }

}
