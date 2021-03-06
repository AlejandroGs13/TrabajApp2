package com.example.alejandrogs.trabajapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.FirebaseHelper;
import Objetos.FirebaseReference;
import Objetos.IMageUrl;
import Objetos.Post;
import Objetos.Usuario;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseHelper firebaseHelper;
    int nav = 1;
    ImageView perfil;
    List<Post> pp;
    FirebaseAuth.AuthStateListener mListener;
    IMageUrl iMageUrl;
    TextView textViewcorreo;
    List<Usuario> posts;
    FloatingActionButton fab;
    android.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseHelper = new FirebaseHelper();
        Bundle bundle = getIntent().getExtras();
        posts = new ArrayList<>();
        pp = new ArrayList<>();
        final String email = bundle.getString("Email");
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nav == 1) {
                    Intent intent = new Intent(view.getContext(), PostActivity.class);
                    intent.putExtra("Email", email);
                    intent.putExtra("Name", posts.get(0).getName());
                    intent.putExtra("Ruta", posts.get(0).getRuta());
                    intent.putExtra("Phone", posts.get(0).getPhone());
                    startActivity(intent);
                }
                if (nav == 2){
                    Intent intent = new Intent(view.getContext(), addTrabajoActivity.class);
                    intent.putExtra("correo", email);
                    intent.putExtra("name", posts.get(0).getName());
                    intent.putExtra("ruta", posts.get(0).getRuta());
                    intent.putExtra("numero", posts.get(0).getPhone());
                    intent.putExtra("ciudad", posts.get(0).getCity());
                    intent.putExtra("pais", posts.get(0).getCountry());
                    startActivity(intent);
                }
            }
        });




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.context_frame, new MainFragment()).commit();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(FirebaseReference.TRABAJA_REFERENCE);
        reference.child(FirebaseReference.Usuario_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("ENTRO","ENTRO1111111111111");
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                    Usuario usuario= snapshot.getValue(Usuario.class);
                    Log.i("ENTRO",usuario.getEmail());
                   if (usuario.getEmail().toUpperCase().equals(email.toUpperCase())){
                       Usuario user= snapshot.getValue(Usuario.class);
                       Log.i("ENTRO","ENTRO22222");
                       posts.add(user);
                   }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            nav =1;
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new MainFragment()).commit();
            fab.show();
        } else if (id == R.id.nav_gallery) {
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new ProfileFragment()).commit();
            fab.hide();
        }  else if (id == R.id.nav_send) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseAuth.getInstance().signOut();


            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_job){
            nav = 2;
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.context_frame, new TrabadoresFragment()).commit();
            fab.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
