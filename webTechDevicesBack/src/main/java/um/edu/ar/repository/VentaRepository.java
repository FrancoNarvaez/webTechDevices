package um.edu.ar.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import um.edu.ar.domain.Venta;

/**
 * Spring Data JPA repository for the Venta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {}
