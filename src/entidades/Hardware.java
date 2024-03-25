/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HelpDesk
 */
@Entity
@Table(name = "hardware")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hardware.findAll", query = "SELECT h FROM Hardware h"),
    @NamedQuery(name = "Hardware.findById", query = "SELECT h FROM Hardware h WHERE h.id = :id"),
    @NamedQuery(name = "Hardware.findByDeviceid", query = "SELECT h FROM Hardware h WHERE h.deviceid = :deviceid"),
    @NamedQuery(name = "Hardware.findByName", query = "SELECT h FROM Hardware h WHERE h.name = :name"),
    @NamedQuery(name = "Hardware.findByWorkgroup", query = "SELECT h FROM Hardware h WHERE h.workgroup = :workgroup"),
    @NamedQuery(name = "Hardware.findByUserdomain", query = "SELECT h FROM Hardware h WHERE h.userdomain = :userdomain"),
    @NamedQuery(name = "Hardware.findByOsname", query = "SELECT h FROM Hardware h WHERE h.osname = :osname"),
    @NamedQuery(name = "Hardware.findByOsversion", query = "SELECT h FROM Hardware h WHERE h.osversion = :osversion"),
    @NamedQuery(name = "Hardware.findByOscomments", query = "SELECT h FROM Hardware h WHERE h.oscomments = :oscomments"),
    @NamedQuery(name = "Hardware.findByProcessort", query = "SELECT h FROM Hardware h WHERE h.processort = :processort"),
    @NamedQuery(name = "Hardware.findByProcessors", query = "SELECT h FROM Hardware h WHERE h.processors = :processors"),
    @NamedQuery(name = "Hardware.findByProcessorn", query = "SELECT h FROM Hardware h WHERE h.processorn = :processorn"),
    @NamedQuery(name = "Hardware.findByMemory", query = "SELECT h FROM Hardware h WHERE h.memory = :memory"),
    @NamedQuery(name = "Hardware.findBySwap", query = "SELECT h FROM Hardware h WHERE h.swap = :swap"),
    @NamedQuery(name = "Hardware.findByIpaddr", query = "SELECT h FROM Hardware h WHERE h.ipaddr = :ipaddr"),
    @NamedQuery(name = "Hardware.findByEtime", query = "SELECT h FROM Hardware h WHERE h.etime = :etime"),
    @NamedQuery(name = "Hardware.findByLastdate", query = "SELECT h FROM Hardware h WHERE h.lastdate = :lastdate"),
    @NamedQuery(name = "Hardware.findByLastcome", query = "SELECT h FROM Hardware h WHERE h.lastcome = :lastcome"),
    @NamedQuery(name = "Hardware.findByQuality", query = "SELECT h FROM Hardware h WHERE h.quality = :quality"),
    @NamedQuery(name = "Hardware.findByFidelity", query = "SELECT h FROM Hardware h WHERE h.fidelity = :fidelity"),
    @NamedQuery(name = "Hardware.findByUserid", query = "SELECT h FROM Hardware h WHERE h.userid = :userid"),
    @NamedQuery(name = "Hardware.findByType", query = "SELECT h FROM Hardware h WHERE h.type = :type"),
    @NamedQuery(name = "Hardware.findByDescription", query = "SELECT h FROM Hardware h WHERE h.description = :description"),
    @NamedQuery(name = "Hardware.findByWincompany", query = "SELECT h FROM Hardware h WHERE h.wincompany = :wincompany"),
    @NamedQuery(name = "Hardware.findByWinowner", query = "SELECT h FROM Hardware h WHERE h.winowner = :winowner"),
    @NamedQuery(name = "Hardware.findByWinprodid", query = "SELECT h FROM Hardware h WHERE h.winprodid = :winprodid"),
    @NamedQuery(name = "Hardware.findByWinprodkey", query = "SELECT h FROM Hardware h WHERE h.winprodkey = :winprodkey"),
    @NamedQuery(name = "Hardware.findByUseragent", query = "SELECT h FROM Hardware h WHERE h.useragent = :useragent"),
    @NamedQuery(name = "Hardware.findByDns", query = "SELECT h FROM Hardware h WHERE h.dns = :dns"),
    @NamedQuery(name = "Hardware.findByDefaultgateway", query = "SELECT h FROM Hardware h WHERE h.defaultgateway = :defaultgateway"),
    @NamedQuery(name = "Hardware.findByChecksum", query = "SELECT h FROM Hardware h WHERE h.checksum = :checksum"),
    @NamedQuery(name = "Hardware.findBySstate", query = "SELECT h FROM Hardware h WHERE h.sstate = :sstate"),
    @NamedQuery(name = "Hardware.findByIpsrc", query = "SELECT h FROM Hardware h WHERE h.ipsrc = :ipsrc"),
    @NamedQuery(name = "Hardware.findByUuid", query = "SELECT h FROM Hardware h WHERE h.uuid = :uuid"),
    @NamedQuery(name = "Hardware.findByUsuarioCodigo", query = "SELECT h FROM Hardware h WHERE h.usuarioCodigo = :usuarioCodigo"),
    @NamedQuery(name = "Hardware.findByUbicacion", query = "SELECT h FROM Hardware h WHERE h.ubicacion = :ubicacion"),
    @NamedQuery(name = "Hardware.findByUltimomantenimiento", query = "SELECT h FROM Hardware h WHERE h.ultimomantenimiento = :ultimomantenimiento")})
