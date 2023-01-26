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

import com.lucas.Gradin.entity.EscuelaEntity;
import com.lucas.Gradin.service.EscuelaService;

@RestController
@RequestMapping("/escuela")
public class EscuelaController {

    @Autowired
    private EscuelaService escuelaService;

    @GetMapping("/{id}")
    public ResponseEntity<EscuelaEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<EscuelaEntity>(escuelaService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<EscuelaEntity>> getPage(
        @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter
    ) {
        return new ResponseEntity<Page<EscuelaEntity>>(escuelaService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody EscuelaEntity nuevoEscuelaEntity) {
        return new ResponseEntity<Long>(escuelaService.create(nuevoEscuelaEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EscuelaEntity> update(@RequestBody EscuelaEntity oEscuelaEntity) {
        return new ResponseEntity<EscuelaEntity>(escuelaService.update(oEscuelaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(escuelaService.delete(id), HttpStatus.OK);
    }
}
