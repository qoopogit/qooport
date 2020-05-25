package org.jnativehook;
import java.awt.Toolkit;
import java.util.EventObject;

public class NativeInputEvent extends EventObject {

    private static final long serialVersionUID = -4115869356455095225L;
    private int id;
    private long when;
    private int modifiers;
    public static final int SHIFT_MASK = 1;
    public static final int CTRL_MASK = 2;
    public static final int META_MASK = 4;
    public static final int ALT_MASK = 8;
    public static final int BUTTON1_MASK = 16;
    public static final int BUTTON2_MASK = 32;
    public static final int BUTTON3_MASK = 64;
    public static final int BUTTON4_MASK = 128;
    public static final int BUTTON5_MASK = 256;

    public NativeInputEvent(GlobalScreen paramGlobalScreen, int paramInt1, long paramLong, int paramInt2) {
        super(paramGlobalScreen);
        this.id = paramInt1;
        this.when = paramLong;
        this.modifiers = paramInt2;
    }

    public int getID() {
        return this.id;
    }

    public long getWhen() {
        return this.when;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public void setModifiers(int paramInt) {
        this.modifiers = paramInt;
    }

    public static String getModifiersText(int paramInt) {
        StringBuilder localStringBuilder = new StringBuilder(255);
        if ((paramInt & 0x1) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.shift", "Shift"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x2) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.control", "Ctrl"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x4) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.meta", "Meta"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x8) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.alt", "Alt"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x10) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.button1", "Button1"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x20) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.button2", "Button2"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x40) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.button3", "Button3"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x80) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.button4", "Button4"));
            localStringBuilder.append('+');
        }
        if ((paramInt & 0x100) != 0) {
            localStringBuilder.append(Toolkit.getProperty("AWT.button5", "Button5"));
            localStringBuilder.append('+');
        }
        if (localStringBuilder.length() > 0) {
            localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
        }
        return localStringBuilder.toString();
    }

    public String paramString() {
        StringBuilder localStringBuilder = new StringBuilder(255);
        localStringBuilder.append("id=");
        localStringBuilder.append(getID());
        localStringBuilder.append(',');
        localStringBuilder.append("when=");
        localStringBuilder.append(getWhen());
        localStringBuilder.append(',');
        localStringBuilder.append("mask=");
        localStringBuilder.append(Integer.toBinaryString(getModifiers()));
        localStringBuilder.append(',');
        localStringBuilder.append("modifiers=");
        localStringBuilder.append(getModifiersText(getModifiers()));
        return localStringBuilder.toString();
    }
}
