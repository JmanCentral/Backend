package co.vinni.soapproyectobase.servicios;

import co.vinni.soapproyectobase.dto.PreguntaDTO;
import co.vinni.soapproyectobase.entidades.Pregunta;
import co.vinni.soapproyectobase.repositorios.RepositorioPregunta;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ServicioPregunta implements Serializable {

    private ModelMapper modelMapper;

    @Autowired
    private RepositorioPregunta repositorioPregunta;

    @PostConstruct
    public void insertAllPreguntas() {
        List<PreguntaDTO> preguntaDTOs = crearPreguntas();

        // Iterar sobre cada PreguntaDTO
        for (PreguntaDTO dto : preguntaDTOs) {

            List<Pregunta> existentes = repositorioPregunta.findByPregunta(dto.getPregunta());

            if (existentes.isEmpty()) {
                Pregunta nuevaPregunta = modelMapper.map(dto, Pregunta.class);
                repositorioPregunta.save(nuevaPregunta);
            }
        }
    }



    private List<PreguntaDTO> crearPreguntas() {
        return List.of(
                new PreguntaDTO(null, "¿Cuál de los siguientes no es un número primo?", "31", "61", "71", "91", "91", "matematicas","facil"),
                new PreguntaDTO(null, "¿Qué número mínimo debe sumarse a 1056 para que el resultado sea divisible completamente por 23?", "2", "3", "18", "21", "2", "matematicas","dificl"),
                new PreguntaDTO(null, "¿1397 x 1397 = ?", "1981709", "1951609", "18362619", "2031719", "1951609", "matematicas","media"),
                new PreguntaDTO(null, "El mayor número de 4 dígitos exactamente divisible por 88 es:", "8888", "9768", "9988", "9944", "9944", "matematicas","media"),
                new PreguntaDTO(null, "¿Cuál de los siguientes es un número primo?", "33", "81", "93", "97", "97", "matematicas","facil"),
                new PreguntaDTO(null, "El porcentaje de tierra irrigada en la India es aproximadamente", "45%", "65%", "35%", "25%", "35%", "geografia","facil"),
                new PreguntaDTO(null, "¿Cuál es la capital del subcontinente indio?", "Berlín", "Delhi", "Chennai", "Estocolmo", "Delhi", "geografia","facil"),
                new PreguntaDTO(null, "El estado con la mayor área de cobertura forestal en la India es", "Arunachal Pradesh", "Haryana", "Madhya Pradesh", "Assam", "Madhya Pradesh", "geografia","dificil"),
                new PreguntaDTO(null, "Las montañas más antiguas de la India son:", "Aravalis", "Vindhyas", "Satpuras", "Colinas Nilgiri", "Aravalis", "geografia","media"),
                new PreguntaDTO(null, "¿Qué país tiene la mayor población?", "India", "Japón", "China", "Shillong", "China", "geografia", "media"),
                new PreguntaDTO(null, "¿Sobre cuál de estos personajes escribió Mark Twain?", "Niño azul", "Dennis el travieso", "Caperucita roja", "Tom Sawyer", "Tom Sawyer", "literatura", "media"),
                new PreguntaDTO(null, "¿Quién escribió \"Matar a un ruiseñor\"?", "Dr. Seuss", "Harper Lee", "Ronald Reagan", "John Lennon", "Harper Lee", "literatura", "media"),
                new PreguntaDTO(null, "¿Cuál obra de Shakespeare presenta a amantes que se suicidan?", "Romeo y Julieta", "La paloma solitaria", "Desayuno en Tiffany's", "Corre conejo corre", "Romeo y Julieta", "literatura", "media"),
                new PreguntaDTO(null, "¿Qué describe mejor \"Rebelión en la granja\" de George Orwell?", "Mujeres/Ciclos mensuales", "Babe Ruth/Yankees", "Dinosaurios/Barney", "Animales/Comunismo", "Animales/Comunismo", "literatura", "media"),
                new PreguntaDTO(null, "¿Qué libro se centra en un caballo?", "Los Muppets", "Rebelión en la granja", "Belleza negra", "Atrapado sin salida", "Belleza negra", "literatura", "media"),
                new PreguntaDTO(null, "¿Cuál es el valor de π (Pi) aproximado a dos decimales?", "3.12", "3.14", "3.16", "3.18", "3.14", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuánto es 12 al cuadrado?", "124", "132", "144", "154", "144", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es el resultado de 25 x 25?", "525", "625", "725", "825", "625", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es el valor absoluto de -15?", "-15", "15", "0", "-1", "15", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es la raíz cuadrada de 64?", "6", "7", "8", "9", "8", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuántos grados tiene un triángulo equilátero en total?", "90", "180", "270", "360", "180", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es el número primo más pequeño?", "1", "2", "3", "5", "2", "matematicas", "media"),
                new PreguntaDTO(null, "Si un coche recorre 60 km en una hora, ¿cuánto tiempo tomará recorrer 180 km a la misma velocidad?", "2 horas", "3 horas", "4 horas", "5 horas", "3 horas", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuántos lados tiene un decágono?", "8", "9", "10", "12", "10", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es la fórmula del área de un círculo?", "πr²", "2πr", "r²", "πd", "πr²", "matematicas", "media"),
                new PreguntaDTO(null, "¿Cuál es el río más largo del mundo?", "Río Amazonas", "Río Nilo", "Río Yangtsé", "Río Misisipi", "Río Nilo", "geografia", "media"),
                new PreguntaDTO(null, "¿Cuál es el país más grande del mundo por área?", "China", "Estados Unidos", "Rusia", "Canadá", "Rusia", "geografia", "media"),
                new PreguntaDTO(null, "¿En qué continente se encuentra el desierto del Sahara?", "Asia", "África", "América del Sur", "Oceanía", "África", "geografia", "media"),
                new PreguntaDTO(null, "¿Cuál es la montaña más alta del mundo?", "Monte Kilimanjaro", "Monte Everest", "Mont Blanc", "Monte Aconcagua", "Monte Everest", "geografia", "media"),
                new PreguntaDTO(null, "¿Qué país tiene más islas en el mundo?", "Suecia", "Filipinas", "Indonesia", "Maldivas", "Suecia", "geografia", "media"),
                new PreguntaDTO(null, "¿Cuál es la capital de Canadá?", "Toronto", "Ottawa", "Vancouver", "Montreal", "Ottawa", "geografia", "media"),
                new PreguntaDTO(null, "¿Qué océano baña las costas occidentales de América del Sur?", "Océano Atlántico", "Océano Índico", "Océano Pacífico", "Océano Ártico", "Océano Pacífico", "geografia", "media"),
                new PreguntaDTO(null, "¿En qué país se encuentra la torre inclinada de Pisa?", "España", "Francia", "Italia", "Grecia", "Italia", "geografia", "media"),
                new PreguntaDTO(null, "¿Cuál es el país más poblado del mundo?", "India", "China", "Estados Unidos", "Brasil", "China", "geografia", "media"),
                new PreguntaDTO(null, "¿Cuál es el país más pequeño del mundo por área?", "Mónaco", "San Marino", "Vaticano", "Liechtenstein", "Vaticano", "geografia", "media"),
                new PreguntaDTO(null, "¿Quién escribió la novela \"Cien años de soledad\"?", "Pablo Neruda", "Gabriel García Márquez", "Mario Vargas Llosa", "Jorge Luis Borges", "Gabriel García Márquez", "literatura", "media"),
                new PreguntaDTO(null, "¿Cuál es la obra más famosa de Miguel de Cervantes?", "El Lazarillo de Tormes", "Don Quijote de la Mancha", "La Celestina", "La Regenta", "Don Quijote de la Mancha", "literatura", "media"),
                new PreguntaDTO(null, "¿En qué país nació William Shakespeare?", "Francia", "España", "Inglaterra", "Italia", "Inglaterra", "literatura", "media"),
                new PreguntaDTO(null, "¿Qué autor escribió la novela \"Moby Dick\"?", "Herman Melville", "Mark Twain", "Jules Verne", "Robert Louis Stevenson", "Herman Melville", "literatura", "media"),
                new PreguntaDTO(null, "¿Qué poeta chileno escribió \"Veinte poemas de amor y una canción desesperada\"?", "Pablo Neruda", "Gabriela Mistral", "Octavio Paz", "César Vallejo", "Pablo Neruda", "literatura", "media"),
                new PreguntaDTO(null, "¿Qué obra es famosa por la frase \"Ser o no ser, esa es la cuestión\"?", "Macbeth", "El Rey Lear", "Hamlet", "Otelo", "Hamlet", "literatura", "alta"),
                new PreguntaDTO(null, "¿Quién es el autor de \"El gran Gatsby\"?", "Ernest Hemingway", "F. Scott Fitzgerald", "John Steinbeck", "William Faulkner", "F. Scott Fitzgerald", "literatura", "baja"),
                new PreguntaDTO(null, "¿Qué novela de George Orwell describe un futuro distópico bajo un régimen totalitario?", "Rebelión en la granja", "1984", "Un mundo feliz", "Fahrenheit 451", "1984", "literatura", "alta"),
                new PreguntaDTO(null, "¿Quién escribió \"Orgullo y prejuicio\"?", "Charlotte Brontë", "Emily Brontë", "Mary Shelley", "Jane Austen", "Jane Austen", "literatura", "media"),
                new PreguntaDTO(null, "¿Cuál es el título de la famosa obra épica de Homero?", "La Ilíada", "La Eneida", "La Odisea", "Los trabajos de Hércules", "La Ilíada", "literatura", "alta"),
                new PreguntaDTO(null, "¿En qué deporte se utiliza un disco volador?", "Fútbol", "Ultimate", "Béisbol", "Golf", "Ultimate", "deportes", "media"),
                new PreguntaDTO(null, "¿Quién tiene el récord de más goles en la historia de la Copa del Mundo?", "Pelé", "Diego Maradona", "Marta", "Miroslav Klose", "Miroslav Klose", "deportes", "alta"),
                new PreguntaDTO(null, "¿En qué deporte se compite en la serie de Grand Slam?", "Tenis", "Golf", "Ciclismo", "Natación", "Tenis", "deportes", "baja"),
                new PreguntaDTO(null, "¿Qué país ganó la Copa Mundial de Fútbol en 2018?", "Alemania", "Francia", "Brasil", "Argentina", "Francia", "deportes", "alta"),
                new PreguntaDTO(null, "¿Quién es conocido como el 'Rey del Fútbol'?", "Lionel Messi", "Cristiano Ronaldo", "Pelé", "Johan Cruyff", "Pelé", "deportes", "media"),
                new PreguntaDTO(null, "¿Cuántos jugadores hay en un equipo de baloncesto?", "5", "6", "7", "8", "5", "deportes", "baja"),
                new PreguntaDTO(null, "¿Qué deporte se juega en un campo con una red alta?", "Fútbol", "Tenis", "Béisbol", "Golf", "Tenis", "deportes", "media"),
                new PreguntaDTO(null, "¿En qué deporte se utiliza una pelota ovalada?", "Fútbol", "Rugby", "Baloncesto", "Hockey", "Rugby", "deportes", "media"),
                new PreguntaDTO(null, "¿Cuál es la distancia de un maratón?", "21 km", "42 km", "10 km", "50 km", "42 km", "deportes", "alta"),
                new PreguntaDTO(null, "¿Qué jugador tiene más títulos de la NBA?", "Kareem Abdul-Jabbar", "Michael Jordan", "LeBron James", "Bill Russell", "Bill Russell", "deportes", "alta"),
                new PreguntaDTO(null, "¿Quién es el creador de la serie 'Los Simpsons'?", "Seth MacFarlane", "Matt Groening", "Trey Parker", "Mike Judge", "Matt Groening", "entretenimiento", "media"),
                new PreguntaDTO(null, "¿Cuál es la película más taquillera de todos los tiempos?", "Avatar", "Titanic", "Star Wars: El despertar de la Fuerza", "Avengers: Endgame", "Avengers: Endgame", "entretenimiento", "alta"),
                new PreguntaDTO(null, "¿En qué año se estrenó 'Titanic'?", "1995", "1997", "2000", "2003", "1997", "entretenimiento", "baja"),
                new PreguntaDTO(null, "¿Qué serie se desarrolla en un mundo post-apocalíptico lleno de zombis?", "Stranger Things", "The Walking Dead", "Game of Thrones", "Westworld", "The Walking Dead", "entretenimiento", "media"),
                new PreguntaDTO(null, "¿Quién ganó el premio Óscar a la mejor película en 2020?", "Parasite", "1917", "Joker", "Ford vs Ferrari", "Parasite", "entretenimiento", "alta"),
                new PreguntaDTO(null, "¿Cuál es el nombre del personaje principal en 'Harry Potter'?", "Draco Malfoy", "Hermione Granger", "Harry Potter", "Albus Dumbledore", "Harry Potter", "entretenimiento", "baja"),
                new PreguntaDTO(null, "¿Qué película de Disney presenta a una sirena llamada Ariel?", "La Bella y la Bestia", "Mulan", "La Sirenita", "Pocahontas", "La Sirenita", "entretenimiento", "media"),
                new PreguntaDTO(null, "¿Cuál es el nombre del famoso mago de 'El Señor de los Anillos'?", "Gandalf", "Dumbledore", "Merlín", "Saruman", "Gandalf", "entretenimiento", "media"),
                new PreguntaDTO(null, "¿Qué banda británica es famosa por canciones como 'Hey Jude' y 'Let It Be'?", "The Rolling Stones", "The Beatles", "Queen", "Led Zeppelin", "The Beatles", "entretenimiento", "alta"),
                new PreguntaDTO(null, "¿Cuál es el nombre del creador de 'Star Wars'?", "Steven Spielberg", "George Lucas", "James Cameron", "Christopher Nolan", "George Lucas", "entretenimiento", "alta")

        );
    }

    public List<PreguntaDTO> getTodasLasPreguntas(String categoria , String dificultad) {
        List<Pregunta> preguntas = repositorioPregunta.findByCategoriaAndDificultad(categoria , dificultad);
        return preguntas.stream()
                .map(pregunta -> modelMapper.map(pregunta, PreguntaDTO.class))
                .collect(Collectors.toList());
    }

}
