package br.com.usinasantafe.pem.to.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pem.pst.Entidade;

@DatabaseTable(tableName="tbapontavar")
public class ApontTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idApont;
    @DatabaseField
    private Long entregadorApont;
    @DatabaseField
    private Long recebedorApont;
    @DatabaseField
    private Long osApont;
    @DatabaseField
    private Long itemOSApont;
    @DatabaseField
    private Long idProdApont;
    @DatabaseField
    private Long equipApont;
    @DatabaseField
    private Double qtdeProdApont;
    @DatabaseField
    private String dthrApont;
    @DatabaseField
    private Long statusApont; //1 - Esta apontando ainda; 2 - Terminou o Apontamento

    public ApontTO() {
    }

    public Long getIdApont() {
        return idApont;
    }

    public void setIdApont(Long idApont) {
        this.idApont = idApont;
    }

    public Long getEntregadorApont() {
        return entregadorApont;
    }

    public void setEntregadorApont(Long entregadorApont) {
        this.entregadorApont = entregadorApont;
    }

    public Long getRecebedorApont() {
        return recebedorApont;
    }

    public void setRecebedorApont(Long recebedorApont) {
        this.recebedorApont = recebedorApont;
    }

    public Long getOsApont() {
        return osApont;
    }

    public void setOsApont(Long osApont) {
        this.osApont = osApont;
    }

    public Long getItemOSApont() {
        return itemOSApont;
    }

    public void setItemOSApont(Long itemOSApont) {
        this.itemOSApont = itemOSApont;
    }

    public Long getIdProdApont() {
        return idProdApont;
    }

    public void setIdProdApont(Long idProdApont) {
        this.idProdApont = idProdApont;
    }

    public Long getEquipApont() {
        return equipApont;
    }

    public void setEquipApont(Long equipApont) {
        this.equipApont = equipApont;
    }

    public Double getQtdeProdApont() {
        return qtdeProdApont;
    }

    public void setQtdeProdApont(Double qtdeProdApont) {
        this.qtdeProdApont = qtdeProdApont;
    }

    public String getDthrApont() {
        return dthrApont;
    }

    public void setDthrApont(String dthrApont) {
        this.dthrApont = dthrApont;
    }

    public Long getStatusApont() {
        return statusApont;
    }

    public void setStatusApont(Long statusApont) {
        this.statusApont = statusApont;
    }

}
