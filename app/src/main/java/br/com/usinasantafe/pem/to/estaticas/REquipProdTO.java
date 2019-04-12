package br.com.usinasantafe.pem.to.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pem.pst.Entidade;

@DatabaseTable(tableName="tbrequipprodest")
public class REquipProdTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idREquipProd;
    @DatabaseField
    private Long idEquip;
    @DatabaseField
    private Long idProd;

    public REquipProdTO() {
    }

    public Long getIdREquipProd() {
        return idREquipProd;
    }

    public void setIdREquipProd(Long idREquipProd) {
        this.idREquipProd = idREquipProd;
    }

    public Long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(Long idEquip) {
        this.idEquip = idEquip;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

}
