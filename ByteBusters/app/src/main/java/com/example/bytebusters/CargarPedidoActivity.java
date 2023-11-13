package com.example.bytebusters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class CargarPedidoActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    EditText descripcion,cliente,vendedor;
    Button btnEnviar;
    ProgressDialog progressDialog;
    Intent volver;
String vendedor1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_pedido);


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Date fechaActual = new Date();

        String fechaFormateada = sdf.format(fechaActual);

        firebaseAuth = FirebaseAuth.getInstance();

        vendedor = findViewById(R.id.vendedor);
        cliente = findViewById(R.id.cliente);
        descripcion = findViewById(R.id.descripcion);
        btnEnviar = findViewById(R.id.btnEnviar);

        Button atras = (Button) findViewById(R.id.volver);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volver = new Intent(getApplicationContext(),VendedorActivity.class);
                startActivity(volver);
            }
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Vendedor = vendedor.getText().toString().trim();
                String Cliente= cliente.getText().toString().trim();
                String Descripcion = descripcion.getText().toString().trim();


                if (Vendedor.isEmpty() && Cliente.isEmpty() && Descripcion.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Campos obligatorios", Toast.LENGTH_SHORT).show();
                }
                else {
                    agregarPedido(Vendedor,Cliente,Descripcion,fechaFormateada);
                    Toast.makeText(CargarPedidoActivity.this, "Pedido enviado", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void agregarPedido(String vendedor, String cliente, String descripcion, String fechaFormateada) {
        String uid = firebaseAuth.getUid();
        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid",uid);

        Datos.put("Clientes",cliente);
        Datos.put("Descripcion",descripcion);
        Datos.put("vendedor",vendedor);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("pedidos");

        databaseReference.child(fechaFormateada)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),VendedorActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText( CargarPedidoActivity.this,  ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}