package br.com.usinasantafe.pem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.pem.bo.Tempo;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;
import br.com.usinasantafe.pem.to.variaveis.ConfiguracaoTO;

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

                if (!editTextPadrao.getText().toString().equals("")) {

                    String qtdeString = editTextPadrao.getText().toString();

                    ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
                    List configList = configuracaoTO.all();
                    configuracaoTO = (ConfiguracaoTO) configList.get(0);
                    configList.clear();

                    ApontTO apontTO = new ApontTO();
                    List apontList = apontTO.get("statusApont", 1L);
                    apontTO = (ApontTO) apontList.get(0);
                    apontTO.setDthrApont(Tempo.getInstance().datahora());
                    apontTO.setQtdeProdApont(Double.valueOf(qtdeString.replace(",", ".")));
                    apontTO.setStatusApont(2L);
                    apontTO.setEquipApont(configuracaoTO.getEquipConfig());
                    apontTO.update();

                    Intent it = new Intent(QtdeProdutoActivity.this, MenuInicialActivity.class);
                    startActivity(it);
                    finish();

                }

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
