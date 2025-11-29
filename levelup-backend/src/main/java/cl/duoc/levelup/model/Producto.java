package cl.duoc.levelup.model;

import jakarta.persistence.*; // Importa las herramientas de base de datos
import lombok.Data;          // Importa Lombok para no escribir getters/setters

@Data // Lombok crea los getters, setters y toString automáticamente
@Entity // Esto le dice a Java: "Crea una tabla en MySQL basada en esta clase"
@Table(name = "productos") // Nombre de la tabla en MySQL
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremental (1, 2, 3...)
    private Long id;

    @Column(nullable = false) // No puede ser nulo
    private String nombre;

    private Integer precio;
    private String categoria;
    
    @Column(length = 1000) // Permitimos descripciones largas
    private String descripcion;
    
    private String imagen; // URL de la imagen
    private Integer stock;
}