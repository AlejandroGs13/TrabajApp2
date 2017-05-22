package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SampleActivity extends AppCompatActivity {

    Button logOutBtn;
    ImageView imageView;
    HttpURLConnection conn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        logOutBtn = (Button)findViewById(R.id.salir);
        Bundle bundle = getIntent().getExtras();
        String url = bundle.getString("photo");
        imageView = (ImageView)findViewById(R.id.image) ;
        TextView textView = (TextView)findViewById(R.id.text);
        textView.setText(url);
        new GetImageToURL().execute(url);
        /*try {
            URL imageUrl = new URL(url);
            conn = (HttpURLConnection) imageUrl.openConnection();
            conn.connect();

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            Bitmap imagen = BitmapFactory.decodeStream(conn.getInputStream(), new Rect(0, 0, 0, 0), options);
            imageView.setImageBitmap(imagen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */


        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

                FirebaseAuth.getInstance().signOut();


                Intent intent = new Intent(SampleActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private class GetImageToURL extends AsyncTask< String, Void, Bitmap > {

        @Override
        protected Bitmap doInBackground(String...params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap myBitMap) {
            imageView.setImageBitmap(myBitMap);
        }
    }
}