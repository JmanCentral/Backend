package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.UsuarioDTO;
import co.vinni.soapproyectobase.entidades.Historial;
import co.vinni.soapproyectobase.entidades.Usuario;
import co.vinni.soapproyectobase.repositorios.RepositorioHistorial;
import co.vinni.soapproyectobase.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.io.Serializable;
import java.util.List;
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

        nuevoUsuario.setNivel("Novato");
        nuevoUsuario.setLogro1("sin desbloquear");
        nuevoUsuario.setLogro2("sin desbloquear");

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

    public void actualizarUsuarioPorHistorial(String username) {
        // Obtener el puntaje total del usuario
        Integer puntajeTotal = repositorioHistorial.getTotalPuntosDelUsuario(username);

        // Obtener el total de ayudas del usuario
        Integer totalAyudas = repositorioHistorial.getTotalAyudasDelUsuario(username);

        // Obtener el tiempo total del usuario
        Integer tiempoTotal = repositorioHistorial.getTiempoTotalDelUsuario(username);

        // Buscar el usuario por username
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsername(username);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Actualizar nivel basado en el puntaje total
            if (puntajeTotal != null && puntajeTotal > 100) {
                usuario.setNivel("Experto");
            } else {
                usuario.setNivel("Novato");
            }

            // Verificar si el tiempo total es menor a 2 minutos (120 segundos)
            if (tiempoTotal != null && tiempoTotal < 120) {
                // Asignar un logro o un mensaje al usuario
                usuario.setLogro1("Tiempo Total: Menos de 2 minutos");
            }

            // Otras condiciones opcionales para logros basados en el total de ayudas
            if (totalAyudas != null && totalAyudas == 0) {
                usuario.setLogro2("Eres todo un rebelde");
            } else if (totalAyudas != null && totalAyudas < 30) {
                usuario.setLogro2("Niño de mami");
            }

            // Guardar los cambios en el repositorio de Usuario
            repositorioUsuario.save(usuario);

        } else {
            throw new IllegalArgumentException("Usuario con username " + username + " no existe");
        }
    }








}
