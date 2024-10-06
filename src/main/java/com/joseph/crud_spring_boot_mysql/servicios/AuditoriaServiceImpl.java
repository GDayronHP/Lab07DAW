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
    private AuditoriaRepository auditoriaRepository;

    @Override
    @Transactional
    public void grabar(Auditoria auditoria) {
        auditoriaRepository.save(auditoria);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        auditoriaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Auditoria buscar(Integer id) {
        return auditoriaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Auditoria> listar() {
        return auditoriaRepository.findAll();
    }
}
