package um.edu.ar.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Venta.
 */
@Entity
@Table(name = "venta")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "id_venta_profe", nullable = false)
    private Long idVentaProfe;

    @Column(name = "descripcion")
    private String descripcion;

    @NotNull
    @Column(name = "preciofinal", precision = 21, scale = 2, nullable = false)
    private BigDecimal preciofinal;

    @Column(name = "fecha")
    private Instant fecha;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Venta id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVentaProfe() {
        return this.idVentaProfe;
    }

    public Venta idVentaProfe(Long idVentaProfe) {
        this.setIdVentaProfe(idVentaProfe);
        return this;
    }

    public void setIdVentaProfe(Long idVentaProfe) {
        this.idVentaProfe = idVentaProfe;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Venta descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPreciofinal() {
        return this.preciofinal;
    }

    public Venta preciofinal(BigDecimal preciofinal) {
        this.setPreciofinal(preciofinal);
        return this;
    }

    public void setPreciofinal(BigDecimal preciofinal) {
        this.preciofinal = preciofinal;
    }

    public Instant getFecha() {
        return this.fecha;
    }

    public Venta fecha(Instant fecha) {
        this.setFecha(fecha);
        return this;
    }

    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venta)) {
            return false;
        }
        return getId() != null && getId().equals(((Venta) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venta{" +
            "id=" + getId() +
            ", idVentaProfe=" + getIdVentaProfe() +
            ", descripcion='" + getDescripcion() + "'" +
            ", preciofinal=" + getPreciofinal() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
