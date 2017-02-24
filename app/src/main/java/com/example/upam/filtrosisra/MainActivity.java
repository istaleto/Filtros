package com.example.upam.filtrosisra;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ImageView img, otroimg;
    BitmapDrawable bd;
    Bitmap tempBmp;
    int wea = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.img = (ImageView) findViewById(R.id.sourceImage);
        this.otroimg = (ImageView) findViewById(R.id.imageResult);
        bd=(BitmapDrawable) img.getDrawable();
    }

    public void blancoynegro(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.greyScale(bd.getBitmap()));
    }

    public void invertir(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.invert(bd.getBitmap()));
    }

    public void masbrilloView(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.brightness(bd.getBitmap()));
    }

    public void contrast(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.contrast(bd.getBitmap()));
    }

    public void edge(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.edgeDetect(bd.getBitmap(),45));
    }

    public void gamma(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.gamma(bd.getBitmap(),2,2,2));
    }

    public void color(View view){
        Filtros fls = new Filtros();
        otroimg.setImageBitmap(fls.colors(bd.getBitmap(),180,180,180));
    }

    public void rotate(View view){
        Filtros fls = new Filtros();
        wea++;
        if(wea==1){
            otroimg.setImageBitmap(fls.reverseMatrix(bd.getBitmap()));
            tempBmp = fls.reverseMatrix(bd.getBitmap());
        } else{
            otroimg.setImageBitmap(fls.reverseMatrix(tempBmp));
            if(wea==2) wea=0;
        }
    }


}
