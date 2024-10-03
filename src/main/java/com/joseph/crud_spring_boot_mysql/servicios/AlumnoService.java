package com.joseph.crud_spring_boot_mysql.servicios;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Alumno;

import java.util.List;

public interface AlumnoService {

    public List<Alumno> listar();

    public void grabar(Alumno alumno);

    public Alumno buscar(Integer id);

    public void eliminar(Integer id);

}



