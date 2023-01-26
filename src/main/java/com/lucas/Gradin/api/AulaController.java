package com.lucas.Gradin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Gradin.entity.AulaEntity;
import com.lucas.Gradin.service.AulaService;

@RestController
@RequestMapping("/aula")
public class AulaController {
    

    @Autowired
    private AulaService oAulaService;

    @GetMapping("/{id}")
    public ResponseEntity<AulaEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<AulaEntity>(oAulaService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AulaEntity>> getPage (
        @PageableDefault(size = 5, page = 0) Pageable oPageable,
        @PathVariable(name = "escuela", required = false) Long idEscuela,
        @PathVariable(name = "filter", required = false) String strFilter
    ) {
        return new ResponseEntity<Page<AulaEntity>>(oAulaService.getPage(oPageable, strFilter, idEscuela), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody AulaEntity oAulaEntity) {
        return new ResponseEntity<Long>(oAulaService.create(oAulaEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AulaEntity> update(@RequestBody AulaEntity oAulaEntity) {
        return new ResponseEntity<AulaEntity>(oAulaService.update(oAulaEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oAulaService.delete(id), HttpStatus.OK);
    }
}
