package com.joseph.crud_spring_boot_mysql.modelos.daos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Alumno;
import org.springframework.data.repository.CrudRepository;

public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {

}
