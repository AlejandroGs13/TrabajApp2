package com.example.alejandrogs.trabajapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import Objetos.FirebaseHelper;

public class PostActivity extends AppCompatActivity {


    FirebaseAuth.AuthStateListener mListener;
    FirebaseHelper firebaseHelper;
    EditText edtTitulo,edtDes;
    Button btnPostear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        edtTitulo = (EditText)findViewById(R.id.edit_tirulo);
        edtDes = (EditText)findViewById(R.id.edit_des);
        btnPostear = (Button)findViewById(R.id.btn_post);
        firebaseHelper = new FirebaseHelper();
        Bundle bundle = getIntent().getExtras();
        final String email = bundle.getString("Email");
        final String name = bundle.getString("Name");
        final String Ruta = bundle.getString("Ruta");
        final String Phone = bundle.getString("Phone");
        btnPostear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),email+"\n"+name+" \n"+Ruta+"\n"+Phone,Toast.LENGTH_LONG).show();
                firebaseHelper.addPost(edtTitulo.getText().toString(),edtDes.getText().toString(),
                        Phone,email,name,Ruta);
                finish();
            }
        });


    }
    /*
    mListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            Log.i("ENTRO","Entro primera parte");
            if (user != null){

                Log.i("ENTRO","Entro primera segunda parte");
                firebaseHelper.addPost(edtTitulo.getText().toString(),
                        edtDes.getText().toString(),firebaseHelper.usuario(user.getEmail()).getPhone(),
                        user.getEmail(),firebaseHelper.usuario(user.getEmail()).getName(),
                        firebaseHelper.usuario(user.getEmail()).getRuta());

                finish();
            }else{
                Log.i("SESION ","sesion cerrada");
            }
        }
    };*/

}
