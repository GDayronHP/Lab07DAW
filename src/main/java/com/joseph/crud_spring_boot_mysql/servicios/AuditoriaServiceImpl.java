package com.joseph.crud_spring_boot_mysql.servicios;

import com.joseph.crud_spring_boot_mysql.modelos.daos.AuditoriaRepository;
import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {
    @Autowired
    private AuditoriaRepository dao;

    @Override
    @Transactional(readOnly = false)
    public void grabar(Auditoria auditoria) {
        dao.save(auditoria);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminar(Integer id) {
        dao.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public Auditoria buscar(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auditoria> listar() {
        return (List<Auditoria>)dao.findAll();
    }
}
