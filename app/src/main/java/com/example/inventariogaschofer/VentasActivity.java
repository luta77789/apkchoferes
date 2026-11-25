
package com.example.inventariogaschofer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VentasActivity extends AppCompatActivity {

    EditText venta5, venta11, venta15, venta45;
    Button btnGuardarVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        venta5 = findViewById(R.id.venta5);
        venta11 = findViewById(R.id.venta11);
        venta15 = findViewById(R.id.venta15);
        venta45 = findViewById(R.id.venta45);

        btnGuardarVenta = findViewById(R.id.btnGuardarVenta);

        btnGuardarVenta.setOnClickListener(v -> guardarVenta());
    }

    private void guardarVenta() {

        int c5 = obtener(venta5);
        int c11 = obtener(venta11);
        int c15 = obtener(venta15);
        int c45 = obtener(venta45);

        if (c5 < 0 || c11 < 0 || c15 < 0 || c45 < 0) {
            Toast.makeText(this, "Cantidades inválidas",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (c5 + c11 + c15 + c45 == 0) {
            Toast.makeText(this, "Ingrese alguna venta",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        android.content.SharedPreferences datos =
                getSharedPreferences("InventarioChofer", MODE_PRIVATE);

        int stock5 = datos.getInt("llenos5", 0);
        int stock11 = datos.getInt("llenos11", 0);
        int stock15 = datos.getInt("llenos15", 0);
        int stock45 = datos.getInt("llenos45", 0);

        if (c5 > stock5 || c11 > stock11 ||
                c15 > stock15 || c45 > stock45) {

            Toast.makeText(this,
                    "No hay suficientes cilindros en la camioneta",
                    Toast.LENGTH_LONG).show();
            return;
        }

        datos.edit()
                .putInt("llenos5", stock5 - c5)
                .putInt("llenos11", stock11 - c11)
                .putInt("llenos15", stock15 - c15)
                .putInt("llenos45", stock45 - c45)
                .apply();

        Toast.makeText(this, "Venta registrada correctamente",
                Toast.LENGTH_LONG).show();

        finish();
    }

    private int obtener(EditText campo) {
        String valor = campo.getText().toString().trim();

        if (valor.isEmpty()) return 0;

        try {
            int cantidad = Integer.parseInt(valor);
            return cantidad >= 0 ? cantidad : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
