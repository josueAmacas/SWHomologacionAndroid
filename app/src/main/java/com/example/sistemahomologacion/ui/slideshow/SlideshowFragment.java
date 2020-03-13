package com.example.sistemahomologacion.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemahomologacion.R;
import com.example.sistemahomologacion.adapter.TramiteAdapter;
import com.example.sistemahomologacion.controlador.SWTramiteVolly;
import com.example.sistemahomologacion.modelo.Tramite;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    EditText codigo,cedula;
    Button botonListarT,botonBuscarId,botonBuscarCedula;
    RecyclerView recycler;
    List<Tramite> listaT;
    TramiteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        cedula = root.findViewById(R.id.txtCedulaT);
        codigo = root.findViewById(R.id.txtCodigoT);
        recycler = root.findViewById(R.id.recyclerT);

        botonBuscarCedula = root.findViewById(R.id.btnBuscarPersonaT);
        botonListarT = root.findViewById(R.id.btnListarTodosT);
        botonBuscarId = root.findViewById(R.id.btnBuscarCodigoT);
        botonBuscarCedula.setOnClickListener(this);
        botonListarT.setOnClickListener(this);
        botonBuscarId.setOnClickListener(this);
        return root;
    }

    private void cargarDatos(View v){
        int cedT = listaT.get(recycler.getChildAdapterPosition(v)).getCedulaT();
        int cod = listaT.get(recycler.getChildAdapterPosition(v)).getIdTramite();
        cedula.setText(cedT+"");
        codigo.setText(cod+"");
    }

    public void cargarLista(List<Tramite> lista){
        listaT = new ArrayList<>();
        listaT = lista;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TramiteAdapter(listaT);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recycler.setAdapter(adapter);
    }

    public void vaciarDatos(){
        cedula.setText("");
        codigo.setText("");
    }

    @Override
    public void onClick(View v) {
        SWTramiteVolly sw = new SWTramiteVolly(getContext());
        switch (v.getId()){
            case R.id.btnListarTodosT:
                cargarLista(sw.findAllTramite());
                break;
            case R.id.btnBuscarCodigoT:
                String cd = codigo.getText().toString();
                if (cd.length() == 0){
                    Toast.makeText(getContext(),"Por favor llene el campo codigo para Buscar por Codigo",Toast.LENGTH_SHORT).show();
                }else {
                    cargarLista(sw.findByID(cd));
                }
                break;
            case R.id.btnBuscarPersonaT:
                String cedu = cedula.getText().toString();
                if (cedu.length() == 0){
                    Toast.makeText(getContext(),"Por favor llene el campo cedula para Buscar por Usuario",Toast.LENGTH_SHORT).show();
                }else {
                    cargarLista(sw.findByCed(cedu));
                }
                break;
        }
    }
}
