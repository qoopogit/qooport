/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.windows;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Union;
import com.sun.jna.platform.win32.BaseTSD.LONG_PTR;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
//import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HICON;
import com.sun.jna.platform.win32.WinDef.HINSTANCE;
import com.sun.jna.platform.win32.WinDef.HRGN;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary.StdCallCallback;
import java.util.Arrays;
import java.util.List;

public interface User32 extends Library {

    public static User32 INSTANCE = (User32) Native.loadLibrary("User32", User32.class);

    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_ARROW = 32512;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_IBEAM = 32513;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_WAIT = 32514;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_CROSS = 32515;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_UPARROW = 32516;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZENWSE = 32642;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZENESW = 32643;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZEWE = 32644;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZENS = 32645;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZEALL = 32646;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_NO = 32648;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_HAND = 32649;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_APPSTARTING = 32650;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_HELP = 32651;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_ICON = 32641;
    /**
     * @see #LoadCursorW(Pointer, int)
     */
    public static final int IDC_SIZE = 32640;

    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_COMPAT = 4;
    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_DEFAULTSIZE = 8;
    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_IMAGE = 2;
    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_MASK = 1;
    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_NORMAL = 3;
    /**
     * @see #DrawIconEx(Pointer, int, int, Pointer, int, int, int, Pointer, int)
     */
    public static final int DI_APPBANDING = 1;

    /**
     * http://msdn.microsoft.com/en-us/library/ms648391(VS.85).aspx
     */
    public Pointer LoadCursorW(Pointer hInstance, int lpCursorName);

    /**
     * http://msdn.microsoft.com/en-us/library/ms648065(VS.85).aspx
     */
    public boolean DrawIconEx(Pointer hdc, int xLeft,
            int yTop, Pointer hIcon, int cxWidth, int cyWidth,
            int istepIfAniCur, Pointer hbrFlickerFreeDraw,
            int diFlags);

//    HCURSOR GetCursor();
    public Pointer GetCursor();

    //    public WinDef.HCURSOR GetCursor();
    boolean GetCursorPos(WinDef.POINT punto);

    //--------------------------------------------------------------------------------------
    //encontrados entre otros ejemplos
    interface WNDENUMPROC extends StdCallCallback {

        boolean callback(Pointer hWnd, Pointer arg);
    }

    public static final int GW_OWNER = 4; // used with GetWindow to get win owner

    boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer userData);

    int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);

    int SetForegroundWindow(Pointer hWnd);

    Pointer GetForegroundWindow();

    boolean GetWindowRect(Pointer hWnd, RECT rect);

    boolean IsWindow(Pointer hWnd);

    Pointer GetWindow(Pointer hWnd, int uCmd);

    //------------------------------------------------------------------------------------------------------------------------------------
    //                                                  tomado de otra pagina
    //------------------------------------------------------------------------------------------------------------------------------------
    HWND GetActiveWindow();

    HDC GetDC(HWND hWnd);

    int ReleaseDC(HWND hWnd, HDC hDC);

    int SendInput(int nInputs, INPUT[] pInputs, int cbSize);

    public static class MOUSEINPUT extends Structure {

        public int dx;
        public int dy;
        public int mouseData;
        public int dwFlags;
        public int time;
        public int dwExtraInfo;

        @Override
        protected List getFieldOrder() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }
    }

    public static class KEYBDINPUT extends Structure {

        public short wVk;
        public short wScan;
        public int dwFlags;
        public int time;
        public int dwExtraInfo;

        @Override
        protected List getFieldOrder() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }
    }

    public static class HARDWAREINPUT extends Structure {

        public int uMsg;
        public short wParamL;
        public short wParamH;

        @Override
        protected List getFieldOrder() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }
    }

    public static class INPUT extends Structure {

        public int type;
        public _Anon anon;

        @Override
        protected List getFieldOrder() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }

        public static class _Anon extends Union {

            public MOUSEINPUT mi;
            public KEYBDINPUT ki;
            public HARDWAREINPUT hi;
        }
    }

    int EWX_LOGOFF = 0;

    int EWX_POWEROFF = 0x00000008;
    int EWX_REBOOT = 0x00000002;
    // Shuts down the system and then restarts it, as well as 
    // any applications that have been registered for restart 
    // using the RegisterApplicationRestart function. These 
    // application receive the WM_QUERYENDSESSION message with 
    // lParam set to the ENDSESSION_CLOSEAPP value
    int EWX_RESTARTAPPS = 0x00000040;
    int EWX_SHUTDOWN = 0x00000001;

    int EWX_FORCE = 0x00000004;
    int EWX_FORCEIFHUNG = 0x00000010;

    boolean ExitWindowsEx(int flags, long reason);

    int FLASHW_STOP = 0;
    int FLASHW_CAPTION = 1;
    int FLASHW_TRAY = 2;
    int FLASHW_ALL = (FLASHW_CAPTION | FLASHW_TRAY);
    int FLASHW_TIMER = 4;
    int FLASHW_TIMERNOFG = 12;

    class FLASHWINFO extends Structure {

        public int cbSize;
        public HANDLE hWnd;
        public int dwFlags;
        public int uCount;
        public int dwTimeout;

        @Override
        protected List getFieldOrder() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }
    }

    int IMAGE_BITMAP = 0;
    int IMAGE_ICON = 1;
    int IMAGE_CURSOR = 2;
    int IMAGE_ENHMETAFILE = 3;

    int LR_DEFAULTCOLOR = 0x0000;
    int LR_MONOCHROME = 0x0001;
    int LR_COLOR = 0x0002;
    int LR_COPYRETURNORG = 0x0004;
    int LR_COPYDELETEORG = 0x0008;
    int LR_LOADFROMFILE = 0x0010;
    int LR_LOADTRANSPARENT = 0x0020;
    int LR_DEFAULTSIZE = 0x0040;
    int LR_VGACOLOR = 0x0080;
    int LR_LOADMAP3DCOLORS = 0x1000;
    int LR_CREATEDIBSECTION = 0x2000;
    int LR_COPYFROMRESOURCE = 0x4000;
    int LR_SHARED = 0x8000;

    void LockWorkStation();

    HWND FindWindow(String winClass, String title);

    int GetClassName(HWND hWnd, byte[] lpClassName, int nMaxCount);

    class GUITHREADINFO extends Structure {

        public int cbSize = size();
        public int flags;
        public HWND hwndActive;
        public HWND hwndFocus;
        public HWND hwndCapture;
        public HWND hwndMenuOwner;
        public HWND hwndMoveSize;
        public HWND hwndCaret;
        public RECT rcCaret;

        @Override
        protected List getFieldOrder() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            return null;
        }
    }

    boolean GetGUIThreadInfo(int idThread, GUITHREADINFO lpgui);

    class WINDOWINFO extends Structure {

        public int cbSize = size();
        public RECT rcWindow;
        public RECT rcClient;
        public int dwStyle;
        public int dwExStyle;
        public int dwWindowStatus;
        public int cxWindowBorders;
        public int cyWindowBorders;
        public short atomWindowType;
        public short wCreatorVersion;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"cbSize", "rcWindow", "rcClient",
                "dwStyle", "dwExStyle", "dwWindowStatus", "cxWindowBorders", "cyWindowBorders",
                "atomWindowType", "wCreatorVersion"});
        }
    }

    boolean GetWindowInfo(HWND hWnd, WINDOWINFO pwi);

    boolean GetWindowRect(HWND hWnd, RECT rect);

    int GetWindowText(HWND hWnd, byte[] lpString, int nMaxCount);

    int GetWindowTextLength(HWND hWnd);

    int GetWindowModuleFileName(HWND hWnd, byte[] lpszFileName, int cchFileNameMax);

    int GetWindowThreadProcessId(HWND hWnd, IntByReference lpdwProcessId);

