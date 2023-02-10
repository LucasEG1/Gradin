package com.lucas.Gradin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Gradin.entity.EvaluacionEntity;
import com.lucas.Gradin.service.EvaluacionService;

@RestController
@RequestMapping("/evaluacion")
public class EvaluacionController {
 
    @Autowired
    private EvaluacionService oEvaluacionService;

    public EvaluacionController(EvaluacionService oEvaluacionService) {
        this.oEvaluacionService = oEvaluacionService;
    }

    @GetMapping
    public ResponseEntity<Page<EvaluacionEntity>> getPage(@PageableDefault(page = 0, size = 5, direction = Sort.Direction.DESC) Pageable oPageable) {
        return new ResponseEntity<Page<EvaluacionEntity>>(oEvaluacionService.getEvaluaciones(oPageable), HttpStatus.OK);
    }
    
}
