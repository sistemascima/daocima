/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SISTEMAS
 */
@Entity
@Table(name = "foto_hidrobiologia", catalog = "cima_desar", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FotoHidrobiologia.findAll", query = "SELECT f FROM FotoHidrobiologia f")
    , @NamedQuery(name = "FotoHidrobiologia.findByPiFotohidroId", query = "SELECT f FROM FotoHidrobiologia f WHERE f.piFotohidroId = :piFotohidroId")
    , @NamedQuery(name = "FotoHidrobiologia.findByDFotohidroFechacreac", query = "SELECT f FROM FotoHidrobiologia f WHERE f.dFotohidroFechacreac = :dFotohidroFechacreac")
    , @NamedQuery(name = "FotoHidrobiologia.findByDFotohidroFechamodi", query = "SELECT f FROM FotoHidrobiologia f WHERE f.dFotohidroFechamodi = :dFotohidroFechamodi")})
public class FotoHidrobiologia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_fotohidro_id", nullable = false)
    private Integer piFotohidroId;
    @Lob
    @Column(name = "by_fotohidro_imagen")
    private byte[] byFotohidroImagen;
    @Column(name = "d_fotohidro_fechacreac")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dFotohidroFechacreac;
    @Column(name = "d_fotohidro_fechamodi", length = 45)
    private String dFotohidroFechamodi;
    @JoinColumn(name = "fi_fotohidro_dathidr", referencedColumnName = "pi_dato_hidro")
    @ManyToOne
    private DatoHidrobiologia fiFotohidroDathidr;
    @JoinColumn(name = "fi_fotohidro_varianal", referencedColumnName = "pi_varianal_id")
    @ManyToOne
    private VariableAnalisis fiFotohidroVarianal;
    @JoinColumn(name = "fs_fotohidro_reporte", referencedColumnName = "pi_reporte_id")
    @ManyToOne
    private Reporte fsFotohidroReporte;
    @JoinColumn(name = "fs_fotohidro_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsFotohidroUsuacrea;
    @JoinColumn(name = "fs_fotohidro_usuamodi", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsFotohidroUsuamodi;

    public FotoHidrobiologia() {
    }

    public FotoHidrobiologia(Integer piFotohidroId) {
        this.piFotohidroId = piFotohidroId;
    }

    public Integer getPiFotohidroId() {
        return piFotohidroId;
    }

    public void setPiFotohidroId(Integer piFotohidroId) {
        this.piFotohidroId = piFotohidroId;
    }

    public byte[] getByFotohidroImagen() {
        return byFotohidroImagen;
    }

    public void setByFotohidroImagen(byte[] byFotohidroImagen) {
        this.byFotohidroImagen = byFotohidroImagen;
    }

    public Date getDFotohidroFechacreac() {
        return dFotohidroFechacreac;
    }

    public void setDFotohidroFechacreac(Date dFotohidroFechacreac) {
        this.dFotohidroFechacreac = dFotohidroFechacreac;
    }

    public String getDFotohidroFechamodi() {
        return dFotohidroFechamodi;
    }

    public void setDFotohidroFechamodi(String dFotohidroFechamodi) {
        this.dFotohidroFechamodi = dFotohidroFechamodi;
    }

    public DatoHidrobiologia getFiFotohidroDathidr() {
        return fiFotohidroDathidr;
    }

    public void setFiFotohidroDathidr(DatoHidrobiologia fiFotohidroDathidr) {
        this.fiFotohidroDathidr = fiFotohidroDathidr;
    }

    public VariableAnalisis getFiFotohidroVarianal() {
        return fiFotohidroVarianal;
    }

    public void setFiFotohidroVarianal(VariableAnalisis fiFotohidroVarianal) {
        this.fiFotohidroVarianal = fiFotohidroVarianal;
    }

    public Reporte getFsFotohidroReporte() {
        return fsFotohidroReporte;
    }

    public void setFsFotohidroReporte(Reporte fsFotohidroReporte) {
        this.fsFotohidroReporte = fsFotohidroReporte;
    }

    public SUsuario getFsFotohidroUsuacrea() {
        return fsFotohidroUsuacrea;
    }

    public void setFsFotohidroUsuacrea(SUsuario fsFotohidroUsuacrea) {
        this.fsFotohidroUsuacrea = fsFotohidroUsuacrea;
    }

    public SUsuario getFsFotohidroUsuamodi() {
        return fsFotohidroUsuamodi;
    }

    public void setFsFotohidroUsuamodi(SUsuario fsFotohidroUsuamodi) {
        this.fsFotohidroUsuamodi = fsFotohidroUsuamodi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piFotohidroId != null ? piFotohidroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FotoHidrobiologia)) {
            return false;
        }
        FotoHidrobiologia other = (FotoHidrobiologia) object;
        if ((this.piFotohidroId == null && other.piFotohidroId != null) || (this.piFotohidroId != null && !this.piFotohidroId.equals(other.piFotohidroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.FotoHidrobiologia[ piFotohidroId=" + piFotohidroId + " ]";
    }
    
}
