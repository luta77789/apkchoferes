
package com.example.inventariogaschofer;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCargar = findViewById(R.id.btnCargar);
        Button btnDescargar = findViewById(R.id.btnDescargar);
        Button btnVentas = findViewById(R.id.btnVentas);
        Button btnInventario = findViewById(R.id.btnInventario);

        // Cargar cilindros llenos
        btnCargar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    CargarActivity.class);
            startActivity(intent);
        });

        // Descargar cilindros vacíos
        btnDescargar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    DescargarActivity.class);
            startActivity(intent);
        });

        // Registrar ventas
        btnVentas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    VentasActivity.class);
            startActivity(intent);
        });

        // Consultar inventario
        btnInventario.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    InventarioActivity.class);
            startActivity(intent);
        });
    }
}
