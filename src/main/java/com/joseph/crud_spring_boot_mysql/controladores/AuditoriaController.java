package com.joseph.crud_spring_boot_mysql.controladores;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import com.joseph.crud_spring_boot_mysql.servicios.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping("/auditorias")
    public String listarAuditorias(Model model) {
        List<Auditoria> auditorias = auditoriaService.listar();
        model.addAttribute("auditorias", auditorias);
        return "auditorias";  // Nombre del template de Thymeleaf
    }
}
