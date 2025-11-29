package cl.duoc.levelup.service;

import cl.duoc.levelup.model.Producto;
import cl.duoc.levelup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired // Inyectamos el Repositorio (La Despensa)
    private ProductoRepository productoRepository;

    // 1. Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // 2. Obtener un producto por ID
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // 3. Guardar (Crear o Actualizar) un producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // 4. Eliminar un producto
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Actualizar SOLO precio y stock (para vendedor)
public Producto updateStockAndPrice(Long id, Integer nuevoPrecio, Integer nuevoStock) {
    Producto p = productoRepository.findById(id).orElse(null);

    if (p == null) return null;

    if (nuevoPrecio != null) p.setPrecio(nuevoPrecio);
    if (nuevoStock != null) p.setStock(nuevoStock);

    return productoRepository.save(p);
}

}