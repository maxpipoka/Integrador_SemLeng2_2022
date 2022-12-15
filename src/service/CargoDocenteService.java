package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.basic.CargoDocente;
import model.basic.Instituto;
import repository.CargoDocenteRepository;

/**
 * Service class to handle the communication with the CargoDocenteRepository
 */
public class CargoDocenteService {

    private CargoDocenteRepository cargoDocenteRepository = new CargoDocenteRepository();

    public CargoDocenteService(){};

    public void saveCargoDocente(EntityManager em, CargoDocente cargoDocente){
        cargoDocenteRepository.saveCargoDocente(em, cargoDocente);
    }

    public CargoDocente findCargoDocenteById(EntityManager em, int numero){
        return cargoDocenteRepository.findCargoDocenteById(em, numero);
    }

    public void removeCargoDocente(EntityManager em, int numero){
        cargoDocenteRepository.removeCargoDocente(em, numero);
    }

    public CargoDocente updateCargoDocente(EntityManager em,
                                            int numero, 
                                            int dedicacionHoras,
                                            Instituto instituto){
        
        return updateCargoDocente(em, numero, dedicacionHoras, instituto);
    }

    public List<CargoDocente> getCargosDocente(EntityManager em){
        return cargoDocenteRepository.getCargosDocente(em);
    }
}
