package com.example.controlspinner;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText num1, num2;
    private TextView res;
    private Button operar;
    private Spinner operaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.edtNumero1);
        num2 = findViewById(R.id.edtNumero2);
        res = findViewById(R.id.txtResultado);
        operar = findViewById(R.id.btnOperar);
        operaciones = findViewById(R.id.spnOperaciones);

        String[] opciones = {"sumar", "restar", "multiplicar", "dividir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, opciones);
        operaciones.setAdapter(adapter);

        operar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String numero1 = num1.getText().toString().trim();
                String numero2 = num2.getText().toString().trim();

                if (numero1.isEmpty() || numero2.isEmpty()) {
                    res.setText("Por favor, ingrese ambos números.");
                    return;
                }

                int n1, n2;
                try {
                    n1 = Integer.parseInt(numero1);
                    n2 = Integer.parseInt(numero2);
                } catch (NumberFormatException e) {
                    res.setText("Ingrese números válidos.");
                    return;
                }

                int resultado = 0;
                String operacion = operaciones.getSelectedItem().toString();

                switch (operacion) {
                    case "sumar":
                        resultado = n1 + n2;
                        break;
                    case "restar":
                        resultado = n1 - n2;
                        break;
                    case "multiplicar":
                        resultado = n1 * n2;
                        break;
                    case "dividir":
                        if (n2 == 0) {
                            res.setText("Error: No se puede dividir por cero.");
                            return;
                        }
                        resultado = n1 / n2;
                        break;
                }
                res.setText(String.valueOf(resultado));
            }
        });
    }
}
