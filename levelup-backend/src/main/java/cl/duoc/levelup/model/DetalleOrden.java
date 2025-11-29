package cl.duoc.levelup.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalles_orden")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    // Guardamos una copia del precio al momento de la compra
    // (Por si el producto sube de precio después)
    private Integer precioUnitario; 

    // Relación: Un detalle tiene UN producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Relación: Un detalle pertenece a UNA orden
    @ManyToOne
    @JoinColumn(name = "orden_id")
    @JsonBackReference // Evita bucles infinitos al convertir a JSON
    private Orden orden;
}