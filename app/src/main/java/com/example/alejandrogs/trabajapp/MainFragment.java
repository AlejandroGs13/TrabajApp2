package com.example.alejandrogs.trabajapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Objetos.FirebaseReference;
import Objetos.MainrecyclerViewAdapter;
import Objetos.Post;
import Objetos.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    List<Post> posts;
    MainrecyclerViewAdapter adapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view  =inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.RecyclerMain);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        posts = new ArrayList<>();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        adapter = new MainrecyclerViewAdapter(posts);

        recyclerView.setAdapter(adapter);
        DatabaseReference reference = database.getReference(FirebaseReference.TRABAJA_REFERENCE);
        reference.child(FirebaseReference.POST_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                posts.removeAll(posts);
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Post post= snapshot.getValue(Post.class);
                    posts.add(post);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
