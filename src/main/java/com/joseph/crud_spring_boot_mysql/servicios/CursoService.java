package com.joseph.crud_spring_boot_mysql.servicios;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Curso;

import java.util.List;

public interface CursoService {

    public List<Curso> listar();

    public void grabar(Curso curso);

    public Curso buscar(Integer id);

    public void eliminar(Integer id);

}



