package com.example.sistemahomologacion.controlador;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemahomologacion.modelo.Persona;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SWPersonaVolly {

    //definimos url del servicio web
    //String host = "http://192.168.1.16:8089";
    String host = "http://10.20.4.149:8090";
    String insert = "/personas/crearPersona";
    String get = "/personas/listaPersonas";
    String getById = "/personas/getPersona/";
    String auntenticar = "/usuarios/ingresar";

    Context contexto;
    boolean estado;

    final private List<Persona> listaPersonasV;

    public SWPersonaVolly(Context contexto) {
        this.contexto = contexto;
        this.listaPersonasV = new ArrayList<>();
    }

    public List<Persona> findAllPersons() {
        String path = host.concat(get);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Devuelve un json
                try {
                    JSONArray jsonPersonas = response.getJSONArray("data");
                    for (int i = 0; i < jsonPersonas.length(); i++) {
                        JSONObject jsonObject = jsonPersonas.getJSONObject(i);
                        Persona Persona = new Persona();
                        Persona.setCedula(jsonObject.getInt("cedula"));
                        Persona.setNombre(jsonObject.getString("first_name"));
                        Persona.setApellido(jsonObject.getString("last_name"));
                        Persona.setEmail(jsonObject.getString("email"));
                        Persona.setFechaNacimiento(jsonObject.getString("fechaNacimiento"));
                        Persona.setEdad(jsonObject.getInt("edad"));
                        Persona.setTelefono(jsonObject.getString("telefono"));
                        Persona.setDireccion(jsonObject.getString("direccion"));
                        listaPersonasV.add(Persona);
                    }

                } catch (JSONException e) {
                    Log.e("ERROR", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Message", "error al cargar todos");
                Log.e("Message", error.toString());
            }
        });
        PersonaSingletonVolly.getInstance(contexto).addToRequestQueue(jsonObjectRequest);
        return listaPersonasV;
    }

    public List<Persona> findByID(String id){
        String path = host.concat(getById) + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonPersonas = response.getJSONArray("data");
                    for (int i = 0; i < jsonPersonas.length(); i++) {
                        JSONObject jsonObject = jsonPersonas.getJSONObject(i);
                        Persona Persona = new Persona();
                        Persona.setCedula(jsonObject.getInt("cedula"));
                        Persona.setNombre(jsonObject.getString("first_name"));
                        Persona.setApellido(jsonObject.getString("last_name"));
                        Persona.setEmail(jsonObject.getString("email"));
                        Persona.setFechaNacimiento(jsonObject.getString("fechaNacimiento"));
                        Persona.setEdad(jsonObject.getInt("edad"));
                        Persona.setTelefono(jsonObject.getString("telefono"));
                        Persona.setDireccion(jsonObject.getString("direccion"));
                        listaPersonasV.add(Persona);
                    }
                } catch (JSONException e) {
                    Log.e("ERROR", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Messagge", "Error en listar By ID");
            }
        });
        PersonaSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaPersonasV;
    }

    public boolean insertPerson(Persona Persona){
        String path = host.concat(insert);
        JSONObject json = new JSONObject();
        try {
            json.put("first_name", Persona.getNombre());
            json.put("last_name", Persona.getApellido());
            json.put("cedula", Persona.getCedula());
            json.put("fechaNacimiento", Persona.getFechaNacimiento());
            json.put("email", Persona.getEmail());
            json.put("direccion", Persona.getDireccion());
            json.put("telefono", Persona.getTelefono());
            json.put("edad", Persona.getEdad());
            json.put("telefono", Persona.getTelefono());
            json.put("username", Persona.getUsuario());
            json.put("password", Persona.getPassword());
        }catch (Exception ex){
            ex.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path,json ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        PersonaSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }

    public boolean login(String email,String pass){
        String path = host.concat(auntenticar);
        JSONObject json = new JSONObject();
        try {
            json.put("email", email);
            json.put("password", pass);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, path,json ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                estado = true;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                estado = false;
            }
        });
        PersonaSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return estado;
    }
}
