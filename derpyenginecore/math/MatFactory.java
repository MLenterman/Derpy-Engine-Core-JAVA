package derpyenginecore.math;

public final class MatFactory{
    private MatFactory(){
        
    }
    
    public static Mat4f indentity(){
        return new Mat4f(1F);
    }
    
    public static Mat4f orthographic(final float left, final float right, final float bottom, final float top, final float near, final float far){
        Mat4f mat = new Mat4f(1F);
        
        mat.elements[0 + 0 * Mat4f.SIZE] = 2.0F / (right - left);
        mat.elements[1 + 1 * Mat4f.SIZE] = 2.0F / (top - bottom);
        mat.elements[2 + 2 * Mat4f.SIZE] = 2.0F / (near - far);
        mat.elements[0 + 3 * Mat4f.SIZE] = (left + right) / (left - right);
        mat.elements[1 + 3 * Mat4f.SIZE] = (bottom + top) / (bottom - top);
        mat.elements[2 + 3 * Mat4f.SIZE] = (far + near) / (far - near);

	return mat;
    }
    
    public static Mat4f perspective(final double fov, final float aspectRatio, final float near, final float far){
        Mat4f mat = new Mat4f(1F);
        
        float tempRes = 1.0F / (float)Math.tan(Math.toRadians(0.5D * fov));
        mat.elements[0 + 0 * Mat4f.SIZE] = tempRes / aspectRatio;
        mat.elements[1 + 1 * Mat4f.SIZE] = tempRes;
        mat.elements[2 + 2 * Mat4f.SIZE] = (near + far) / (near - far);
        mat.elements[3 + 2 * Mat4f.SIZE] = -1.0F;
        mat.elements[2 + 3 * Mat4f.SIZE] = (2.0F * near * far) / (near - far);

        return mat;
    }
    
    public static Mat4f translation(final Vec3f translation){
        Mat4f mat = new Mat4f(1F);
        
        mat.elements[0 + 3 * Mat4f.SIZE] = translation.x;
        mat.elements[1 + 3 * Mat4f.SIZE] = translation.y;
        mat.elements[2 + 3 * Mat4f.SIZE] = translation.z;

        return mat;
    }
    
    public static Mat4f rotation(final float angle, final Vec3f axis){
        Mat4f mat = new Mat4f(1F);

        float rads = (float)Math.toRadians(angle);
        float cos = (float)Math.cos(rads);
        float sin = (float)Math.sin(rads);
        float oc = 1.0F - cos;

        mat.elements[0 + 0 * Mat4f.SIZE] = axis.x * oc + cos;
        mat.elements[1 + 0 * Mat4f.SIZE] = axis.y * axis.x * oc + axis.z * sin;
        mat.elements[2 + 0 * Mat4f.SIZE] = axis.x * axis.z * oc - axis.y * sin;
        mat.elements[0 + 1 * Mat4f.SIZE] = axis.x * axis.y * oc - axis.z * sin;
        mat.elements[1 + 1 * Mat4f.SIZE] = axis.y * oc + cos;
        mat.elements[2 + 1 * Mat4f.SIZE] = axis.y * axis.z * oc + axis.x * sin;
        mat.elements[0 + 2 * Mat4f.SIZE] = axis.x * axis.z * oc + axis.y * sin;
        mat.elements[1 + 2 * Mat4f.SIZE] = axis.y * axis.z * oc - axis.x * sin;
        mat.elements[2 + 2 * Mat4f.SIZE] = axis.z * oc + cos;
		
        return mat;
    }
    
    public static Mat4f scale(final Vec3f scale){
        Mat4f mat = new Mat4f(1F);

        mat.elements[0 + 0 * Mat4f.SIZE] = scale.x;
        mat.elements[1 + 1 * Mat4f.SIZE] = scale.y;
        mat.elements[2 + 2 * Mat4f.SIZE] = scale.z;

        return mat;
    }
}
