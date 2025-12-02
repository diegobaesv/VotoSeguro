package com.sise.votoseguro.presentation.inicio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sise.votoseguro.R;
import com.sise.votoseguro.data.model.Persona;
import com.sise.votoseguro.data.request.VerificarPersonaRequest;
import com.sise.votoseguro.presentation.common.Util;
import com.sise.votoseguro.presentation.reconocimiento.ReconocimientoActivity;
import com.sise.votoseguro.presentation.common.Validator;

public class InicioActivity extends AppCompatActivity {

    private InicioViewModel inicioViewModel;

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

        inicioViewModel = new ViewModelProvider(this).get(InicioViewModel.class);
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

        VerificarPersonaRequest verificarPersonaRequest = new VerificarPersonaRequest();
        verificarPersonaRequest.setNumeroDocumento(edtDni.getText().toString());
        verificarPersonaRequest.setDigitoVerificador(Integer.parseInt(edtDigito.getText().toString()));
        verificarPersonaRequest.setFechaEmision(Util.stringToDate(edtFechaEmision.getText().toString()));

        //llamada al api
        inicioViewModel.verificarPersona(verificarPersonaRequest).observe(this, response -> {
            if(!response.isSuccess()) {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Persona p = response.getData();
                Toast.makeText(this,"Bienvenido "+ p.getNombres()+" al sistema de votos", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ReconocimientoActivity.class);
                startActivity(intent);
            }
        });

    }

}