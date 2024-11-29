package um.edu.ar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

import um.edu.ar.domain.Adicional;
import um.edu.ar.domain.Opcion;

/**
 * A DTO for the {@link um.edu.ar.domain.Venta} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VentaDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idVenta;

    private Long idDispositivo;

    private List<Opcion> personalizaciones;

    private List<Adicional> adicionales;

    private String descripcion;

    @NotNull
    private BigDecimal preciofinal;

    private Instant fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public List<Opcion> getPersonalizaciones() {
        return personalizaciones;
    }

    public void setpersonalizaciones(List<Opcion> personalizaciones) {
        this.personalizaciones = personalizaciones;
    }

    public List<Adicional> getAdicionales() {
        return adicionales;
    }

    public void setadicionales(List<Adicional> adicionaleses) {
        this.adicionales = adicionaleses;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPreciofinal() {
        return preciofinal;
    }

    public void setPreciofinal(BigDecimal preciofinal) {
        this.preciofinal = preciofinal;
    }

    public Instant getFecha() {
        return fecha;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VentaDTO)) {
            return false;
        }

        VentaDTO ventaDTO = (VentaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ventaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VentaDTO{" +
            "id=" + getId() +
            ", idVenta=" + getIdVenta() +
            ", idDispositivo=" + getIdDispositivo() +
            ", personalizaciones=" + getPersonalizaciones() +
            ", adicionales=" + getAdicionales() +
            ", descripcion='" + getDescripcion() + "'" +
            ", preciofinal=" + getPreciofinal() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }

}
