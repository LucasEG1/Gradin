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

import com.lucas.Gradin.entity.AlumnoEntity;
import com.lucas.Gradin.service.AlumnoService;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    
    @Autowired
    private final AlumnoService oAlumnoService;


    public AlumnoController(AlumnoService alumnoService) {
        this.oAlumnoService = alumnoService;
    }

    // Metodos GET
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<AlumnoEntity>(oAlumnoService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AlumnoEntity>> getPage(
        @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter,
        @RequestParam(name = "clase", required = false) Long idClase
    ) {
        return new ResponseEntity<Page<AlumnoEntity>>(oAlumnoService.getPage(oPageable, idClase, strFilter), HttpStatus.OK);
    }

    // Metodos POST
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AlumnoEntity oAlumnoEntity) {
        return new ResponseEntity<Long>(oAlumnoService.create(oAlumnoEntity), HttpStatus.OK);
    }

    // Metodos PUT
    @PutMapping
    public ResponseEntity<AlumnoEntity> update(@RequestBody AlumnoEntity oAlumnoEntity) {
        return new ResponseEntity<AlumnoEntity>(oAlumnoService.update(oAlumnoEntity), HttpStatus.OK);
    }

    // Metodos DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oAlumnoService.delete(id), HttpStatus.OK);
    } 
}
