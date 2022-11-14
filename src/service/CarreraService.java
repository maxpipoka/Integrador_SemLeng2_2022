package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.basic.Carrera;
import model.basic.Instituto;
import repository.CarreraRepository;

/**
 * Service class to handle the communication with the CarreraRepository
 */
public class CarreraService {
    
    private CarreraRepository carreraRepository;

    public void saveCarrera(EntityManager em, Carrera carrera){
        carreraRepository.saveCarera(em, carrera);
    }

    public Carrera findCarreraById(EntityManager em, int codigo){
        return carreraRepository.findCarreraById(em, codigo);
    }

    public void removeCarrera(EntityManager em, int codigo){
        carreraRepository.removeCarrera(em, codigo);
    }

    public Carrera updateCarrera(EntityManager em, 
                                    int codigo,
                                    String nombre,
                                    Instituto instituto){
    
        return carreraRepository.updateCarrera(em, codigo, nombre, instituto);
    }

    public List<Carrera> getCarreras(EntityManager em){
        return carreraRepository.getCarreras(em);
    }
}
