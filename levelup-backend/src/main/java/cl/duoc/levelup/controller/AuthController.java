package cl.duoc.levelup.controller;

import cl.duoc.levelup.model.Usuario;
import cl.duoc.levelup.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
// Permite que React (puerto 5173) se conecte
@CrossOrigin(origins = "http://localhost:5173") 
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    // Clase auxiliar para mensajes de error
    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }

    // --- 1. ENDPOINT DE REGISTRO ---
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        // Validación básica
        if (usuario.getRun() == null || usuario.getCorreo() == null || usuario.getPassword() == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Faltan datos obligatorios."));
        }

        try {
            // Guardar usuario
            Usuario nuevoUsuario = usuarioService.save(usuario);
            // Éxito (201 Created)
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Error de validación (RUN o Correo duplicado) (400 Bad Request)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        } catch (Exception e) {
            // Error interno (500)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error del servidor."));
        }
    }

    // --- 2. ENDPOINT DE LOGIN (¡ESTE FALTABA!) ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String email = credenciales.get("email");
        String password = credenciales.get("password");

        try {
            Usuario usuario = usuarioService.login(email, password);
            
            if (usuario != null) {
                // Éxito: Devolvemos el usuario encontrado
                return ResponseEntity.ok(usuario);
            } else {
                // Fallo: Credenciales incorrectas (401 Unauthorized)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Correo o contraseña incorrectos."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error en el login."));
        }
    }
}