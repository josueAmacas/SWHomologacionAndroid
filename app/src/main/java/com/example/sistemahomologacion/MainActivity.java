package com.example.sistemahomologacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistemahomologacion.controlador.SWPersonaVolly;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email,pass;
    Button botonIngresar;

    //String host = "http://192.168.1.16:8089";
    String host = "http://10.20.4.149:8090";
    String auntenticar = "/usuarios/ingresar";
    SWHilo sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarComponentes();
    }

    private void cargarComponentes(){
        email = findViewById(R.id.txtEmailLogin);
        pass = findViewById(R.id.txtPassword);

        botonIngresar = findViewById(R.id.btnIngresar);
        botonIngresar.setOnClickListener(this);
    }

    public class SWHilo extends AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... parametros) {
            String consulta ="";
            URL url = null;
            String ruta = parametros[0];

            if(parametros[1].equals("1")){
                try {
                    url = new URL(ruta);
                    HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                    conexion.setDoInput(true);
                    conexion.setDoOutput(true);
                    conexion.setUseCaches(false);
                    conexion.setRequestProperty("Content-Type", "application/json");
                    conexion.setRequestProperty("Accept", "application/json");
                    conexion.connect();

                    //se crea el json
                    JSONObject json = new JSONObject();
                    json.put("email", parametros[2]);
                    json.put("password", parametros[3]);

                    // Envio los parámetros post.
                    OutputStream os = conexion.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(json.toString());
                    writer.flush();
                    writer.close();

                    int codigoRespuesta = conexion.getResponseCode();
                    if (codigoRespuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(conexion.getInputStream());
                        BufferedReader lector = new BufferedReader(new InputStreamReader(in));
                        consulta += lector.readLine();
                        lector.close();
                    }else {

                        Log.e("mensaje","no hay coneccion");
                    }
                    conexion.disconnect();
                }catch (Exception ex){
                    Log.e("mensaje","Error Ingresar");
                }
            }
            return consulta;
        }
    }

    @Override
    public void onClick(View v) {
       sw = new SWHilo();
        String e = email.getText().toString();
        String p = pass.getText().toString();

        if(e.length() == 0 || p.length() == 0){
            Toast.makeText(this,"Por favor llene todos los campos",Toast.LENGTH_SHORT).show();
        }else {
            try {
                String c = sw.execute(host.concat(auntenticar),"1",e,p).get();
                if(c.length() > 65){
                    Toast.makeText(this," Usuario Correcto ",Toast.LENGTH_SHORT).show();
                    email.setText("");
                    pass.setText("");
                    Intent intent = new Intent(MainActivity.this, ActivityHomologacion.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(this," Usuario y/o contrasenia incorrectos ",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
