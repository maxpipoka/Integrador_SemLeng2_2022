package service;

import java.util.List;

import jakarta.persistence.EntityManager;
import model.basic.Instituto;
import repository.InstitutoRepository;

/**
 * Service class to handle the communication with the InstitutoRepository
 */
public class InstitutoService {

    private InstitutoRepository institutoRepository;

    public void saveInstituto(EntityManager em, Instituto instituto){
        institutoRepository.saveInstituto(em, instituto);
    }

    public Instituto findInstitutoById(EntityManager em, int codigo){
        return institutoRepository.findInstitutoById(em, codigo);
    }

    public void removeInstituto(EntityManager em, int codigo){
        institutoRepository.removeInstituto(em, codigo);
    }

    public Instituto updateInstituto(EntityManager em,
                                        int codigo,
                                        String denominacion){
        return institutoRepository.updateInstituto(em, codigo, denominacion);
    }

    public List<Instituto> getInstitutos(EntityManager em){
        return institutoRepository.getInstitutos(em);
    }
    
}
