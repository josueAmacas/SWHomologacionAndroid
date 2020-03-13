package com.example.sistemahomologacion.ui.home;

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
import com.example.sistemahomologacion.adapter.PersonaAdapter;
import com.example.sistemahomologacion.controlador.SWPersonaVolly;
import com.example.sistemahomologacion.modelo.Persona;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    EditText cedulula,apellido,nombre,fechaN,edad,email,direccion,telefono,usuario,pass;
    Button botonAgregar,botonListar,botonBuscar;
    RecyclerView recycler;
    List<Persona> listaP;
    PersonaAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        cedulula = root.findViewById(R.id.txtCedula);
        apellido = root.findViewById(R.id.txtApellido);
        nombre = root.findViewById(R.id.txtNombre);
        fechaN = root.findViewById(R.id.txtFechaNac);
        edad = root.findViewById(R.id.txtEdad);
        email = root.findViewById(R.id.txtEmail);
        direccion = root.findViewById(R.id.txtDireccion);
        telefono = root.findViewById(R.id.txtTelefono);
        usuario = root.findViewById(R.id.txtUsuario);
        pass = root.findViewById(R.id.txtPassword);

        recycler = root.findViewById(R.id.recyclerP);

        botonAgregar = root.findViewById(R.id.btnAgregar);
        botonListar = root.findViewById(R.id.btnListar);
        botonBuscar = root.findViewById(R.id.btnBuscar);
        botonAgregar.setOnClickListener(this);
        botonListar.setOnClickListener(this);
        botonBuscar.setOnClickListener(this);
        return root;
    }

    private void cargarDatos(View v){
        int cedulaP = listaP.get(recycler.getChildAdapterPosition(v)).getCedula();
        String nombreP= listaP.get(recycler.getChildAdapterPosition(v)).getNombre();
        String apellidoP= listaP.get(recycler.getChildAdapterPosition(v)).getApellido();
        int edadP = listaP.get(recycler.getChildAdapterPosition(v)).getEdad();
        String emailP = listaP.get(recycler.getChildAdapterPosition(v)).getEmail();
        String direccionP = listaP.get(recycler.getChildAdapterPosition(v)).getDireccion();
        String telefonoP = listaP.get(recycler.getChildAdapterPosition(v)).getTelefono();
        String fechaNP = listaP.get(recycler.getChildAdapterPosition(v)).getFechaNacimiento();
        cedulula.setText(cedulaP+"");
        apellido.setText(apellidoP);
        nombre.setText(nombreP);
        edad.setText(edadP+"");
        email.setText(emailP);
        direccion.setText(direccionP);
        telefono.setText(telefonoP);
        fechaN.setText(fechaNP);
    }
    
    public void cargarLista(List<Persona> lista){
        listaP = new ArrayList<>();
        listaP = lista;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PersonaAdapter(listaP);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarDatos(v);
            }
        });
        recycler.setAdapter(adapter);
    }

    public void vaciarDatos(){
        cedulula.setText("");
        nombre.setText("");
        apellido.setText("");
        fechaN.setText("");
        email.setText("");
        direccion.setText("");
        telefono.setText("");
        usuario.setText("");
        pass.setText("");
    }

    @Override
    public void onClick(View v) {
        SWPersonaVolly sw = new SWPersonaVolly(getContext());
        switch (v.getId()){
            case R.id.btnAgregar:
                String n = nombre.getText().toString();
                String a = apellido.getText().toString();
                String c = cedulula.getText().toString();
                String f = fechaN.getText().toString();
                String e = email.getText().toString();
                String d = direccion.getText().toString();
                String t = telefono.getText().toString();
                String y = edad.getText().toString();
                String un = usuario.getText().toString();
                String ps = pass.getText().toString();
                if(n.length() == 0 || a.length() == 0 || c.length() == 0|| f.length() == 0|| e.length() == 0|| d.length() == 0|| t.length() == 0|| y.length() == 0|| un.length() == 0|| ps.length() == 0){
                    Toast.makeText(getContext(),"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    Persona p = new Persona();
                    p.setNombre(n);
                    p.setApellido(a);
                    p.setCedula(Integer.parseInt(c));
                    p.setFechaNacimiento(f);
                    p.setEmail(e);
                    p.setDireccion(d);
                    p.setTelefono(t);
                    p.setEdad(Integer.parseInt(y));
                    p.setUsuario(un);
                    p.setPassword(ps);
                    sw.insertPerson(p);
                    vaciarDatos();
                    Toast.makeText(getContext(),"Registrado con exito",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnListar:
                cargarLista(sw.findAllPersons());
                break;
            case R.id.btnBuscar:
                String cedula = cedulula.getText().toString();
                if (cedula.length() == 0){
                    Toast.makeText(getContext(),"Por favor llene el campos cedula para Buscar Usuario",Toast.LENGTH_SHORT).show();
                }else {
                    cargarLista(sw.findByID(cedula));
                }
                break;
        }
    }
}
