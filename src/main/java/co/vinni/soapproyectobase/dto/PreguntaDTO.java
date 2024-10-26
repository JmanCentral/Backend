package co.vinni.soapproyectobase.dto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PreguntaDTO implements Serializable {

    private Long id_pregunta;
    private String pregunta;
    private String op1;
    private String op2;
    private String op3;
    private String op4;
    private String respuesta;
    private String categoria;
    private String dificultad;


}
