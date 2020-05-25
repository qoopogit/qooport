/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plugin.jna.linux;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aigarcia
 */
public interface LibXFixes extends Library {

    public static LibXFixes INSTANCE = (LibXFixes) Native.loadLibrary("libXfixes.so", LibXFixes.class);

    //public Pointer CreateCompatibleDC(Pointer hdc);
    public XFixesGetCursorImageTipo XFixesGetCursorImage(Pointer display);

    public XFixesGetCursorNameTipo XFixesGetCursorName(Pointer cursor);

    public XFixesFindDisplayTipo XFixesFindDisplay(Pointer display);

//    public Pointer GetCursorImage(
//            int x,
//            int y,
//            int width,
//            int height,
//            int x_hot,
//            int y_hot,
//            long cursor_serial,
//            Pointer cursor_image
//    );
    public static class XFixesGetCursorImageTipo extends Structure {

        public int x;
        public int y;
        public int width;
        public int height;
        public int x_hot;
        public int y_hot;
        public long cursor_serial;
        //public byte[] cursor_image= new byte[1024];
        public Pointer cursor_image;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"x", "y", "width", "height", "x_hot", "y_hot", "cursor_serial", "cursor_image"});
        }
    }

    public static class XFixesGetCursorNameTipo extends Structure {

        public Pointer atom;
        public String name;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"atom", "name"});
        }
    }

    public static class XFixesFindDisplayTipo extends Structure {

        public Pointer next;
        public Pointer display;
        public Pointer codes;
        public int major_version;
        public int minor_version;

        @Override
        protected List getFieldOrder() {
            return Arrays.asList(new String[]{"next", "display", "codes", "major_version", "minor_version"});
        }
    }

}
