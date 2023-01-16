package com.lucas.Gradin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.ProfesorEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.ProfesorRepository;
import com.lucas.Gradin.helper.RandomHelper;
import com.lucas.Gradin.helper.ValidationHelper;

@Service
public class ProfesorService {

    private final String GRADIN_DEFAULT_PASSWORD = "7a84143d54b59fe2186d394f66fa59b5b81e12c8edf9cbe71cece88d9388ff45";
    private final String[] NOMBRES_PROFESORES = {
        "Alberto", "Flora", "Daniela", "Isabela", "Guillermo",
        "Luis", "Miguel", "Javier", "Jorge", "Jesús",
        "Juan", "José", "Manuel", "Francisco", "Antonio",
        "Gabriel", "Carlos", "Daniel", "David", "Diego",
        "Eduardo", "Elias", "Enrique", "Esteban", "Fernando",
        "Fidel", "Félix", "Francisco", "Gabriel", "Gonzalo",
        "Gregorio", "Guillermo", "Héctor", "Hugo", "Ignacio",
        "Iker", "Isaac", "Ismael", "Iván", "Jaime"};
        
    private final String[] APELLIDOS_PROFESORES = {
        "García", "González", "Rodríguez", "Fernández", "López",
        "Martínez", "Sánchez", "Pérez", "Gómez", "Martín",
        "Jiménez", "Ruiz", "Hernández", "Díaz", "Moreno",
        "Álvarez", "Muñoz", "Romero", "Alonso", "Gutiérrez",
        "Navarro", "Torres", "Domínguez", "Vázquez", "Ramos",
        "Gil", "Ramírez", "Serrano", "Blanco", "Suárez",
        "Molina", "Morales", "Ortega", "Delgado", "Castro",
        "Ortiz", "Rubio", "Marín", "Sanz", "Iglesias"};

    @Autowired
    private ProfesorRepository oProfesorRepository;

    @Autowired
    private AuthService oAuthService;

    // VALIDACIONES
    public void validate(Long id) {
        if (!oProfesorRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe el profesor con id " + id + ".");
        }
    }
    public void validateEntity(ProfesorEntity oProfesorEntity) {
        ValidationHelper.validateDNI(oProfesorEntity.getDni() , "campo DNI de Usuario");
        ValidationHelper.validateStringLength(oProfesorEntity.getNombre(), 2, 20, "campo 'nombre' del profesor debe tener longitud entre 2 y 20 caracteres");
        ValidationHelper.validateStringLength(oProfesorEntity.getApellido1(), 2, 20, "campo 'apellido 1' del profesor debe tener longitud entre 2 y 20 caracteres");
        ValidationHelper.validateEmail(oProfesorEntity.getEmail(), " proporciona un correo electrónico válido");
    }

    public ProfesorEntity getOne(Long id) {
        validate(id);
        oAuthService.OnlyOwnerOrSuperuser(id);
        return oProfesorRepository.findById(id).get();
    }

    public Page<ProfesorEntity> getPage(Pageable oPageable, String strFilter) {
        oAuthService.OnlySuperuser();
        
        if (strFilter == null || strFilter.length()==0) {
            return oProfesorRepository.findAll(oPageable);
        } else {
            return oProfesorRepository.findByNombreIgnoreCaseContainingOrApellido1IgnoreCaseContainingOrApellido2IgnoreCaseContaining(strFilter, strFilter, strFilter, oPageable);
        }
    }

    public Long create(ProfesorEntity profesorRecibido) {
        oAuthService.OnlySuperuser();
        validateEntity(profesorRecibido);
        profesorRecibido.setId(0L);
        profesorRecibido.setPass(GRADIN_DEFAULT_PASSWORD);
        return oProfesorRepository.save(profesorRecibido).getId();
    }

    public Long generateOne() {
        oAuthService.OnlySuperuser();
        ProfesorEntity oProfesorEntity = new ProfesorEntity();
        oProfesorEntity.setId(0L);
        oProfesorEntity.setDni(RandomHelper.generateDNI());
        oProfesorEntity.setNombre(NOMBRES_PROFESORES[(int) (Math.random() * NOMBRES_PROFESORES.length)]);
        oProfesorEntity.setApellido1(APELLIDOS_PROFESORES[(int) (Math.random() * APELLIDOS_PROFESORES.length)]);
        oProfesorEntity.setApellido2(APELLIDOS_PROFESORES[(int) (Math.random() * APELLIDOS_PROFESORES.length)]);
        oProfesorEntity.setEmail(RandomHelper.generateEmail(oProfesorEntity.getNombre(), oProfesorEntity.getApellido1()));
        oProfesorEntity.setPass(GRADIN_DEFAULT_PASSWORD);
        return oProfesorRepository.save(oProfesorEntity).getId();
    }
    public Long generateMany(int n) {
        oAuthService.OnlySuperuser();
        for (int i = 0; i < n; i++) {
            generateOne();
        }
        return count();
    }

    public ProfesorEntity update(ProfesorEntity oProfesorEntity) {
        oAuthService.OnlySuperuser();
        validate(oProfesorEntity.getId());
        validateEntity(oProfesorEntity);
        ProfesorEntity oProfesorOriginal = oProfesorRepository.findById(oProfesorEntity.getId()).get();
        oProfesorEntity.setPass(oProfesorOriginal.getPass());
        return oProfesorRepository.save(oProfesorEntity);
    }

    public Long count() {
        return oProfesorRepository.count();
    }

    public Long delete(Long id) {
        validate(id);
        oAuthService.OnlySuperuser();
        oProfesorRepository.deleteById(id);
        return id;
    }
}
