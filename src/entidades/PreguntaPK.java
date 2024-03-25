/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Jhon
 */
@Embeddable
public class PreguntaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "pfi_pregunta_formeval")
    private int pfiPreguntaFormeval;
    @Basic(optional = false)
    @Column(name = "pfs_pregunta_aspecto")
    private char pfsPreguntaAspecto;
    @Basic(optional = false)
    @Column(name = "pi_pregunta_orden")
    private int piPreguntaOrden;

    public PreguntaPK() {
    }

    public PreguntaPK(int pfiPreguntaFormeval, char pfsPreguntaAspecto, int piPreguntaOrden) {
        this.pfiPreguntaFormeval = pfiPreguntaFormeval;
        this.pfsPreguntaAspecto = pfsPreguntaAspecto;
        this.piPreguntaOrden = piPreguntaOrden;
    }

    public int getPfiPreguntaFormeval() {
        return pfiPreguntaFormeval;
    }

    public void setPfiPreguntaFormeval(int pfiPreguntaFormeval) {
        this.pfiPreguntaFormeval = pfiPreguntaFormeval;
    }

    public char getPfsPreguntaAspecto() {
        return pfsPreguntaAspecto;
    }

    public void setPfsPreguntaAspecto(char pfsPreguntaAspecto) {
        this.pfsPreguntaAspecto = pfsPreguntaAspecto;
    }

    public int getPiPreguntaOrden() {
        return piPreguntaOrden;
    }

    public void setPiPreguntaOrden(int piPreguntaOrden) {
        this.piPreguntaOrden = piPreguntaOrden;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) pfiPreguntaFormeval;
        hash += (int) pfsPreguntaAspecto;
        hash += (int) piPreguntaOrden;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaPK)) {
            return false;
        }
        PreguntaPK other = (PreguntaPK) object;
        if (this.pfiPreguntaFormeval != other.pfiPreguntaFormeval) {
            return false;
        }
        if (this.pfsPreguntaAspecto != other.pfsPreguntaAspecto) {
            return false;
        }
        if (this.piPreguntaOrden != other.piPreguntaOrden) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.PreguntaPK[ pfiPreguntaFormeval=" + pfiPreguntaFormeval + ", pfsPreguntaAspecto=" + pfsPreguntaAspecto + ", piPreguntaOrden=" + piPreguntaOrden + " ]";
    }
    
}
