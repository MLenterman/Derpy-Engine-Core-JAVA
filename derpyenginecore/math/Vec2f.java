package derpyenginecore.math;

import java.io.Serializable;

public class Vec2f implements Cloneable, Serializable{
    public float x, y;
    
    public Vec2f(){
        this.x = 0;
        this.y = 0;
    }
    
    public Vec2f(final float x, final float y){
        this.x = x;
        this.y = y;
    }
    
    public Vec2f(final Vec2f vec){
        this.x = vec.x;
        this.y = vec.y;
    }
    
    public Vec2f add(final Vec2f rightHand){
        x += rightHand.x;
        y += rightHand.y;
        
        return this;
    }
    
    public Vec2f substract(final Vec2f rightHand){
        x -= rightHand.x;
        y -= rightHand.y;
        
        return this;
    }
    
    public Vec2f multiply(final Vec2f rightHand){
        x *= rightHand.x;
        y *= rightHand.y;
        
        return this;
    }
    
    public Vec2f devide(final Vec2f rightHand){
        x /= rightHand.x;
        y /= rightHand.y;
        
        return this;
    }
    
    public static Vec2f addVec3F(final Vec2f leftHand, final Vec2f rightHand){
        Vec2f vec = new Vec2f(leftHand);
        vec.add(rightHand);
        return vec;
    }
    
    public static Vec2f substractVec3F(final Vec2f leftHand, final Vec2f rightHand){
        Vec2f vec = new Vec2f(leftHand);
        vec.substract(rightHand);
        return vec;
    }
    
    public static Vec2f multiplyVec3F(final Vec2f leftHand, final Vec2f rightHand){
        Vec2f vec = new Vec2f(leftHand);
        vec.multiply(rightHand);
        return vec;
    }
    
    public static Vec2f devideVec3F(final Vec2f leftHand, final Vec2f rightHand){
        Vec2f vec = new Vec2f(leftHand);
        vec.devide(rightHand);
        return vec;
    }

    public Object clone(){
        return new Vec2f(this);
    }
    
    @Override
    public int hashCode(){
        int hash = 5;
        hash = 41 * hash + Float.floatToIntBits(this.x);
        hash = 41 * hash + Float.floatToIntBits(this.y);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        
        final Vec2f other = (Vec2f)obj;
        
        if(Float.floatToIntBits(this.x) != Float.floatToIntBits(other.x))
            return false;
        if(Float.floatToIntBits(this.y) != Float.floatToIntBits(other.y))
            return false;
        
        return true;
    }

    @Override
    public String toString(){
        return "Vec2F{" + "x=" + x + ", y=" + y + '}';
    }
}
