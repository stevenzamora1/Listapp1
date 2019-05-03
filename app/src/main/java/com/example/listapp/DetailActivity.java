package com.example.listapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("com.example.ITEM_INDEX", -1);


        if(index > -1){
            int pic = getImage(index);
            ImageView img = (ImageView) findViewById(R.id.imageView) ;
            scaleImg(img, pic);


        }

        //Take the user to google so they can look up the stock
        Button googleBtn = (Button) findViewById(R.id.googleBtn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String google = "http:www.google.com";
                Uri webadress = Uri.parse(google);

                Intent gotoGoogle = new Intent (Intent.ACTION_VIEW, webadress);

                if(gotoGoogle.resolveActivity(getPackageManager()) != null){

                    startActivity(gotoGoogle);

                }
            }
        });

        if(in.hasExtra("com.example.quicklauncher.SOMETHING")){


            TextView tv = (TextView) findViewById(R.id.nameTextView);
            String text = in.getExtras().getString("com.example.quicklauncher.SOMETHING");
            tv.setText(text);
        }

    }




    private int getImage(int index){
        switch(index){
            case 0: return R.drawable.kraft;
            case 1: return R.drawable.ge;
            case 2: return R.drawable.apple;
            default: return -1;
        }


    }



    private void scaleImg(ImageView img, int pic ){

        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic, options );

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if(imgWidth > screenWidth){

            int ratio = Math.round((float)imgWidth / (float) screenWidth);
            options.inSampleSize = ratio;

        }
        options.inJustDecodeBounds = false;
        Bitmap scaleImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaleImg);




    }

}
