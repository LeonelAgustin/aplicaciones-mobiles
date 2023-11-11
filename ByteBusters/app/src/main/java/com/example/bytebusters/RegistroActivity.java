package com.example.bytebusters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
public class RegistroActivity extends AppCompatActivity {
    Intent volver;
    EditText NombreET,CorreoET,ContraseñaET;
    Button RegistrarUsuario;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    String nombre1 ="",correo1 ="",password ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NombreET = findViewById(R.id.ingresarnombre);
        CorreoET = findViewById(R.id.ingresaremail);
        ContraseñaET = findViewById(R.id.ingresarcontra);
        RegistrarUsuario = findViewById(R.id.registrarbtn);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegistroActivity.this);
        progressDialog.setTitle("espere por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.isEmpty() || nombre1.isEmpty() ||correo1.isEmpty()  ){
                    Toast.makeText( RegistroActivity.this,  "CAMPOS INCOMPLETOS",Toast.LENGTH_SHORT).show();

                }else if(password.length()<8 || password.length()>10){
                    Toast.makeText( RegistroActivity.this,  "CONTRASEÑA INVALIDA",Toast.LENGTH_SHORT).show();
                }else{
                    ValidarDatos();
                }

            }
        });


//NO SACAR LAS VARIABLES DE onCreate
        TextView fecha = (TextView) findViewById(R.id.ingresarfecha);
        TextView nombre = (TextView) findViewById(R.id.ingresarnombre);
        TextView correo = (TextView) findViewById(R.id.ingresaremail);
        TextView contrasena = (TextView) findViewById(R.id.ingresarcontra);
        MaterialButton registrarse = (MaterialButton) findViewById(R.id.registrarbtn);
        MaterialButton volverbtn = (MaterialButton) findViewById(R.id.volverbtn);
//
        volverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(volver);
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contrasena.getText().toString().isEmpty() || nombre.getText().toString().isEmpty() ||correo.getText().toString().isEmpty() || fecha.getText().toString().isEmpty() ){
                    Toast.makeText( RegistroActivity.this,  "CAMPOS INCOMPLETOS",Toast.LENGTH_SHORT).show();

                }else if(contrasena.getText().toString().length()<8 || contrasena.getText().toString().length()>10){
                    Toast.makeText( RegistroActivity.this,  "CONTRASEÑA INVALIDA",Toast.LENGTH_SHORT).show();
                }else{
                    ValidarDatos();
                }

            }
        });
    }
    private void ValidarDatos(){
        nombre1 = NombreET.getText().toString();
        correo1 = CorreoET.getText().toString();
        password = ContraseñaET.getText().toString();

        if(password.isEmpty() || nombre1.isEmpty() ||correo1.isEmpty()){
            Toast.makeText( RegistroActivity.this,  "CAMPOS INCOMPLETOS",Toast.LENGTH_SHORT).show();

        }else if(password.length()<8 || password.length()>10){
            Toast.makeText( RegistroActivity.this,  "CONTRASEÑA INVALIDA",Toast.LENGTH_SHORT).show();
        }else{
            CrearCuenta();
            Toast.makeText( RegistroActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
        }
}

    private void CrearCuenta() {
        progressDialog.setMessage("creando su cuenta...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(correo1,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        GuardarInformacion();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText( RegistroActivity.this,  ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void GuardarInformacion() {
        progressDialog.setTitle("guardando su informacion");
        progressDialog.dismiss();
        String uid = firebaseAuth.getUid();
        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid",uid);
        Datos.put("correo",correo1);
        Datos.put("nombres",nombre1);
        Datos.put("password",password);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("usuarios");
        databaseReference.child(uid)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText( RegistroActivity.this,  "cuenta creada con exito",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroActivity.this,ClienteActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText( RegistroActivity.this,  ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
/*
public class RegistroActivity extends AppCompatActivity {
    Intent volver;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);





    }


    }

    private void CrearCuenta() {
        
    }

    private void GuardarInformacion() {

    }

    */
