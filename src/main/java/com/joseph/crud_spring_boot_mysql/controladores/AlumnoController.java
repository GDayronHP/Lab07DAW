package com.joseph.crud_spring_boot_mysql.controladores;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Alumno;
import com.joseph.crud_spring_boot_mysql.servicios.AlumnoService;
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
@SessionAttributes("alumno")
public class AlumnoController {
    @Autowired
    private AlumnoService servicio;

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public String listar(Model model) {
        model.addAttribute("titulo", "Listado de Alumnos  CJAVA");
        model.addAttribute("alumnos", servicio.listar());
        return "listarView";
    }

    @RequestMapping(value = "/formAlumno", method = RequestMethod.GET) // Cambiado a /formAlumno
    public String crear(Map<String, Object> model) {
        Alumno alumno = new Alumno();
        model.put("alumno", alumno);
        model.put("titulo", "Formulario de Alumno");
        return "formView"; // Asegúrate de que esta vista es correcta
    }

    @RequestMapping(value = "/formAlumno/{id}", method = RequestMethod.GET) // Cambiado a /formAlumno/{id}
    public String editar(@PathVariable(value = "id") Integer id, Map<String, Object> model) {
        Alumno alumno = null;

        if (id > 0) {
            alumno = servicio.buscar(id);
        } else {
            return "redirect:/listar";
        }
        model.put("alumno", alumno);
        model.put("titulo", "Editar Alumno");
        return "formView";
    }

    @RequestMapping(value = "/formAlumno", method = RequestMethod.POST) // Cambiado a /formAlumno
    public String guardar(@Valid Alumno alumno, BindingResult result, Model model, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de Alumno");
            return "formView";
        }
        servicio.grabar(alumno);
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
