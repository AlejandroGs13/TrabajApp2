package com.example.alejandrogs.trabajapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import Objetos.FirebaseHelper;

public class AltaActivity extends AppCompatActivity  {
    FirebaseHelper firebaseHelper;
    EditText edtEmail,edtPass,edtName,edtciudad,edtTelefono,edtDireccion,edtEdad;
    Spinner spinner;
    private final int MY_PERMISSIONS = 100;
    private final int PHOTO_CODE = 200;
    private static String APP_DIRECTORY = "TrabjApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "Photos";
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/TrabajA´/";
    private static final int SELECT_PICTURE = 1;
    private StorageReference mStorage;
    Button btnRe,btnPhoto;
    String ruta;
    private String mPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);
        edtciudad = (EditText)findViewById(R.id.edit_city);
        edtPass = (EditText)findViewById(R.id.edt_pass);
        edtEmail = (EditText)findViewById(R.id.edit_usu);
        edtName = (EditText)findViewById(R.id.edit_name);
        edtTelefono = (EditText)findViewById(R.id.edit_phone);
        edtDireccion = (EditText)findViewById(R.id.edit_adress);
        edtEdad = (EditText)findViewById(R.id.edit_edad);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnRe = (Button) findViewById(R.id.btn_registrate);
        btnPhoto = (Button) findViewById(R.id.btn_Foto);
        firebaseHelper = new FirebaseHelper();
        mStorage = FirebaseStorage.getInstance().getReference();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOptions();
            }
        });
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                registrar(email,pass,v);
            }
        });
    }


    private void registrar(final String email, String pass, final View view){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("SESION","usuario creda correctamente");
                    firebaseHelper.addUsuario(email,edtName.getText().toString(),
                            spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString(),
                            edtciudad.getText().toString(),Integer.parseInt(edtEdad.getText().toString()),
                            edtDireccion.getText().toString(),edtTelefono.getText().toString(),ruta);
                    Intent intent = new Intent(view.getContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Log.e("SESION",task.getException().getMessage()+"");
                }
            }
        });

    }

    private void showOptions() {
        final CharSequence[] option = {"Tomar foto", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(AltaActivity.this);
        builder.setTitle("Eleige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(option[which] == "Tomar foto"){
                    /********************************************************/
                    File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
                    boolean isDirectoryCreated = file.exists();

                    if(!isDirectoryCreated)
                        isDirectoryCreated = file.mkdirs();

                    if(isDirectoryCreated){
                        Long timestamp = System.currentTimeMillis() / 1000;
                        String imageName = timestamp.toString() + ".jpg";

                        mPath = Environment.getExternalStorageDirectory() + File.separator + MEDIA_DIRECTORY
                                + File.separator + imageName;

                        File newFile = new File(mPath);
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
                        startActivityForResult(intent, PHOTO_CODE);
                    }

                    /***************************************************************/
                }else if(option[which] == "Elegir de galeria"){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_PICTURE);
                }else {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case PHOTO_CODE:

                    MediaScannerConnection.scanFile(this,
                            new String[]{mPath}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    StorageReference filepath2 = mStorage.child("Fotos").child(uri.getLastPathSegment());
                                    filepath2.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            @SuppressWarnings("VisibleForTests")   Uri bajaIma = taskSnapshot.getDownloadUrl();

                                            ruta = bajaIma.toString();

                                            Toast.makeText(AltaActivity.this, "Se subio la foto Exitosamente", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });

                    break;
                case SELECT_PICTURE:
                    Uri uri2 = data.getData();

                    StorageReference filepath2 = mStorage.child("Fotos").child(uri2.getLastPathSegment());
                    filepath2.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            @SuppressWarnings("VisibleForTests")   Uri bajaIma = taskSnapshot.getDownloadUrl();

                            ruta = bajaIma.toString();

                            Toast.makeText(AltaActivity.this, "Se subio la foto Exitosamente", Toast.LENGTH_SHORT).show();
                        }
                    });

                    break;

            }
        }
    }







}
