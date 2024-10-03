package com.joseph.crud_spring_boot_mysql.modelos.entidades;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tabla", nullable = false)
    private String tabla;

    @Column(name = "id_registro", nullable = false)
    private Integer idRegistro;

    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "usuario", nullable = false)
    private String usuario;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Constructor vacío
    public Auditoria() {}

    // Constructor parametrizado
    public Auditoria(String tabla, Integer idRegistro, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTabla() {
        return tabla;
    }
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    public Integer getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Auditoria [id=" + id + ", tabla=" + tabla + ", idRegistro=" + idRegistro +
                ", fecha=" + fecha + ", usuario=" + usuario + ", tipo=" + tipo + "]";
    }
}
