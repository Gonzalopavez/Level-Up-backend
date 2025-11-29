package cl.duoc.levelup.controller;

import cl.duoc.levelup.model.Producto;
import cl.duoc.levelup.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Dice que esta clase responde peticiones web (JSON)
@RequestMapping("/api/productos") // La URL base será http://localhost:8080/api/productos
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // GET: Traer todos
    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAllProductos();
    }

    // GET: Traer uno por ID
    @GetMapping("/{id}")
    public Producto getById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    // POST: Crear un producto nuevo
    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoService.saveProducto(producto);
    }

    // PUT: Actualizar un producto existente
    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto producto) {
        producto.setId(id); // Aseguramos que el ID sea el correcto
        return productoService.saveProducto(producto);
    }

    // DELETE: Borrar un producto
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    // PATCH: Actualizar SOLO precio y stock (para vendedor)
@PatchMapping("/{id}/stock-precio")
public Producto updateStockAndPrice(
        @PathVariable Long id,
        @RequestBody Producto cambios
) {
    return productoService.updateStockAndPrice(id, cambios.getPrecio(), cambios.getStock());
}

}