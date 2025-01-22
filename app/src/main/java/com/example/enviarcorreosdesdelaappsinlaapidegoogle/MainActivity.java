package com.example.enviarcorreosdesdelaappsinlaapidegoogle;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCorreo, editTextAsunto, editTextContenido;
    private Button buttonEnviar;
    private TextView textViewMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        editTextCorreo = findViewById(R.id.editTextCorreo);
        editTextAsunto = findViewById(R.id.editTextAsunto);
        editTextContenido = findViewById(R.id.editTextContenido);
        buttonEnviar = findViewById(R.id.buttonEnviar);
        textViewMensaje = findViewById(R.id.textViewMensaje);

        // Configurar el botón para enviar
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores de los EditText
                String correo = editTextCorreo.getText().toString();
                String asunto = editTextAsunto.getText().toString();
                String contenido = editTextContenido.getText().toString();

                // Validar el correo
                if (isCorreoValido(correo)) {
                    textViewMensaje.setText("Correo enviado");
                    MailSender.enviarCorreo(correo, asunto, contenido);
                } else {
                    textViewMensaje.setText("Correo no válido");
                }
            }
        });
    }

    // Método para validar el correo
    private boolean isCorreoValido(String correo) {
        // Validación simple: debe contener una @ y terminar en .com o .es
        return !TextUtils.isEmpty(correo) && Patterns.EMAIL_ADDRESS.matcher(correo).matches() &&
                (correo.endsWith(".com") || correo.endsWith(".es"));
    }
}