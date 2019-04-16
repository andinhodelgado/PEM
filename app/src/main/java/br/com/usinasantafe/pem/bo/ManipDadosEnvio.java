package br.com.usinasantafe.pem.bo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pem.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pem.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pem.pst.EspecificaPesquisa;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;

public class ManipDadosEnvio {

    private static ManipDadosEnvio instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
    private boolean enviando = false;

    public ManipDadosEnvio() {
        // TODO Auto-generated constructor stub
        urlsConexaoHttp = new UrlsConexaoHttp();
    }

    public static ManipDadosEnvio getInstance() {
        if (instance == null) {
            instance = new ManipDadosEnvio();
        }
        return instance;
    }

    //////////////////////// ENVIAR DADOS ////////////////////////////////////////////

    public void envioApontamento() {

        JsonArray jsonArrayAponta = new JsonArray();

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.all();

        for (int i = 0; i < apontaList.size(); i++) {
            apontTO = (ApontTO) apontaList.get(i);
            Gson gson = new Gson();
            jsonArrayAponta.add(gson.toJsonTree(apontTO, apontTO.getClass()));
        }

        JsonObject jsonAponta = new JsonObject();
        jsonAponta.add("aponta", jsonArrayAponta);

        String dados = jsonAponta.toString();

        Log.i("PMM", "APONTAMENTO = " + dados);

        String[] url = {urlsConexaoHttp.getsInsertApont()};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", dados);

        ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
        conHttpPostCadGenerico.setParametrosPost(parametrosPost);
        conHttpPostCadGenerico.execute(url);

    }

    /////////////////////////////// DELETAR DADOS ///////////////////////////////////////////////

    public void delApont() {

        new ApontTO().deleteAll();

    }


    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public boolean verifAponta() {
        return new ApontTO().hasElements();
    }

    /////////////////////////MECANISMO DE ENVIO//////////////////////////////////

    public void envioDados(Context context) {
        enviando = true;
        ConexaoWeb conexaoWeb = new ConexaoWeb();
        if (conexaoWeb.verificaConexao(context)) {
            envioDadosPrinc();
        }
        else{
            enviando = false;
        }

    }

    public void envioDadosPrinc() {
        if (verifAponta()) {
            envioApontamento();
        }
    }

    public boolean verifDadosEnvio() {
        if ((!verifAponta())) {
            enviando = false;
            return false;
        } else {
            return true;
        }
    }

    public int getStatusEnvio() {
        if (enviando) {
            statusEnvio = 1;
        } else {
            if (!verifDadosEnvio()) {
                statusEnvio = 3;
            } else {
                statusEnvio = 2;
            }
        }
        return statusEnvio;
    }

    public void setEnviando(boolean enviando) {
        this.enviando = enviando;
    }

}
