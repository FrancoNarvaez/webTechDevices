package um.edu.ar.service.mapper;

import org.mapstruct.*;
import um.edu.ar.domain.Adicional;
import um.edu.ar.domain.Dispositivo;
import um.edu.ar.service.dto.AdicionalDTO;
import um.edu.ar.service.dto.DispositivoDTO;

/**
 * Mapper for the entity {@link Adicional} and its DTO {@link AdicionalDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdicionalMapper extends EntityMapper<AdicionalDTO, Adicional> {
    @Mapping(target = "dispositivo", source = "dispositivo", qualifiedByName = "dispositivoId")
    AdicionalDTO toDto(Adicional s);

    @Named("dispositivoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DispositivoDTO toDtoDispositivoId(Dispositivo dispositivo);
}
