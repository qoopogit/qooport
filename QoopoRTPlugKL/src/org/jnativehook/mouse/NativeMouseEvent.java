package org.jnativehook.mouse;
import java.awt.Point;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;

public class NativeMouseEvent extends NativeInputEvent {

    private static final long serialVersionUID = 6869201569046923469L;
    private int x;
    private int y;
    private int clickCount;
    private int button;
    public static final int NATIVE_MOUSE_FIRST = 2500;
    public static final int NATIVE_MOUSE_LAST = 2505;
    public static final int NATIVE_MOUSE_CLICKED = 2500;
    public static final int NATIVE_MOUSE_PRESSED = 2501;
    public static final int NATIVE_MOUSE_RELEASED = 2502;
    public static final int NATIVE_MOUSE_MOVED = 2503;
    public static final int NATIVE_MOUSE_DRAGGED = 2504;
    public static final int NATIVE_MOUSE_WHEEL = 2505;
    public static final int NOBUTTON = 0;
    public static final int BUTTON1 = 1;
    public static final int BUTTON2 = 2;
    public static final int BUTTON3 = 3;
    public static final int BUTTON4 = 4;
    public static final int BUTTON5 = 5;

    public NativeMouseEvent(int paramInt1, long paramLong, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        this(paramInt1, paramLong, paramInt2, paramInt3, paramInt4, paramInt5, 0);
    }

    public NativeMouseEvent(int paramInt1, long paramLong, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        super(GlobalScreen.getInstance(), paramInt1, paramLong, paramInt2);
        this.x = paramInt3;
        this.y = paramInt4;
        this.clickCount = paramInt5;
        this.button = paramInt6;
    }

    public int getButton() {
        return this.button;
    }

    public int getClickCount() {
        return this.clickCount;
    }

    public Point getPoint() {
        return new Point(this.x, this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String paramString() {
        StringBuilder localStringBuilder = new StringBuilder(255);
        switch (getID()) {
            case 2500:
                localStringBuilder.append("NATIVE_MOUSE_CLICKED");
                break;
            case 2501:
                localStringBuilder.append("NATIVE_MOUSE_PRESSED");
                break;
            case 2502:
                localStringBuilder.append("NATIVE_MOUSE_RELEASED");
                break;
            case 2503:
                localStringBuilder.append("NATIVE_MOUSE_MOVED");
                break;
            case 2504:
                localStringBuilder.append("NATIVE_MOUSE_DRAGGED");
                break;
            case 2505:
                localStringBuilder.append("NATIVE_MOUSE_WHEEL");
                break;
            default:
                localStringBuilder.append("unknown type");
        }
        localStringBuilder.append(",(");
        localStringBuilder.append(this.x);
        localStringBuilder.append(',');
        localStringBuilder.append(this.y);
        localStringBuilder.append("),");
        localStringBuilder.append("button=");
        localStringBuilder.append(this.button);
        if (getModifiers() != 0) {
            localStringBuilder.append(",modifiers=");
            localStringBuilder.append(getModifiersText(getModifiers()));
        }
        localStringBuilder.append(",clickCount=");
        localStringBuilder.append(getClickCount());
        return localStringBuilder.toString();
    }
}
