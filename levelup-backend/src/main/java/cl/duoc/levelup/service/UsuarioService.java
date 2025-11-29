package cl.duoc.levelup.service;

import cl.duoc.levelup.model.Usuario;
import cl.duoc.levelup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ============================================================
    // REGISTRO: Guarda un usuario validando RUN y correo únicos
    // ============================================================
    public Usuario save(Usuario usuario) {

        // Validación: RUN único
        if (usuarioRepository.findByRun(usuario.getRun()).isPresent()) {
            throw new IllegalArgumentException("El RUN ya está registrado.");
        }

        // Validación: Correo único
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        // Forzar autoincremento
        if (usuario.getId() != null && usuario.getId() == 0) {
            usuario.setId(null);
        }

        // Asignar rol por defecto
        if (usuario.getTipo() == null || usuario.getTipo().isEmpty()) {
            usuario.setTipo("Cliente");
        }

        return usuarioRepository.save(usuario);
    }

    // ============================================================
    // LOGIN: Buscar usuario por correo y contraseña
    // ============================================================
    public Usuario login(String correo, String password) {
        return usuarioRepository.findByCorreoAndPassword(correo, password);
    }

    // ============================================================
    // OBTENER UN USUARIO POR ID
    // ============================================================
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // ============================================================
    // LISTAR TODOS LOS USUARIOS
    // ============================================================
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // ============================================================
    // ELIMINAR USUARIO
    // ============================================================
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    // ============================================================
    // ACTUALIZAR ROL DE USUARIO
    // ============================================================
    public Usuario updateRole(Long id, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setTipo(nuevoRol);
        return usuarioRepository.save(usuario);
    }


    public Usuario updateUser(Long id, Usuario datos) {
    Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    usuario.setNombre(datos.getNombre());
    usuario.setApellidos(datos.getApellidos());
    usuario.setCorreo(datos.getCorreo());
    usuario.setRun(datos.getRun());
    usuario.setTelefono(datos.getTelefono());
    usuario.setFechaNacimiento(datos.getFechaNacimiento());
    usuario.setDireccion(datos.getDireccion());
    usuario.setRegion(datos.getRegion());
    usuario.setComuna(datos.getComuna());
    usuario.setCodigoReferido(datos.getCodigoReferido());
    usuario.setTipo(datos.getTipo()); // 🔥 También puede cambiar el rol aquí

    return usuarioRepository.save(usuario);
}

}
