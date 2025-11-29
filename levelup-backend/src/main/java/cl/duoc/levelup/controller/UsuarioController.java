package cl.duoc.levelup.controller;

import cl.duoc.levelup.model.Usuario;
import cl.duoc.levelup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // ===============================
    // 1. OBTENER TODOS LOS USUARIOS
    // ===============================
    @GetMapping
    public List<Usuario> getAllUsers() {
        return usuarioService.getAll();
    }

    // ===============================
    // 2. ELIMINAR USUARIO
    // (Protección del admin principal)
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }

        // ❗ PROTEGER ADMIN PRINCIPAL
        if (usuario.getCorreo().equalsIgnoreCase("admin@duoc.cl")) {
            return ResponseEntity.status(400).body("❌ No puedes eliminar al administrador principal.");
        }

        usuarioService.delete(id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    // ===============================
    // 3. ACTUALIZAR ROL DE UN USUARIO
    // ===============================
    @PutMapping("/{id}/rol")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody String nuevoRol) {

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado.");
        }

        // ❗ NO PERMITIR CAMBIAR ROL DEL ADMIN PRINCIPAL
        if (usuario.getCorreo().equalsIgnoreCase("admin@duoc.cl")) {
            return ResponseEntity.status(400).body("❌ No puedes modificar el rol del administrador principal.");
        }

        Usuario actualizado = usuarioService.updateRole(id, nuevoRol);
        return ResponseEntity.ok(actualizado);
    }


    // ===============================
// 4. EDITAR TODOS LOS DATOS DE UN USUARIO
// ===============================
@PutMapping("/{id}")
public ResponseEntity<?> updateUser(
        @PathVariable Long id,
        @RequestBody Usuario datosActualizados
) {

    Usuario usuario = usuarioService.findById(id);
    if (usuario == null) {
        return ResponseEntity.status(404).body("Usuario no encontrado.");
    }

    // ❗ Proteger admin principal
    if (usuario.getCorreo().equalsIgnoreCase("admin@duoc.cl")) {
        return ResponseEntity.status(400).body("❌ No puedes modificar al administrador principal.");
    }

    Usuario actualizado = usuarioService.updateUser(id, datosActualizados);
    return ResponseEntity.ok(actualizado);
}

}

