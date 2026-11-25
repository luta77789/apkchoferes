
package com.example.inventariogaschofer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InventarioActivity extends AppCompatActivity {

    TextView stockLlenos, stockVacios;
    Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        stockLlenos = findViewById(R.id.stockLlenos);
        stockVacios = findViewById(R.id.stockVacios);
        btnActualizar = findViewById(R.id.btnActualizar);

        btnActualizar.setOnClickListener(v -> mostrarInventario());

        mostrarInventario();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarInventario();
    }

    private void mostrarInventario() {

        android.content.SharedPreferences datos =
                getSharedPreferences("InventarioChofer", MODE_PRIVATE);

        int lleno5 = datos.getInt("llenos5", 0);
        int lleno11 = datos.getInt("llenos11", 0);
        int lleno15 = datos.getInt("llenos15", 0);
        int lleno45 = datos.getInt("llenos45", 0);

        int vacio5 = datos.getInt("vacios5", 0);
        int vacio11 = datos.getInt("vacios11", 0);
        int vacio15 = datos.getInt("vacios15", 0);
        int vacio45 = datos.getInt("vacios45", 0);

        stockLlenos.setText(
                "5 kg: " + lleno5 +
                        "\n11 kg: " + lleno11 +
                        "\n15 kg: " + lleno15 +
                        "\n45 kg: " + lleno45
        );

        stockVacios.setText(
                "5 kg: " + vacio5 +
                        "\n11 kg: " + vacio11 +
                        "\n15 kg: " + vacio15 +
                        "\n45 kg: " + vacio45
        );
    }
}
