package org.jnativehook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EventListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.event.EventListenerList;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class GlobalScreen {

    private static final GlobalScreen instance = new GlobalScreen();
    private EventListenerList eventListeners = new EventListenerList();
    private ExecutorService eventExecutor;

    private GlobalScreen() {
        loadNativeLibrary();
    }

    protected void finalize()
            throws Throwable {
        if (isNativeHookRegistered()) {
            unloadNativeLibrary();
        }
        super.finalize();
    }

    public static GlobalScreen getInstance() {
        return instance;
    }

    public void addNativeKeyListener(NativeKeyListener paramNativeKeyListener) {
        if (paramNativeKeyListener != null) {
            this.eventListeners.add(NativeKeyListener.class, paramNativeKeyListener);
        }
    }

    public void removeNativeKeyListener(NativeKeyListener paramNativeKeyListener) {
        if (paramNativeKeyListener != null) {
            this.eventListeners.remove(NativeKeyListener.class, paramNativeKeyListener);
        }
    }

    public void addNativeMouseListener(NativeMouseListener paramNativeMouseListener) {
        if (paramNativeMouseListener != null) {
            this.eventListeners.add(NativeMouseListener.class, paramNativeMouseListener);
        }
    }

    public void removeNativeMouseListener(NativeMouseListener paramNativeMouseListener) {
        if (paramNativeMouseListener != null) {
            this.eventListeners.remove(NativeMouseListener.class, paramNativeMouseListener);
        }
    }

    public void addNativeMouseMotionListener(NativeMouseMotionListener paramNativeMouseMotionListener) {
        if (paramNativeMouseMotionListener != null) {
            this.eventListeners.add(NativeMouseMotionListener.class, paramNativeMouseMotionListener);
        }
    }

    public void removeNativeMouseMotionListener(NativeMouseMotionListener paramNativeMouseMotionListener) {
        if (paramNativeMouseMotionListener != null) {
            this.eventListeners.remove(NativeMouseMotionListener.class, paramNativeMouseMotionListener);
        }
    }

    public void addNativeMouseWheelListener(NativeMouseWheelListener paramNativeMouseWheelListener) {
        if (paramNativeMouseWheelListener != null) {
            this.eventListeners.add(NativeMouseWheelListener.class, paramNativeMouseWheelListener);
        }
    }

    public void removeNativeMouseWheelListener(NativeMouseWheelListener paramNativeMouseWheelListener) {
        if (paramNativeMouseWheelListener != null) {
            this.eventListeners.remove(NativeMouseWheelListener.class, paramNativeMouseWheelListener);
        }
    }

    public static native void registerNativeHook()
            throws NativeHookException;

    public static native void unregisterNativeHook();

    public static native boolean isNativeHookRegistered();

    public final void dispatchEvent(final NativeInputEvent paramNativeInputEvent) {
        this.eventExecutor.execute(new Runnable() {
            public void run() {
                if ((paramNativeInputEvent instanceof NativeKeyEvent)) {
                    GlobalScreen.this.processKeyEvent((NativeKeyEvent) paramNativeInputEvent);
                } else if ((paramNativeInputEvent instanceof NativeMouseWheelEvent)) {
                    GlobalScreen.this.processMouseWheelEvent((NativeMouseWheelEvent) paramNativeInputEvent);
                } else if ((paramNativeInputEvent instanceof NativeMouseEvent)) {
                    GlobalScreen.this.processMouseEvent((NativeMouseEvent) paramNativeInputEvent);
                }
            }
        });
    }

    protected void processKeyEvent(NativeKeyEvent paramNativeKeyEvent) {
        int i = paramNativeKeyEvent.getID();
        EventListener[] arrayOfEventListener = this.eventListeners.getListeners(NativeKeyListener.class);
        for (int j = 0; j < arrayOfEventListener.length; j++) {
            switch (i) {
                case 2401:
                    ((NativeKeyListener) arrayOfEventListener[j]).nativeKeyPressed(paramNativeKeyEvent);
                    break;
                case 2400:
                    ((NativeKeyListener) arrayOfEventListener[j]).nativeKeyTyped(paramNativeKeyEvent);
                    break;
                case 2402:
                    ((NativeKeyListener) arrayOfEventListener[j]).nativeKeyReleased(paramNativeKeyEvent);
            }
        }
    }

    protected void processMouseEvent(NativeMouseEvent paramNativeMouseEvent) {
        int i = paramNativeMouseEvent.getID();
        EventListener[] arrayOfEventListener;
        if ((i == 2503) || (i == 2504)) {
            arrayOfEventListener = this.eventListeners.getListeners(NativeMouseMotionListener.class);
        } else {
            arrayOfEventListener = this.eventListeners.getListeners(NativeMouseListener.class);
        }
        for (int j = 0; j < arrayOfEventListener.length; j++) {
            switch (i) {
                case 2500:
                    ((NativeMouseListener) arrayOfEventListener[j]).nativeMouseClicked(paramNativeMouseEvent);
                    break;
                case 2501:
                    ((NativeMouseListener) arrayOfEventListener[j]).nativeMousePressed(paramNativeMouseEvent);
                    break;
                case 2502:
                    ((NativeMouseListener) arrayOfEventListener[j]).nativeMouseReleased(paramNativeMouseEvent);
                    break;
                case 2503:
                    ((NativeMouseMotionListener) arrayOfEventListener[j]).nativeMouseMoved(paramNativeMouseEvent);
                    break;
                case 2504:
                    ((NativeMouseMotionListener) arrayOfEventListener[j]).nativeMouseDragged(paramNativeMouseEvent);
            }
        }
    }

    protected void processMouseWheelEvent(NativeMouseWheelEvent paramNativeMouseWheelEvent) {
        EventListener[] arrayOfEventListener = this.eventListeners.getListeners(NativeMouseWheelListener.class);
        for (int i = 0; i < arrayOfEventListener.length; i++) {
            ((NativeMouseWheelListener) arrayOfEventListener[i]).nativeMouseWheelMoved(paramNativeMouseWheelEvent);
        }
    }

    protected void startEventDispatcher() {
        this.eventExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() {
            public Thread newThread(Runnable paramAnonymousRunnable) {
                Thread localThread = new Thread(paramAnonymousRunnable);
                localThread.setName("JNativeHook Native Dispatch");
                return localThread;
            }
        });
    }

    protected void stopEventDispatcher() {
        if (this.eventExecutor != null) {
            this.eventExecutor.shutdownNow();
            this.eventExecutor = null;
        }
    }

    protected static void loadNativeLibrary() {
        String str1 = "JNativeHook";
        try {
            System.loadLibrary(str1);
        } catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {
            try {
                String str2 = "/org/jnativehook/lib/" + NativeSystem.getFamily() + "/" + NativeSystem.getArchitecture() + "/";
                String str3 = System.mapLibraryName(str1);
                str3 = str3.replaceAll("\\.jnilib$", "\\.dylib");
                int i = str3.lastIndexOf('.');
                String str4 = str3.substring(0, i) + '_';
                String str5 = str3.substring(i);
                File localFile = File.createTempFile(str4, str5);
                FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
                byte[] arrayOfByte = new byte[4096];
                InputStream localInputStream = GlobalScreen.class.getResourceAsStream(str2.toLowerCase() + str3);
                if (localInputStream == null) {
                    throw new IOException("Unable to locate the native library.");
                }
                int j;
                while ((j = localInputStream.read(arrayOfByte)) != -1) {
                    localFileOutputStream.write(arrayOfByte, 0, j);
                }
                localFileOutputStream.close();
                localInputStream.close();
                localFile.deleteOnExit();
                System.load(localFile.getPath());
            } catch (IOException localIOException) {
                throw new RuntimeException(localIOException.getMessage());
            }
        }
    }

    protected static void unloadNativeLibrary()
            throws NativeHookException {
        unregisterNativeHook();
    }
}
