package com.example.darkn.ejercicio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtDolar;
    TextView txvResultado;
    Button btnCalcular;
    Double valorDolar;
    private Double total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtDolar = (EditText) findViewById(R.id.edt_dolar);
        txvResultado    = (TextView) findViewById(R.id.txv_resultado);
        btnCalcular     = (Button) findViewById(R.id.btn_calcular);

        btnCalcular.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String valorSTR =edtDolar.getText().toString();
        int valorUSD;
        //Log.e("","aca"+valorClp);
        switch (v.getId()){
            case R.id.btn_calcular:
                if(!valorSTR.equals("")) {
                    valorUSD =Integer.parseInt(edtDolar.getText().toString());
                    calcularCLP(valorUSD);
                }
                else{
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Ingrese la cantidad de dolares que desea convertir ", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
        }
    }

    private void calcularCLP(final int x) {
        RequestQueue queue = Volley.newRequestQueue(this);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        //Log.e("","FECHA = "+fecha);
        String url ="https://mindicador.cl/api/dolar/"+fecha;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respuestaJson    = new JSONObject(response);
                            JSONArray arregloJson       = respuestaJson.getJSONArray("serie");
                            JSONObject contenidoJson    = arregloJson.getJSONObject(0);
                            String valorUF              = contenidoJson.getString("valor");
                            valorDolar = Double.parseDouble(valorUF);
                            total=valorDolar*x;
                            edtDolar.setText("");
                            txvResultado.setText("Reultado en CLP: "+String.format("%,.2f", total));
                            Log.e("","caca"+valorDolar);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txvResultado.setText("No hay respuesta!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
