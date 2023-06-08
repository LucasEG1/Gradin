package com.lucas.Gradin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Gradin.entity.NotaEntity;
import com.lucas.Gradin.service.NotaService;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaService oNotaService;

    public NotaController(NotaService oNotaService) {
        this.oNotaService = oNotaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<NotaEntity>(oNotaService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<NotaEntity>> getPage(
            @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "alumno", required = false) Long idAlumno,
            @RequestParam(name = "asignatura", required = false) Long idAsignatura,
            @RequestParam(name = "evaluacion", required = false) Long idEvaluacion,
            @RequestParam(name = "profesor") Long idProfesor) {
        return new ResponseEntity<>(oNotaService.getPage(oPageable, idAlumno, idAsignatura, idEvaluacion, idProfesor), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oNotaService.count(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody NotaEntity oNotaEntity) {
        return new ResponseEntity<Long>(oNotaService.create(oNotaEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<NotaEntity> update(@RequestBody NotaEntity oNotaEntity, @RequestParam(name = "profesor") Long idProfesor) {
        return new ResponseEntity<NotaEntity>(oNotaService.update(oNotaEntity, idProfesor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oNotaService.delete(id), HttpStatus.OK);
    }
}