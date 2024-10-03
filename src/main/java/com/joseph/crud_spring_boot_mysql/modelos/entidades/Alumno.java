package com.joseph.crud_spring_boot_mysql.modelos.entidades;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String nombres;

    @Column
    @NotNull
    @Size(min = 2, max = 30)
    private String apellidos;

    @Column
    @NotNull
    @Past
    private LocalDate fechaNacimiento;

    @Column
    @NotNull
    @Size(min = 1, max = 10) // Suponiendo que el sexo se represente como 'Masculino', 'Femenino', etc.
    private String sexo;

    public Alumno() {}

    public Alumno(int id, String nombres, String apellidos, LocalDate fechaNacimiento, String sexo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}
