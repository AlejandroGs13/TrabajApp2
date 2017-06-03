package Objetos;

import android.content.Context;
import android.content.SharedPreferences;
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

public class MainrecyclerViewAdapter extends RecyclerView.Adapter<MainrecyclerViewAdapter.MainviewHolder> implements View.OnClickListener{

    List<Post> posts;
    private View.OnClickListener listener;
    public MainrecyclerViewAdapter(List<Post> posts) {
        this.posts = posts;
    }



    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public MainviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_main,parent,false);
        view.setOnClickListener(this);
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
        iMageUrl.cambiar(holder.itemView.getContext());
        guardar(holder.itemView.getContext(),usuario.getEmail(),usuario.getTitulo(),usuario.getRuta());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }



    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
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

    public void guardar(Context context,String dato,String dato2,String dato3){
        SharedPreferences preferencias = context.getSharedPreferences("mospreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("dato",dato);
        editor.putString("dato2",dato2);
        editor.putString("dato3",dato3);
        editor.apply();
        editor.commit();
    }
}
