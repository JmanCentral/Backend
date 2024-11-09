package co.vinni.soapproyectobase.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HISTORIAL")

public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PUNTAJE")
    private int puntaje;

    @Column(name = "FECHA")
    private String fecha;

    @Column(name = "TIEMPO")
    private int tiempo;

    @Column(name = "AYUDAS")
    private int ayudas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;


    @ManyToMany
    @JoinTable(
            name = "Pregunta_Historial",
            joinColumns = @JoinColumn(name = "id_historial"),
            inverseJoinColumns = @JoinColumn(name = "id_pregunta")
    )
    private List<Pregunta> preguntas = new ArrayList<>();

}



