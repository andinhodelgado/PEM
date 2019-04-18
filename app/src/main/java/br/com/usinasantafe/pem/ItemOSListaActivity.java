package br.com.usinasantafe.pem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pem.to.estaticas.ComponenteTO;
import br.com.usinasantafe.pem.to.estaticas.ItemOSTO;
import br.com.usinasantafe.pem.to.estaticas.OSTO;
import br.com.usinasantafe.pem.to.estaticas.ServicoTO;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;

public class ItemOSListaActivity extends Activity {

    private ListView lista;
    private List listItemOS;
    private ApontTO apontTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_oslista);

        Button buttonRetItemOS = (Button) findViewById(R.id.buttonRetItemOS);

        apontTO = new ApontTO();
        List apontList = apontTO.get("statusApont", 1L);
        apontTO = (ApontTO) apontList.get(0);
        apontList.clear();

        OSTO osto = new OSTO();
        List osList = osto.get("nroOS", apontTO.getOsApont());
        osto = (OSTO) osList.get(0);
        osList.clear();

        ItemOSTO itemOSTO = new ItemOSTO();
        listItemOS = itemOSTO.getAndOrderBy("idOS", osto.getIdOS(),"seqItemOS",true);

        ArrayList<String> itens = new ArrayList<String>();

        for(int i = 0; i < listItemOS.size(); i++){
            itemOSTO = (ItemOSTO) listItemOS.get(i);

            ServicoTO servicoTO = new ServicoTO();
            List servicoList = servicoTO.get("idServico", itemOSTO.getIdServItemOS());
            servicoTO = (ServicoTO) servicoList.get(0);
            servicoList.clear();

            ComponenteTO componenteTO = new ComponenteTO();
            List componenteList = componenteTO.get("idComponente", itemOSTO.getIdCompItemOS());
            componenteTO = (ComponenteTO) componenteList.get(0);
            componenteList.clear();

            itens.add(itemOSTO.getSeqItemOS() + " - " + servicoTO.getDescrServico() + " - "
                    + componenteTO.getCodComponente() + " - " + componenteTO.getDescrComponente());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listItemOS);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                ItemOSTO itemOSTO = (ItemOSTO) listItemOS.get(position);
                apontTO.setItemOSApont(itemOSTO.getSeqItemOS());
                apontTO.update();

                Intent it = new Intent(ItemOSListaActivity.this, ProdutoLeitorActivity.class);
                startActivity(it);


            }

        });

        buttonRetItemOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ItemOSListaActivity.this, OSActivity.class);
                startActivity(it);
            }
        });

    }

    public void onBackPressed()  {
    }

}
