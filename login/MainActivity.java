package com.example.logintp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    Intent acceder;
    Intent registrar;
    Button facebook, gmail, instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*NO TOCAR*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**/

        TextView correo = (TextView) findViewById(R.id.ingresarmail);
        EditText usuario = (EditText) findViewById(R.id.ingresarusuario);
        TextView contrasena = (TextView) findViewById(R.id.contrasena);

        MaterialButton iniciarbtn = (MaterialButton) findViewById(R.id.iniciarbtn);
        MaterialButton registrarbtn = (MaterialButton) findViewById(R.id.registrarbtn);

        ImageButton btnface = (ImageButton) findViewById(R.id.facebook);
        ImageButton btngmail = (ImageButton) findViewById(R.id.gmail);
        ImageButton btnig = (ImageButton) findViewById(R.id.instagram);

       // ImageView fondo = (ImageButton) findViewById(R.id.fondo); rompe la aplicacion


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
        iniciarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usuario.getText().toString().equals("administrador") && correo.getText().toString().equals("administrador@correo.com") && contrasena.getText().toString().equals("Admin123")){
                    Toast.makeText( MainActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
                    acceder = new Intent(getApplicationContext(), Aministrador.class);
                    String saludar = usuario.getText().toString();
                    acceder.putExtra("nombre",saludar);
                    //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                    startActivity(acceder);
                }else if (usuario.getText().toString().equals("empleado") && correo.getText().toString().equals("empleado@correo.com") && contrasena.getText().toString().equals("Employ123")) {
                    Toast.makeText( MainActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
                    acceder = new Intent(getApplicationContext(), Empleado.class);
                    String saludar = usuario.getText().toString();
                    acceder.putExtra("nombre",saludar);
                    //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                    startActivity(acceder);
                } else if(usuario.getText().toString().equals("cliente") && correo.getText().toString().equals("cliente@correo.com") && contrasena.getText().toString().equals("Client123")){
                    Toast.makeText( MainActivity.this,  "ACCESO CONCEDIDO",Toast.LENGTH_SHORT).show();
                    acceder = new Intent(getApplicationContext(), Cliente.class);
                    String saludar = usuario.getText().toString();
                    acceder.putExtra("nombre",saludar);
                    //debe ser el mismo intent para iniciar la siguiente pantalla y para pasar el string
                    startActivity(acceder);
                } else{
                    Toast.makeText( MainActivity.this,  "ACCESO DENEGADO",Toast.LENGTH_SHORT).show();
                }
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

        registrarbtn.setOnClickListener(new View.OnClickListener() {//REGISTRARSE
            @Override
            public void onClick(View v) {
                registrar = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(registrar);
            }
        });

        
    }
}