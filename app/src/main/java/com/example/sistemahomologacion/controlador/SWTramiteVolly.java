package com.example.sistemahomologacion.controlador;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sistemahomologacion.modelo.Tramite;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SWTramiteVolly {

    //definimos url del servicio web
    //String host = "http://192.168.1.16:8080";
    String host = "http://10.20.4.149:8090";
    String get = "/personas/listarTramites";
    String getByCed = "/personas/listarTramitesEstudiante/";
    String getById = "/personas/getTramite/";


    Context contexto;
    boolean estado;

    final private List<Tramite> listaTramitesV;

    public SWTramiteVolly(Context contexto) {
        this.contexto = contexto;
        this.listaTramitesV = new ArrayList<>();
    }

    public List<Tramite> findAllTramite() {
        String path = host.concat(get);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Devuelve un json
                try {
                    JSONArray jsonTramites = response.getJSONArray("data");
                    for (int i = 0; i < jsonTramites.length(); i++) {
                        JSONObject jsonObject = jsonTramites.getJSONObject(i);
                        Tramite Tramite = new Tramite();
                        Tramite.setIdTramite(Integer.parseInt(jsonObject.getString("tramite_id")));
                        Tramite.setRegistro(jsonObject.getString("registro"));
                        Tramite.setTipo(jsonObject.getString("tipo"));
                        int status = Integer.parseInt(jsonObject.getString("estado"));
                        if(status == 1){
                            Tramite.setEstado("Aceptado");
                        }else {
                            Tramite.setEstado("En Espera");
                        }
                        Tramite.setCedulaT(jsonObject.getInt("cedula"));
                        Tramite.setNombreT(jsonObject.getString("first_name"));
                        Tramite.setApellidoT(jsonObject.getString("last_name"));
                        Tramite.setEmailT(jsonObject.getString("email"));
                        listaTramitesV.add(Tramite);
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
        TramiteSingletonVolly.getInstance(contexto).addToRequestQueue(jsonObjectRequest);
        return listaTramitesV;
    }

    public List<Tramite> findByID(String id){
        String path = host.concat(getById) + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonTramites = response.getJSONArray("data");
                    for (int i = 0; i < jsonTramites.length(); i++) {
                        JSONObject jsonObject = jsonTramites.getJSONObject(i);
                        Tramite Tramite = new Tramite();
                        Tramite.setIdTramite(Integer.parseInt(jsonObject.getString("tramite_id")));
                        Tramite.setRegistro(jsonObject.getString("registro"));
                        Tramite.setTipo(jsonObject.getString("tipo"));
                        int status = Integer.parseInt(jsonObject.getString("estado"));
                        if(status == 1){
                            Tramite.setEstado("Aceptado");
                        }else {
                            Tramite.setEstado("En Espera");
                        }
                        Tramite.setCedulaT(jsonObject.getInt("cedula"));
                        Tramite.setNombreT(jsonObject.getString("first_name"));
                        Tramite.setApellidoT(jsonObject.getString("last_name"));
                        Tramite.setEmailT(jsonObject.getString("email"));
                        listaTramitesV.add(Tramite);
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
        TramiteSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaTramitesV;
    }

    public List<Tramite> findByCed(String id){
        String path = host.concat(getByCed) + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, path,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonTramites = response.getJSONArray("data");
                    for (int i = 0; i < jsonTramites.length(); i++) {
                        JSONObject jsonObject = jsonTramites.getJSONObject(i);
                        Tramite Tramite = new Tramite();
                        Tramite.setIdTramite(Integer.parseInt(jsonObject.getString("tramite_id")));
                        Tramite.setRegistro(jsonObject.getString("registro"));
                        Tramite.setTipo(jsonObject.getString("tipo"));
                        int status = Integer.parseInt(jsonObject.getString("estado"));
                        if(status == 1){
                            Tramite.setEstado("Aceptado");
                        }else {
                            Tramite.setEstado("En Espera");
                        }
                        Tramite.setCedulaT(jsonObject.getInt("cedula"));
                        Tramite.setNombreT(jsonObject.getString("first_name"));
                        Tramite.setApellidoT(jsonObject.getString("last_name"));
                        Tramite.setEmailT(jsonObject.getString("email"));
                        listaTramitesV.add(Tramite);
                    }
                } catch (JSONException e) {
                    Log.e("ERROR", e.getMessage());
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Messagge", "Error en listar By Cedula");
            }
        });
        TramiteSingletonVolly.getInstance(contexto).addToRequestQueue(request);
        return listaTramitesV;
    }

}
