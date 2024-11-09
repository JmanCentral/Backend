package co.vinni.soapproyectobase.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PREGUNTA")

public class Pregunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pregunta;

    @Column(name = "PREGUNTA")
    private String pregunta;

    @Column(name = "OP1")
    private String op1;

    @Column(name = "OP2")
    private String op2;

    @Column(name = "OP3")
    private String op3;

    @Column(name = "OP4")
    private String op4;

    @Column(name = "RESPUESTA")
    private String respuesta;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "DIFICULTAD")
    private String dificultad;

    @ManyToMany(mappedBy = "preguntas")
    private List<Historial> historiales = new ArrayList<>();
}
