
package cl.duoc.levelup.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true) // El RUN no se debe repetir
    private String run;

    @Column(nullable = false, unique = true) // El correo no se debe repetir
    private String correo;

    @Column(nullable = false)
    private String password;

    // Datos extra del formulario
    private String fechaNacimiento;
    private String telefono;
    private String direccion;
    private String region;
    private String comuna;
    private String codigoReferido;

    @Column(nullable = false)
    private String tipo; // "Cliente", "Vendedor", "Administrador"
}