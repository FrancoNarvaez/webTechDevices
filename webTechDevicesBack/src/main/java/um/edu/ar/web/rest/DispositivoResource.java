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
import um.edu.ar.repository.DispositivoRepository;
import um.edu.ar.service.DispositivoService;
import um.edu.ar.service.dto.DispositivoDTO;
import um.edu.ar.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link um.edu.ar.domain.Dispositivo}.
 */
@RestController
@RequestMapping("/api/catedra/dispositivos")
public class DispositivoResource {

    private static final Logger LOG = LoggerFactory.getLogger(DispositivoResource.class);

    private static final String ENTITY_NAME = "dispositivo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DispositivoService dispositivoService;

    private final DispositivoRepository dispositivoRepository;

    public DispositivoResource(DispositivoService dispositivoService, DispositivoRepository dispositivoRepository) {
        this.dispositivoService = dispositivoService;
        this.dispositivoRepository = dispositivoRepository;
    }

    @PostMapping("")
    public ResponseEntity<DispositivoDTO> createDispositivo(@Valid @RequestBody DispositivoDTO dispositivoDTO) throws URISyntaxException {
        LOG.debug("REST request to save Dispositivo : {}", dispositivoDTO);
        if (dispositivoDTO.getId() != null) {
            throw new BadRequestAlertException("A new dispositivo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        dispositivoDTO = dispositivoService.save(dispositivoDTO);
        return ResponseEntity.created(new URI("/api/catedra/dispositivos/" + dispositivoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, dispositivoDTO.getId().toString()))
            .body(dispositivoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoDTO> updateDispositivo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DispositivoDTO dispositivoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Dispositivo : {}, {}", id, dispositivoDTO);
        if (dispositivoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispositivoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispositivoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        dispositivoDTO = dispositivoService.update(dispositivoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dispositivoDTO.getId().toString()))
            .body(dispositivoDTO);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DispositivoDTO> partialUpdateDispositivo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DispositivoDTO dispositivoDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Dispositivo partially : {}, {}", id, dispositivoDTO);
        if (dispositivoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dispositivoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!dispositivoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DispositivoDTO> result = dispositivoService.partialUpdate(dispositivoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dispositivoDTO.getId().toString())
        );
    }

//    @GetMapping("")
//    public ResponseEntity<List<DispositivoDTO>> getAllDispositivos(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
//        LOG.debug("REST request to get a page of Dispositivos");
//        Page<DispositivoDTO> page = dispositivoService.findAll(pageable);
//        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
//        return ResponseEntity.ok().headers(headers).body(page.getContent());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> getDispositivo(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Dispositivo : {}", id);
        Optional<DispositivoDTO> dispositivoDTO = dispositivoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dispositivoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivo(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Dispositivo : {}", id);
        dispositivoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
