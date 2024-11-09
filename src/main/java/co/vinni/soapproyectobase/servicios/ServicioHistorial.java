package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.HistorialDTO;
import co.vinni.soapproyectobase.entidades.Historial;
import co.vinni.soapproyectobase.entidades.Pregunta;
import co.vinni.soapproyectobase.entidades.Usuario;
import co.vinni.soapproyectobase.repositorios.RepositorioHistorial;
import co.vinni.soapproyectobase.repositorios.RepositorioPregunta;
import co.vinni.soapproyectobase.repositorios.RepositorioUsuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ServicioHistorial {

    private ModelMapper modelMapper;

    @Autowired
    private RepositorioHistorial repositorioHistorial;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioPregunta repositorioPregunta;
    @Autowired
    private ServicioUsuario servicioUsuario;

    public HistorialDTO registrarHistorial(HistorialDTO historialDto) {

        Usuario usuario = obtenerUsuarioPorId(historialDto.getId_usuario());

        List<Pregunta> preguntas = historialDto.getIds_preguntas().stream()
                .map(this::obtenerPreguntaPorId)
                .collect(Collectors.toList());

        Historial historial = modelMapper.map(historialDto, Historial.class);
        historial.setUsuario(usuario);
        historial.setPreguntas(preguntas);

        Historial historialGuardado = repositorioHistorial.save(historial);

        HistorialDTO historialGuardadoDto = modelMapper.map(historialGuardado, HistorialDTO.class);
        historialGuardadoDto.setId_usuario(usuario.getId());
        historialGuardadoDto.setUsername(usuario.getUsername());

        // Opcional: agregar más información si se necesita

        return historialGuardadoDto;
    }
    private Usuario obtenerUsuarioPorId(Long idUsuario) {
        return repositorioUsuario.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con ID " + idUsuario + " no existe"));
    }

    private Pregunta obtenerPreguntaPorId(Long idPregunta) {
        return repositorioPregunta.findById(idPregunta)
                .orElseThrow(() -> new IllegalArgumentException("La pregunta con ID " + idPregunta + " no existe"));
    }

    public List<HistorialDTO> obtenerHistorialPorUsuario(String username) {
        List<Historial> historialList = repositorioHistorial.getDatosDelUsuario(username);

        return historialList.stream()
                .map(historial -> {
                    HistorialDTO historialDto = modelMapper.map(historial, HistorialDTO.class);
                    historialDto.setId_usuario(historial.getUsuario().getId());
                    historialDto.setUsername(historial.getUsuario().getUsername());

                    // Suponemos que todas las preguntas tienen la misma categoría y dificultad
                    if (!historial.getPreguntas().isEmpty()) {
                        Pregunta primeraPregunta = historial.getPreguntas().get(0);  // Tomamos la primera pregunta
                        historialDto.setCategoria(primeraPregunta.getCategoria());
                        historialDto.setDificultad(primeraPregunta.getDificultad());
                    }

                    return historialDto;
                })
                .collect(Collectors.toList());
    }


    public Integer obtenerTotalPuntosPorUsuario(String username) {
            return repositorioHistorial.getTotalPuntosDelUsuario(username);
        }


}