//    interface WNDENUMPROC extends StdCallCallback {
//
//        /**
//         * Return whether to continue enumeration.
//         */
//        boolean callback(HWND hWnd, Pointer data);
//    }
//
//    boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer data);
    boolean EnumThreadWindows(int dwThreadId, WNDENUMPROC lpEnumFunc, Pointer data);

    boolean FlashWindowEx(FLASHWINFO info);

    HICON LoadIcon(HINSTANCE hInstance, String iconName);

    HANDLE LoadImage(HINSTANCE hinst, // handle to instance
            String name, // image to load
            int type, // image type
            int xDesired, // desired width
            int yDesired, // desired height
            int load // load options
    );

    boolean DestroyIcon(HICON hicon);

    int GWL_EXSTYLE = -20;
    int GWL_STYLE = -16;
    int GWL_WNDPROC = -4;
    int GWL_HINSTANCE = -6;
    int GWL_ID = -12;
    int GWL_USERDATA = -21;
    int DWL_DLGPROC = 4;
    int DWL_MSGRESULT = 0;
    int DWL_USER = 8;
    int WS_EX_COMPOSITED = 0x20000000;
    int WS_EX_TOOLWINDOW = 0x00000080;
    int WS_EX_LAYERED = 0x80000;
    int WS_EX_TRANSPARENT = 32;
    int WS_VISIBLE = 0x10000000;

    int GetWindowLong(HWND hWnd, int nIndex);

    int SetWindowLong(HWND hWnd, int nIndex, int dwNewLong);

    // Do not use this version on win64
    Pointer SetWindowLong(HWND hWnd, int nIndex, Pointer dwNewLong);

    LONG_PTR GetWindowLongPtr(HWND hWnd, int nIndex);

    LONG_PTR SetWindowLongPtr(HWND hWnd, int nIndex, LONG_PTR dwNewLongPtr);

    Pointer SetWindowLongPtr(HWND hWnd, int nIndex, Pointer dwNewLongPtr);

    int LWA_COLORKEY = 1;
    int LWA_ALPHA = 2;
    int ULW_COLORKEY = 1;
    int ULW_ALPHA = 2;
    int ULW_OPAQUE = 4;

    boolean SetLayeredWindowAttributes(HWND hwnd, int crKey,
            byte bAlpha, int dwFlags);

    boolean GetLayeredWindowAttributes(HWND hwnd,
            IntByReference pcrKey,
            ByteByReference pbAlpha,
            IntByReference pdwFlags);

    /**
     * Defines the x- and y-coordinates of a point.
     */
    class POINT extends Structure {

        public int x, y;

        public POINT() {
        }

        public POINT(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"x", "y"});
        }
    }

    /**
     * Specifies the width and height of a rectangle.
     */
    class SIZE extends Structure {

        public int cx, cy;

        public SIZE() {
        }

        public SIZE(int w, int h) {
            this.cx = w;
            this.cy = h;
        }

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"cx", "cy"});
        }
    }
    int AC_SRC_OVER = 0x00;
    int AC_SRC_ALPHA = 0x01;
    int AC_SRC_NO_PREMULT_ALPHA = 0x01;
    int AC_SRC_NO_ALPHA = 0x02;

    class BLENDFUNCTION extends Structure {

        public byte BlendOp = AC_SRC_OVER; // only valid value
        public byte BlendFlags = 0; // only valid value
        public byte SourceConstantAlpha;
        public byte AlphaFormat;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"BlendOp", "BlendFlags", "SourceConstantAlpha", "AlphaFormat"});
        }
    }

    boolean UpdateLayeredWindow(HWND hwnd, HDC hdcDst,
            POINT pptDst, SIZE psize,
            HDC hdcSrc, POINT pptSrc, int crKey,
            BLENDFUNCTION pblend, int dwFlags);

    int SetWindowRgn(HWND hWnd, HRGN hRgn, boolean bRedraw);
    int VK_SHIFT = 16;
    int VK_LSHIFT = 0xA0;
    int VK_RSHIFT = 0xA1;
    int VK_CONTROL = 17;
    int VK_LCONTROL = 0xA2;
    int VK_RCONTROL = 0xA3;
    int VK_MENU = 18;
    int VK_LMENU = 0xA4;
    int VK_RMENU = 0xA5;

    boolean GetKeyboardState(byte[] state);

    short GetAsyncKeyState(int vKey);

    int WH_KEYBOARD = 2;
    int WH_MOUSE = 7;
    int WH_KEYBOARD_LL = 13;
    int WH_MOUSE_LL = 14;

    class HHOOK extends HANDLE {
    }

    interface HOOKPROC extends StdCallCallback {
    }
    int WM_KEYDOWN = 256;
    int WM_KEYUP = 257;
    int WM_SYSKEYDOWN = 260;
    int WM_SYSKEYUP = 261;

    class KBDLLHOOKSTRUCT extends Structure {

        public int vkCode;
        public int scanCode;
        public int flags;
        public int time;
        public ULONG_PTR dwExtraInfo;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"vkCode", "scanCode", "flags", "time", "dwExtraInfo"});
        }
    }

    interface LowLevelKeyboardProc extends HOOKPROC {

        LRESULT callback(int nCode, WPARAM wParam, KBDLLHOOKSTRUCT lParam);
    }

    class MSLLHOOKSTRUCT extends Structure {

        public POINT pt;
        public int mouseData;
        public int flags;
        public int time;
        public ULONG_PTR dwExtraInfo;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"pt", "mouseData", "flags", "time", "dwExtraInfo"});
        }
    }

    interface LowLevelMouseProc extends HOOKPROC {

        LRESULT callback(int nCode, WPARAM wParam, MSLLHOOKSTRUCT lParam);
    }

    interface MouseMessages {

        int WM_LBUTTONDOWN = 0x0201;
        int WM_LBUTTONUP = 0x0202;
        int WM_MOUSEMOVE = 0x0200;
        int WM_MOUSEWHEEL = 0x020A;
        int WM_RBUTTONDOWN = 0x0204;
        int WM_RBUTTONUP = 0x0205;
        int WM_MBUTTONDOWN = 519;
        int WM_MBUTTONUP = 520;
        int WM_SBUTTONDOWN = 523;
        int WM_SBUTTONUP = 524;

        int WD_SIDE1 = 131072;
        int WD_SIDE2 = 65536;
    }

    HHOOK SetWindowsHookEx(int idHook, HOOKPROC lpfn, HINSTANCE hMod, int dwThreadId);

    LRESULT CallNextHookEx(HHOOK hhk, int nCode, WPARAM wParam, LPARAM lParam);

    LRESULT CallNextHookEx(HHOOK hhk, int nCode, WPARAM wParam, Pointer lParam);
    LRESULT SKIP_HOOK = new LRESULT(1);
    LRESULT CALL_NEXT_HOOK = new LRESULT(0); // Dirty fast for CallNextHookEx

    boolean UnhookWindowsHookEx(HHOOK hhk);

    class MSG extends Structure {

        public HWND hWnd;
        public int message;
        public WPARAM wParam;
        public LPARAM lParam;
        public int time;
        public POINT pt;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"hWnd", "message", "wParam", "lParam", "time", "pt"});
        }
    }

    int GetMessage(MSG lpMsg, HWND hWnd, int wMsgFilterMin, int wMsgFilterMax);

    boolean PeekMessage(MSG lpMsg, HWND hWnd, int wMsgFilterMin, int wMsgFilterMax, int wRemoveMsg);

    boolean TranslateMessage(MSG lpMsg);

    LRESULT DispatchMessage(MSG lpMsg);

    void PostMessage(HWND hWnd, int msg, WPARAM wParam, LPARAM lParam);

    void PostQuitMessage(int nExitCode);

    //------------------------------------------------------------------------------------------------------------------------------------
    //                                                  tomado de otra pagina 2
    //------------------------------------------------------------------------------------------------------------------------------------
    boolean GetCursorInfo(CURSORINFO pCursorInfo);

    public static class CURSORINFO extends Structure {

        public DWORD cbSize;
        public DWORD flags;
        public HCURSOR hCursor;
        public POINT ptScreenPos;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"cbSize", "flags", "hCursor", "ptScreenPos"});
        }
    }

    class HCURSOR extends HICON {
//      public HINSTANCE hInstance;
//      public byte[] lpCursorName = new byte[512];
    }

    
    HWND GetDesktopWindow();
}
