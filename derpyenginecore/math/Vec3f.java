package derpyenginecore.math;

import java.io.Serializable;

public class Vec3f implements Cloneable, Serializable{
    public float x, y, z;
    
    public Vec3f(){
        this.x = 0;
        this.y = 0;
        this.z = 0; 
    }
    
    public Vec3f(final float x, final float y, final float z){
        this.x = x;
        this.y = y;
        this.z = z; 
    }
    
    public Vec3f(final Vec3f vec){
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }
    
    public Vec3f add(final Vec3f rightHand){
        x += rightHand.x;
        y += rightHand.y;
        z += rightHand.z;
        
        return this;
    }
    
    public Vec3f substract(final Vec3f rightHand){
        x -= rightHand.x;
        y -= rightHand.y;
        z -= rightHand.z;
        
        return this;
    }
    
    public Vec3f multiply(final Vec3f rightHand){
        x *= rightHand.x;
        y *= rightHand.y;
        z *= rightHand.z;
        
        return this;
    }
    
    public Vec3f devide(final Vec3f rightHand){
        x /= rightHand.x;
        y /= rightHand.y;
        z /= rightHand.z;
        
        return this;
    }
    
    public static Vec3f addVec3F(final Vec3f leftHand, final Vec3f rightHand){
        Vec3f vec = new Vec3f(leftHand);
        vec.add(rightHand);
        return vec;
    }
    
    public static Vec3f substractVec3F(final Vec3f leftHand, final Vec3f rightHand){
        Vec3f vec = new Vec3f(leftHand);
        vec.substract(rightHand);
        return vec;
    }
    
    public static Vec3f multiplyVec3F(final Vec3f leftHand, final Vec3f rightHand){
        Vec3f vec = new Vec3f(leftHand);
        vec.multiply(rightHand);
        return vec;
    }
    
    public static Vec3f devideVec3F(final Vec3f leftHand, final Vec3f rightHand){
        Vec3f vec = new Vec3f(leftHand);
        vec.devide(rightHand);
        return vec;
    }
    
    public Object clone(){
        return new Vec3f(this);
    }

    @Override
    public int hashCode(){
        int hash = 7;
        hash = 71 * hash + Float.floatToIntBits(this.x);
        hash = 71 * hash + Float.floatToIntBits(this.y);
        hash = 71 * hash + Float.floatToIntBits(this.z);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        
        final Vec3f other = (Vec3f)obj;
        
        if(Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x))
            return false;
        if(Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y))
            return false;
        if(Float.floatToIntBits(this.z) != Float.floatToIntBits(other.z))
            return false;

        return true;
    }

    @Override
    public String toString(){
        return "Vec3F{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

}
