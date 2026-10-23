package com.example.app01_2026b_suma_maciasenriquesi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etValor1,etValor2,etResultado;
    private ImageButton btSumar,btBorrar,btSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
        etValor1=(EditText)findViewById(R.id.etValor1);
        etValor2=(EditText)findViewById(R.id.etValor2);
        etResultado=(EditText)findViewById(R.id.etResultado);
        btSumar=(ImageButton)findViewById(R.id.btSumar);
        btBorrar=(ImageButton)findViewById(R.id.btBorrar);
        btSalir=(ImageButton)findViewById(R.id.btSalir);
    }
    public void sumar(View view){
        String valor1=etValor1.getText().toString();
        String valor2=etValor2.getText().toString();
        if(TextUtils.isEmpty(valor1)||TextUtils.isEmpty(valor2)) {
            Toast.makeText(this, "Debe ingresar un valor", Toast.LENGTH_LONG).show();
        }else{
            double n1=Double.parseDouble(valor1);
            double n2=Double.parseDouble(valor2);
            double suma=n1+n2;
            etResultado.setText(String.valueOf(suma));
            btSumar.setEnabled(false);
            btBorrar.setEnabled(true);
        }
    }
    public void borrar(View view)
    {
        etValor1.setText("");
        etValor2.setText("");
        etResultado.setText("");
        btSumar.setEnabled(true);
        btBorrar.setEnabled(false);
        etValor1.requestFocus();
    }
    public void salir(View view){
        System.exit(0);
    }
}