package com.caoym;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WinRobot
        extends Robot {

    public static final int CURSOR_APPSTARTIN = 0;
    public static final int CURSOR_ARROW = 1;
    public static final int CURSOR_CROSS = 2;
    public static final int CURSOR_HAND = 3;
    public static final int CURSOR_HELP = 4;
    public static final int CURSOR_IBEAM = 5;
    public static final int CURSOR_ICON = 6;
    public static final int CURSOR_NO = 7;
    public static final int CURSOR_SIZE = 8;
    public static final int CURSOR_SIZEALL = 9;
    public static final int CURSOR_SIZENESW = 10;
    public static final int CURSOR_SIZENS = 11;
    public static final int CURSOR_SIZENWSE = 12;
    public static final int CURSOR_SIZEWE = 13;
    public static final int CURSOR_UPARROW = 14;
    public static final int CURSOR_WAIT = 15;
    public static final int CURSOR_UNKNOWN = -1;
    public static final int JNI_LIB_VERSION = 121;
    private long __pJniObj;
    private WinFileMappingBuffer m_last_cursor_buffer;
    private BufferedImage m_last_cursor_image;

    public WinRobot() throws AWTException {
        CreateJNIObj(null);
    }

    public WinRobot(GraphicsDevice device)
            throws AWTException {
        if (device == null) {
            CreateJNIObj(null);
        } else {
            CreateJNIObj(device.getIDstring());
        }
    }

    private static boolean exportLibFromJar(String name) {
        try {
            InputStream in = WinRobot.class.getResourceAsStream("/extras/winrobot/" + name);
            if (in == null) {
                System.out.println("getResourceAsStream  from /extras/winrobot/" + name + " failed");
            }

            if (in == null) {
                System.out.println("try to get " + name + " from file");
                File file = new File(name);
                in = new FileInputStream(file);
                if (in == null) {
                    System.out.println("get " + name + "from file failed");
                }
            }
            if (in == null) {
                return false;
            }

            String path = System.getProperty("java.io.tmpdir") + "WinRobot\\" + 121 + "\\" + name;
            path = path.replace('/', '\\');

            File mkdir = new File(path.substring(0, path.lastIndexOf('\\')));
            mkdir.mkdirs();

            File fileOut = new File(path);
            System.out.println("Writing dll to: " + path);

            OutputStream out = new FileOutputStream(fileOut);

            byte[] buf = new byte[1024];

            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
        return true;
    }

    private static void deleteFile(File file, File except) {
        if (file.equals(except)) {
            return;
        }
        if (file.exists()) {
            if (file.isFile()) {
                try {
                    file.delete();
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i], except);
                }
            }
            file.delete();
        }
    }

    private static void ClearOldVersion() {
        deleteFile(new File(System.getProperty("java.io.tmpdir") + "WinRobot\\"), new File(System.getProperty("java.io.tmpdir") + "WinRobot\\" + 121));
    }

    static {
        boolean bLoaded = false;

        String[] libs = {"JNIAdapterx86.dll", "WinRobotCorex86.dll", "WinRobotHostx86.exe", "WinRobotHookx86.dll", "WinRobotHostPSx86.dll", "JNIAdapterx64.dll", "WinRobotCorex64.dll", "WinRobotHostx64.exe", "WinRobotHookx64.dll", "WinRobotHostPSx64.dll"};

        for (int i = 0; i < libs.length; i++) {
            exportLibFromJar(libs[i]);
        }
        ClearOldVersion();
        String path = new String();

        path = System.getProperty("java.io.tmpdir") + "WinRobot\\" + 121;

        try {
            System.load(path + "\\JNIAdapterx64.dll");
            bLoaded = true;
        } catch (UnsatisfiedLinkError e) {
//            System.out.print("load " + path + "\\JNIAdapterx64.dll" + " failed," + e.getMessage() + "\r\n");
        }

        if (!bLoaded) {
            try {
                System.load(path + "\\JNIAdapterx86.dll");
                bLoaded = true;
            } catch (UnsatisfiedLinkError e) {
//                System.out.print("load " + path + "\\JNIAdapterx86.dll" + " failed," + e.getMessage() + "\r\n");
            }
        }
        if (!bLoaded) {
            try {
                System.loadLibrary("JNIAdapterx86");
                bLoaded = true;
            } catch (UnsatisfiedLinkError e) {
//                System.out.print("load JNIAdapterx86 failed," + e.getMessage() + "\r\n");
            }
        }

        if (!bLoaded) {
            try {
                System.loadLibrary("JNIAdapterx64");
                bLoaded = true;
            } catch (UnsatisfiedLinkError e) {
//                System.out.print("load JNIAdapterx64 failed," + e.getMessage() + "\r\n");
            }
        }
    }

    @Override
    protected void finalize() {
        DestroyJNIObj();
        try {
            super.finalize();
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public BufferedImage createScreenCapture(Rectangle screenRect) {
        if (screenRect == null) {
            return null;
        }
        WinFileMappingBuffer fm = createScreenCaptureAsFileMapping(screenRect);

        if (fm == null) {
            return null;
        }
        BufferedImage image = CreateBuffedImage(fm, false);
        fm.close();
        return image;
    }

    public BufferedImage captureMouseCursor(Point origin, Point hotspot, int[] type) {
        WinFileMappingBuffer fm = captureMouseCursorAsFileMapping(origin, hotspot, type);
        if (fm == null) {
            return null;
        }
        if ((this.m_last_cursor_buffer == fm) && (this.m_last_cursor_image != null)) {
            return this.m_last_cursor_image;
        }
        if (this.m_last_cursor_buffer != null) {
            this.m_last_cursor_buffer.close();
        }
        this.m_last_cursor_buffer = fm;
        this.m_last_cursor_image = CreateBuffedImage(fm, true);

        return this.m_last_cursor_image;
    }

    private BufferedImage CreateBuffedImage(WinFileMappingBuffer fm, boolean bWithAlphaChanle) {
        BitmapFileBuffer bitmap = new BitmapFileBuffer();
        if (!bitmap.Load(fm.getBuffer())) {
            return null;
        }
        return bitmap.GetBuffedImage(bWithAlphaChanle);
    }

    public native WinFileMappingBuffer createScreenCaptureAsFileMapping(Rectangle paramRectangle);

    public native void delay(int paramInt);

    public native int getAutoDelay();

    public native Color getPixelColor(int paramInt1, int paramInt2);

    public native boolean isAutoWaitForIdle();

    public native void keyPress(int paramInt);

    public native void keyRelease(int paramInt);

    public native void mouseMove(int paramInt1, int paramInt2);

    public native void mousePress(int paramInt);

    public native void mouseRelease(int paramInt);

    public native void mouseWheel(int paramInt);

    public native void setAutoDelay(int paramInt);

    public native void setAutoWaitForIdle(boolean paramBoolean);

    public native String toString();

    public native void waitForIdle();

    public native WinFileMappingBuffer captureMouseCursorAsFileMapping(Point paramPoint1, Point paramPoint2, int[] paramArrayOfInt);

    public native void keyType(char paramChar);

    public native void sendCtrlAltDel();

    public native void setKeyboardLayout(String paramString);

    private native void CreateJNIObj(String paramString);

    private native void DestroyJNIObj();
}


/* Location:              C:\Users\aigarcia\Downloads\WinRobot.jar!\com\caoym\WinRobot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
