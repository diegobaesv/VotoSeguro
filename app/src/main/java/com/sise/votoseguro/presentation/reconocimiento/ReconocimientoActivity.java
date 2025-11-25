package com.sise.votoseguro.presentation.reconocimiento;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.common.util.concurrent.ListenableFuture;
import com.sise.votoseguro.R;
import com.sise.votoseguro.presentation.elecciones_vigentes.EleccionesVigentesActivity;
import com.sise.votoseguro.presentation.inicio.InicioActivity;

public class ReconocimientoActivity extends AppCompatActivity {

    PreviewView previewView;
    private final String TAG = InicioActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reconocimiento);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        previewView = findViewById(R.id.actreconocimiento_previewview);

        if(allPermissionsGranted()) {
            Log.i(TAG,"allPermissionsGranted: true");
            startCamera();
        } else {
            Log.i(TAG,"pidiendo permisos camera");
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.CAMERA }, 100);
        }

        goToEleccionesVigentes();
    }

    private void goToEleccionesVigentes() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(this, EleccionesVigentesActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    private void startCamera() {
        Log.i(TAG,"ejecutando starcamera");
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(previewView.getSurfaceProvider());

                CameraSelector selector = CameraSelector.DEFAULT_FRONT_CAMERA;

                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, selector, preview);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

    private boolean allPermissionsGranted() {
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG,"onRequestPermissionsResult: true");
            startCamera();
        }
    }

}