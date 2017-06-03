package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Objetos.FirebaseHelper;

public class addTrabajoActivity extends AppCompatActivity {


    EditText titulo,contenido;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trabajo);


        Bundle bundle = getIntent().getExtras();
        final String correo = bundle.getString("correo");
        final String nombre = bundle.getString("name");
        final String ciudad = bundle.getString("ciudad");
        final String pais = bundle.getString("pais");
        final String ruta = bundle.getString("ruta");
        final String numero =bundle.getString("numero");

        titulo = (EditText)findViewById(R.id.edit_tirulo);
        contenido = (EditText)findViewById(R.id.edit_des);

        final FirebaseHelper firebaseHelper = new FirebaseHelper();
        btn = (Button)findViewById(R.id.btn_post);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseHelper.addTrabajo(nombre,titulo.getText().toString(),numero,correo,ciudad,pais,ruta,contenido.getText().toString());
                finish();

            }
        });
    }
}
