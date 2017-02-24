package com.example.upam.filtrosisra;

import android.graphics.Bitmap;
import android.icu.math.BigDecimal;

/**
 * Created by upam on 10/02/17.
 */

class Filtros {

    Bitmap greyScale(Bitmap bitmap){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0, grey = 0;

        for(int x=0; x<bitmap.getWidth();x++){
            for(int y=0; y<bitmap.getHeight();y++){
                pixel = bitmap.getPixel(x,y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;

                grey = (r+g+b)/3;

                pixel = ((a << 24) | (grey << 16) | (grey << 8) | grey);

                bmp.setPixel(x,y,pixel);

            }
        }

        return bmp;
    }

    Bitmap invert(Bitmap bitmap){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0;

        for(int x=0; x<bitmap.getWidth();x++){
            for(int y=0; y<bitmap.getHeight();y++){
                pixel = bitmap.getPixel(x,y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;

                r = 255 - r;
                g = 255 - g;
                b = 255 - b;

                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;

                pixel=((a << 24) | (r << 16) | (g << 8) | b);

                bmp.setPixel(x,y,pixel);

            }
        }

        return bmp;
    }

    Bitmap brightness(Bitmap bitmap){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0;

        for(int x=0; x<bitmap.getWidth();x++){
            for(int y=0; y<bitmap.getHeight();y++){
                pixel = bitmap.getPixel(x,y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;

                r = 40 + r;
                g = 40 + g;
                b = 40 + b;

                if(r > 255) r = 255;
                if(g > 255) g = 255;
                if(b > 255) b = 255;

                pixel=((a << 24) | (r << 16) | (g << 8) | b);

                bmp.setPixel(x,y,pixel);

            }
        }
        return bmp;
    }

    //aun terminar

    Bitmap contrast(Bitmap bitmap){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0;

        for(int x=0; x<bitmap.getWidth();x++){
            for(int y=0; y<bitmap.getHeight();y++){
                pixel = bitmap.getPixel(x,y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;
                double calculoAux = (1 / ((Math.PI) / 4));
                double calculo = (50 + 1) * (Math.PI / 4);
                calculo *= calculoAux;

                r = (r - 180) * (int) calculo;
                g = (g - 180) * (int) calculo;
                b = (b - 180) * (int) calculo;

                if(r > 255) r = 255;
                if(g > 255) g = 255;
                if(b > 255) b = 255;
                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;

                pixel=((a << 24) | (r << 16) | (g << 8) | b);

                bmp.setPixel(x,y,pixel);

            }
        }
        return bmp;
    }

    Bitmap edgeDetect(Bitmap bitmap, int umbral) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());

        int pixel = 0, r1 = 0, g1 = 0, b1 = 0, a = 0,
                r2 = 0, g2 = 0, b2 = 0,
                r3 = 0, g3 = 0, b3 = 0;

        for (int x = 0; x < bitmap.getWidth()-2; x++) {
            for (int y = 0; y < bitmap.getHeight()-2; y++) {
                pixel = bitmap.getPixel(x, y);
                a = (pixel >>> 24) & 0xff;
                r1 = (pixel >> 16) & 0xff;
                g1 = (pixel >> 8) & 0xff;
                b1 = pixel & 0xff;
                pixel = bitmap.getPixel(x+1, y);
                r2 = (pixel >> 16) & 0xff;
                g2 = (pixel >> 8) & 0xff;
                b2 = pixel & 0xff;
                pixel = bitmap.getPixel(x, y+1);
                r3 = (pixel >> 16) & 0xff;
                g3 = (pixel >> 8) & 0xff;
                b3 = pixel & 0xff;

                int d1 = (int) Math.sqrt(Math.pow((r1-r2),2)+Math.pow((g1-g2),2)+Math.pow((b1-b2),2));
                int d2 = (int) Math.sqrt(Math.pow((r1-r3),2)+Math.pow((g1-g3),2)+Math.pow((b1-b3),2));

                if (d1 >= umbral | d2 >= umbral) d1 = 255;
                else d1 = 0;

                pixel=((a << 24) | (d1 << 16) | (d1 << 8) | d1);
                bmp.setPixel(x,y,pixel);
            }
        }

        return bmp;
    }

    Bitmap gamma(Bitmap bitmap, int redGammaValue, int greenGammaValue, int blueGammaValue){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0;

        for(int x=0; x<bitmap.getWidth();x++){
            for(int y=0; y<bitmap.getHeight();y++){
                pixel = bitmap.getPixel(x,y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;

                r = (int) Math.min(255, Math.round((255.0 * Math.pow(r/255.0,1.0)/redGammaValue))+0.5);
                g = (int) Math.min(255, Math.round((255.0 * Math.pow(g/255.0,1.0)/greenGammaValue))+0.5);
                b = (int) Math.min(255, Math.round((255.0 * Math.pow(b/255.0,1.0)/blueGammaValue))+0.5);


                pixel=((a << 24) | (r << 16) | (g << 8) | b);
                bmp.setPixel(x,y,pixel);
            }
        }
        return bmp;
    }

    Bitmap colors(Bitmap bitmap, int red, int green, int blue){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel = 0, r = 0, g = 0, b = 0, a = 0;

        for(int x=0; x<bitmap.getWidth();x++) {
            for (int y = 0; y < bitmap.getHeight(); y++) {
                pixel = bitmap.getPixel(x, y);
                a = (pixel >>> 24) & 0xff;
                r = (pixel >> 16) & 0xff;
                g = (pixel >> 8) & 0xff;
                b = pixel & 0xff;

                r = r+red;
                if(r > 255) r = 255; else r = 0;
                g = g+green;
                if(g > 255) g = 255; else g = 0;
                b = b+blue;
                if(b > 255) b = 255; else b = 0;

                pixel = ((a << 24) | (r << 16) | (g << 8) | b);
                bmp.setPixel(x,y,pixel);
            }
        }
        return bmp;
    }

    Bitmap reverseMatrix(Bitmap bitmap){
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());

        int pixel;

        for (int x = 0; x < bitmap.getWidth(); x++){
            for (int y = 0; y < bitmap.getHeight(); y++) {
                pixel = bitmap.getPixel(x, y);
                bmp.setPixel(y, x, pixel);
            }
        }

        return bmp;
    }

}
