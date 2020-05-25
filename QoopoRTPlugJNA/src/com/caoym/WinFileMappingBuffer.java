package com.caoym;

import java.nio.ByteBuffer;

public final class WinFileMappingBuffer {

    private long __pJniObj;

    WinFileMappingBuffer(String name, int size) {
        CreateJNIObj();
        open(name, size);
    }

    public native boolean open(String paramString, int paramInt);

    public native void close();

    public native ByteBuffer getBuffer();

    protected void finalize() {
        DestroyJNIObj();
        try {
            super.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private native void CreateJNIObj();

    private native void DestroyJNIObj();
}
