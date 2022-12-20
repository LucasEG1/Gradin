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

import com.lucas.Gradin.bean.ProfesorBean;
import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.service.AuthService;
import com.lucas.Gradin.service.ProfesorService;

@RestController
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private final ProfesorService oProfesorService;

    @Autowired
    AuthService oAuthService;

    public ProfesorController(ProfesorService profesorService) {
        this.oProfesorService = profesorService;
    }

    // Metodos GET
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorEntity> getOne(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<ProfesorEntity>(oProfesorService.getOne(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProfesorEntity>> getPage(
            @PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter) {
        return new ResponseEntity<>(oProfesorService.getPage(oPageable, strFilter), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oProfesorService.count(), HttpStatus.OK);
    }

    // Metodos POST
    @PostMapping
    public ResponseEntity<Long> create(@RequestBody ProfesorEntity oProfesorEntity) {
        return new ResponseEntity<Long>(oProfesorService.create(oProfesorEntity), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ProfesorEntity> login(@RequestBody ProfesorBean oProfesorEntity) {
        return new ResponseEntity<ProfesorEntity>(oAuthService.login(oProfesorEntity), HttpStatus.OK);
    }

    // Metodos PUT
    @PutMapping
    public ResponseEntity<ProfesorEntity> update(@RequestBody ProfesorEntity oProfesorEntity) {
        return new ResponseEntity<ProfesorEntity>(oProfesorService.update(oProfesorEntity), HttpStatus.OK);
    }

    // Metodos DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oProfesorService.delete(id), HttpStatus.OK);
    }
}
