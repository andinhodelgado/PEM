package br.com.usinasantafe.pem.to.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pem.pst.Entidade;

@DatabaseTable(tableName="tbescalatrabest")
public class EscalaTrabTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEscalaTrab;
    @DatabaseField
    private String horarioEntEscalaTrab;
    @DatabaseField
    private String horarioSaiEscalaTrab;

    public EscalaTrabTO() {
    }

    public Long getIdEscalaTrab() {
        return idEscalaTrab;
    }

    public void setIdEscalaTrab(Long idEscalaTrab) {
        this.idEscalaTrab = idEscalaTrab;
    }

    public String getHorarioEntEscalaTrab() {
        return horarioEntEscalaTrab;
    }

    public void setHorarioEntEscalaTrab(String horarioEntEscalaTrab) {
        this.horarioEntEscalaTrab = horarioEntEscalaTrab;
    }

    public String getHorarioSaiEscalaTrab() {
        return horarioSaiEscalaTrab;
    }

    public void setHorarioSaiEscalaTrab(String horarioSaiEscalaTrab) {
        this.horarioSaiEscalaTrab = horarioSaiEscalaTrab;
    }
}
