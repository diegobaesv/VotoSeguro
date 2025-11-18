package com.sise.votoseguro.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sise.votoseguro.R;
import com.sise.votoseguro.presentation.common.Validator;

public class InicioActivity extends AppCompatActivity {

    private final String TAG = InicioActivity.class.getSimpleName();
    private EditText edtDni;
    private EditText edtDigito;
    private EditText edtFechaEmision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtDni = findViewById(R.id.actinicio_edt_dni);
        edtDigito = findViewById(R.id.actinicio_edt_digito);
        edtFechaEmision = findViewById(R.id.actinicio_edt_fechaemision);
    }

    public void onClicVerificar(View v) {

        if(!Validator.with(edtDni)
                .required()
                .length(8)
                .validate()) return;

        if(!Validator.with(edtDigito)
                .required()
                .length(1)
                .validate()) return;

        if(!Validator.with(edtFechaEmision)
                .required()
                .isDate()
                .validate()) return;

        //llamada al api

        Intent intent = new Intent(this, ReconocimientoActivity.class);
        startActivity(intent);
    }

}