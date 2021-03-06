package br.com.usinasantafe.pem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pem.to.variaveis.ApontTO;

public class ItemOSDigActivity extends ActivityGeneric {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_osdig);

        Button buttonOkItemOSDig = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancItemOSDig = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkItemOSDig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    if(Long.parseLong(editTextPadrao.getText().toString()) < 1000){

                        ApontTO apontTO = new ApontTO();
                        List apontList = apontTO.get("statusApont", 1L);
                        apontTO = (ApontTO) apontList.get(0);
                        apontTO.setItemOSApont(Long.parseLong(editTextPadrao.getText().toString()));
                        apontTO.update();
                        apontList.clear();

                        Intent it = new Intent(ItemOSDigActivity.this, ProdutoLeitorActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder(  ItemOSDigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("VALOR ACIMA DO QUE O PERMITIDO. POR FAVOR, VERIFIQUE O VALOR!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }

                }
            }
        });

        buttonCancItemOSDig.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(ItemOSDigActivity.this, OSActivity.class);
        startActivity(it);
        finish();
    }

}
