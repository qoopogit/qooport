package org.jnativehook;

public class NativeHookException extends Exception {

    private static final long serialVersionUID = 6199753732102764333L;

    public NativeHookException() {
    }

    public NativeHookException(String paramString) {
        super(paramString);
    }

    public NativeHookException(String paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }

    public NativeHookException(Throwable paramThrowable) {
        super(paramThrowable);
    }
}
