package rasterize;

public class DashedLineRasterizer extends RasterBufferedImage.FilledLineRasterizer {

    public DashedLineRasterizer(Raster raster) {
        super(raster);
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2, int color) {
        int dtl = 14;
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

