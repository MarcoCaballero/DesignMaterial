package miliziandevelopers.mydesignmaterial;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilNombre;
    private TextInputLayout tilTelefono;
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoCorreo;
    private FloatingActionButton botonAceptar = null;
    private FloatingActionButton botonCancelar = null;
    private FloatingActionButton botonNuevaCuenta = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonCancelar = (FloatingActionButton) findViewById(R.id.fablogX);
        botonCancelar.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(241, 244, 66)));
        botonCancelar.animate().translationY(-getResources().getDimension(R.dimen.article_keylines));
        botonNuevaCuenta = (FloatingActionButton) findViewById(R.id.fablogNew);
        botonNuevaCuenta.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(223, 66, 244)));
        botonNuevaCuenta.animate().translationX(-getResources().getDimension(R.dimen.article_keylines));

        botonAceptar = (FloatingActionButton) findViewById(R.id.fablog);
        botonAceptar.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(72, 66, 244)));
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarDatos()){
                    Log.d("hola","problems");
                    //Context context = v.getContext();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    //intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    startActivity(intent);
                }
            }
        });


        // Referencias TILs
        tilNombre = (TextInputLayout) findViewById(R.id.til_nombre);
        tilTelefono = (TextInputLayout) findViewById(R.id.til_telefono);


        // Referencias ETs
        campoNombre = (EditText) findViewById(R.id.campo_nombre);
        campoTelefono = (EditText) findViewById(R.id.campo_telefono);


        campoNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
        }

        return true;
    }


    private boolean validarDatos() {
        String nombre = tilNombre.getEditText().getText().toString();
        String telefono = tilTelefono.getEditText().getText().toString();


        boolean a = esNombreValido(nombre);


        if (a) {
            // OK, se pasa a la siguiente acción
            Toast.makeText(this, "Se guarda el registro", Toast.LENGTH_LONG).show();


        }
        return a;
    }

}
