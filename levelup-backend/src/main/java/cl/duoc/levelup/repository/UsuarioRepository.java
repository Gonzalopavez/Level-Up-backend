package cl.duoc.levelup.repository;

import cl.duoc.levelup.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Para validación de registro
    Optional<Usuario> findByRun(String run);
    Optional<Usuario> findByCorreo(String correo);

    // Para el Login
    Usuario findByCorreoAndPassword(String correo, String password);
}