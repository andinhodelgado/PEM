package br.com.usinasantafe.pem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pem.bo.ConexaoWeb;
import br.com.usinasantafe.pem.bo.ManipDadosVerif;
import br.com.usinasantafe.pem.to.estaticas.ColabTO;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;

public class RecebedorDigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebedor_dig);

        Button buttonOkRecebedor = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancRecebedor = (Button) findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(RecebedorDigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(RecebedorDigActivity.this)) {

                            progressBar = new ProgressDialog(RecebedorDigActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Colaborador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Colab"
                                    , RecebedorDigActivity.this, RecebedorDigActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(RecebedorDigActivity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            alerta.show();

                        }
                    }
                });

                alerta.setPositiveButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alerta.show();

            }

        });

        buttonOkRecebedor.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("rawtypes")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    ColabTO colabTO = new ColabTO();
                    List colabList = colabTO.get("matricColab", Long.parseLong(editTextPadrao.getText().toString()));

                    if (colabList.size() > 0) {

                        colabTO = (ColabTO) colabList.get(0);

                        ApontTO apontTO = new ApontTO();
                        List apontList = apontTO.get("statusApont", 1L);
                        apontTO = (ApontTO) apontList.get(0);
                        apontTO.setRecebedorApont(colabTO.getIdColab());
                        apontTO.update();

                        Intent it = new Intent(RecebedorDigActivity.this, OSActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(RecebedorDigActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("NUMERAÇÃO DO FUNCIONÁRIO INEXISTENTE! FAVOR VERIFICA A MESMA.");
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

        buttonCancRecebedor.setOnClickListener(new View.OnClickListener()

            {

                @Override
                public void onClick (View v){
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
            }
            });

        }

        public void onBackPressed () {
            Intent it = new Intent(RecebedorDigActivity.this, EntregadorLeitorActivity.class);
            startActivity(it);
            finish();
        }

    }
