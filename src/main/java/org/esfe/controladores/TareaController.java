package org.esfe.controladores;

import org.esfe.dtos.proyecto.ProyectoGuardar;
import org.esfe.dtos.proyecto.ProyectoModificar;
import org.esfe.dtos.proyecto.ProyectoSalida;
import org.esfe.dtos.tarea.TareaCambiarEstado;
import org.esfe.dtos.tarea.TareaModificar;
import org.esfe.dtos.tarea.TareaSalida;
import org.esfe.dtos.tarea.TereaGuardar;
import org.esfe.servicios.interfaces.ITareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
    @Autowired
    private ITareaService tareaService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<TareaSalida>> mostrarTodosPaginados(Pageable pageable)
    {
        Page<TareaSalida> tareas = tareaService.obtenerTodosPaginados(pageable);
        if(tareas.hasContent())
            return ResponseEntity.ok(tareas);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<TareaSalida>> mostrarTodos()
    {
        List<TareaSalida> tareas = tareaService.obtenerTodos();
        if(!tareas.isEmpty())
            return ResponseEntity.ok(tareas);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TareaSalida> buscarPorId(@PathVariable Integer id)
    {
        TareaSalida tarea = tareaService.obtenerPorId(id);

        if(tarea != null)
            return ResponseEntity.ok(tarea);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/proyecto/{id}")
    public ResponseEntity<List<TareaSalida>> mostrarPorProyecto(@PathVariable Integer id)
    {
        List<TareaSalida> tareas = tareaService.obtenerPorProyectoId(id);
        if(!tareas.isEmpty())
            return ResponseEntity.ok(tareas);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TareaSalida> crear(@RequestBody TereaGuardar tareaGuardar)
    {
        TareaSalida tarea = tareaService.crear(tareaGuardar);
        return ResponseEntity.ok(tarea);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TareaSalida> editar(@PathVariable Integer id,
                                                 @RequestBody TareaModificar tareaModificar)
    {
        TareaSalida tarea = tareaService.editar(tareaModificar);

        if(tarea != null)
            return ResponseEntity.ok(tarea);
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping
    public ResponseEntity<TareaSalida> cambiarEstado(
            @RequestBody TareaCambiarEstado tareaCambiarEstado)
    {
        TareaSalida tarea = tareaService.cambiarEstado(tareaCambiarEstado);

        if(tarea != null)
            return ResponseEntity.ok(tarea);

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id)
    {
        tareaService.eliminarPorId(id);
        return ResponseEntity.ok("Tarea eliminada correctamente");
    }
}
