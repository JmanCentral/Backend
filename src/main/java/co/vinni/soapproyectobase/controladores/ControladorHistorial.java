package co.vinni.soapproyectobase.controladores;


import co.vinni.soapproyectobase.dto.HistorialDTO;
import co.vinni.soapproyectobase.servicios.ServicioHistorial;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@RestController
@RequestMapping("/Trivia/historial")
public class ControladorHistorial {

    @Autowired
    private ServicioHistorial servicioHistorial;

    @PostMapping("/registrar")
    public ResponseEntity<HistorialDTO> registrarHistorial(@RequestBody HistorialDTO historialDto) {
        HistorialDTO historialGuardado = servicioHistorial.registrarHistorial(historialDto);
        return ResponseEntity.ok(historialGuardado);
    }

    @GetMapping("/usuario/{username}")
    public ResponseEntity<List<HistorialDTO>> obtenerHistorialPorUsuario(@PathVariable String username) {
        List<HistorialDTO> historialUsuario = servicioHistorial.obtenerHistorialPorUsuario(username);
        return ResponseEntity.ok(historialUsuario);
    }

    @GetMapping("/usuario/{username}/puntos-totales")
    public ResponseEntity<Integer> obtenerTotalPuntosPorUsuario(@PathVariable String username) {
        Integer totalPuntos = servicioHistorial.obtenerTotalPuntosPorUsuario(username);
        return ResponseEntity.ok(totalPuntos);
    }

}
