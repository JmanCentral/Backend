package co.vinni.soapproyectobase.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HistorialDTO {

    private Long id;
    private int puntaje;
    private String fecha;
    private int tiempo;
    private int ayudas;
    private Long id_usuario;
    private String username;
    private List<Long> ids_preguntas;
    private String categoria;
    private String dificultad;


}
