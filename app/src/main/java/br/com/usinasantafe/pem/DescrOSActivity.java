package br.com.usinasantafe.pem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pem.to.estaticas.OSTO;

public class DescrOSActivity extends Activity {

    private PEMContext pemContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descr_os);

        TextView textViewCodEquip = (TextView) findViewById(R.id.textViewCodEquip);
        TextView textViewDescEquip = (TextView) findViewById(R.id.textViewDescEquip);
        Button buttonOkDescrOS = (Button) findViewById(R.id.buttonOkDescrOS);
        Button buttonCancDescrOS = (Button) findViewById(R.id.buttonCancDescrOS);

        pemContext = (PEMContext) getApplication();

        OSTO osto = new OSTO();
        List osList = osto.get("nroOS", 2L);
        osto = (OSTO) osList.get(0);
        osList.clear();

        textViewCodEquip.setText(String.valueOf(osto.getEquipOS()));
        textViewDescEquip.setText(osto.getDescrEquipOS());

        buttonOkDescrOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(DescrOSActivity.this, ItemOSListaActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancDescrOS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(DescrOSActivity.this, OSActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

}
