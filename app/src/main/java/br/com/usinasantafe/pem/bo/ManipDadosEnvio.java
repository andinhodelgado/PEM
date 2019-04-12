package br.com.usinasantafe.pem.bo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pem.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pem.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pem.pst.EspecificaPesquisa;
import br.com.usinasantafe.pem.to.variaveis.ApontTO;
import br.com.usinasantafe.pem.to.variaveis.BoletimTO;

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
        List apontaList = dadosApontamento();

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

    public void atualApont() {

        ApontTO apontTO = new ApontTO();
        List apontaList = apontTO.all();

        for (int i = 0; i < apontaList.size(); i++) {
            apontTO = (ApontTO) apontaList.get(i);
            apontTO.setStatusApont(1L);
            apontTO.update();
        }

    }

    //////////////////////////TRAZER DADOS////////////////////////////

    public List boletinsAbertoSemEnvio() {

        BoletimTO boletimTO = new BoletimTO();
        ArrayList listaPesq = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusBoletim");
        pesquisa.setValor(1L);
        listaPesq.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("idExtBoletim");
        pesquisa2.setValor(0L);
        listaPesq.add(pesquisa2);

        return boletimTO.get(listaPesq);

    }

    public List boletinsFechado() {
        BoletimTO boletimMMTO = new BoletimTO();
        return boletimMMTO.get("statusBoletim", 2L);
    }

    public List dadosApontamento() {
        ApontTO apontTO = new ApontTO();
        return apontTO.get("statusApont", 0L);
    }


    //////////////////////VERIFICAÇÃO DE DADOS///////////////////////////

    public Boolean verifBolAbertoSemEnvio() {
        return boletinsAbertoSemEnvio().size() > 0;
    }

    public Boolean verifBolFechado() {
        return boletinsFechado().size() > 0;
    }

    public Boolean verifAponta() {

        List apontList = dadosApontamento();
        for (int i = 0; i < apontList.size(); i++) {
            ApontTO apontTO = (ApontTO) apontList.get(i);
            if(apontTO.getIdExtBolApont() == 0){
                BoletimTO boletimTO = new BoletimTO();
                List boletimList = boletimTO.get("idBoletim", apontTO.getIdBolApont());
                boletimTO = (BoletimTO) boletimList.get(0);
                boletimList.clear();
                apontTO.setIdExtBolApont(boletimTO.getIdExtBoletim());
                apontTO.update();
            }
        }

        return dadosApontamento().size() > 0;
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
        if ((!verifBolFechado())
                && (!verifBolAbertoSemEnvio())
                && (!verifAponta())) {
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
