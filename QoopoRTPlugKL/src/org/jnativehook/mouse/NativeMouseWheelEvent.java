package org.jnativehook.mouse;

public class NativeMouseWheelEvent extends NativeMouseEvent {

    private static final long serialVersionUID = -183110294708745910L;
    public static final int WHEEL_UNIT_SCROLL = 0;
    public static final int WHEEL_BLOCK_SCROLL = 1;
    private int scrollAmount;
    private int scrollType;
    private int wheelRotation;

    public NativeMouseWheelEvent(int paramInt1, long paramLong, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8) {
        super(paramInt1, paramLong, paramInt2, paramInt3, paramInt4, paramInt5);
        this.scrollType = paramInt6;
        this.scrollAmount = paramInt7;
        this.wheelRotation = paramInt8;
    }

    public int getScrollAmount() {
        return this.scrollAmount;
    }

    public int getScrollType() {
        return this.scrollType;
    }

    public int getWheelRotation() {
        return this.wheelRotation;
    }

    public String paramString() {
        StringBuilder localStringBuilder = new StringBuilder(super.paramString());
        localStringBuilder.append(",scrollType=");
        switch (getScrollType()) {
            case 0:
                localStringBuilder.append("WHEEL_UNIT_SCROLL");
                break;
            case 1:
                localStringBuilder.append("WHEEL_BLOCK_SCROLL");
                break;
            default:
                localStringBuilder.append("unknown scroll type");
        }
        localStringBuilder.append(",scrollAmount=");
        localStringBuilder.append(getScrollAmount());
        localStringBuilder.append(",wheelRotation=");
        localStringBuilder.append(getWheelRotation());
        return localStringBuilder.toString();
    }
}
