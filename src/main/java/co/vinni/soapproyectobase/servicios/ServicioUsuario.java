package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.UsuarioDTO;
import co.vinni.soapproyectobase.entidades.Usuario;
import co.vinni.soapproyectobase.repositorios.RepositorioHistorial;
import co.vinni.soapproyectobase.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ServicioUsuario  implements Serializable {

    private ModelMapper modelMapper;

    private final  RepositorioUsuario repositorioUsuario;
    private RepositorioHistorial repositorioHistorial;

    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDto) {

        if (repositorioUsuario.findByUsername(usuarioDto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        Usuario nuevoUsuario = modelMapper.map(usuarioDto, Usuario.class);
        nuevoUsuario.setLogro("Novato");
        Usuario usuarioGuardado = repositorioUsuario.save(nuevoUsuario);
        return modelMapper.map(usuarioGuardado, UsuarioDTO.class);
    }


    public UsuarioDTO VerificarUsuario(String usuario , String password) {
        
        Optional<Usuario> usuarios = repositorioUsuario.findByUsernameAndPassword(usuario, password);

        if (usuarios.isEmpty()) {
            return null;
        }

        Usuario user = usuarios.get();
        return modelMapper.map(user , UsuarioDTO.class);

    }

    public void actualizarLogro(Usuario usuario, int puntaje, String dificultad) {
        String logro = usuario.getLogro();

        if (dificultad.equals("Difícil") && puntaje > 80) {
            logro = "Experto";
        } else if (dificultad.equals("Media") && puntaje > 50) {
            logro = "Intermedio";
        } else if (dificultad.equals("Fácil") && puntaje > 30) {
            logro = "Novato";
        }

        usuario.setLogro(logro);
        repositorioUsuario.save(usuario);
    }

    public Optional<UsuarioDTO> verificarusuarioexistente(String username) {

        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsername(username);

        return usuarioOptional.map(usuario -> modelMapper.map(usuario, UsuarioDTO.class));
    }

    public boolean actualizarPassword(String username, String nuevaPassword) {

        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsername(username);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setPassword(nuevaPassword);
            repositorioUsuario.save(usuario);
            return true;
        } else {
            return false;
        }
    }


}
