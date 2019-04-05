package br.com.usinasantafe.pem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class EntregadorDigActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregador_dig);

        Button buttonOkEntregador = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancEntregador = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });

        buttonOkEntregador.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent( EntregadorDigActivity.this, RecebedorLeitorActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed()  {
        Intent it = new Intent(EntregadorDigActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}
