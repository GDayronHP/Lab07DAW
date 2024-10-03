package com.joseph.crud_spring_boot_mysql.modelos.daos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Integer> {

}
