package um.edu.ar.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import um.edu.ar.repository.VentaRepository;
import um.edu.ar.service.VentaService;
import um.edu.ar.service.dto.VentaDTO;
import um.edu.ar.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link um.edu.ar.domain.Venta}.
 */
@RestController
@RequestMapping("/api/ventas")
public class VentaResource {

    private static final Logger LOG = LoggerFactory.getLogger(VentaResource.class);

    private static final String ENTITY_NAME = "venta";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VentaService ventaService;

    private final VentaRepository ventaRepository;


    public VentaResource(VentaService ventaService, VentaRepository ventaRepository) {
        this.ventaService = ventaService;
        this.ventaRepository = ventaRepository;

    }

    /**
     * {@code POST  /ventas} : Create a new venta.
     *
     */
    @PostMapping("")
    public ResponseEntity<VentaDTO> createVenta(@RequestBody VentaDTO ventaDTO) {
        LOG.debug("REST request to save Venta : {}", ventaDTO);
        VentaDTO result = ventaService.save(ventaDTO);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code PUT  /ventas/:id} : Updates an existing venta.
     *
     * @param id the id of the ventaDTO to save.
     * @param ventaDTO the ventaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaDTO,
     * or with status {@code 400 (Bad Request)} if the ventaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ventaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> updateVenta(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VentaDTO ventaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Venta : {}, {}", id, ventaDTO);
        if (ventaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ventaDTO = ventaService.update(ventaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaDTO.getId().toString()))
            .body(ventaDTO);
    }

    /**
     * {@code PATCH  /ventas/:id} : Partial updates given fields of an existing venta, field will ignore if it is null
     *
     * @param id the id of the ventaDTO to save.
     * @param ventaDTO the ventaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ventaDTO,
     * or with status {@code 400 (Bad Request)} if the ventaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ventaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ventaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VentaDTO> partialUpdateVenta(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VentaDTO ventaDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Venta partially : {}, {}", id, ventaDTO);
        if (ventaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ventaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ventaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VentaDTO> result = ventaService.partialUpdate(ventaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ventaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ventas} : get all the ventas.
     *
     *
     */
    @GetMapping("")
    public ResponseEntity<List<VentaDTO>> getAllVentas() {
        LOG.debug("REST request to get all Ventas");
        List<VentaDTO> result = ventaService.getVentas();
        return ResponseEntity.ok(result);
    }

    /**
     * {@code GET  /ventas/:id} : get the "id" venta.
     *
     * @param id the id of the ventaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ventaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> getVenta(@PathVariable Long id) {
        LOG.debug("REST request to get Venta : {}", id);
        VentaDTO result = ventaService.getVentaById(id);
        return ResponseEntity.ok(result);
    }

    /**
     * {@code DELETE  /ventas/:id} : delete the "id" venta.
     *
     * @param id the id of the ventaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Venta : {}", id);
        ventaService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
