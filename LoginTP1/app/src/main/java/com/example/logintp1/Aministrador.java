package com.example.logintp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Aministrador extends AppCompatActivity {

    Intent volver;
    String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent recibir = getIntent();
        titulo = recibir.getStringExtra("nombre");
        TextView titulousuario = findViewById(R.id.usuario);
        titulousuario.setText(titulo);

        ImageButton cerrarbtn = (ImageButton) findViewById(R.id.cerrarbtn);

        cerrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(volver);
            }
        });
    }
}