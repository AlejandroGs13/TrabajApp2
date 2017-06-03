package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import Objetos.IMageUrl;

public class TrabajadorView extends AppCompatActivity {


    TextView name,correo,ciudad,skill;
    ImageView ima;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajador_view);

        Bundle bundle = getIntent().getExtras();
        String sname = bundle.getString("name");
        String scorreo = bundle.getString("correo");
        String sciudad = bundle.getString("ciudad");
        String skills = bundle.getString("skill");
        final String phone = bundle.getString("phone");
        String ruta = bundle.getString("ruta");


        name = (TextView) findViewById(R.id.name);
        correo = (TextView)findViewById(R.id.correo);
        ciudad = (TextView)findViewById(R.id.ciudad);
        skill = (TextView)findViewById(R.id.skill);
        ima = (ImageView)findViewById(R.id.Image);
        btn = (Button)findViewById(R.id.btn_llamar);

        name.setText(sname);
        correo.setText(scorreo);
        ciudad.setText(sciudad);
        skill.setText(skills);
        IMageUrl iMageUrl = new IMageUrl(ima,ruta);
        iMageUrl.cambiar(getApplicationContext());
        //Toast.makeText(getApplicationContext(),sname+"sssss\n"+scorreo+"\n"+sciudad+"\n"+skill,Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
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
