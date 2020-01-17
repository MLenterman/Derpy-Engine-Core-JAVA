package derpyenginecore.math;

import java.io.Serializable;

public class Vec4f implements Cloneable, Serializable{
    public float x, y, z, w;
    
    public Vec4f(){
        this.x = 0;
        this.y = 0;
        this.z = 0; 
        this.w = 0;
    }
    
    public Vec4f(final float x, final float y, final float z, final float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    public Vec4f(final Vec4f vec){
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        this.w = vec.w;
    }
    
    public Vec4f add(final Vec4f rightHand){
        x += rightHand.x;
        y += rightHand.y;
        z += rightHand.z;
        w += rightHand.w;
        
        return this;
    }
    
    public Vec4f substract(final Vec4f rightHand){
        x -= rightHand.x;
        y -= rightHand.y;
        z -= rightHand.z;
        w -= rightHand.w;
        
        return this;
    }
    
    public Vec4f multiply(final Vec4f rightHand){
        x *= rightHand.x;
        y *= rightHand.y;
        z *= rightHand.z;
        w *= rightHand.w;
        
        return this;
    }
    
    public Vec4f devide(final Vec4f rightHand){
        x /= rightHand.x;
        y /= rightHand.y;
        z /= rightHand.z;
        w /= rightHand.w;
        
        return this;
    }
    
    public static Vec4f addVec3F(final Vec4f leftHand, final Vec4f rightHand){
        Vec4f vec = new Vec4f(leftHand);
        vec.add(rightHand);
        return vec;
    }
    
    public static Vec4f substractVec3F(final Vec4f leftHand, final Vec4f rightHand){
        Vec4f vec = new Vec4f(leftHand);
        vec.substract(rightHand);
        return vec;
    }
    
    public static Vec4f multiplyVec3F(final Vec4f leftHand, final Vec4f rightHand){
        Vec4f vec = new Vec4f(leftHand);
        vec.multiply(rightHand);
        return vec;
    }
    
    public static Vec4f devideVec3F(final Vec4f leftHand, final Vec4f rightHand){
        Vec4f vec = new Vec4f(leftHand);
        vec.devide(rightHand);
        return vec;
    }
    
    public Object clone(){
        return new Vec4f(this);
    }

    @Override
    public int hashCode(){
        int hash = 5;
        hash = 17 * hash + Float.floatToIntBits(this.x);
        hash = 17 * hash + Float.floatToIntBits(this.y);
        hash = 17 * hash + Float.floatToIntBits(this.z);
        hash = 17 * hash + Float.floatToIntBits(this.w);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;

        final Vec4f other = (Vec4f)obj;
        
        if(Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x))
            return false;
        if(Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y))
            return false;
        if(Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z))
            return false;
        if(Float.floatToIntBits(this.w) != Float.floatToIntBits(other.w))
            return false;

        return true;
    }

    @Override
    public String toString(){
        return "Vec4F{" + "x=" + x + ", y=" + y + ", z=" + z + ", w=" + w + '}';
    }
    
}
