package com.caoym;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BitmapFileBuffer {

    private ByteBuffer _src;
    public BitmapFileHeader fileHeader;
    public BitmapInfoHeader infoHeader;
    private int[] _data;

    BitmapFileBuffer() {
    }

    public class BitmapFileHeader {

        public static final int LENGTH = 14;
        public static final short BM = 19778;
        public short bfType;
        public int bfSize;
        public short bfReserved1;
        public short bfReserved2;
        public int bfOffBits;

        BitmapFileHeader(ByteBuffer src) {
            this.bfType = src.getShort();
            this.bfSize = src.getInt();
            this.bfReserved1 = src.getShort();
            this.bfReserved2 = src.getShort();
            this.bfOffBits = src.getInt();
        }

        public boolean equals(BitmapFileHeader rh) {
            if (rh == this) {
                return true;
            }
            if (this.bfType != rh.bfType) {
                return false;
            }
            if (this.bfSize != rh.bfSize) {
                return false;
            }
            if (this.bfReserved1 != rh.bfReserved1) {
                return false;
            }
            if (this.bfReserved2 != rh.bfReserved2) {
                return false;
            }
            if (this.bfOffBits != rh.bfOffBits) {
                return false;
            }
            return true;
        }
    }

    public class BitmapInfoHeader {

        public static final int LENGTH = 40;

        public int biSize;

        public int biWidth;

        public int biHeight;

        public short biPlanes;

        public short biBitCount;

        public int biCompression;

        public int biSizeImage;

        public int biXPelsPerMeter;

        public int biYPelsPerMeter;

        public int biClrUsed;

        public int biClrImportant;

        BitmapInfoHeader(ByteBuffer src) {
            this.biSize = src.getInt();
            this.biWidth = src.getInt();
            this.biHeight = src.getInt();
            this.biPlanes = src.getShort();
            this.biBitCount = src.getShort();
            this.biCompression = src.getInt();
            this.biSizeImage = src.getInt();
            this.biXPelsPerMeter = src.getInt();
            this.biYPelsPerMeter = src.getInt();
            this.biClrUsed = src.getInt();
            this.biClrImportant = src.getInt();
            if (this.biSize > 40) {
                src.position(src.position() + 40 - this.biSize);
            }
        }

        public boolean equals(BitmapInfoHeader rh) {
            if (rh == this) {
                return true;
            }
            if (this.biSize != rh.biSize) {
                return false;
            }
            if (this.biWidth != rh.biWidth) {
                return false;
            }
            if (this.biHeight != rh.biHeight) {
                return false;
            }
            if (this.biPlanes != rh.biPlanes) {
                return false;
            }
            if (this.biBitCount != rh.biBitCount) {
                return false;
            }
            if (this.biCompression != rh.biCompression) {
                return false;
            }
            if (this.biSizeImage != rh.biSizeImage) {
                return false;
            }
            if (this.biXPelsPerMeter != rh.biXPelsPerMeter) {
                return false;
            }
            if (this.biYPelsPerMeter != rh.biYPelsPerMeter) {
                return false;
            }
            if (this.biClrUsed != rh.biClrUsed) {
                return false;
            }
            if (this.biClrImportant != rh.biClrImportant) {
                return false;
            }

            return true;
        }
    }

    boolean Load(ByteBuffer src) {
        if (src == null) {
            return false;
        }
        this._data = null;
        src.position(0);
        src.order(ByteOrder.LITTLE_ENDIAN);
        if (src.capacity() < 54) {
            return false;
        }
        BitmapFileHeader fh = new BitmapFileHeader(src);
        BitmapInfoHeader ih = new BitmapInfoHeader(src);
        if (fh.bfType != 19778) {
            return false;
        }
        if (fh.bfSize > src.capacity()) {
            return false;
        }
        if (ih.biSize < 40) {
            return false;
        }
        if (fh.bfOffBits < 54) {
            return false;
        }
        if (fh.bfOffBits + ih.biSizeImage > fh.bfSize) {
            return false;
        }
        if ((ih.biWidth * ih.biHeight * ih.biBitCount + 7) / 8 > ih.biSizeImage) {
            return false;
        }

        if (ih.biBitCount != 32) {
            return false;
        }
        src.position(0);

        if (this.fileHeader == null) {
            this.fileHeader = fh;
        }
        if (this.infoHeader == null) {
            this.infoHeader = ih;
        }
        if ((!fh.equals(this.fileHeader)) || (!ih.equals(this.infoHeader))) {
            this.fileHeader = fh;
            this.infoHeader = ih;
        }
        this._src = src;
        if (getData() == null) {
            return false;
        }
        return getData().length * 4 == this.infoHeader.biSizeImage;
    }

    public int[] getPalette() {
        if (this.infoHeader.biClrUsed != 0) {
            int[] palette = new int[this.infoHeader.biClrUsed];
            this._src.position(14 + this.infoHeader.biSize);
            this._src.asIntBuffer().get(palette, 0, this.infoHeader.biClrUsed);
            this._src.position(0);
            return palette;
        }
        return null;
    }

    public BufferedImage GetBuffedImage(boolean bWithAlphaChannel) {
        int[] bandmasks;

        DirectColorModel screenCapCM;

        if (bWithAlphaChannel) {
            bandmasks = new int[4];
            screenCapCM = new DirectColorModel(32, 16711680, 65280, 255, -16777216);

            bandmasks[3] = screenCapCM.getAlphaMask();
        } else {
            bandmasks = new int[3];
            screenCapCM = new DirectColorModel(24, 16711680, 65280, 255);
        }

        DataBuffer buffer = new DataBufferInt(getData(), this.infoHeader.biSizeImage / 4, 0);
        bandmasks[0] = screenCapCM.getRedMask();
        bandmasks[1] = screenCapCM.getGreenMask();
        bandmasks[2] = screenCapCM.getBlueMask();

        WritableRaster raster = Raster.createPackedRaster(buffer, this.infoHeader.biWidth, Math.abs(this.infoHeader.biHeight), this.infoHeader.biWidth, bandmasks, null);

        BufferedImage image = new BufferedImage(screenCapCM, raster, false, null);
        return image;
    }

    public int[] getData() {
        if (this._data == null) {
            this._src.position(this.fileHeader.bfOffBits);
            this._data = new int[this.infoHeader.biSizeImage / 4];
            this._src.asIntBuffer().get(this._data, 0, this._data.length);
            this._src.position(0);
        }

        return this._data;
    }
}
