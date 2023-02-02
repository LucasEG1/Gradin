package com.lucas.Gradin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lucas.Gradin.entity.AlumnoEntity;
import com.lucas.Gradin.exception.ResourceNotFoundException;
import com.lucas.Gradin.repository.AlumnoRepository;
import com.lucas.Gradin.repository.ClaseRepository;

import java.util.List;

@Service
public class AlumnoService {
    
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private AuthService oAuthService;

    private final String[] NOMBRES = {
        "Alberto", "Angel", "Amaia", "Ainhoa",
        "Aitor", "Aitziber", "Ane", "Ana",
        "Andoni", "Andrea", "Andres", "Bautista",
        "Begoña", "Belén", "Benito", "Borja",
        "Carmen", "Cristina", "Daniel", "David",
        "Diego", "Eduardo", "Elena", "Eneko",
        "Fernando", "Francisco", "Gabriel", "Gema",
        "Gonzalo", "Guillermo", "Iker", "Iñigo",
        "Jaime", "Javier", "Jesús", "Joaquín",
        "Kevin", "Karim", "Laura", "Lorena",
        "Lucas", "Luis", "Manuel", "María",
        "Marta", "Miguel", "Mikel", "Miren",
        "Nerea", "Nacho", "Natalia", "Olga",
        "Oier", "Oihana", "Oscar", "Pablo",
        "Patricia", "Paula", "Pedro", "Rafael",
        "Raquel", "Raul", "Roberto", "Rodrigo",
        "Ruben", "Sara", "Sergio", "Sofia",
        "Susana", "Teresa", "Vicente", "Víctor",
        "Ximo", "Yaiza", "Yolanda", "Yonathan",
        "Zaida", "Zoe"
    };
    
    private final String[] APELLIDOS = {
        "Alvarez", "Alonso", "Aranda", "Arce", "Aznar",
        "Arroyo", "Arteaga", "Arzola", "Avalos",
        "Baeza", "Bailon", "Ballesteros", "Barajas",
        "Cabrera", "Cadena", "Calderon", "Calvo",
        "Camacho", "Campos", "Cano", "Cantú",
        "Diaz", "Diez", "Domínguez",
        "Echevarria", "Estrada", "Fernandez",
        "Garcia", "Garrido", "Gimenez", "Gonzalez",
        "Gutierrez", "Hernandez", "Herrera", "Hidalgo",
        "Iglesias", "Izquierdo", "Jimenez", "Jordán",
        "Lara", "López", "Lozano", "Lucas",
        "Machado", "Maldonado", "Mancilla", "Manzano",
        "Marin", "Marquez", "Martinez", "Mata",
        "Navarro", "Nieto", "Noguera", "Novoa",
        "Ochoa", "Oliver", "Olivera", "Olivares",
        "Pacheco", "Padilla", "Palacios", "Palomino",
        "Pantoja", "Pascual", "Pastor", "Paz",
        "Ramirez", "Ramos", "Reyes", "Rico",
        "Saez", "Sainz", "Santana", "Santiago",
        "Santos", "Sanz", "Serrano", "Soler"
    };

    // VALIDACIONES
    public void validateAlumnoId(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe alumno con id " + id + ".");
        }
    }
    public void validateClaseId(Long id) {
        if (!claseRepository.existsById(id)) {
            throw new ResourceNotFoundException("No existe clase con id " + id + ".");
        }
    }

    public AlumnoEntity getOne(Long id) {
        oAuthService.OnlyOwnerOrSuperuser(id);
        validateAlumnoId(id);
        return alumnoRepository.findById(id).get();
    }

    public Page<AlumnoEntity> getPage(Pageable oPageable, Long idClase, String strFilter) {
        oAuthService.OnlySuperuser();

        if (strFilter == null || strFilter.length() == 0 && idClase == null) {
            return alumnoRepository.findAll(oPageable);
        } else if (strFilter != null && strFilter.length() > 0 && idClase == null) {
            return alumnoRepository.findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCase(strFilter, strFilter, strFilter, strFilter, strFilter, oPageable);
        } else {
            return alumnoRepository.findByNombreContainingIgnoreCaseOrApellido1ContainingIgnoreCaseOrApellido2ContainingIgnoreCaseOrEmailContainingIgnoreCaseOrTelefonoContainingIgnoreCaseAndClaseId(strFilter, strFilter, strFilter, strFilter, strFilter, idClase, oPageable);
        }
    }

    public Long create(AlumnoEntity nuevoAlumnoEntity) {
        oAuthService.OnlySuperuser();
        nuevoAlumnoEntity.setId(0L);
        validateClaseId(nuevoAlumnoEntity.getClase().getId());
        return alumnoRepository.save(nuevoAlumnoEntity).getId();
    }

    public AlumnoEntity update(AlumnoEntity oAlumnoEntity) {
        oAuthService.OnlySuperuser();
        validateAlumnoId(oAlumnoEntity.getId());
        validateClaseId(oAlumnoEntity.getClase().getId());
        return alumnoRepository.save(oAlumnoEntity);
    }

    public Long delete(Long id) {
        oAuthService.OnlySuperuser();
        validateAlumnoId(id);
        return id;
    }

    public AlumnoEntity getOneRandom() {
        oAuthService.OnlySuperuser();
        return alumnoRepository.findById((long) (Math.random() * alumnoRepository.count() + 1)).get();
    }

    public AlumnoEntity generateRandom() {
        oAuthService.OnlySuperuser();
        AlumnoEntity oAlumnoEntity = new AlumnoEntity();
        oAlumnoEntity.setNombre(NOMBRES[(int) (Math.random() * NOMBRES.length)]);
        oAlumnoEntity.setApellido1(APELLIDOS[(int) (Math.random() * APELLIDOS.length)]);
        oAlumnoEntity.setApellido2(APELLIDOS[(int) (Math.random() * APELLIDOS.length)]);
        oAlumnoEntity.setEmail(oAlumnoEntity.getNombre().toLowerCase().substring(0, 3) + "." + oAlumnoEntity.getApellido1().toLowerCase().substring(0, 2) + "@gmail.com");
        oAlumnoEntity.setTelefono("6" + (int) (Math.random() * 100000000));
        oAlumnoEntity.setClase(claseRepository.findById((long) (Math.random() * claseRepository.count() + 1)).get());  
        return alumnoRepository.save(oAlumnoEntity);
    }

    public Long count() {
        oAuthService.OnlySuperuser();
        return alumnoRepository.count();
    }

    public Long generateMany(int cantidad) {
        oAuthService.OnlySuperuser();
        List<AlumnoEntity> oLista = new ArrayList<AlumnoEntity>();
        for (int i = 0; i < cantidad; i++) {
            oLista.add(generateRandom());
        }
        for (AlumnoEntity alumnoEntity : oLista) {
            create(alumnoEntity);
        }
        return count(); 
    }


}
