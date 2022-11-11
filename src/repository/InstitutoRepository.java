package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import models.basic.Instituto;

/**
* Repository class to handle persistence with the Institutes table.
*/
public class InstitutoRepository {
    

    /**
     * Persist a Instituto object into de DB.
     * Receive a EntityManager 'em' created by the EntityManagerFactory
     * with the PersistenceUnit´s params and the object 'instituto' to persist
     */
    public void saveInstituto(EntityManager em, Instituto instituto){

        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(instituto);
            transaction.commit();
        } catch (Exception e) {
            // TODO: implement a return value to the main app with the persit's confirmation
            transaction.rollback();
            System.out.println("Error en el guardado del Instituto");
        }
    }
    

    /**
     * Find a Instituto object in the DB.
     * Receive a EntityManager 'em' created by the EntityManagerFactory
     * with the PersistenceUnit´s params and the int value 'codigo'
     * of the Instituto's codigo to find.
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
     * Receive a EntityManager 'em' created by the EntityManagerFactory
     * with the PersistenceUnit´s params and the int value 'codigo'
     * of the Instituto's codigo to delete.
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

    
}
