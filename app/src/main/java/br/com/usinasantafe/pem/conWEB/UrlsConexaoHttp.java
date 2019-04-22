package br.com.usinasantafe.pem.conWEB;

public class UrlsConexaoHttp {

    public static String urlPrincipal = "http://www.usinasantafe.com.br/pemdev/";
    public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pemdev/";

    public static String localPSTEstatica = "br.com.usinasantafe.pem.to.estaticas.";
    public static String localUrl = "br.com.usinasantafe.pem.conWEB.UrlsConexaoHttp";

    public static String ColabTO = urlPrincipal + "colab.php";
    public static String ComponenteTO = urlPrincipal + "componente.php";
    public static String ServicoTO = urlPrincipal + "servico.php";
    public static String EquipTO = urlPrincipal + "equip.php";
    public static String REquipProdTO = urlPrincipal + "requipprod.php";
    public static String ProdTO = urlPrincipal + "prod.php";

    public UrlsConexaoHttp() {
        // TODO Auto-generated constructor stub
    }

    public String getsInsertApont() {
        return urlPrincEnvio + "inserirapont.php";
    }

    public String urlVerifica(String classe) {
        String retorno = "";
        if (classe.equals("Equip")) {
            retorno = urlPrincEnvio + "verequip.php";
        } else if (classe.equals("Colab")) {
            retorno = urlPrincEnvio + "colab.php";
        } else if (classe.equals("Atualiza")) {
            retorno = urlPrincEnvio + "atualizaaplic.php";
        } else if (classe.equals("OS")) {
            retorno = urlPrincEnvio + "verifos.php";
        }
        return retorno;
    }

}
