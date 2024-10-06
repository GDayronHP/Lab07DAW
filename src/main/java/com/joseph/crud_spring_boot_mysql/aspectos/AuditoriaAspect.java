package com.joseph.crud_spring_boot_mysql.aspectos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import com.joseph.crud_spring_boot_mysql.servicios.AuditoriaService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaService auditoriaService;

    @After("execution(* com.joseph.crud_spring_boot_mysql.servicios.*.grabar(..)) || " +
            "execution(* com.joseph.crud_spring_boot_mysql.servicios.*.eliminar(..)) || " +
            "execution(* com.joseph.crud_spring_boot_mysql.servicios.*.editar(..))")
    public void registrarAuditoria(JoinPoint joinPoint) {
        String metodo = joinPoint.getSignature().getName();
        String tabla = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        // Obtiene el idRegistro de manera segura
        Optional<Integer> idRegistroOpt = obtenerIdRegistro(args);
        if (idRegistroOpt.isPresent()) {
            Integer idRegistro = idRegistroOpt.get();
            String usuario = obtenerUsuarioActual(); // Método para obtener el usuario actual
            String tipo = determinarTipo(metodo);

            Auditoria auditoria = new Auditoria(tabla, idRegistro, new Date(), usuario, tipo);
            auditoriaService.grabar(auditoria);
        } else {
            // Manejar el caso en que no se encuentra el idRegistro
            System.out.println("No se pudo obtener el idRegistro para la auditoría.");
        }
    }

    private Optional<Integer> obtenerIdRegistro(Object[] args) {
        // Asegúrate de que el primer argumento sea un objeto que tenga un método para obtener el ID
        if (args.length > 0 && args[0] instanceof Identificable) {
            return Optional.of(((Identificable) args[0]).getId()); // Asegúrate de que tu entidad implementa la interfaz Identificable
        }
        return Optional.empty();
    }

    private String obtenerUsuarioActual() {
        // Aquí debes implementar la lógica para obtener el usuario actual
        // Puede ser desde el contexto de seguridad, un hilo local, etc.
        return "Usuario"; // Reemplaza con la lógica adecuada
    }

    private String determinarTipo(String metodo) {
        switch (metodo) {
            case "grabar":
                return "INSERT";
            case "eliminar":
                return "DELETE";
            case "editar":
                return "UPDATE";
            default:
                return "UNKNOWN";
        }
    }

    // Interface para obtener el ID
    public interface Identificable {
        Integer getId();
    }
}
