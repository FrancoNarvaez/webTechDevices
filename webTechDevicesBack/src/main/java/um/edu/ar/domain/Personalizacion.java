package um.edu.ar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Personalizacion.
 */
@Entity
@Table(name = "personalizacion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Personalizacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personalizacion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "personalizacion" }, allowSetters = true)
    private Set<Opcion> opcions = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "caracteristicas", "personalizaciones", "adicionales" }, allowSetters = true)
    private Dispositivo opciones;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Personalizacion id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Personalizacion nombre(String nombre) {
        this.setNombre(nombre);
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Personalizacion descripcion(String descripcion) {
        this.setDescripcion(descripcion);
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Opcion> getOpcions() {
        return this.opcions;
    }

    public void setOpcions(Set<Opcion> opcions) {
        if (this.opcions != null) {
            this.opcions.forEach(i -> i.setPersonalizacion(null));
        }
        if (opcions != null) {
            opcions.forEach(i -> i.setPersonalizacion(this));
        }
        this.opcions = opcions;
    }

    public Personalizacion opcions(Set<Opcion> opcions) {
        this.setOpcions(opcions);
        return this;
    }

    public Personalizacion addOpcion(Opcion opcion) {
        this.opcions.add(opcion);
        opcion.setPersonalizacion(this);
        return this;
    }

    public Personalizacion removeOpcion(Opcion opcion) {
        this.opcions.remove(opcion);
        opcion.setPersonalizacion(null);
        return this;
    }

    public Dispositivo getOpciones() {
        return this.opciones;
    }

    public void setOpciones(Dispositivo dispositivo) {
        this.opciones = dispositivo;
    }

    public Personalizacion opciones(Dispositivo dispositivo) {
        this.setOpciones(dispositivo);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personalizacion)) {
            return false;
        }
        return getId() != null && getId().equals(((Personalizacion) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personalizacion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
