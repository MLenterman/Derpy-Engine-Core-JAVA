package derpyenginecore.math;

import java.io.Serializable;
import java.util.Arrays;

public class Mat4f implements Cloneable, Serializable{
    public final static int SIZE = 4;
    
    public float[] elements;
    
    public Mat4f(){
        this.elements = new float[SIZE * SIZE];
        Arrays.fill(this.elements, 0F);
    }
    
    public Mat4f(final float diagonal){
        this.elements = new float[SIZE * SIZE];
        Arrays.fill(this.elements, 0F);
        
        this.elements[0 * 0 * SIZE] = diagonal;
        this.elements[1 * 1 * SIZE] = diagonal;
        this.elements[2 * 2 * SIZE] = diagonal;
        this.elements[3 * 3 * SIZE] = diagonal;
    }
    
    public Mat4f(final Mat4f mat){
        this.elements = Arrays.copyOf(mat.elements, mat.elements.length);
    }
    
    public Mat4f multiply(final Mat4f rightHand){
        float[] temp = new float[SIZE * SIZE];
        
        for (int y = 0; y < SIZE; y++){
            for (int x = 0; x < SIZE; x++){
                float sum = 0.0F;

                for (int e = 0; e < SIZE; e++){
                    sum += elements[x + e * SIZE] * rightHand.elements[e + y * SIZE];
                }
                
                temp[x + y * SIZE] = sum;
            }
        }

        elements = Arrays.copyOf(temp, temp.length);
        return this;
    }
    
    public Vec3f multiply(final Vec3f rightHand){
        return new Vec3f(
                elements[0 * 0 * SIZE] * rightHand.x + elements[1 * 0 * SIZE] * rightHand.y + elements[2 * 0 * SIZE] * rightHand.z + elements[3 * 0 * SIZE],
                elements[0 * 1 * SIZE] * rightHand.x + elements[1 * 1 * SIZE] * rightHand.y + elements[2 * 1 * SIZE] * rightHand.z + elements[3 * 1 * SIZE],
                elements[0 * 2 * SIZE] * rightHand.x + elements[1 * 2 * SIZE] * rightHand.y + elements[2 * 2 * SIZE] * rightHand.z + elements[3 * 2 * SIZE]);
    }
    
    public Vec4f multiply(final Vec4f rightHand){
        return new Vec4f(
                elements[0 * 0 * SIZE] * rightHand.x + elements[1 * 0 * SIZE] * rightHand.y + elements[2 * 0 * SIZE] * rightHand.z + elements[3 * 0 * SIZE] * rightHand.w,
                elements[0 * 1 * SIZE] * rightHand.x + elements[1 * 1 * SIZE] * rightHand.y + elements[2 * 1 * SIZE] * rightHand.z + elements[3 * 1 * SIZE] * rightHand.w,
                elements[0 * 2 * SIZE] * rightHand.x + elements[1 * 2 * SIZE] * rightHand.y + elements[2 * 2 * SIZE] * rightHand.z + elements[3 * 2 * SIZE] * rightHand.w,
                elements[0 * 3 * SIZE] * rightHand.x + elements[1 * 3 * SIZE] * rightHand.y + elements[2 * 3 * SIZE] * rightHand.z + elements[3 * 3 * SIZE] * rightHand.w);
    }
    
}
