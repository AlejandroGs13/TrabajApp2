package com.example.alejandrogs.trabajapp;


import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.zip.Inflater;

import Objetos.FirebaseReference;
import Objetos.IMageUrl;
import Objetos.Post;
import Objetos.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    List<Usuario> usuarios;
    TextView email,name,phone,city,country;
    ImageView profile;
    RatingBar ratingBar;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile,container,false);
        email = (TextView)view.findViewById(R.id.email);
        city = (TextView)view.findViewById(R.id.txtcity);
        country = (TextView)view.findViewById(R.id.txtcountry);
        name = (TextView)view.findViewById(R.id.txtname);
        phone = (TextView)view.findViewById(R.id.phone);
        profile = (ImageView)view.findViewById(R.id.ImageProfile);
        usuarios = new ArrayList<>();



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(FirebaseReference.TRABAJA_REFERENCE);
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
        final String correo = firebaseAuth.getCurrentUser().getEmail();
        //Toast.makeText(view.getContext(),"Correo:"+correo,Toast.LENGTH_SHORT).show();
        reference.child(FirebaseReference.Usuario_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Log.i("ENTRO","ENTRO1111111111111");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    Usuario usuario= snapshot.getValue(Usuario.class);
                    Log.i("ENTRO",usuario.getEmail());
                    if (usuario.getEmail().toUpperCase().equals(correo.toUpperCase())){
                        Usuario user= snapshot.getValue(Usuario.class);
                        Log.i("ENTRO","ENTRO22222");
                        usuarios.add(user);
                    }
                }
                email.setText(usuarios.get(0).getEmail());
                city.setText(usuarios.get(0).getCity());
                phone.setText(usuarios.get(0).getPhone());
                name.setText(usuarios.get(0).getName());
                country.setText(usuarios.get(0).getCountry());
                IMageUrl iMageUrl;
                iMageUrl = new IMageUrl(profile,usuarios.get(0).getRuta());
                iMageUrl.cambiar(view.getContext());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return  view;
    }

}
