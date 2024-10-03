package com.joseph.crud_spring_boot_mysql.controladores;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Curso;
import com.joseph.crud_spring_boot_mysql.servicios.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.util.Map;

@Controller
@SessionAttributes("curso")
public class CursoController {
    @Autowired
    private CursoService servicio;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Cursos  CJAVA");
        model.addAttribute("cursos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/formCurso", method = RequestMethod.GET) // Cambiado a /formCurso
    public String crear(Map<String, Object> model) {
        Curso curso = new Curso();
        model.put("curso", curso);
        model.put("titulo", "Formulario de Curso");
        return "formView"; // Asegúrate de que esta vista es correcta
    }

    @RequestMapping(value = "/formCurso/{id}", method = RequestMethod.GET) // Cambiado a /formCurso/{id}
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Curso curso = null;

        if (id > 0) {
            curso = servicio.buscar(id);
        } else {
            return "redirect:/listar";
        }
        model.put("curso", curso);
        model.put("titulo", "Editar Curso");
        return "formView";
    }

    @RequestMapping(value = "/formCurso", method = RequestMethod.POST) // Cambiado a /formCurso
    public String guardar(@Valid Curso curso, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Curso");
            return "formView";
        }
        servicio.grabar(curso);
        status.setComplete();
        return "redirect:listar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Integer id) {
        if (id > 0) {
            servicio.eliminar(id);
        }
        return "redirect:/listar";
    }
}
