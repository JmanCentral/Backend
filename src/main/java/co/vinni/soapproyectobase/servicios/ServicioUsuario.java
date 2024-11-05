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
        nuevoUsuario.setLogro3("sin desbloquear");
        nuevoUsuario.setLogro4("sin desbloquear");
        nuevoUsuario.setLogro5("sin desbloquear");



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
        Integer puntajeTotal = repositorioHistorial.getTotalPuntosDelUsuario(username);
        Integer totalAyudas = repositorioHistorial.getTotalAyudasDelUsuario(username);
        Integer tiempoTotal = repositorioHistorial.getTiempoTotalDelUsuario(username);

        List<Object[]> dificultadesYCategorias = repositorioHistorial.getDificultadYCategoriaPorUsuario(username);
        Optional<Usuario> usuarioOptional = repositorioUsuario.findByUsername(username);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (puntajeTotal != null && puntajeTotal > 100) {
                usuario.setNivel("Experto");
            } else {
                usuario.setNivel("Novato");
            }

            if (tiempoTotal != null && tiempoTotal < 120) {
                usuario.setLogro1("Muy Rapido");
            }

            if (totalAyudas != null && totalAyudas == 0) {
                usuario.setLogro2("Invencible");
            } else if (totalAyudas != null && totalAyudas < 30) {
                usuario.setLogro2("Loser");
            }


            boolean jugoCategoriaConDificultad = false;
            String categoriaObjetivo = "matematicas";
            String dificultadObjetivo = "dificil";

            for (Object[] row : dificultadesYCategorias) {
                String dificultad = (String) row[0];
                String categoria = (String) row[1];
                if (dificultad.equals(dificultadObjetivo) && categoria.equals(categoriaObjetivo)) {
                    jugoCategoriaConDificultad = true;
                    break;
                }
            }

            if (jugoCategoriaConDificultad) {
                usuario.setLogro3("Aprende a las malas");
            }


            boolean jugoCategoriaConDificultad1 = false;
            String categoriaObjetivo1 = "geografia";
            String dificultadObjetivo1 = "dificil";

            for (Object[] row : dificultadesYCategorias) {
                String dificultad = (String) row[0];
                String categoria = (String) row[1];
                if (dificultad.equals(dificultadObjetivo1) && categoria.equals(categoriaObjetivo1)) {
                    jugoCategoriaConDificultad1 = true;
                    break;
                }
            }

            if (jugoCategoriaConDificultad1) {
                usuario.setLogro4("Conocedor");
            }

            boolean jugoCategoriaConDificultad2 = false;
            String categoriaObjetivo2 = "literatura";
            String dificultadObjetivo2 = "dificil";

            for (Object[] row : dificultadesYCategorias) {
                String dificultad = (String) row[0];
                String categoria = (String) row[1];
                if (dificultad.equals(dificultadObjetivo2) && categoria.equals(categoriaObjetivo2)) {
                    jugoCategoriaConDificultad2 = true;
                    break;
                }
            }

            if (jugoCategoriaConDificultad2) {
                usuario.setLogro5("Cosas de neruda");
            }



            repositorioUsuario.save(usuario);

        } else {
            throw new IllegalArgumentException("Usuario con username " + username + " no existe");
        }
    }









}
