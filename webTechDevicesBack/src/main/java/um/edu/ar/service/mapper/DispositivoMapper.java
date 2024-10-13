package um.edu.ar.service.mapper;

import org.mapstruct.*;
import um.edu.ar.domain.Dispositivo;
import um.edu.ar.service.dto.DispositivoDTO;

/**
 * Mapper for the entity {@link Dispositivo} and its DTO {@link DispositivoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DispositivoMapper extends EntityMapper<DispositivoDTO, Dispositivo> {}
