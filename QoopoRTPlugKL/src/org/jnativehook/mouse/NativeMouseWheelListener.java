package org.jnativehook.mouse;
import java.util.EventListener;

public abstract interface NativeMouseWheelListener extends EventListener {

    public abstract void nativeMouseWheelMoved(NativeMouseWheelEvent paramNativeMouseWheelEvent);
}
