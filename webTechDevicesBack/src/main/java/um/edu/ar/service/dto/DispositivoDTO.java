package um.edu.ar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link um.edu.ar.domain.Dispositivo} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DispositivoDTO implements Serializable {

    private Long id;

    @NotNull
    private String codigo;

    @NotNull
    private String nombre;

    private String descripcion;

    @NotNull
    private BigDecimal precioBase;

    @NotNull
    private String moneda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DispositivoDTO)) {
            return false;
        }

        DispositivoDTO dispositivoDTO = (DispositivoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dispositivoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DispositivoDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precioBase=" + getPrecioBase() +
            ", moneda='" + getMoneda() + "'" +
            "}";
    }
}
