package org.jnativehook.mouse;
import java.util.EventListener;

public abstract interface NativeMouseMotionListener extends EventListener {

    public abstract void nativeMouseMoved(NativeMouseEvent paramNativeMouseEvent);

    public abstract void nativeMouseDragged(NativeMouseEvent paramNativeMouseEvent);
}
