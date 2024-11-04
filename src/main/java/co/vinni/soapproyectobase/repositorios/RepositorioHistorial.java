package co.vinni.soapproyectobase.repositorios;

import co.vinni.soapproyectobase.entidades.Historial;
import co.vinni.soapproyectobase.entidades.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioHistorial extends JpaRepository<Historial, Long> , JpaSpecificationExecutor<Historial> {

    @Query("SELECT h FROM Historial h WHERE h.usuario.username = :username ORDER BY h.tiempo DESC")
    List<Historial> getDatosDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.puntaje) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTotalPuntosDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.ayudas) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTotalAyudasDelUsuario(@Param("username") String username);

    @Query("SELECT SUM(h.tiempo) FROM Historial h WHERE h.usuario.username = :username")
    Integer getTiempoTotalDelUsuario(@Param("username") String username);


}

