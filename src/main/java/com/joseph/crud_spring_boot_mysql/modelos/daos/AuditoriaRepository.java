package com.joseph.crud_spring_boot_mysql.modelos.daos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import org.springframework.data.repository.CrudRepository;

public interface AuditoriaRepository extends CrudRepository<Auditoria, Integer> {

}
