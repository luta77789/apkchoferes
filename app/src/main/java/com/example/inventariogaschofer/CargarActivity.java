
package com.example.inventariogaschofer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CargarActivity extends AppCompatActivity {

    EditText lleno5, lleno11, lleno15, lleno45;
    Button btnGuardarCarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar);

        lleno5 = findViewById(R.id.lleno5);
        lleno11 = findViewById(R.id.lleno11);
        lleno15 = findViewById(R.id.lleno15);
        lleno45 = findViewById(R.id.lleno45);

        btnGuardarCarga = findViewById(R.id.btnGuardarCarga);

        btnGuardarCarga.setOnClickListener(v -> guardarCarga());
    }

    private void guardarCarga() {

        int c5 = obtenerCantidad(lleno5);
        int c11 = obtenerCantidad(lleno11);
        int c15 = obtenerCantidad(lleno15);
        int c45 = obtenerCantidad(lleno45);

        if (c5 < 0 || c11 < 0 || c15 < 0 || c45 < 0) {
            Toast.makeText(this, "Ingrese cantidades válidas",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (c5 + c11 + c15 + c45 == 0) {
            Toast.makeText(this, "Ingrese al menos una cantidad",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        android.content.SharedPreferences datos =
                getSharedPreferences("InventarioChofer", MODE_PRIVATE);

        android.content.SharedPreferences.Editor editor =
                datos.edit();

        editor.putInt("llenos5",
                datos.getInt("llenos5", 0) + c5);

        editor.putInt("llenos11",
                datos.getInt("llenos11", 0) + c11);

        editor.putInt("llenos15",
                datos.getInt("llenos15", 0) + c15);

        editor.putInt("llenos45",
                datos.getInt("llenos45", 0) + c45);

        editor.apply();

        Toast.makeText(this, "Carga guardada correctamente",
                Toast.LENGTH_LONG).show();

        finish();
    }

    private int obtenerCantidad(EditText campo) {
        String valor = campo.getText().toString().trim();

        if (valor.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
