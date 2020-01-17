package derpyenginecore.graphics.rendering;

import derpyenginecore.graphics.fonts.Font;
import derpyenginecore.math.Vec3f;

public interface FontRenderer{
    void drawString(final String text, final Vec3f position, final Font font, final int color);
}
