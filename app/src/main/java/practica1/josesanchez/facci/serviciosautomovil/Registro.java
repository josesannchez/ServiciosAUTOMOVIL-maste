package practica1.josesanchez.facci.serviciosautomovil;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import practica1.josesanchez.facci.serviciosautomovil.Objetos.Referencia;
import practica1.josesanchez.facci.serviciosautomovil.Objetos.Usuario;

public class Registro extends AppCompatActivity {

    Button Registrar, Login;
    EditText txtNombre, txtApellidos, txtEmail, txtPasswordRegistro, txtPasswordVerificacion;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPasswordRegistro = (EditText) findViewById(R.id.txtPasswordRegistro);
        txtPasswordVerificacion = (EditText) findViewById(R.id.txtPasswordVerificacion);
        Registrar = (Button) findViewById(R.id.btnRegistrar);
        Login = (Button) findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase evenTime = FirebaseDatabase.getInstance();
        final DatabaseReference reference = evenTime.getReference(Referencia.REFERENCIA_PRINCIPAL);
        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre = txtNombre.getText().toString();
                final String apellidos = txtApellidos.getText().toString();
                String passwordRegistro = txtPasswordRegistro.getText().toString();
                String passwordVerificacion =  txtPasswordVerificacion.getText().toString();
                final String email = txtEmail.getText().toString();



                if(!nombre.isEmpty() &&
                        !apellidos.isEmpty() &&
                        !email.isEmpty() &&
                        !passwordRegistro.isEmpty() &&
                        !passwordVerificacion.isEmpty()){
                    if (!passwordRegistro.equals(passwordVerificacion)){
                        Toast.makeText(getApplicationContext(), "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        txtPasswordVerificacion.setText("");
                    } else {
                        mAuth.createUserWithEmailAndPassword(email,passwordVerificacion).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Usuario usuario = new Usuario(nombre ,apellidos, email);
                                    reference.child(Referencia.REFERENCIA_USUARIO).push().setValue(usuario);
                                    Toast.makeText(getApplicationContext(), "Se ha registrado con éxito", Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(Registro.this , MainActivity.class);
                                    startActivity(login);
                                    finish();
                                }else {
                                    Toast.makeText(getApplicationContext(), "No ha podido registrarse", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void Login(View v){
        Intent login = new Intent(this, Datos.class);
        startActivity(login);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

