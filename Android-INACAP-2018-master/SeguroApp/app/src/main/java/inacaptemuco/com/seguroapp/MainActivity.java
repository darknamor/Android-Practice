package inacaptemuco.com.seguroapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

import operaciones.Seguro;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtAnoVehiculo;
    ImageButton imbCalcular;
    TextView txvResultado;
    Double valorUfApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtAnoVehiculo = (EditText) findViewById(R.id.edt_ano_vehiculo);
        imbCalcular = (ImageButton) findViewById(R.id.imb_calcular);
        txvResultado = (TextView) findViewById(R.id.txv_resultado);

        consultarDatos();


        imbCalcular.setOnClickListener(this);

        }

        @Override
        public void onClick(View v){

        int anoIngresado = Integer.parseInt(edtAnoVehiculo.getText().toString());


        Seguro seguro = new Seguro();
        seguro.setAnoVehiculo(anoIngresado);
        seguro.setValorUf(valorUfApi);


        double valorSeguro = seguro.calcularValorSeguro();

        txvResultado.setText("Valor seguro: " + valorSeguro);


        }

        public void consultarDatos(){

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://mindicador.cl/api/uf/17-10-2018";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //Creamos un objeto de la clase JSONObject para capturar la respuesta
                            JSONObject respuestaJson    = new JSONObject(response);
                            //Creamos objeto de la clase JSONArray  para manejar los datos como un objeto recorrible
                            JSONArray arregloJson       = respuestaJson.getJSONArray("serie");
                            //Creamos otro objeto de la clase JSONOject para llegar al valor de la UF
                            JSONObject contenidoJson    = arregloJson.getJSONObject(0);
                            //Obtenemos de la fila e particular, el valor que necesitamos
                            String valorUF              = contenidoJson.getString("valor");
                            //Efectuamos una conversión del dato
                            valorUfApi           = Double.parseDouble(valorUF);
                            // Display the first 500 characters of the response string.
                            // mTextView.setText("Response is: "+ response.substring(0,500));

                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "Valor Uf obtenido es: " + valorUF, Toast.LENGTH_SHORT);
                            toast.show();
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

