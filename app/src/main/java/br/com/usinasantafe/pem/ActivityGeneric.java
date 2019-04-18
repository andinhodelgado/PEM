package br.com.usinasantafe.pem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import br.com.usinasantafe.pem.pst.DatabaseHelper;

public class ActivityGeneric extends OrmLiteBaseActivity<DatabaseHelper> {


    public EditText editTextPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        getHelper();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        if ((EditText) findViewById(R.id.editTextPadrao) != null) {
            editTextPadrao = (EditText) findViewById(R.id.editTextPadrao);
            if(!this.getLocalClassName().equals("OSActivity")) {
                editTextPadrao.setText("");
            }
        }

        if((Button) findViewById(R.id.buttonNum0) != null){
            Button buttonNum0 = (Button) findViewById(R.id.buttonNum0);
            buttonNum0.setOnClickListener(new EventoBotao("0"));
        }

        if((Button) findViewById(R.id.buttonNum1) != null){
            Button buttonNum1 = (Button) findViewById(R.id.buttonNum1);
            buttonNum1.setOnClickListener(new EventoBotao("1"));
        }

        if((Button) findViewById(R.id.buttonNum2) != null){
            Button buttonNum2 = (Button) findViewById(R.id.buttonNum2);
            buttonNum2.setOnClickListener(new EventoBotao("2"));
        }

        if((Button) findViewById(R.id.buttonNum3) != null){
            Button buttonNum3 = (Button) findViewById(R.id.buttonNum3);
            buttonNum3.setOnClickListener(new EventoBotao("3"));
        }

        if((Button) findViewById(R.id.buttonNum4) != null){
            Button buttonNum4 = (Button) findViewById(R.id.buttonNum4);
            buttonNum4.setOnClickListener(new EventoBotao("4"));
        }

        if((Button) findViewById(R.id.buttonNum5) != null){
            Button buttonNum5 = (Button) findViewById(R.id.buttonNum5);
            buttonNum5.setOnClickListener(new EventoBotao("5"));
        }

        if((Button) findViewById(R.id.buttonNum6) != null){
            Button buttonNum6 = (Button) findViewById(R.id.buttonNum6);
            buttonNum6.setOnClickListener(new EventoBotao("6"));
        }

        if((Button) findViewById(R.id.buttonNum7) != null){
            Button buttonNum7 = (Button) findViewById(R.id.buttonNum7);
            buttonNum7.setOnClickListener(new EventoBotao("7"));
        }

        if((Button) findViewById(R.id.buttonNum8) != null){
            Button buttonNum8 = (Button) findViewById(R.id.buttonNum8);
            buttonNum8.setOnClickListener(new EventoBotao("8"));
        }

        if((Button) findViewById(R.id.buttonNum9) != null){
            Button buttonNum9 = (Button) findViewById(R.id.buttonNum9);
            buttonNum9.setOnClickListener(new EventoBotao("9"));
        }

        if((Button) findViewById(R.id.buttonNum00) != null){
            Button buttonNum00 = (Button) findViewById(R.id.buttonNum00);
            buttonNum00.setOnClickListener(new EventoBotao("00"));
        }

        if((Button) findViewById(R.id.buttonVirg) != null){
            Button buttonVirg = (Button) findViewById(R.id.buttonVirg);
            buttonVirg.setOnClickListener(new EventoBotao(","));
        }

        if((Button) findViewById(R.id.buttonLetraA) != null){
            Button buttonNum00 = (Button) findViewById(R.id.buttonLetraA);
            buttonNum00.setOnClickListener(new EventoBotao("A"));
        }

    }

    private class EventoBotao implements View.OnClickListener{

        private String numBotao;

        public EventoBotao(String numBotao) {
            this.numBotao = numBotao;
        }

        @Override
        public void onClick(View v) {

            String texto = editTextPadrao.getText().toString();
            if(numBotao.equals(",")){
                if(!texto.contains(",")){
                    editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
                }
            }
            else{
                editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
            }

        }

    }

}
