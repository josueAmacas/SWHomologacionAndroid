package com.example.sistemahomologacion.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemahomologacion.R;
import com.example.sistemahomologacion.adapter.DocenteAdapter;
import com.example.sistemahomologacion.controlador.SWDocenteVolly;
import com.example.sistemahomologacion.modelo.Docente;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment implements View.OnClickListener{

    EditText cedula;
    Button botonListar,botonBuscar;
    RecyclerView recycler;
    List<Docente> listaD;
    DocenteAdapter adapter;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        cedula = root.findViewById(R.id.txtCedulaD);
        recycler = root.findViewById(R.id.recyclerD);

        botonListar = root.findViewById(R.id.btnListarD);
        botonBuscar = root.findViewById(R.id.btnBuscarD);
        botonListar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        return root;
    }

    private void cargarDatos(View v){
        int cedD = listaD.get(recycler.getChildAdapterPosition(v)).getCedulaD();
        cedula.setText(cedD+"");
    }

    public void cargarLista(List<Docente> lista){
        listaD = new ArrayList<>();
        listaD = lista;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DocenteAdapter(listaD);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recycler.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        SWDocenteVolly sw = new SWDocenteVolly(getContext());
        switch (v.getId()){
            case R.id.btnListarD:
                cargarLista(sw.findAllDocentes());
                break;
            case R.id.btnBuscarD:
                String ced = cedula.getText().toString();
                if (ced.length() == 0){
                    Toast.makeText(getContext(),"Por favor llene el campos cedula para Buscar Docente",Toast.LENGTH_SHORT).show();
                }else {
                    cargarLista(sw.findByID(ced));
                }
                break;
        }
    }
}
