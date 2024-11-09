package co.vinni.soapproyectobase.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    private Long id_pregunta;
    private String username;
    private String categoria;
    private String dificultad;

}
