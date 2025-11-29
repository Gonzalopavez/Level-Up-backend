package cl.duoc.levelup.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private Long total;

    // Relación: Una orden la hace UN usuario (cliente)
    // Usamos "cliente" para que coincida con tu React
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario cliente;

    // Relación: Una orden tiene MUCHOS detalles (items)
    // CascadeType.ALL significa: "Si guardo la Orden, guarda sus detalles automáticamente"
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    @JsonManagedReference // Maneja la relación para que el JSON se vea bien
    private List<DetalleOrden> items;
}