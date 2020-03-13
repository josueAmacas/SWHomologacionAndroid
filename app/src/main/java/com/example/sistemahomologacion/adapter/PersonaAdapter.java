package com.example.sistemahomologacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemahomologacion.R;
import com.example.sistemahomologacion.modelo.Persona;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolderPersona> implements View.OnClickListener{

    List<Persona> lista;
    public PersonaAdapter(List<Persona> lista){
        this.lista = lista;
    }

    public View.OnClickListener click;

    @Override
    public ViewHolderPersona onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, null);
        view.setOnClickListener(this);
        return new ViewHolderPersona(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersona viewHolderPersona, int position) {
        viewHolderPersona.datonCedula.setText(lista.get(position).getCedula()+"");
        viewHolderPersona.datonombre.setText(lista.get(position).getNombre());
        viewHolderPersona.datoApellido.setText(lista.get(position).getApellido());
        viewHolderPersona.datoemail.setText(lista.get(position).getEmail());
        viewHolderPersona.datofechaN.setText(lista.get(position).getFechaNacimiento());
        viewHolderPersona.datoedad.setText(lista.get(position).getEdad()+"");
        viewHolderPersona.datodireccion.setText(lista.get(position).getDireccion());
        viewHolderPersona.datoTelefono.setText(lista.get(position).getTelefono());
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

    public static  class ViewHolderPersona extends RecyclerView.ViewHolder {
        TextView datonCedula,datonombre,datoApellido,datoemail,datofechaN,datoedad,datodireccion,datoTelefono;

        public ViewHolderPersona(View itemView) {
            super(itemView);
            datonCedula = itemView.findViewById(R.id.lblCedula);
            datonombre = itemView.findViewById(R.id.lblNombre);
            datoApellido = itemView.findViewById(R.id.lblApellido);
            datoemail = itemView.findViewById(R.id.lblEmail);
            datofechaN = itemView.findViewById(R.id.lblFechaNac);
            datoedad = itemView.findViewById(R.id.lblEdad);
            datodireccion= itemView.findViewById(R.id.lblDireccion);
            datoTelefono= itemView.findViewById(R.id.lblTelefono);
        }
    }

}
