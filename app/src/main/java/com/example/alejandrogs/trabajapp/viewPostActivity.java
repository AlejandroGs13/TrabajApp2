package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Objetos.IMageUrl;

public class viewPostActivity extends AppCompatActivity {


    TextView titulo,contenido,correo;
    ImageView profile;
    Button llamar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);


        titulo = (TextView)findViewById(R.id.titulo);
        contenido = (TextView)findViewById(R.id.contenido);
        correo = (TextView)findViewById(R.id.Correo);
        profile = (ImageView)findViewById(R.id.profile);
        llamar = (Button)findViewById(R.id.btn_llamar);

         Bundle bundle = getIntent().getExtras();

        String ti = bundle.getString("titulo");
        String rut = bundle.getString("ruta");
        String conte = bundle.getString("contenido");
        String email = bundle.getString("email");
        final String phone = bundle.getString("phone");

        titulo.setText(ti);
        contenido.setText(conte);
        correo.setText(email);
        IMageUrl  iMageUrl = new IMageUrl(profile,rut);
        iMageUrl.cambiar(getApplicationContext());

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(phone);
            }
        });

    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
}
