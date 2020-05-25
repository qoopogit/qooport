package org.jnativehook.keyboard;
import java.util.EventListener;

public abstract interface NativeKeyListener extends EventListener {

    public abstract void nativeKeyPressed(NativeKeyEvent paramNativeKeyEvent);

    public abstract void nativeKeyReleased(NativeKeyEvent paramNativeKeyEvent);

    public abstract void nativeKeyTyped(NativeKeyEvent paramNativeKeyEvent);
}
