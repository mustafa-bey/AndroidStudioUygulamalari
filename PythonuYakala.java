package com.mustafa.python;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.ListPreference;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView zaman;
    TextView skor;
    int score;

    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] resimDizisi;

    Handler handler;
    Runnable runnable;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zaman = findViewById(R.id.zaman);
        zaman.setText("120");

        skor = findViewById(R.id.skorTablo);
        skor.setText("0");

        score=0;

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6= findViewById(R.id.imageView6);
        imageView7= findViewById(R.id.imageView7);
        imageView8= findViewById(R.id.imageView8);
        imageView9= findViewById(R.id.imageView9);


resimDizisi = new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        resimSakla();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) { // ontick metodu her bir saniyede ne yapılması gerektiğini yazarız
                zaman.setText("zaman = "+l/1000);
            }

            @Override
            public void onFinish() {
                zaman.setText("zaman doldu");
                handler.removeCallbacks(runnable);
                for (ImageView image :resimDizisi){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder uyarı = new AlertDialog.Builder(MainActivity.this);
                uyarı.setTitle("tekrar oyanyın");
                uyarı.setMessage("tekrar oynamak istediğine emin misin ");
                uyarı.setPositiveButton("evet oynamak isterim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);


                    }
                });
                uyarı.setNegativeButton("hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"oyun bitti",Toast.LENGTH_SHORT).show();
                    }
                });
                uyarı.show();

            }
        }.start();


    }

    public void SkorArttır(View view){

        score=score+1;
        skor.setText("skor = "+score);

    }

    public void resimSakla(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image :resimDizisi){
                    image.setVisibility(View.INVISIBLE);
                }
                random = new Random();
                int i = random.nextInt(9);
                resimDizisi[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,700);
            }
        };
        handler.post(runnable);




    }

}
