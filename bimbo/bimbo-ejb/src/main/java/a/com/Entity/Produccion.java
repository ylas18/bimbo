/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a.com.Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Yesid
 */
@Entity
@Table(name = "produccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produccion.findAll", query = "SELECT p FROM Produccion p"),
    @NamedQuery(name = "Produccion.findByIdProduccion", query = "SELECT p FROM Produccion p WHERE p.idProduccion = :idProduccion"),
    @NamedQuery(name = "Produccion.findByCantidadMaximaPan", query = "SELECT p FROM Produccion p WHERE p.cantidadMaximaPan = :cantidadMaximaPan"),
    @NamedQuery(name = "Produccion.findByCantidadMinimaPan", query = "SELECT p FROM Produccion p WHERE p.cantidadMinimaPan = :cantidadMinimaPan"),
    @NamedQuery(name = "Produccion.findByCantidadPan", query = "SELECT p FROM Produccion p WHERE p.cantidadPan = :cantidadPan"),
    @NamedQuery(name = "Produccion.findByFecha", query = "SELECT p FROM Produccion p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Produccion.findByTiempoAmasado", query = "SELECT p FROM Produccion p WHERE p.tiempoAmasado = :tiempoAmasado"),
    @NamedQuery(name = "Produccion.findByTiempoElaboracion", query = "SELECT p FROM Produccion p WHERE p.tiempoElaboracion = :tiempoElaboracion"),
    @NamedQuery(name = "Produccion.findByTiempoEmpaque", query = "SELECT p FROM Produccion p WHERE p.tiempoEmpaque = :tiempoEmpaque"),
    @NamedQuery(name = "Produccion.findByTiempoEnfriado", query = "SELECT p FROM Produccion p WHERE p.tiempoEnfriado = :tiempoEnfriado"),
    @NamedQuery(name = "Produccion.findByTiempoMoldeado", query = "SELECT p FROM Produccion p WHERE p.tiempoMoldeado = :tiempoMoldeado")})
public class Produccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produccion")
    private Integer idProduccion;
    @Column(name = "cantidad_maxima_pan")
    private Integer cantidadMaximaPan;
    @Column(name = "cantidad_minima_pan")
    private Integer cantidadMinimaPan;
    @Column(name = "cantidad_pan")
    private Integer cantidadPan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_amasado")
    private int tiempoAmasado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_elaboracion")
    private int tiempoElaboracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_empaque")
    private int tiempoEmpaque;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_enfriado")
    private int tiempoEnfriado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_moldeado")
    private int tiempoMoldeado;

    public Produccion() {
    }

    public Produccion(Integer idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Produccion(Integer idProduccion, Date fecha, int tiempoAmasado, int tiempoElaboracion, int tiempoEmpaque, int tiempoEnfriado, int tiempoMoldeado) {
        this.idProduccion = idProduccion;
        this.fecha = fecha;
        this.tiempoAmasado = tiempoAmasado;
        this.tiempoElaboracion = tiempoElaboracion;
        this.tiempoEmpaque = tiempoEmpaque;
        this.tiempoEnfriado = tiempoEnfriado;
        this.tiempoMoldeado = tiempoMoldeado;
    }

    public Integer getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(Integer idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Integer getCantidadMaximaPan() {
        return cantidadMaximaPan;
    }

    public void setCantidadMaximaPan(Integer cantidadMaximaPan) {
        this.cantidadMaximaPan = cantidadMaximaPan;
    }

    public Integer getCantidadMinimaPan() {
        return cantidadMinimaPan;
    }

    public void setCantidadMinimaPan(Integer cantidadMinimaPan) {
        this.cantidadMinimaPan = cantidadMinimaPan;
    }

    public Integer getCantidadPan() {
        return cantidadPan;
    }

    public void setCantidadPan(Integer cantidadPan) {
        this.cantidadPan = cantidadPan;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTiempoAmasado() {
        return tiempoAmasado;
    }

    public void setTiempoAmasado(int tiempoAmasado) {
        this.tiempoAmasado = tiempoAmasado;
    }

    public int getTiempoElaboracion() {
        return tiempoElaboracion;
    }

    public void setTiempoElaboracion(int tiempoElaboracion) {
        this.tiempoElaboracion = tiempoElaboracion;
    }

    public int getTiempoEmpaque() {
        return tiempoEmpaque;
    }

    public void setTiempoEmpaque(int tiempoEmpaque) {
        this.tiempoEmpaque = tiempoEmpaque;
    }

    public int getTiempoEnfriado() {
        return tiempoEnfriado;
    }

    public void setTiempoEnfriado(int tiempoEnfriado) {
        this.tiempoEnfriado = tiempoEnfriado;
    }

    public int getTiempoMoldeado() {
        return tiempoMoldeado;
    }

    public void setTiempoMoldeado(int tiempoMoldeado) {
        this.tiempoMoldeado = tiempoMoldeado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduccion != null ? idProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produccion)) {
            return false;
        }
        Produccion other = (Produccion) object;
        if ((this.idProduccion == null && other.idProduccion != null) || (this.idProduccion != null && !this.idProduccion.equals(other.idProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "a.com.Entity.Produccion[ idProduccion=" + idProduccion + " ]";
    }
    
}
