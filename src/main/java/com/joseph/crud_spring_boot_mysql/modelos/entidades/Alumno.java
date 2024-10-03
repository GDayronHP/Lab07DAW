package com.joseph.crud_spring_boot_mysql.modelos.entidades;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nombres", nullable = false)
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    @NotEmpty(message = "El apellido no puede estar vacío")
    private String apellidos;

    @Column(name = "fecha_nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    private LocalDate fechaNacimiento; // Cambiado a LocalDate

    @Column(name = "sexo", nullable = false)
    @NotEmpty(message = "El sexo no puede estar vacío")
    private String sexo;


    public Alumno() {
    }

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

    public @NotEmpty(message = "El nombre no puede estar vacío") String getNombres() {
        return nombres;
    }

    public void setNombres(@NotEmpty(message = "El nombre no puede estar vacío") String nombres) {
        this.nombres = nombres;
    }

    public @NotEmpty(message = "El apellido no puede estar vacío") String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NotEmpty(message = "El apellido no puede estar vacío") String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NotNull(message = "La fecha de nacimiento no puede estar vacía") LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public @NotEmpty(message = "El sexo no puede estar vacío") String getSexo() {
        return sexo;
    }

    public void setSexo(@NotEmpty(message = "El sexo no puede estar vacío") String sexo) {
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
