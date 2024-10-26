package co.vinni.soapproyectobase.controladores;


import co.vinni.soapproyectobase.dto.UsuarioDTO;
import co.vinni.soapproyectobase.servicios.ServicioUsuario;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/Trivia/usuarios")
public class ControladorUsuario {

    private static final Logger logger = LogManager.getLogger(ControladorUsuario.class);

    @Autowired
    ServicioUsuario servicioUsuario;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuario) {

        UsuarioDTO usuarioinsertado = servicioUsuario.registrarUsuario(usuario);

        if (usuarioinsertado != null) {
            return ResponseEntity.ok(usuarioinsertado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PostMapping("/verificarUsuario")
    public ResponseEntity<UsuarioDTO> verificarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioVerificado = servicioUsuario.VerificarUsuario(usuarioDTO.getUsername(), usuarioDTO.getPassword());

        if (usuarioVerificado != null) {
            return ResponseEntity.ok(usuarioVerificado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/verificarUsuarioExistente/{username}")
    public ResponseEntity<UsuarioDTO> verificarUsuarioExistente(@PathVariable String username) {

        Optional<UsuarioDTO> usuarioVerificado = servicioUsuario.verificarusuarioexistente(username);

        return usuarioVerificado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }

    @PutMapping("{username}/actualizarPassword")
    public ResponseEntity<String> actualizarPassword(@PathVariable String username, @RequestBody UsuarioDTO usuarioDTO) {

        String nuevaPassword = usuarioDTO.getPassword();
        boolean actualizado = servicioUsuario.actualizarPassword(username, nuevaPassword);

        if (actualizado) {
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
