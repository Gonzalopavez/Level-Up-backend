package cl.duoc.levelup.repository;

import cl.duoc.levelup.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // ¡Magia! No necesitamos escribir código aquí.
    // Al extender JpaRepository, ya tenemos todos los métodos CRUD listos.
}