package um.edu.ar.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import um.edu.ar.domain.Dispositivo;

/**
 * Spring Data JPA repository for the Dispositivo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {}
