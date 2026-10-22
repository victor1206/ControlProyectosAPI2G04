package org.esfe.servicios.implementaciones;

import org.esfe.dtos.categoria.CategoriaGuardar;
import org.esfe.dtos.categoria.CategoriaModificar;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.servicios.interfaces.ICategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoriaServiceTest {

    @Autowired
    private ICategoriaService categoriaService;

    @Test
    void t1_crear() {
        CategoriaSalida categoriaSalida = categoriaService.crear(
                new CategoriaGuardar("Categoria de prueba"));
        assertTrue(categoriaSalida != null);
    }

    @Test
    void t2_obtenerTodos() {
        int actual = categoriaService.obtenerTodos().size();
        assertEquals(1000, actual);
    }

    @Test
    void obtenerTodosPaginados() {
    }

    @Test
    void obtenerPorId() {
    }

    @Test
    void t3_editar() {
        CategoriaSalida categoriaSalida = categoriaService.editar(new CategoriaModificar(
        6, "Categoria Modificada"
        ));
        assertNotEquals(null, categoriaSalida);
    }

    @Test
    void eliminarPorId() {
        assertDoesNotThrow(new Executable() {
                               @Override
                               public void execute() throws Throwable {
                                   categoriaService.eliminarPorId(6);
                               }
                           }

        );
    }
}