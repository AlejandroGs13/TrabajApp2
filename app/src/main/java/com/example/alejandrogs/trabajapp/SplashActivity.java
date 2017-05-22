package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread() {
            public void run() {  ///Declara el timerTheread y lo inicia
                try {
                    sleep(3000);//Duerne 3000 milisegundos el hilo
                } catch (InterruptedException e) {
                    e.printStackTrace();//Muestra el error encaso que atrape uno
                } finally {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent); //Inica la el login activity
                    finish();
                }
            }
        };
        timerThread.start();

    }
        @Override
        protected void onPause() {
            // TODO Auto-generated method stub
            super.onPause();
            finish();
        }

}