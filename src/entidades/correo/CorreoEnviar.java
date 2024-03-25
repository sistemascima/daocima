/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.correo;

import entidades.SUsuario;
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
 * @author Daniel-SIS
 */
@Entity
@Table(name = "correo_enviar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CorreoEnviar.findAll", query = "SELECT c FROM CorreoEnviar c"),
    @NamedQuery(name = "CorreoEnviar.findByPiCorrenviConsecutivo", query = "SELECT c FROM CorreoEnviar c WHERE c.piCorrenviConsecutivo = :piCorrenviConsecutivo"),
    @NamedQuery(name = "CorreoEnviar.findByBody", query = "SELECT c FROM CorreoEnviar c WHERE c.sCorrenviBody = :sCorrenviBody"),
    @NamedQuery(name = "CorreoEnviar.findBySCorrenviSubject", query = "SELECT c FROM CorreoEnviar c WHERE c.sCorrenviSubject = :sCorrenviSubject"),
    @NamedQuery(name = "CorreoEnviar.findByDCorrenviEnviado", query = "SELECT c FROM CorreoEnviar c WHERE c.dCorrenviEnviado = :dCorrenviEnviado"),
    @NamedQuery(name = "CorreoEnviar.findByDCorrenviCreacion", query = "SELECT c FROM CorreoEnviar c WHERE c.dCorrenviCreacion = :dCorrenviCreacion"),
    @NamedQuery(name = "CorreoEnviar.findByDCorrenviUltimodi", query = "SELECT c FROM CorreoEnviar c WHERE c.dCorrenviUltimodi = :dCorrenviUltimodi")})
public class CorreoEnviar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_correnvi_consecutivo")
    private Integer piCorrenviConsecutivo;
    @Basic(optional = false)
    @Lob
    @Column(name = "s_correnvi_destinatarios")
    private String sCorrenviDestinatarios;
    @Basic(optional = false)
    @Lob
    @Column(name = "s_correnvi_destcopi")
    private String sCorrenviDestcopi;
    @Basic(optional = false)
    @Column(name = "s_correnvi_subject")
    private String sCorrenviSubject;
    @Basic(optional = false)
    @Lob
    @Column(name = "s_correnvi_body")
    private String sCorrenviBody;
    @Column(name = "d_correnvi_enviado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCorrenviEnviado;
    @Basic(optional = false)
    @Column(name = "d_correnvi_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCorrenviCreacion;
    @Column(name = "d_correnvi_ultimodi")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dCorrenviUltimodi;
    @JoinColumn(name = "fs_correnvi_usuacrea", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne(optional = false)
    private SUsuario fsCorrenviUsuacrea;
    @JoinColumn(name = "fs_correnvi_usuultmod", referencedColumnName = "ps_usuario_codigo")
    @ManyToOne
    private SUsuario fsCorrenviUsuultmod;

    public CorreoEnviar() {
    }

    public CorreoEnviar(Integer piCorrenviConsecutivo) {
        this.piCorrenviConsecutivo = piCorrenviConsecutivo;
    }

    public CorreoEnviar(Integer piCorrenviConsecutivo, String sCorrenviDestinatarios, String sCorrenviDestcopi, String sCorrenviSubject, String sCorrenviBody, Date dCorrenviCreacion) {
        this.piCorrenviConsecutivo = piCorrenviConsecutivo;
        this.sCorrenviDestinatarios = sCorrenviDestinatarios;
        this.sCorrenviDestcopi = sCorrenviDestcopi;
        this.sCorrenviSubject = sCorrenviSubject;
        this.sCorrenviBody = sCorrenviBody;
        this.dCorrenviCreacion = dCorrenviCreacion;
    }
    
    public CorreoEnviar(String sCorrenviDestinatarios, String sCorrenviDestcopi, String sCorrenviSubject, String sCorrenviBody)
    {
        this.sCorrenviDestinatarios = sCorrenviDestinatarios;
        this.sCorrenviDestcopi = sCorrenviDestcopi;
        this.sCorrenviSubject = sCorrenviSubject;
        this.sCorrenviBody = sCorrenviBody;
    }

    public Integer getPiCorrenviConsecutivo() {
        return piCorrenviConsecutivo;
    }

    public void setPiCorrenviConsecutivo(Integer piCorrenviConsecutivo) {
        this.piCorrenviConsecutivo = piCorrenviConsecutivo;
    }

    public String getSCorrenviDestinatarios() {
        return sCorrenviDestinatarios;
    }

    public void setSCorrenviDestinatarios(String sCorrenviDestinatarios) {
        this.sCorrenviDestinatarios = sCorrenviDestinatarios;
    }

    public String getSCorrenviDestcopi() {
        return sCorrenviDestcopi;
    }

    public void setSCorrenviDestcopi(String sCorrenviDestcopi) {
        this.sCorrenviDestcopi = sCorrenviDestcopi;
    }

    public String getSCorrenviSubject() {
        return sCorrenviSubject;
    }

    public void setSCorrenviSubject(String sCorrenviSubject) {
        this.sCorrenviSubject = sCorrenviSubject;
    }

    public String getSCorrenviBody() {
        return sCorrenviBody;
    }

    public void setSCorrenviBody(String sCorrenviBody) {
        this.sCorrenviBody = sCorrenviBody;
    }

    public Date getDCorrenviEnviado() {
        return dCorrenviEnviado;
    }

    public void setDCorrenviEnviado(Date dCorrenviEnviado) {
        this.dCorrenviEnviado = dCorrenviEnviado;
    }

    public Date getDCorrenviCreacion() {
        return dCorrenviCreacion;
    }

    public void setDCorrenviCreacion(Date dCorrenviCreacion) {
        this.dCorrenviCreacion = dCorrenviCreacion;
    }

    public Date getDCorrenviUltimodi() {
        return dCorrenviUltimodi;
    }

    public void setDCorrenviUltimodi(Date dCorrenviUltimodi) {
        this.dCorrenviUltimodi = dCorrenviUltimodi;
    }

    public SUsuario getFsCorrenviUsuacrea() {
        return fsCorrenviUsuacrea;
    }

    public void setFsCorrenviUsuacrea(SUsuario fsCorrenviUsuacrea) {
        this.fsCorrenviUsuacrea = fsCorrenviUsuacrea;
    }

    public SUsuario getFsCorrenviUsuultmod() {
        return fsCorrenviUsuultmod;
    }

    public void setFsCorrenviUsuultmod(SUsuario fsCorrenviUsuultmod) {
        this.fsCorrenviUsuultmod = fsCorrenviUsuultmod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piCorrenviConsecutivo != null ? piCorrenviConsecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CorreoEnviar)) {
            return false;
        }
        CorreoEnviar other = (CorreoEnviar) object;
        if ((this.piCorrenviConsecutivo == null && other.piCorrenviConsecutivo != null) || (this.piCorrenviConsecutivo != null && !this.piCorrenviConsecutivo.equals(other.piCorrenviConsecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.correo.CorreoEnviar[ piCorrenviConsecutivo=" + piCorrenviConsecutivo + " ]";
    }
    
}
