package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.basic.Instituto;

/**
* Repository class to handle persistence with the Institutes table.
*/
public class InstitutoRepository {
    

    /**
     * Persist a Instituto object into the DB.
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param instituto instance of 'instituto' class to persist
     */
    public void saveInstituto(EntityManager em, Instituto instituto){

        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(instituto);
            transaction.commit();
            // TODO: implement a return value to the main app with the persit's confirmation
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el guardado del Instituto");
        }
    }
    

    /**
     * Find a Instituto object in the DB.
     * @param em a EntityManager created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param codigo int value 'codigo' of the Instituto's codigo to find.
     * @return findedInstituto A instance of Instituto class
     */
    public Instituto findInstitutoById(EntityManager em, int codigo){

        Instituto findedInstituto = null;
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            findedInstituto = em.find(Instituto.class, codigo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al buscar el Instituto");
        }
        return findedInstituto;
    }

    /**
     * Delete a Instituto object from the DB.
     * @param em EntityManager created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param codigo int codigo value of the Instituto's codigo to delete.
     */
    public void removeInstituto(EntityManager em, int codigo){

        Instituto institutoToRemove = null;

        EntityTransaction transaction = em.getTransaction();
        try {
            institutoToRemove = this.findInstitutoById(em, codigo);
        } catch (Exception e) {
            System.out.println("Error al buscar el instituto a borrar");
        }

        try {
            transaction.begin();
            em.remove(institutoToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al borrar el instituto");
        }

    }

    /**
     * Update a Instituto in the DB.
     * @param em EntityManager created by the Factory in main app this the PersistenceUnit params
     * @param codigo Int Instituto's Id to find
     * @param denominacion String new Instituto's denominacion attribute to update.
     * @return Return the instance of Instituto updated, after persist it in the DB.
     */
    public Instituto updateInstituto(EntityManager em, int codigo, String denominacion){

        Instituto institutoToUpdate = this.findInstitutoById(em, codigo);

        if (denominacion!= null){
            institutoToUpdate.setDenominacion(denominacion);
        }

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(institutoToUpdate);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al actualizar el Instituto");
        }

        return institutoToUpdate;
    }

    /**
     * Return a List of all Instituto objects from the DB
     * @param em EntityManager created by the Factory in main app this the PersistenceUnit params
     * @return a List of Instituto instances
     */
    public List<Instituto> getInstitutos(EntityManager em){
        
        List<Instituto> allInstitutos = null;

        try {
            // obtenemos (creamos) un objeto de tipo CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // se crea un objeto de consulta que devolvera objetos de Instituto
            CriteriaQuery<Instituto> query = cb.createQuery(Instituto.class);

            // definimos el origen de la consulta (FROM)
            Root<Instituto> source = query.from(Instituto.class);

            // obtenemos el resultado de la consulta
            allInstitutos = em.createQuery(query.select(source)).getResultList();

        } catch (Exception e) {
            System.out.println("Error al recuperar los institutos");
        }
        
        return allInstitutos;
    }
}