package um.edu.ar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link um.edu.ar.domain.Venta} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VentaDTO implements Serializable {

    private Long id;

    @NotNull
    private Long idVentaProfe;

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

    public Long getIdVentaProfe() {
        return idVentaProfe;
    }

    public void setIdVentaProfe(Long idVentaProfe) {
        this.idVentaProfe = idVentaProfe;
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
            ", idVentaProfe=" + getIdVentaProfe() +
            ", descripcion='" + getDescripcion() + "'" +
            ", preciofinal=" + getPreciofinal() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
