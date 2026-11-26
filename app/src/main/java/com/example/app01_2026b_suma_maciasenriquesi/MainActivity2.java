package com.example.app01_2026b_suma_maciasenriquesi;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private TextView tvResulta;
    private EditText etResulta;
    private ImageButton btRegresar;
    private TextToSpeech tts; // Variable para el motor de voz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResulta = findViewById(R.id.tvResulta);
        etResulta = findViewById(R.id.etResulta);
        btRegresar = findViewById(R.id.btRegresar);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            tvResulta.setText(bundle.getString("Cabecera"));
            etResulta.setText(bundle.getString("Resultado"));
        }
        // 1. Inicializar el motor de Texto a Voz
        tts = new TextToSpeech(this, this);

        // Manejo moderno del botón atrás
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }
    // 2. Este método se ejecuta cuando el motor de voz termina de cargar
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            // Configurar el idioma a Español
            int result = tts.setLanguage(new Locale("es", "MX"));

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "El idioma no es compatible o faltan datos");
            } else {
                // Hablar el resultado
                reproducirVoz();
            }
        } else {
            Log.e("TTS", "Error en la inicialización");
        }
    }
    private void reproducirVoz() {
        String mensaje = tvResulta.getText().toString() + " " + etResulta.getText().toString();
        // QUEUE_FLUSH hace que si hay algo hablándose, se detenga y hable esto de inmediato
        tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    // 3. Muy importante liberar la memoria al cerrar la actividad
    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
    public void regreso(View view) {
        getOnBackPressedDispatcher().onBackPressed();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}