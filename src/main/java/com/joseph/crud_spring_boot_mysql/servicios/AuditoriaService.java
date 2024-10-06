package com.joseph.crud_spring_boot_mysql.servicios;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import java.util.List;

public interface AuditoriaService {
    void grabar(Auditoria auditoria);
    void eliminar(Integer id);
    Auditoria buscar(Integer id);
    List<Auditoria> listar();
}
