package org.jnativehook.mouse;
import java.util.EventListener;

public abstract interface NativeMouseListener extends EventListener {

    public abstract void nativeMouseClicked(NativeMouseEvent paramNativeMouseEvent);

    public abstract void nativeMousePressed(NativeMouseEvent paramNativeMouseEvent);

    public abstract void nativeMouseReleased(NativeMouseEvent paramNativeMouseEvent);
}
