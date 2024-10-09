package com.joseph.crud_spring_boot_mysql.aspectos;

import com.joseph.crud_spring_boot_mysql.modelos.entidades.Auditoria;
import com.joseph.crud_spring_boot_mysql.servicios.AuditoriaService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Table;
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
        Object[] args = joinPoint.getArgs();

        // Obtiene el idRegistro de manera segura
        Optional<Integer> idRegistroOpt = obtenerIdRegistro(args);
        if (idRegistroOpt.isPresent()) {
            Integer idRegistro = idRegistroOpt.get();
            String usuario = obtenerUsuarioActual();
            String tipo = determinarTipo(metodo, args);

            String nombreTabla;
            if ("eliminar".equals(metodo)) {
                nombreTabla = obtenerNombreTablaDesdeEliminacion(idRegistro);
            } else {
                nombreTabla = obtenerNombreTabla(args[0]);
            }

            Auditoria auditoria = new Auditoria(nombreTabla, idRegistro, new Date(), usuario, tipo);
            auditoriaService.grabar(auditoria);
        } else {
            System.out.println("No se pudo obtener el idRegistro para la auditoría.");
        }
    }

    // Método para obtener el nombre de la tabla desde la anotación @Table
    private String obtenerNombreTabla(Object entidad) {
        if (entidad != null) {
            Table tableAnnotation = entidad.getClass().getAnnotation(Table.class);
            if (tableAnnotation != null) {
                return tableAnnotation.name();
            }
        }
        return entidad.getClass().getSimpleName();
    }

    private String obtenerNombreTablaDesdeEliminacion(Integer id) {
        // Aquí puedes implementar la lógica para obtener el nombre de la tabla
        // desde la eliminación, basado en el id o entidad específica
        return "Desconocido"; // Reemplaza esto según tu lógica
    }

    private Optional<Integer> obtenerIdRegistro(Object[] args) {
        if (args.length > 0) {
            if (args[0] instanceof Identificable) {
                return Optional.ofNullable(((Identificable) args[0]).getId());
            } else if (args[0] instanceof Integer) {  // Maneja el caso de eliminar
                return Optional.of((Integer) args[0]);
            }
        }
        return Optional.empty();
    }

    private String obtenerUsuarioActual() {
        // Aquí debes implementar la lógica para obtener el usuario actual
        return "Usuario"; // Reemplaza con la lógica adecuada
    }

    private String determinarTipo(String metodo, Object[] args) {
        if ("grabar".equals(metodo)) {
            if (args[0] instanceof Identificable && ((Identificable) args[0]).getId() == null) {
                return "INSERT";
            } else {
                return "UPDATE";
            }
        }
        if ("eliminar".equals(metodo)) {
            return "DELETE";
        }
        return "UNKNOWN";
    }

    // Interface para obtener el ID de los objetos auditables
    public interface Identificable {
        Integer getId();
    }
}
