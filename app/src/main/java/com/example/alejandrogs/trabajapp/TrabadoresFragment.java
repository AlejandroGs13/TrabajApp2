package com.example.alejandrogs.trabajapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.FirebaseReference;
import Objetos.Post;
import Objetos.TrabajaRecyclerAdapter;
import Objetos.Trabajo;
import Objetos.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class TrabadoresFragment extends Fragment {



    TrabajaRecyclerAdapter adapter;
    RecyclerView recyclerView;
    List<Trabajo> trabajoList;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_trabadores, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerTrabajador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        trabajoList = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new TrabajaRecyclerAdapter(trabajoList);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrir(recyclerView.getChildAdapterPosition(v),v);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        DatabaseReference reference = database.getReference(FirebaseReference.TRABAJA_REFERENCE);
        reference.child(FirebaseReference.POST_TRABAJO).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                trabajoList.removeAll(trabajoList);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Trabajo trabajo= snapshot.getValue(Trabajo.class);
                    trabajoList.add(trabajo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        return view;
    }


    public void abrir(int pos,View view){
        Intent intent = new Intent(view.getContext(),TrabajadorView.class);
        intent.putExtra("name",trabajoList.get(pos).getNombre());
        intent.putExtra("correo",trabajoList.get(pos).getCorreo());
        intent.putExtra("ciudad",trabajoList.get(pos).getCiudad());
        intent.putExtra("skill",trabajoList.get(pos).getSkills());
        intent.putExtra("phone",trabajoList.get(pos).getNumero());
        intent.putExtra("ruta",trabajoList.get(pos).getRuta());
        startActivity(intent);


    }
}
