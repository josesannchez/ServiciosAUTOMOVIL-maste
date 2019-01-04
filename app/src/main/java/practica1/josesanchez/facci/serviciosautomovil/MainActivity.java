package practica1.josesanchez.facci.serviciosautomovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button button4Siguiente;
Button datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button4Siguiente = (Button) findViewById(R.id.button4Siguiente);
        button4Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se genera la navegabilidad entre la actividad principal y la actividad de login
                 Intent intent = new Intent(MainActivity.this, servicios.class);
                startActivity(intent);
        } });
        datos = (Button) findViewById(R.id.button2Registrar);
        datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // se genera la navegabilidad entre la actividad principal y la actividad de login
                Intent intent = new Intent(MainActivity.this, Datos.class);
                startActivity(intent);
            } });
    }
}
