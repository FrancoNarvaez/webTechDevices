package um.edu.ar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link um.edu.ar.domain.Personalizacion} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PersonalizacionDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    private String descripcion;

    private DispositivoDTO opciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DispositivoDTO getOpciones() {
        return opciones;
    }

    public void setOpciones(DispositivoDTO opciones) {
        this.opciones = opciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonalizacionDTO)) {
            return false;
        }

        PersonalizacionDTO personalizacionDTO = (PersonalizacionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, personalizacionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonalizacionDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", opciones=" + getOpciones() +
            "}";
    }
}
