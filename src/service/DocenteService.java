package service;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.EntityManager;
import model.basic.CargoDocente;
import model.basic.Docente;
import repository.DocenteRepository;

/**
 * Service class to handle the communication with the DocenteRepository
 */
public class DocenteService {
    
    private DocenteRepository docenteRepository = new DocenteRepository();

    public DocenteService(){};

    public void saveDocente(EntityManager em, Docente docente){
        docenteRepository.saveDocente(em, docente);
    }

    public Docente findDocenteById(EntityManager em, int legajo){
        return docenteRepository.findDocenteById(em, legajo);
    }

    public void removeDocente(EntityManager em, int legajo){
        docenteRepository.removeDocente(em, legajo);
    }

    public Docente updateDocente(EntityManager em,
                                    int legajo,
                                    int docUnico,
                                    String nombres,
                                    String apellidos,
                                    Calendar fechaNac,
                                    String direccionNotif,
                                    CargoDocente cargoDocente){
        return docenteRepository.updateDocente(em, legajo, docUnico, nombres, apellidos, fechaNac, direccionNotif, cargoDocente);
    }

    public List<Docente> getDocentes(EntityManager em){
        return docenteRepository.getDocentes(em); 
    }
}
