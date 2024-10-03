package com.joseph.crud_spring_boot_mysql.servicios;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;

import java.util.List;

public interface AuditoriaService {

    public List<Auditoria> listar();

    public void grabar(Auditoria auditoria);

    public Auditoria buscar(Integer id);

    public void eliminar(Integer id);

}



