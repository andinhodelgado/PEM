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

import br.com.usinasantafe.pem.bo.ConexaoWeb;
import br.com.usinasantafe.pem.bo.ManipDadosVerif;
import br.com.usinasantafe.pem.to.estaticas.ItemOSTO;
import br.com.usinasantafe.pem.to.estaticas.OSTO;

public class OSActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private PEMContext pemContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        pemContext = (PEMContext) getApplication();

        Button buttonOkOS = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!editTextPadrao.getText().toString().equals("")) {

                    try {

                        OSTO osto = new OSTO();
                        List osList = osto.get("nroOS", Long.parseLong(editTextPadrao.getText().toString()));

                        if(osList.size() > 0) {

                            Intent it = new Intent(OSActivity.this, ItemOSListaActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            ConexaoWeb conexaoWeb = new ConexaoWeb();
                            if (conexaoWeb.verificaConexao(OSActivity.this)) {

                                progressBar = new ProgressDialog(v.getContext());
                                progressBar.setCancelable(true);
                                progressBar.setMessage("Pequisando a OS...");
                                progressBar.show();

                                osto.deleteAll();
                                ItemOSTO itemOSTO = new ItemOSTO();
                                itemOSTO.deleteAll();

                                ManipDadosVerif.getInstance().verDados(editTextPadrao.getText().toString(), "OS"
                                        , OSActivity.this, DescrOSActivity.class, progressBar);

                            } else {

                                Intent it = new Intent(OSActivity.this, ItemOSDigActivity.class);
                                startActivity(it);
                                finish();

                            }


                        }


                    } catch (Exception e) {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O.S. INCORRETA OU INEXISTENTE! FAVOR VERIFICAR.");
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

        buttonCancOS.setOnClickListener(new View.OnClickListener() {

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
        Intent it = new Intent(OSActivity.this, RecebedorLeitorActivity.class);
        startActivity(it);
        finish();
    }

}
