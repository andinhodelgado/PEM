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
import br.com.usinasantafe.pem.pst.EspecificaPesquisa;
import br.com.usinasantafe.pem.to.estaticas.ProdTO;
import br.com.usinasantafe.pem.to.estaticas.REquipProdTO;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;
import br.com.usinasantafe.pem.to.variaveis.ConfiguracaoTO;

public class ProdutoLeitorActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txtRetProduto;
    private String produto;
    private Boolean verProd;
    private ProdTO prodTO;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_leitor);

        txtRetProduto = (TextView) findViewById(R.id.txtRetProduto);
        Button buttonOkProduto = (Button) findViewById(R.id.buttonOkProduto);
        Button buttonCancProduto = (Button) findViewById(R.id.buttonCancProduto);
        Button buttonDigProduto = (Button) findViewById(R.id.buttonDigProduto);
        Button buttonAtualPadrao = (Button) findViewById(R.id.buttonAtualPadrao);

        buttonOkProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (verProd) {

                    ApontTO apontTO = new ApontTO();
                    List apontList = apontTO.get("statusApont", 1L);
                    apontTO = (ApontTO) apontList.get(0);
                    apontTO.setIdProdApont(prodTO.getIdProd());
                    apontTO.update();

                    Intent it = new Intent(ProdutoLeitorActivity.this, QtdeProdutoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ProdutoLeitorActivity.this, OSActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonDigProduto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ProdutoLeitorActivity.this, ProdutoDigActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonAtualPadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ProdutoLeitorActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
                alerta.setNegativeButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ConexaoWeb conexaoWeb = new ConexaoWeb();

                        if (conexaoWeb.verificaConexao(ProdutoLeitorActivity.this)) {

                            progressBar = new ProgressDialog(ProdutoLeitorActivity.this);
                            progressBar.setCancelable(true);
                            progressBar.setMessage("Atualizando Colaborador...");
                            progressBar.show();

                            ManipDadosVerif.getInstance().verDados("", "Colab"
                                    , ProdutoLeitorActivity.this, ProdutoLeitorActivity.class, progressBar);

                        } else {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(ProdutoLeitorActivity.this);
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
        Intent it = new Intent(ProdutoLeitorActivity.this, br.com.usinasantafe.pem.zxing.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){

            produto = data.getStringExtra("SCAN_RESULT");

            prodTO = new ProdTO();
            List prodList = prodTO.get("codProd", produto);

            if (prodList.size() > 0) {

                ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                List configList = configuracaoTO.all();
                configuracaoTO = (ConfiguracaoTO) configList.get(0);
                configList.clear();

                REquipProdTO rEquipProdTO = new REquipProdTO();
                ArrayList listaPesq = new ArrayList();

                EspecificaPesquisa pesquisa = new EspecificaPesquisa();
                pesquisa.setCampo("idEquip");
                pesquisa.setValor(configuracaoTO.getEquipConfig());
                listaPesq.add(pesquisa);

                EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
                pesquisa2.setCampo("idProd");
                pesquisa2.setValor(prodTO.getIdProd());
                listaPesq.add(pesquisa2);

                List rEquipProdList = rEquipProdTO.get(listaPesq);

                if(rEquipProdList.size() > 0){
                    verProd = true;
                    prodTO = (ProdTO) prodList.get(0);
                    txtRetProduto.setText("CODIGO: " + prodTO.getCodProd() + "\n" + prodTO.getDescrProd());
                }
                else{
                    verProd = false;
                    txtRetProduto.setText("PRODUTO INEXISTENTE NO ESTOQUE DO EQUIPAMENTO");
                }

            }
            else{
                verProd = false;
                txtRetProduto.setText("PRODUTO INEXISTENTE");
            }

        }
    }
}
