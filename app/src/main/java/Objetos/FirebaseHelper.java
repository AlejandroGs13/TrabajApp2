package Objetos;

import android.content.pm.PackageManager;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Objetos.FirebaseReference;
import Objetos.Usuario;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class FirebaseHelper {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference reference = database.getReference(FirebaseReference.TRABAJA_REFERENCE);


    public void addUsuario(String Correo,String Name,String Country,String City,int Age,String Adress,String Phone,String Ruta){
        Usuario usuario = new Usuario(Adress,Age,City,Country,Correo,Name,Phone,Ruta);
        reference.child(FirebaseReference.Usuario_REFERENCE).push().setValue(usuario);
    }


    public void addTrabajo(String name, String skill, String numero, String correo, String ciudad,String pais,String ruta,String descripcion){
        Trabajo trabajo = new Trabajo(correo,name,numero,ciudad,pais,skill,ruta,descripcion);
        reference.child(FirebaseReference.POST_TRABAJO).push().setValue(trabajo);
    }

    public void addPost(String titulo, String necesita, String numero, String correo, String nombre,String ruta){
        Post post = new Post(titulo,necesita,numero,correo,nombre,ruta);
        reference.child(FirebaseReference.POST_REFERENCE).push().setValue(post);
    }

    public Usuario usuario(final String Email){
        final Usuario[] usu = new Usuario[1];
        reference.child(FirebaseReference.Usuario_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    if (usuario.getEmail().equals(Email)){
                        usu[0] = usuario;
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return usu[0];
    }

}
