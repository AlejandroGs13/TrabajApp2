package Objetos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandrogs.trabajapp.R;
import com.example.alejandrogs.trabajapp.SampleActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by alejandrogs on 20/05/17.
 */

public class MainrecyclerViewAdapter extends RecyclerView.Adapter<MainrecyclerViewAdapter.MainviewHolder>{

    List<Post> posts;

    public MainrecyclerViewAdapter(List<Post> posts) {
        this.posts = posts;
    }


    @Override
    public MainviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_main,parent,false);
        MainviewHolder viewHolder = new MainviewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(MainviewHolder holder, int position) {
        Post usuario = posts.get(position);
        String url = usuario.getRuta();

        holder.correo.setText(usuario.getEmail());
        holder.Necesita.setText(usuario.getTitulo());
        holder.name.setText(usuario.getNombre());
        holder.direccion.setText("Necesita:"+usuario.getNecesita());
        IMageUrl iMageUrl;
        iMageUrl = new IMageUrl(holder.perfil,url);
        iMageUrl.cambiar();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }





    public static class  MainviewHolder extends RecyclerView.ViewHolder{


        TextView correo,name,direccion,Necesita;
        ImageView perfil;

        public MainviewHolder(View itemView) {
            super(itemView);
            correo = (TextView) itemView.findViewById(R.id.Correo);
            Necesita = (TextView) itemView.findViewById(R.id.Necesita);
            name = (TextView) itemView.findViewById(R.id.Name);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            perfil = (ImageView)itemView.findViewById(R.id.image);
        }
    }
}
