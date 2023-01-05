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

import com.lucas.Gradin.entity.AsignaturaEntity;
import com.lucas.Gradin.service.AuthService;
import com.lucas.Gradin.service.AsignaturaService;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    private final AsignaturaService oAsignaturaService;

    @Autowired
    AuthService oAuthService;

    public AsignaturaController(AsignaturaService AsignaturaService) {
        this.oAsignaturaService = AsignaturaService;
    }

    // Metodos GET
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<AsignaturaEntity>(oAsignaturaService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AsignaturaEntity>> getPage(
            @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "profesor", required = false) Long idProfesor) {
        return new ResponseEntity<>(oAsignaturaService.getPage(oPageable, strFilter, idProfesor), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oAsignaturaService.count(), HttpStatus.OK);
    }

    // Metodos POST
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AsignaturaEntity oAsignaturaEntity) {
        return new ResponseEntity<Long>(oAsignaturaService.create(oAsignaturaEntity), HttpStatus.OK);
    }

    // Metodos PUT
    @PutMapping
    public ResponseEntity<AsignaturaEntity> update(@RequestBody AsignaturaEntity oAsignaturaEntity) {
        return new ResponseEntity<AsignaturaEntity>(oAsignaturaService.update(oAsignaturaEntity), HttpStatus.OK);
    }

    // Metodos DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oAsignaturaService.delete(id), HttpStatus.OK);
    }
}
