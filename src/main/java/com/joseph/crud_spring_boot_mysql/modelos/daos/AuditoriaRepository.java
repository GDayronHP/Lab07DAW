package com.joseph.crud_spring_boot_mysql.modelos.daos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    // Ya tienes acceso a operaciones CRUD
}
