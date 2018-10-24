package inacaptemuco.com.inacapone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

     //1.- Creamos objetos de componentes a utilizar en este activity. Luego los vinculamos
    //Con la definición en el archivo de layout.
    EditText edtEdad;
    EditText edtNombre;
    TextView txvResultado;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //2.- Asociamos los objetos de la clase con los elementos de la interfaz
        edtEdad         = (EditText) findViewById(R.id.edt_edad);
        edtNombre       = (EditText) findViewById(R.id.edt_nombre);
        txvResultado    = (TextView) findViewById(R.id.txv_resultado);
        btnCalcular     = (Button) findViewById(R.id.btn_calcular);
        //3.- Habilitamos la posibilidad de que un elemento de la interfaz ejecute una acción en el evento click (tap)
        btnCalcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //4.- Capturamos el valor ingresado en edtEdad
        int edadIngresada   = Integer.parseInt(edtEdad.getText().toString());
        String nombreIngresado = edtNombre.getText().toString();
        /*int pesoIdeal       = edadIngresada * 2 + 8 ;
        txvResultado.setText("El peso ideal es "+pesoIdeal);*/
        //5. Establecemos un intento para abrir un nuevo activity (ResultadoActivity)
        Intent intento  = new Intent(MainActivity.this,ResultadoActivity.class);
        //6. Especificamos los datos que queremos enviar al activity ResultadoActivity.
        intento.putExtra("p_edad",edadIngresada);
        intento.putExtra("p_nombre", nombreIngresado);
        //7. Ejecutamos el activity con startActivity especificando cual es el objeto
        // de la clase Intent que define el destino y los datos a transportar.
        startActivity(intento);

    }
}
