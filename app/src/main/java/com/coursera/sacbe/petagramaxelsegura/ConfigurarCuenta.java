package com.coursera.sacbe.petagramaxelsegura;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.coursera.sacbe.petagramaxelsegura.mail.SendMail;

public class ConfigurarCuenta extends AppCompatActivity {

    private TextInputEditText ietNombrePerfil;
    private TextInputLayout tilNombrePerfil;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tilNombrePerfil = (TextInputLayout) findViewById(R.id.tilNombrePerfil);
        ietNombrePerfil = (TextInputEditText) findViewById(R.id.ietNombrePerfil);
        ietNombrePerfil.addTextChangedListener(new MyTextWatcher(ietNombrePerfil));
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        mostrarPreferencia();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormulario();
            }
        });

    }

    private void submitFormulario() {
        if (!validaNombrePerfil()) {
            return;
        }

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        String nombrePerfil = ietNombrePerfil.getText().toString();

        editor.putString(getResources().getString(R.string.pNombrePerfil), nombrePerfil);

        editor.commit();

        Toast.makeText(ConfigurarCuenta.this, "Se ha guardado la cuenta de Instagram", Toast.LENGTH_SHORT).show();
        ietNombrePerfil.setText("");

        mostrarPreferencia();
    }

    public void mostrarPreferencia(){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombrePerfilActual = miPreferenciaCompartida.getString(getResources().getString(R.string.pNombrePerfil), "");
        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.tvCuentaPerfil);
        tvPreferenciaCompartida.setText(nombrePerfilActual);

    }

    private boolean validaNombrePerfil() {
        if (ietNombrePerfil.getText().toString().trim().isEmpty()) {
            tilNombrePerfil.setError(getString(R.string.err_msg_nombrePerfil));
            requestFocus(ietNombrePerfil);
            return false;
        } else {
            tilNombrePerfil.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.ietNombreCompleto:
                    validaNombrePerfil();
                    break;
            }
        }
    }

}
