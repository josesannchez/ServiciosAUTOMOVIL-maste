package practica1.josesanchez.facci.serviciosautomovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Datos extends AppCompatActivity {

    EditText txtemail, txtpassword;
    Button registrar, ingresar;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;


        @Override
        protected void onCreate(Bundle savedInstanceState) {   //AQUI CREO UN PROTECT VOID ONCREATE QUE HACE GUARDAR Y ENVIAR LOS DATOS DEL ACTIVITY
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_datos);

            txtemail = (EditText) findViewById(R.id.et_usuario);
            txtpassword = (EditText) findViewById(R.id.et_password);
            registrar = (Button)findViewById(R.id.btnRegistrar);
            ingresar = (Button) findViewById(R.id.btnIngresar);
            mAuth = FirebaseAuth.getInstance();

            listener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null){
                        miCuenta();
                    }else{
                        Toast.makeText(getApplicationContext(), "Inicia sesión", Toast.LENGTH_SHORT).show();
                    }
                }
            };

        }

    private void miCuenta() {
        Intent abrirCuenta = new Intent(this, servicios.class);
        startActivity(abrirCuenta);
        finish();
    }

    public void Ingresar(View view){
        String email = txtemail.getText().toString();
        String password = txtpassword.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Iniciando sesión", Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        miCuenta();
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrate (View view){
        Intent registrate = new Intent(getApplicationContext(), Registro.class);
        startActivity(registrate);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(listener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
