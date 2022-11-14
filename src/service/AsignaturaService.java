package service;


import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import model.basic.Asignatura;
import model.basic.Carrera;
import model.basic.Docente;
import model.basic.Instituto;
import repository.AsignaturaRepository;

/**
 * Service class to handle the communication with de AsignaturaRepository
 */
public class AsignaturaService {
    
    private AsignaturaRepository asignaturaRepository;

    public void saveAsignatura(EntityManager em, Asignatura asignatura){
        asignaturaRepository.saveAsignatura(em, asignatura);
    }

    public Asignatura findAsignaturaById(EntityManager em, int codigo){
        return asignaturaRepository.findAsignaturaById(em, codigo);
    }

    public void removeAsignatura(EntityManager em, int codigo){
        asignaturaRepository.removeAsignatura(em, codigo);
    }

    public Asignatura updateAsignatura(EntityManager em, 
                                        int codigo,
                                        String nombre,
                                        String descripcion,
                                        Docente docente,
                                        Instituto instituto,
                                        Set<Carrera> carreras){
        
        return asignaturaRepository.updateAsignatura(em, codigo, nombre, descripcion, docente, instituto, carreras);
    }

    public List<Asignatura> getAsignaturas(EntityManager em){
        return asignaturaRepository.getAsignaturas(em);
    }

}
