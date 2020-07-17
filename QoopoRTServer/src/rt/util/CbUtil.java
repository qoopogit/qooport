package rt.util;

import comunes.Interfaz;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.ImageIcon;

public class CbUtil {

    private Clipboard clipboard;
    private FlavorListener flavorlistener;
    private Object object;
    private String txt = "qooport";
    private ImageIcon img = null;
    private Interfaz servicio;

    public CbUtil(Interfaz servicio) {
        this.servicio = servicio;
        initClipboard();
        this.clipboard.addFlavorListener(this.flavorlistener);

    }

    public void initClipboard() {
        this.clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        this.flavorlistener = new FlavorListener() {
            public void flavorsChanged(FlavorEvent event) {
                try {
                    Transferable content = CbUtil.this.clipboard.getContents(this);
                    if (content == null) {
                        return;
                    }
                    if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                        String newtxt = (String) CbUtil.this.clipboard.getData(DataFlavor.stringFlavor);
                        boolean diferente = false;
                        try {
                            diferente = !CbUtil.this.txt.equals(newtxt);
                        } catch (Exception e) {
                            diferente = true;
                        }

                        if (diferente) {

                        }
                        if (diferente) {
                            CbUtil.this.txt = newtxt;
                            CbUtil.this.object = CbUtil.this.txt;
                            servicio.ejecutar(12, 1, CbUtil.this.object);
                        }
                    } else if (content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                        Image image = (Image) CbUtil.this.clipboard.getData(DataFlavor.imageFlavor);
                        boolean diferente = false;
                        try {
                            diferente = !CbUtil.this.img.getImage().equals(image);
                        } catch (Exception e) {
                            diferente = true;
                        }

                        if (diferente) {
                            CbUtil.this.img = new ImageIcon(image);
                            CbUtil.this.object = CbUtil.this.img;
                            servicio.ejecutar(12, 2, CbUtil.this.object);
                        }
                    }
                } catch (Exception ex) {
                }
            }
        };
    }

    public boolean isEmpty() {
        return this.object == null;
    }

    public void setText(String string) {
        try {
            if (this.txt.equals(string)) {
                return;
            }
            this.txt = string;
            this.clipboard.setContents(new StringSelection(this.txt), null);
        } catch (Exception e) {
//            
        }
    }

    public void setImage(ImageIcon image) {
        try {
            this.img = image;
            this.clipboard.setContents(new ImageSelection(this.img.getImage()), null);
        } catch (Exception e) {
//            
        }
    }

    public void setContent(Object object) {
        try {
            if (object == null) {
                return;
            }
            if ((object instanceof String)) {
                setText((String) object);
            } else if ((object instanceof ImageIcon)) {
                setImage((ImageIcon) object);
            }
        } catch (Exception e) {
        }
    }

    public Object getContent() {
        Object obj = this.object;
        this.object = null;
        return obj;
    }

    public void addFlavorListener() {
        this.clipboard.addFlavorListener(this.flavorlistener);
    }

    public void removeFlavorListener() {
        this.clipboard.removeFlavorListener(this.flavorlistener);
    }

    public static class ImageSelection implements Transferable {
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
