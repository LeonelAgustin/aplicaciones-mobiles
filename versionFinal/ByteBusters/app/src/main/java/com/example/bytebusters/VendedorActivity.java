package com.example.bytebusters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VendedorActivity extends AppCompatActivity {
    Intent volver;
    String titulo;
    ImageButton CerrarSesion;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendedor);

//NOMBRE DE USUARIO
        Intent recibir = getIntent();
        titulo = recibir.getStringExtra("nombre");
        TextView titulousuario = findViewById(R.id.usuario);
        titulousuario.setText(titulo);

//CREAR PEDIDO
        ImageButton crearPedido = (ImageButton) findViewById(R.id.opcion3);
        crearPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendedorActivity.this, CargarPedidoActivity.class));
            }
        });
//CERRAR SESION
        ImageButton cerrarbtn = (ImageButton) findViewById(R.id.cerrarbtn);

        cerrarbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(volver);
            }
        });

        CerrarSesion = (ImageButton) findViewById(R.id.cerrarbtn);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        CerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalirAplicacion();

            }
        });
    }
    private void SalirAplicacion() {
        firebaseAuth.signOut();
        startActivity(new Intent(VendedorActivity.this, LoginActivity.class));
        Toast.makeText( VendedorActivity.this, "Cerraste sesion",Toast.LENGTH_SHORT).show();
    }
}