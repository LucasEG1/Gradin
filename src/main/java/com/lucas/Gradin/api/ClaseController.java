package com.lucas.Gradin.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Gradin.entity.ClaseEntity;
import com.lucas.Gradin.service.ClaseService;

@RestController
@RequestMapping("/clase")
public class ClaseController {
    
    @Autowired
    private ClaseService oClaseService;


    public ClaseController(ClaseService oClaseService) {
        this.oClaseService = oClaseService;
    }

    // GET
    @GetMapping("/{id}")
    public ResponseEntity<ClaseEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<ClaseEntity>(oClaseService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ClaseEntity>> getPage(
        @PageableDefault(page = 0, size = 5) Pageable oPageable,
        @RequestParam(name = "filter", required = false) String strFilter,
        @RequestParam(name = "aula", required = false) Long idAula
    ) {
        return new ResponseEntity<Page<ClaseEntity>>(oClaseService.getPage(oPageable, strFilter, idAula), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oClaseService.count(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ClaseEntity oClaseEntity) {
        return new ResponseEntity<Long>(oClaseService.create(oClaseEntity), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClaseEntity> update(@RequestBody ClaseEntity oClaseEntity) {
        return new ResponseEntity<ClaseEntity>(oClaseService.update(oClaseEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oClaseService.delete(id), HttpStatus.OK);
    }

}
