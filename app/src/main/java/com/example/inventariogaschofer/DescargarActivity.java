
package com.example.inventariogaschofer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DescargarActivity extends AppCompatActivity {

    EditText vacio5, vacio11, vacio15, vacio45;
    Button btnGuardarDescarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar);

        vacio5 = findViewById(R.id.vacio5);
        vacio11 = findViewById(R.id.vacio11);
        vacio15 = findViewById(R.id.vacio15);
        vacio45 = findViewById(R.id.vacio45);

        btnGuardarDescarga =
                findViewById(R.id.btnGuardarDescarga);

        btnGuardarDescarga.setOnClickListener(v -> guardar());
    }

    private void guardar() {

        int c5 = obtener(vacio5);
        int c11 = obtener(vacio11);
        int c15 = obtener(vacio15);
        int c45 = obtener(vacio45);

        if (c5 < 0 || c11 < 0 || c15 < 0 || c45 < 0) {
            Toast.makeText(this, "Cantidades inválidas",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (c5 + c11 + c15 + c45 == 0) {
            Toast.makeText(this, "Ingrese alguna cantidad",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        android.content.SharedPreferences datos =
                getSharedPreferences("InventarioChofer", MODE_PRIVATE);

        datos.edit()
                .putInt("vacios5",
                        datos.getInt("vacios5", 0) + c5)
                .putInt("vacios11",
                        datos.getInt("vacios11", 0) + c11)
                .putInt("vacios15",
                        datos.getInt("vacios15", 0) + c15)
                .putInt("vacios45",
                        datos.getInt("vacios45", 0) + c45)
                .apply();

        Toast.makeText(this, "Descarga guardada correctamente",
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
