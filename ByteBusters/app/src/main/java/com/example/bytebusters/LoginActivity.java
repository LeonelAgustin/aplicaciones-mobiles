package com.example.bytebusters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    Intent acceder;
    Intent registrar;
    Button facebook, gmail, instagram;
    EditText usuario,correo,contraseña;
    Button logearse;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    String usuario1 ="", correo1 = "", password = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*NO TOCAR*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**/
        usuario = findViewById(R.id.ingresarmail);
        correo = findViewById(R.id.ingresarmail);
        contraseña = findViewById(R.id.contrasena);
        logearse = findViewById(R.id.iniciarbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("espere porfavor");
        progressDialog.setCanceledOnTouchOutside(false);

        logearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidarDatos();
            }
        });
        //
        TextView correo = (TextView) findViewById(R.id.ingresarmail);
        EditText usuario = (EditText) findViewById(R.id.ingresarusuario);
        TextView contrasena = (TextView) findViewById(R.id.contrasena);




        ImageButton btnface = (ImageButton) findViewById(R.id.facebook);
        ImageButton btngmail = (ImageButton) findViewById(R.id.gmail);
        ImageButton btnig = (ImageButton) findViewById(R.id.instagram);

        // ImageView fondo = (ImageButton) findViewById(R.id.fondo); rompe la aplicacion
        MaterialButton registrarbtn = (MaterialButton) findViewById(R.id.registrarbtn);

        registrarbtn.setOnClickListener(new View.OnClickListener() {//REGISTRARSE
            @Override
            public void onClick(View v) {
                registrar = new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(registrar);
            }
        });

        btnface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent face = new Intent(Intent.ACTION_SEND);
                face.setType("text/plain");
                face.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/");
                face.setPackage("facebook.com");
                startActivity(face);
            }
        });

        btngmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(Intent.ACTION_SEND);
                mail.setType("text/plain");
                mail.putExtra(Intent.EXTRA_TEXT, "https://www.google.com");
                mail.setPackage("com.google.android.gm");
                startActivity(mail);
            }
        });

        btnig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ig = new Intent(Intent.ACTION_SEND);
                ig.setType("text/plain");
                ig.putExtra(Intent.EXTRA_TEXT, "https://www.google.com");
                ig.setPackage("com.instagram.android");
                startActivity(ig);

            }
        });


/*
        iniciarbtn.setOnClickListener(new View.OnClickListener() {//INICIAR SESION
            @Override
            public void onClick(View v) {
                // cuando se hace click en INICIAR SESION

                switch (usuario.getText().toString()){
                    case "administrador":

                        break;
                    case "empleado":
                        if(correo.getText().toString().equals("empleado@correo.com") && contrasena.getText().toString().equals("Employ123")){
                            Toast.makeText( MainActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
                            acceder = new Intent(getApplicationContext(), Empleado.class);
                            String saludar = usuario.getText().toString();
                            acceder.putExtra("nombre",saludar);
                            //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                            startActivity(acceder);
                        }else{
                            Toast.makeText( MainActivity.this,  "ACCESO DENEGADO",Toast.LENGTH_SHORT).show();

                        }
                        break;
                    case "cliente":
                        if(correo.getText().toString().equals("cliente@correo.com") && contrasena.getText().toString().equals("Client123")){
                            Toast.makeText( MainActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
                            acceder = new Intent(getApplicationContext(), Cliente.class);
                            String saludar = usuario.getText().toString();
                            acceder.putExtra("nombre",saludar);
                            //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                            startActivity(acceder);
                        }else{
                            Toast.makeText( MainActivity.this,  "ACCESO DENEGADO",Toast.LENGTH_SHORT).show();

                        }
                        break;
                }
            }
        });

        */

    }


    private void ValidarDatos() {
        usuario1 =usuario.getText().toString();
        correo1 = correo.getText().toString();
        password = contraseña.getText().toString();

        if (!Patterns.EMAIL_ADDRESS.matcher(correo1).matches()){
            Toast.makeText( LoginActivity.this,  "CORREO INVALIDO",Toast.LENGTH_SHORT).show();

        } else if(usuario1.isEmpty() || correo1.isEmpty() || password.isEmpty()){
                Toast.makeText( LoginActivity.this,  "CAMPOS INCOMPLETOS",Toast.LENGTH_SHORT).show();

        }else{
            LoginDeUsuario();
        }

    }

    private void LoginDeUsuario() {
        progressDialog.setMessage("Iniciando sesion...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(correo1,password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            if(user.getEmail().equals("juan@correo.com")){
                                acceder = new Intent(LoginActivity.this,ClienteActivity.class);
                                acceder.putExtra("nombre",usuario1);
                                //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                                startActivity(acceder);

                            } else if (user.getEmail().equals("leonel@correo.com")) {
                                startActivity(new Intent(LoginActivity.this,EmpleadoActivity.class));
                            } else if (user.getEmail().equals("luciano@correo.com")) {
                                
                            }

                            Toast.makeText( LoginActivity.this,  "Bienvenido(a): "+user.getDisplayName(),Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText( LoginActivity.this,  "Credenciales incorrectas",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText( LoginActivity.this,  " "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
