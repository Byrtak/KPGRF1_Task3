package rasterize;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {

    private final BufferedImage img;
    private int clearColor;

    public RasterBufferedImage(int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    public void repaint(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public void draw(RasterBufferedImage raster) {
        Graphics graphics = getGraphics();
        graphics.setColor(new Color(clearColor));
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(raster.img, 0, 0, null);
    }

    public BufferedImage getImg() {
        return img;
    }

    public Graphics getGraphics() {
        return img.getGraphics();
    }

    @Override
    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        if(x>0 && y>0 && x<getWidth() && y<getHeight()){
            getImg().setRGB(x, y, color);
        }

    }

    @Override
    public void clear() {
        Graphics g = img.getGraphics();
        g.setColor(new Color(clearColor));
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public void setClearColor(int clearColor) {
        this.clearColor = clearColor;
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    public static class FilledLineRasterizer extends LineRasterizer {

        public FilledLineRasterizer(Raster raster) {
            super(raster);
        }

        /**
         * Triviální algoritmus
         * Nevýhoda: násobení a sčítání v plovoucí řádové čárce neefektivní
         * Výhoda: postup použitelný i pro složitější křivky
         */
        @Override
        public void rasterize(int x1, int y1, int x2, int y2, int color) {
            int dx = x2 - x1;
            int dy = y2 - y1;
            float k =  dy/ (float) dx;
            float q = y1 - k * x1;
            if(Math.abs(dy) < Math.abs(dx)){
                if (x2<x1) {
                    for (int x = x2 ; x <=x1 ; x ++) {
                        float y = k * x +q;
                        raster.setPixel(x, Math.round(y),color);
                    }
                }else {
                    for (int x = x1; x <= x2; x++) {
                        float y = k * x + q;
                        raster.setPixel(x, Math.round(y), color);
                    }
                }
            } else {
                if (y2<y1) {
                    for (int y = y2 ; y <=y1 ; y ++) {
                        float x = (y-q)/k;
                        raster.setPixel(Math.round(x),y,color);
                    }
                }else {
                    for (int y = y1 ; y <=y2 ; y ++) {
                        float x = (y-q)/k;
                        raster.setPixel(Math.round(x), y, color);
                    }
                }

            }

        }

        public void rasterize(int x1, int y1, int x2, int y2, int color,int dtl) {
            int dtp = 0;
            int dx = x2 - x1;
            int dy = y2 - y1;
            float k =  dy/ (float) dx;
            float q = y1 - k * x1;
            if(Math.abs(dy) < Math.abs(dx)){
                if (x2<x1) {
                    for (int x = x2 ; x <=x1 ; x ++) {
                        float y = k * x +q;
                        if(dtp<=4) raster.setPixel(x, Math.round(y), color);
                        dtp++;
                        if(dtp>=dtl) dtp = 0;
                    }
                }else {
                    for (int x = x1; x <= x2; x++) {
                        float y = k * x + q;
                        if(dtp<=4) raster.setPixel(x, Math.round(y),color);
                        dtp++;
                        if(dtp>=dtl) dtp = 0;
                    }
                }
            } else {
                if (y2<y1) {
                    for (int y = y2 ; y <=y1 ; y ++) {
                        float x = (y-q)/k;
                        if(dtp<=4) raster.setPixel(Math.round(x),y,color);
                        dtp++;
                        if(dtp>=dtl) dtp = 0;
                    }
                }else {
                    for (int y = y1 ; y <=y2 ; y ++) {
                        float x = (y-q)/k;
                        if(dtp<=4) raster.setPixel(Math.round(x), y, color);
                        dtp++;
                        if(dtp>=dtl) dtp = 0;
                    }
                }

            }

        }
    }
}
