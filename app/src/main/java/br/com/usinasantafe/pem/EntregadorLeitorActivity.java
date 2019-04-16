package br.com.usinasantafe.pem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pem.bo.ConexaoWeb;
import br.com.usinasantafe.pem.bo.ManipDadosVerif;
import br.com.usinasantafe.pem.to.estaticas.ColabTO;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;

public class EntregadorLeitorActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtRetMatricEntregador;
    private ColabTO colabTO;
    private String matricula;
    private Boolean verFunc;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregador_leitor);

        txtRetMatricEntregador = (TextView) findViewById(R.id.txtRetMatricEntregador);
        Button buttonOkEntregador = (Button) findViewById(R.id.buttonOkEntregador);
        Button buttonCancEntregador = (Button) findViewById(R.id.buttonCancEntregador);
        Button buttonDigEntregador = (Button) findViewById(R.id.buttonDigEntregador);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonOkEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (verFunc) {

                    ApontTO apontTO = new ApontTO();
                    apontTO.setEntregadorApont(colabTO.getIdColab());
                    apontTO.setStatusApont(1L);
                    apontTO.insert();

                    Intent it = new Intent(EntregadorLeitorActivity.this, RecebedorLeitorActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

        buttonCancEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(EntregadorLeitorActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonDigEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(EntregadorLeitorActivity.this, EntregadorDigActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(EntregadorLeitorActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(EntregadorLeitorActivity.this)) {

                            progressBar = new ProgressDialog(EntregadorLeitorActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Colaborador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Colab"
                                    , EntregadorLeitorActivity.this, EntregadorLeitorActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(EntregadorLeitorActivity.this);
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

    }

    public void callZXing(View view) {
        Intent it = new Intent(EntregadorLeitorActivity.this, br.com.usinasantafe.pem.zxing.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            matricula = data.getStringExtra("SCAN_RESULT");
            if (matricula.length() == 8) {
                matricula = matricula.substring(0, 7);
                colabTO = new ColabTO();
                List listColab = colabTO.get("matricColab", Long.parseLong(matricula));
                if (listColab.size() > 0) {
                    colabTO = (ColabTO) listColab.get(0);
                    verFunc = true;
                    txtRetMatricEntregador.setText(matricula + "\n" + colabTO.getNomeColab());
                } else {
                    verFunc = false;
                    txtRetMatricEntregador.setText("Funcionário Inexistente");
                }
            }
        }

    }

    public void onBackPressed() {
    }

}
