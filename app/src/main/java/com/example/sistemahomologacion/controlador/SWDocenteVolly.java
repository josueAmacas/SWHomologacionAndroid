package com.example.sistemahomologacion.controlador;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemahomologacion.modelo.Docente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SWDocenteVolly {

    //definimos url del servicio web
    //String host = "http://192.168.1.16:8089";
    String host = "http://10.20.4.149:8090";
    String get = "/personas/listaDocentes";
    String getById = "/personas/getDocente/";

    Context contexto;
        final private List<Docente> listaDocentesV;

    public SWDocenteVolly(Context contexto) {
        this.contexto = contexto;
        this.listaDocentesV = new ArrayList<>();
    }

    public List<Docente> findAllDocentes() {
        String path = host.concat(get);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Devuelve un json
                try {
                    JSONArray jsonDocentes = response.getJSONArray("data");
                    for (int i = 0; i < jsonDocentes.length(); i++) {
                        JSONObject jsonObject = jsonDocentes.getJSONObject(i);
                        Docente Docente = new Docente();
                        Docente.setIdD(jsonObject.getInt("docente_id"));
                        Docente.setCedulaD(jsonObject.getInt("cedula"));
                        Docente.setNombreD(jsonObject.getString("first_name"));
                        Docente.setApellidoD(jsonObject.getString("last_name"));
                        Docente.setEmailD(jsonObject.getString("email"));
                        Docente.setTituloD(jsonObject.getString("titulo"));
                        Docente.setCarreraD("Ingenieria en Sistemas");
                        listaDocentesV.add(Docente);
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
        DocenteSingletonVolly.getInstance(contexto).addToRequestQueue(jsonObjectRequest);
        return listaDocentesV;
    }

    public List<Docente> findByID(String id){
        String path = host.concat(getById) + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonDocentes = response.getJSONArray("data");
                    for (int i = 0; i < jsonDocentes.length(); i++) {
                        JSONObject jsonObject = jsonDocentes.getJSONObject(i);
                        Docente Docente = new Docente();
                        Docente.setIdD(jsonObject.getInt("docente_id"));
                        Docente.setCedulaD(jsonObject.getInt("cedula"));
                        Docente.setNombreD(jsonObject.getString("first_name"));
                        Docente.setApellidoD(jsonObject.getString("last_name"));
                        Docente.setEmailD(jsonObject.getString("email"));
                        Docente.setTituloD(jsonObject.getString("titulo"));
                        Docente.setCarreraD("Sistemas");
                        listaDocentesV.add(Docente);
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
        DocenteSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaDocentesV;
    }

}
