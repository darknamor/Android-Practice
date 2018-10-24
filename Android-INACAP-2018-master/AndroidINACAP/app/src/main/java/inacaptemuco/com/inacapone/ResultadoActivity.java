package inacaptemuco.com.inacapone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {
   //1.- Creamos un objeto de la clase TextView
    TextView txvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        //2. Asociar al objeto el componente del layout
        txvResultado            = (TextView) findViewById(R.id.txv_resultado);
        //3. Capturamos el objeto de la clase Intent que se envía desde el MainActivity
        Intent intento          = getIntent();
        //4.- Obtenemos los datos enviados en el activity
        Bundle datosRecibidos   = intento.getExtras();
        //5. Capturamos uno de los datos enviados desde el Activity mediante el intento.
        int edadRecibida        = datosRecibidos.getInt("p_edad");
        String nombreRecibido = datosRecibidos.getString("p_nombre");
        //6. Modificamos el TextView de Resultado con lo que corresponda
        int pesoIdeal           = edadRecibida * 2 +8;
        txvResultado.setText("Peso ideal de "+nombreRecibido+" es: "+ pesoIdeal);
    }
}
