package com.lucas.Gradin.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.bean.ProfesorBean;
import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.repository.ProfesorRepository;
import com.lucas.Gradin.exception.UnauthorizedException;

@Service
public class AuthService {
    
    @Autowired
    HttpSession oHttpSession;

    @Autowired
    ProfesorRepository oProfesorRepository;

    // OPERACIONES DE SESIÓN
    public ProfesorEntity login(ProfesorBean oProfesorBean) {
        if (oProfesorBean.getPass() != null) {
            ProfesorEntity oProfesorEntity = oProfesorRepository.findByDniAndPass(oProfesorBean.getDni(), oProfesorBean.getPass());
            if (oProfesorEntity != null) {
                oHttpSession.setAttribute("profesor", oProfesorEntity);
                return oProfesorEntity;
            } else {
                throw new UnauthorizedException("DNI o contraseña incorrectos");
            }
        } else {
            throw new UnauthorizedException("contraseña incorrecta");
        }
    }

    public void logout() {
        oHttpSession.invalidate();
    }

    public ProfesorEntity check() {
        ProfesorEntity oProfesorSessionEntity = (ProfesorEntity) oHttpSession.getAttribute("profesor");
        if (oProfesorSessionEntity != null) {
            return oProfesorSessionEntity;
        } else {
            throw new UnauthorizedException("sin sesión activa");
        }
    }

    // COMPROBACIONES 
    public void OnlySuperuser() {
        ProfesorEntity oProfesorEntity = (ProfesorEntity) oHttpSession.getAttribute("profesor");
        if (oProfesorEntity == null || !oProfesorEntity.isSuperuser()) {
            throw new UnauthorizedException("Operación solo permitida a superusuarios");
        }
    }
    
    // CONTROL DE ACCESO
    public void OnlyOwnerOrSuperuser(Long id) {
        ProfesorEntity oProfesorEntity = (ProfesorEntity) oHttpSession.getAttribute("profesor");
        if (oProfesorEntity == null || (!oProfesorEntity.isSuperuser() && !oProfesorEntity.getId().equals(id))) {
            throw new UnauthorizedException("Operación solo permitida al propietario o a superusuarios");
        }
    }


}
