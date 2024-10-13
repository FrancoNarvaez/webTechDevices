package um.edu.ar.service.mapper;

import org.mapstruct.*;
import um.edu.ar.domain.Venta;
import um.edu.ar.service.dto.VentaDTO;

/**
 * Mapper for the entity {@link Venta} and its DTO {@link VentaDTO}.
 */
@Mapper(componentModel = "spring")
public interface VentaMapper extends EntityMapper<VentaDTO, Venta> {}
