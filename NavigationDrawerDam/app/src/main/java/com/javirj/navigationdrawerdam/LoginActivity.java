package com.javirj.navigationdrawerdam;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView recPass, user, pass;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        recPass = findViewById(R.id.textViewRecoverPassword);
        button = findViewById(R.id.button);
        user = findViewById(R.id.textInputEditTextUser);
        pass = findViewById(R.id.textInputEditTextPassword);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent i = new Intent(LoginActivity.this, MainActivity.class);
                //startActivity(i);

                // Simple verificación para evitar un user en blanco
                if (user.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Email incorrecto", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        recPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecuperar();
            }
        });

    }

    private void showDialogRecuperar() {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_recPass_mensaje)
                .setTitle(R.string.dialog_recPass_titulo);


        // Añadir botones
        builder.setPositiveButton(R.string.dialog_rec_ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Enviar email
                String email = "pepito@palotes.com";
                composeEmail(email,"Nueva contraseña");

            }
        });
        builder.setNegativeButton(R.string.dialog_rec_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Cerramos el cuadro de diálogo
                dialog.dismiss();
            }
        });


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        // 4. Mostrar el diálogo en la pantalla
        dialog.show();
    }

    public void composeEmail(String address, String subject) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
