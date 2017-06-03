package Objetos;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandrogs.trabajapp.R;

import java.util.List;

/**
 * Created by alejandrogs on 23/05/17.
 */

public class TrabajaRecyclerAdapter  extends RecyclerView.Adapter<TrabajaRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    List<Trabajo> trabajoList;
    private View.OnClickListener listener;

    public TrabajaRecyclerAdapter(List<Trabajo> trabajoList) {
        this.trabajoList = trabajoList;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtrabajo,parent,false);
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trabajo trabajador = trabajoList.get(position);
        String url = trabajador.getRuta();
        holder.nombre.setText(trabajador.getDescripcion());
        holder.skills.setText(trabajador.getSkills());
        IMageUrl iMageUrl = new IMageUrl(holder.imageView,url);
        iMageUrl.cambiar(holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return  trabajoList.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,skills;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
            nombre = (TextView) itemView.findViewById(R.id.Name);
            skills = (TextView) itemView.findViewById(R.id.Necesita);
        }
    }
}
