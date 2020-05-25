/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.windows;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinGDI;

/**
 *
 * @author aigarcia
 */
public interface Gdi32 extends Library {

    public static Gdi32 INSTANCE = (Gdi32) Native.loadLibrary("Gdi32", Gdi32.class);

    int SRCCOPY = 0xCC0020;

    /**
     * http://msdn.microsoft.com/en-us/library/dd183489(VS.85).aspx
     */
    public Pointer CreateCompatibleDC(Pointer hdc);

    /**
     * http://msdn.microsoft.com/en-us/library/dd183488(VS.85).aspx
     */
    public Pointer CreateCompatibleBitmap(Pointer hdc,
            int nWidth, int nHeight);

    /**
     * http://msdn.microsoft.com/en-us/library/dd162957(VS.85).aspx
     */
    public Pointer SelectObject(Pointer hdc, Pointer hgdiobj);

    /**
     * http://msdn.microsoft.com/en-us/library/dd145078(VS.85).aspx
     */
    public int SetPixel(Pointer hdc, int X, int Y, int crColor);

    /**
     * http://msdn.microsoft.com/en-us/library/dd144909(VS.85).aspx
     */
    public int GetPixel(Pointer hdc, int nXPos, int nYPos);

    /**
     * http://msdn.microsoft.com/en-us/library/dd183539(VS.85).aspx
     */
    public boolean DeleteObject(Pointer hObject);

    /**
     * http://msdn.microsoft.com/en-us/library/dd183533(VS.85).aspx
     */
    public boolean DeleteDC(Pointer hdc);

    boolean BitBlt(WinDef.HDC hdcDest, int nXDest, int nYDest, int nWidth, int nHeight, WinDef.HDC hdcSrc, int nXSrc, int nYSrc, int dwRop);

    WinDef.HDC GetDC(WinDef.HWND hWnd);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, byte[] pixels, WinGDI.BITMAPINFO bi, int usage);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, short[] pixels, WinGDI.BITMAPINFO bi, int usage);

    boolean GetDIBits(WinDef.HDC dc, WinDef.HBITMAP bmp, int startScan, int scanLines, int[] pixels, WinGDI.BITMAPINFO bi, int usage);

}
