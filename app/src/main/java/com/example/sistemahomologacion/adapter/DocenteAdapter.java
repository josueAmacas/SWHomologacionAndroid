package com.example.sistemahomologacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemahomologacion.R;
import com.example.sistemahomologacion.modelo.Docente;

import java.util.List;

public class DocenteAdapter extends RecyclerView.Adapter<DocenteAdapter.ViewHolderDocente> implements View.OnClickListener{

    List<Docente> lista;
    public DocenteAdapter(List<Docente> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderDocente onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_docente, null);
        view.setOnClickListener(this);
        return new ViewHolderDocente(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDocente viewHolderDocente, int position) {
        viewHolderDocente.datoId.setText(lista.get(position).getIdD()+"");
        viewHolderDocente.datoCedula.setText(lista.get(position).getCedulaD()+"");
        viewHolderDocente.datoNombre.setText(lista.get(position).getNombreD());
        viewHolderDocente.datoApellido.setText(lista.get(position).getApellidoD());
        viewHolderDocente.datoEmail.setText(lista.get(position).getEmailD());
        viewHolderDocente.datoTitulo.setText(lista.get(position).getTituloD());
        viewHolderDocente.datoCarrera.setText(lista.get(position).getCarreraD());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.click = listener;
    }

    @Override
    public void onClick(View v) {
        if(click != null){
            click.onClick(v);
        }
    }

    public static  class ViewHolderDocente extends RecyclerView.ViewHolder {
        TextView datoCedula,datoNombre,datoApellido,datoEmail,datoCarrera,datoTitulo,datoId;

        public ViewHolderDocente(View itemView) {
            super(itemView);
            datoId= itemView.findViewById(R.id.lblIDD);
            datoCedula = itemView.findViewById(R.id.lblCedulaD);
            datoNombre = itemView.findViewById(R.id.lblNombreD);
            datoApellido = itemView.findViewById(R.id.lblApellidoD);
            datoEmail = itemView.findViewById(R.id.lblEmailD);
            datoCarrera = itemView.findViewById(R.id.lblCarreraD);
            datoTitulo = itemView.findViewById(R.id.lblTituloD);
        }
    }

}
