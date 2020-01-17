package derpyenginecore.graphics.rendering;

import derpyenginecore.math.Mat4f;

public interface TransformationStack{
    void push(final Mat4f matrix);
    void push(final Mat4f matrix, boolean override);
    void pop();
}
