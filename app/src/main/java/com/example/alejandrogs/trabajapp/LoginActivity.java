package com.example.alejandrogs.trabajapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsu,edtPass;
    Button btnRe,btnLo;
    private int widgetID = 0;


    FirebaseAuth.AuthStateListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnRe = (Button) findViewById(R.id.btn_registrate);
        btnLo = (Button) findViewById(R.id.btn_login);
        edtUsu = (EditText) findViewById(R.id.edit_usu);
        edtPass= (EditText) findViewById(R.id.edt_pass);

        btnRe.setOnClickListener(this);
        btnLo.setOnClickListener(this);

        mListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null){
                    Toast.makeText(getApplicationContext(),"sesision inicida con:\n" +user.getEmail(),Toast.LENGTH_SHORT).show();
                    Log.i("SESION ","sesision inicida con mail" +user.getEmail());
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("Email",user.getEmail());
                    startActivity(intent);
                    finish();
                }else{
                    Log.i("SESION ","sesion cerrada");
                }
            }
        };


    }



    private void iniciar(String email,String pass){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SESION","sesion iniciada");
                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.btn_login:
                String email = edtUsu.getText().toString();
                String pass = edtPass.getText().toString();
                iniciar(email,pass);
                break;

            case  R.id.btn_registrate:
                Intent intent = new Intent(v.getContext(),AltaActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mListener);
        }
    }

    public void snackbarMessage(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
