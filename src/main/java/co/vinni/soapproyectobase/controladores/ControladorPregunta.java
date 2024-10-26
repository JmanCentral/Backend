package co.vinni.soapproyectobase.controladores;


import co.vinni.soapproyectobase.dto.PreguntaDTO;
import co.vinni.soapproyectobase.servicios.ServicioPregunta;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/Trivia/preguntas")
public class ControladorPregunta {

    @Autowired
    private ServicioPregunta servicioPregunta;


    @PostMapping("/insertar")
    public ResponseEntity<String> insertarPreguntas() {
        servicioPregunta.insertAllPreguntas();
        return ResponseEntity.ok("Preguntas insertadas correctamente");
    }

    @GetMapping("/categoria/{categoria}/dificultad/{dificultad}")
    public ResponseEntity<List<PreguntaDTO>> obtenerPreguntasPorCategoria(
            @PathVariable String categoria,
            @PathVariable String dificultad) {
        List<PreguntaDTO> preguntas = servicioPregunta.getTodasLasPreguntas(categoria, dificultad);

        if (preguntas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(preguntas);
    }

}
