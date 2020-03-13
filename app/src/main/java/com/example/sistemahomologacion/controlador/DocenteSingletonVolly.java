package com.example.sistemahomologacion.controlador;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class DocenteSingletonVolly {

    private RequestQueue queue;
    private Context context;
    private static DocenteSingletonVolly miInstancia;

    public DocenteSingletonVolly(Context context){
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

    public static synchronized DocenteSingletonVolly getInstance(Context context){
        if(miInstancia == null){
            miInstancia = new DocenteSingletonVolly(context);
        }
        return miInstancia;
    }

    public <T> void addToRequestQueue (Request request){
        queue.add(request);
    }
}