public class Hardware implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pi_hardware_id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "pi_hardware_deviceid", nullable = false, length = 255)
    private String deviceid;
    @Column(name = "s_hardware_name", length = 255)
    private String name;
    @Column(name = "s_hardware_workgroup", length = 255)
    private String workgroup;
    @Column(name = "s_hardware_userdomain", length = 255)
    private String userdomain;
    @Column(name = "s_hardware_osname", length = 255)
    private String osname;
    @Column(name = "s_hardware_osversion", length = 255)
    private String osversion;
    @Column(name = "s_hardware_oscoments", length = 255)
    private String oscomments;
    @Column(name = "s_hardware_processort", length = 255)
    private String processort;
    @Column(name = "i_hardware_processors")
    private Integer processors;
    @Column(name = "i_hardware_processorn")
    private Short processorn;
    @Column(name = "i_hardware_memory")
    private Integer memory;
    @Column(name = "i_hardware_swap")
    private Integer swap;
    @Column(name = "s_hardware_ipaddr", length = 255)
    private String ipaddr;
    @Column(name = "d_hardware_etime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date etime;
    @Column(name = "d_hardware_lastdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastdate;
    @Column(name = "d_hardware_lastcome")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastcome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "db_hardware_quality", precision = 7, scale = 4)
    private BigDecimal quality;
    @Column(name = "i_hardware_fidelity")
    private BigInteger fidelity;
    @Column(name = "s_hardware_userid", length = 255)
    private String userid;
    @Column(name = "s_hardware_type")
    private Integer type;
    @Column(name = "s_hardware_description", length = 255)
    private String description;
    @Column(name = "s_hardware_wincompany", length = 255)
    private String wincompany;
    @Column(name = "s_hardware_winowner", length = 255)
    private String winowner;
    @Column(name = "s_hardware_winprodid", length = 255)
    private String winprodid;
    @Column(name = "s_hardware_winprodkey", length = 255)
    private String winprodkey;
    @Column(name = "s_hardware_useragent", length = 50)
    private String useragent;
    @Column(name = "s_hardware_dns", length = 255)
    private String dns;
    @Column(name = "s_hardware_defaultgateway", length = 255)
    private String defaultgateway;
    @Column(name = "i_hardware_checksum")
    private Integer checksum;
    @Column(name = "i_hardware_sstate")
    private Integer sstate;
    @Column(name = "s_hardware_ipsrc", length = 15)
    private String ipsrc;
    @Column(name = "s_hardware_uuid", length = 255)
    private String uuid;
    @Basic(optional = false)
    @Column(name = "s_hardware_ubicacion", nullable = false, length = 255)
    private String ubicacion;
    @Basic(optional = false)
    @Column(name = "s_hardware_ultmant", nullable = false, length = 255)
    private String ultimomantenimiento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hardware")
    private Bios bios;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "hardware")
    private Accountinfo accountinfo;
    @JoinColumn(name = "ps_hardware_usuacodi", referencedColumnName = "ps_usuario_codigo", nullable = false)
    @ManyToOne(optional = false)
    private SUsuario usuarioCodigo;
    

    public Hardware() {
    }

    public Hardware(Integer id) {
        this.id = id;
    }

    public Hardware(Integer id, String deviceid, String ubicacion, String ultimomantenimiento) {
        this.id = id;
        this.deviceid = deviceid;
        this.ubicacion = ubicacion;
        this.ultimomantenimiento = ultimomantenimiento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkgroup() {
        return workgroup;
    }

    public void setWorkgroup(String workgroup) {
        this.workgroup = workgroup;
    }

    public String getUserdomain() {
        return userdomain;
    }

    public void setUserdomain(String userdomain) {
        this.userdomain = userdomain;
    }

    public String getOsname() {
        return osname;
    }

    public void setOsname(String osname) {
        this.osname = osname;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getOscomments() {
        return oscomments;
    }

    public void setOscomments(String oscomments) {
        this.oscomments = oscomments;
    }

    public String getProcessort() {
        return processort;
    }

    public void setProcessort(String processort) {
        this.processort = processort;
    }

    public Integer getProcessors() {
        return processors;
    }

    public void setProcessors(Integer processors) {
        this.processors = processors;
    }

    public Short getProcessorn() {
        return processorn;
    }

    public void setProcessorn(Short processorn) {
        this.processorn = processorn;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getSwap() {
        return swap;
    }

    public void setSwap(Integer swap) {
        this.swap = swap;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public Date getLastcome() {
        return lastcome;
    }

    public void setLastcome(Date lastcome) {
        this.lastcome = lastcome;
    }

    public BigDecimal getQuality() {
        return quality;
    }

    public void setQuality(BigDecimal quality) {
        this.quality = quality;
    }

    public BigInteger getFidelity() {
        return fidelity;
    }

    public void setFidelity(BigInteger fidelity) {
        this.fidelity = fidelity;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWincompany() {
        return wincompany;
    }

    public void setWincompany(String wincompany) {
        this.wincompany = wincompany;
    }

    public String getWinowner() {
        return winowner;
    }

    public void setWinowner(String winowner) {
        this.winowner = winowner;
    }

    public String getWinprodid() {
        return winprodid;
    }

    public void setWinprodid(String winprodid) {
        this.winprodid = winprodid;
    }

    public String getWinprodkey() {
        return winprodkey;
    }

    public void setWinprodkey(String winprodkey) {
        this.winprodkey = winprodkey;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getDefaultgateway() {
        return defaultgateway;
    }

    public void setDefaultgateway(String defaultgateway) {
        this.defaultgateway = defaultgateway;
    }

    public Integer getChecksum() {
        return checksum;
    }

    public void setChecksum(Integer checksum) {
        this.checksum = checksum;
    }

    public Integer getSstate() {
        return sstate;
    }

    public void setSstate(Integer sstate) {
        this.sstate = sstate;
    }

    public String getIpsrc() {
        return ipsrc;
    }

    public void setIpsrc(String ipsrc) {
        this.ipsrc = ipsrc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getUltimomantenimiento() {
        return ultimomantenimiento;
    }

    public void setUltimomantenimiento(String ultimomantenimiento) {
        this.ultimomantenimiento = ultimomantenimiento;
    }

    public Bios getBios() {
        return bios;
    }

    public void setBios(Bios bios) {
        this.bios = bios;
    }

    public Accountinfo getAccountinfo() {
        return accountinfo;
    }

    public void setAccountinfo(Accountinfo accountinfo) {
        this.accountinfo = accountinfo;
    }

    public SUsuario getUsuarioCodigo() {
        return usuarioCodigo;
    }

    public void setUsuarioCodigo(SUsuario usuarioCodigo) {
        this.usuarioCodigo = usuarioCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hardware)) {
            return false;
        }
        Hardware other = (Hardware) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Hardware[ id=" + id + " ]";
    }
    
}
