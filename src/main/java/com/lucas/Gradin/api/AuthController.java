package com.lucas.Gradin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Gradin.bean.ProfesorBean;
import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.service.AuthService;
import com.lucas.Gradin.service.ProfesorService;



@RestController
@RequestMapping("/sesion")
public class AuthController {
    
    @Autowired
    AuthService oAuthService;
    
    @Autowired
    ProfesorService oProfesorService;

    // Metodos POST
    @PostMapping("/login")
    public ResponseEntity<ProfesorEntity> login(@RequestBody ProfesorBean oProfesorBean) {
        return new ResponseEntity<ProfesorEntity>(oAuthService.login(oProfesorBean), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProfesorEntity> check() {
        return new ResponseEntity<ProfesorEntity>(oAuthService.check(), HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<?> logout() {
        oAuthService.logout();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
