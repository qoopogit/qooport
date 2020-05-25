/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qooport.utilidades;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import qooport.asociado.Asociado;

/**
 *
 * @author alberto
 */
public class ClipboardUtility {

    private Clipboard clipboard;
    private FlavorListener flavorlistener;
    private Object object;
    private String txt = "qooport";
//    private ImageIcon img = new ImageIcon(Commons.ALIVE_ICON);
    private ImageIcon img = Util.cargarIcono("/resources/start.png");

    private Asociado servidor;

    public ClipboardUtility(Asociado servidor) {
        initClipboard();
        this.clipboard.addFlavorListener(this.flavorlistener);
        this.servidor = servidor;
    }

    public void initClipboard() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        this.flavorlistener = new FlavorListener() {
            public void flavorsChanged(FlavorEvent event) {
                try {
                    Transferable content = ClipboardUtility.this.clipboard.getContents(this);
                    if (content == null) {
                        return;
                    }
                    if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String newtxt = (String) ClipboardUtility.this.clipboard.getData(DataFlavor.stringFlavor);

                        boolean diferente = false;
                        try {
                            diferente = !ClipboardUtility.this.txt.equals(newtxt);
                        } catch (Exception e) {
                            diferente = true;
                        }

                        if (diferente) {

                        }
                        if (diferente) {
                            ClipboardUtility.this.txt = newtxt;
                            ClipboardUtility.this.object = ClipboardUtility.this.txt;

                            servidor.enviarPortaPapeles(1, ClipboardUtility.this.object);
                        }
                    } else if (content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                        Image image = (Image) ClipboardUtility.this.clipboard.getData(DataFlavor.imageFlavor);
                        boolean diferente = false;
                        try {
                            diferente = !ClipboardUtility.this.img.getImage().equals(image);
                        } catch (Exception e) {
                            diferente = true;
                        }

                        if (diferente) {
                            ClipboardUtility.this.img = new ImageIcon(image);
                            ClipboardUtility.this.object = ClipboardUtility.this.img;
                            servidor.enviarPortaPapeles(2, ClipboardUtility.this.object);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    public boolean isEmpty() {
        return this.object == null;
    }

    public void setText(String string) {
        if (this.txt.equals(string)) {
            return;
        }
        this.txt = string;
        this.clipboard.setContents(new StringSelection(this.txt), null);
    }

    public void setImage(ImageIcon image) {
//        if (this.img.getImage().equals(image.getImage())) {
//            return;
//        }
        this.img = image;
        this.clipboard.setContents(new ImageSelection(this.img.getImage()), null);
    }

    public void setContent(Object object) {
        if (object == null) {
            return;
        }
        if ((object instanceof String)) {
            setText((String) object);
        } else if ((object instanceof ImageIcon)) {
            setImage((ImageIcon) object);
        }
    }

    public Object getContent() {
        Object obj = this.object;
        this.object = null;
        return obj;
    }

    public File[] getFiles() {
        File[] files = new File[0];
        try {
            Transferable transferable = this.clipboard.getContents(this);
            if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                List list = (List) this.clipboard.getData(DataFlavor.javaFileListFlavor);
                files = (File[]) list.toArray(new File[list.size()]);
            } else if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String data = (String) this.clipboard.getData(new DataFlavor("application/x-java-serialized-object; class=java.lang.String"));
                List<File> list = ArchivoUtil.textoaListaArchivo(data);
                files = (File[]) list.toArray(new File[list.size()]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;
    }

    public void addFlavorListener() {
        this.clipboard.addFlavorListener(this.flavorlistener);
    }

    public void removeFlavorListener() {
        this.clipboard.removeFlavorListener(this.flavorlistener);
    }

    public static class ImageSelection
            implements Transferable {

        private final Image img;

        public ImageSelection(Image img) {
            this.img = img;
        }

        static DataFlavor[] flavors = {DataFlavor.imageFlavor};

        public DataFlavor[] getTransferDataFlavors() {
            return (DataFlavor[]) flavors.clone();
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(DataFlavor.imageFlavor);
        }

        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return this.img;
        }
    }

}
