package com.example.bytebusters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegistroActivity extends AppCompatActivity {
    Intent volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        TextView fecha = (TextView) findViewById(R.id.ingresarfecha);
        TextView nombre = (TextView) findViewById(R.id.ingresarnombre);
        TextView correo = (TextView) findViewById(R.id.ingresaremail);
        TextView contrasena = (TextView) findViewById(R.id.ingresarcontra);

        MaterialButton registrarse = (MaterialButton) findViewById(R.id.registrarbtn);
        MaterialButton volverbtn = (MaterialButton) findViewById(R.id.volverbtn);

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
                    //YA ESTAS REGSITRADO
                }

            }
        });
    }
}