package com.example.sistemahomologacion.controlador;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class TramiteSingletonVolly {

    private RequestQueue queue;
    private Context context;
    private static TramiteSingletonVolly miInstancia;

    public TramiteSingletonVolly(Context context){
        this.context = context;
        //get RequestQueue
        queue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public static synchronized TramiteSingletonVolly getInstance(Context context){
        if(miInstancia == null){
            miInstancia = new TramiteSingletonVolly(context);
        }
        return miInstancia;
    }

    public <T> void addToRequestQueue (Request request){
        queue.add(request);
    }
}
