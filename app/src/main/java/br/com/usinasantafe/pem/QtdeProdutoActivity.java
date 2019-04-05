package br.com.usinasantafe.pem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QtdeProdutoActivity extends ActivityGeneric {

    private PEMContext pemContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtde_produto);

        Button buttonOkEntregador = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancEntregador = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkEntregador.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String lascas = editTextPadrao.getText().toString();
                Double lascasNum = Double.valueOf(lascas.replace(",", "."));

                Intent it = new Intent( QtdeProdutoActivity.this, MenuInicialActivity.class);
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
        Intent it = new Intent(QtdeProdutoActivity.this, MenuInicialActivity.class);
        startActivity(it);
        finish();
    }

}
