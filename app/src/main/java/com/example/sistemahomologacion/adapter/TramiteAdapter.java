package com.example.sistemahomologacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemahomologacion.R;
import com.example.sistemahomologacion.modelo.Tramite;

import java.util.List;

public class TramiteAdapter extends RecyclerView.Adapter<TramiteAdapter.ViewHolderTramite> implements View.OnClickListener{

    List<Tramite> lista;
    public TramiteAdapter(List<Tramite> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderTramite onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tramite, null);
        view.setOnClickListener(this);
        return new ViewHolderTramite(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTramite viewHolderTramite, int position) {
        viewHolderTramite.datoId.setText(lista.get(position).getIdTramite()+"");
        viewHolderTramite.datoCedula.setText(lista.get(position).getCedulaT()+"");
        viewHolderTramite.datoNombre.setText(lista.get(position).getNombreT());
        viewHolderTramite.datoApellido.setText(lista.get(position).getApellidoT());
        viewHolderTramite.datoEmail.setText(lista.get(position).getEmailT());
        viewHolderTramite.datoRegistro.setText(lista.get(position).getRegistro());
        viewHolderTramite.datoEstado.setText(lista.get(position).getEstado());
        viewHolderTramite.datoTipo.setText(lista.get(position).getTipo());
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

    public static  class ViewHolderTramite extends RecyclerView.ViewHolder {
        TextView datoCedula,datoNombre,datoApellido,datoEmail,datoRegistro,datoEstado,datoTipo,datoId;

        public ViewHolderTramite(View itemView) {
            super(itemView);
            datoId= itemView.findViewById(R.id.lblIDT);
            datoCedula = itemView.findViewById(R.id.lblCedulaT);
            datoNombre = itemView.findViewById(R.id.lblNombreT);
            datoApellido = itemView.findViewById(R.id.lblApellidoT);
            datoEmail = itemView.findViewById(R.id.lblEmailT);
            datoRegistro = itemView.findViewById(R.id.lblCodigoT);
            datoEstado = itemView.findViewById(R.id.lblEstadoT);
            datoTipo = itemView.findViewById(R.id.lblTipoT);
        }
    }

}
