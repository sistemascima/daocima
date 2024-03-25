/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "resultado_calidad_muestra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultadoCalidadMuestra.findAll", query = "SELECT r FROM ResultadoCalidadMuestra r"),
    @NamedQuery(name = "ResultadoCalidadMuestra.findByPiResultadoCalidadMuestra", query = "SELECT r FROM ResultadoCalidadMuestra r WHERE r.piResultadoCalidadMuestra = :piResultadoCalidadMuestra")})
public class ResultadoCalidadMuestra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_resultado_calidad_muestra", nullable = false)
    private Integer piResultadoCalidadMuestra;
    @JoinColumn(name = "fki_restultado_calidad_muestra_muestra", referencedColumnName = "pi_muestra_id")
    @ManyToOne
    private Muestra fkiRestultadoCalidadMuestraMuestra;
    @JoinColumn(name = "fki_resultado_calidad_muestra_resultado", referencedColumnName = "pi_resultado_id")
    @ManyToOne
    private Resultado fkiResultadoCalidadMuestraResultado;
     @JoinColumn(name = "fki_resultado_calidad_muestra_variable", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fkiResultadoCalidadMuestraVariable;
    
    
    public ResultadoCalidadMuestra() {
    }

    public ResultadoCalidadMuestra(Integer piResultadoCalidadMuestra) {
        this.piResultadoCalidadMuestra = piResultadoCalidadMuestra;
    }

    public Integer getPiResultadoCalidadMuestra() {
        return piResultadoCalidadMuestra;
    }

    public void setPiResultadoCalidadMuestra(Integer piResultadoCalidadMuestra) {
        this.piResultadoCalidadMuestra = piResultadoCalidadMuestra;
    }

    public Muestra getFkiRestultadoCalidadMuestraMuestra() {
        return fkiRestultadoCalidadMuestraMuestra;
    }

    public void setFkiRestultadoCalidadMuestraMuestra(Muestra fkiRestultadoCalidadMuestraMuestra) {
        this.fkiRestultadoCalidadMuestraMuestra = fkiRestultadoCalidadMuestraMuestra;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piResultadoCalidadMuestra != null ? piResultadoCalidadMuestra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoCalidadMuestra)) {
            return false;
        }
        ResultadoCalidadMuestra other = (ResultadoCalidadMuestra) object;
        if ((this.piResultadoCalidadMuestra == null && other.piResultadoCalidadMuestra != null) || (this.piResultadoCalidadMuestra != null && !this.piResultadoCalidadMuestra.equals(other.piResultadoCalidadMuestra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ResultadoCalidadMuestra[ piResultadoCalidadMuestra=" + piResultadoCalidadMuestra + " ]";
    }

    public Resultado getFkiResultadoCalidadMuestraResultado() {
        return fkiResultadoCalidadMuestraResultado;
    }

    public void setFkiResultadoCalidadMuestraResultado(Resultado fkiResultadoCalidadMuestraResultado) {
        this.fkiResultadoCalidadMuestraResultado = fkiResultadoCalidadMuestraResultado;
    }

    public VariableAnalisis getFkiResultadoCalidadMuestraVariable() {
        return fkiResultadoCalidadMuestraVariable;
    }

    public void setFkiResultadoCalidadMuestraVariable(VariableAnalisis fkiResultadoCalidadMuestraVariable) {
        this.fkiResultadoCalidadMuestraVariable = fkiResultadoCalidadMuestraVariable;
    }

    
    
    
    
    
}
