package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.basic.Carrera;
import model.basic.Instituto;

/**
 * Repository class to handle persistence with the Carrera table.
 */
public class CarreraRepository {
    
    /**
     * Persis a Carrera object into the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param carrera instance of 'Carrera' class to persist.
     */
    public void saveCarera(EntityManager em, Carrera carrera){

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(carrera);
            transaction.commit();
            // TODO: implement a return value to the main app with the persist's confirmation
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el guardado de la Carrera");
        }
    }

    /**
     * Find a Carrera object in the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param codigo int value 'codigo' of the Carrera's codigo to find
     * @return findedCarrera, a instance o Carrera class
     */
    public Carrera findCarreraById(EntityManager em, int codigo){

        Carrera findedCarrera = null;

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            findedCarrera = em.find(Carrera.class, codigo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al buscar la Carrera");
        }

        return findedCarrera;
    }

    /**
     * Remove a Carrera object from the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param codigo int value 'codigo' of the Carrera's codigo to delete
     */
    public void removeCarrera(EntityManager em, int codigo){

        Carrera carreraToRemove = this.findCarreraById(em, codigo);

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(carreraToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el borrado de la Carrera");
        }
    }

    /**
     * 
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param codigo int value 'codigo' of the Carrera's codigo to update
     * @param nombre String nombre to update in the Carrera
     * @param instituto Instituto instance to asociate with the Carrera
     * @return the instance of the Carrera class updated.
     */
    public Carrera updateCarrera(EntityManager em, 
                                    int codigo, 
                                    String nombre, 
                                    Instituto instituto){
        
        Carrera carreraToUpdate = this.findCarreraById(em, codigo);

        if (nombre != null){
            carreraToUpdate.setNombre(nombre);
        }

        if (instituto != null){
            carreraToUpdate.setInstituto(instituto);
        }

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(carreraToUpdate);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al actualizar la Carrera");
        }
        return carreraToUpdate;
    }

    /**
     * Return a List of all Carrera objects from the DB
     * @param em EntityManager created by the Factory in main app this the PersistenceUnit params
     * @return a List of Carrera instances
     */
    public List<Carrera> getCarreras(EntityManager em){
        
        List<Carrera> allCarreras = null;

        try {
            // obtenemos (creamos) un objeto de tipo CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // se crea un objeto de consulta que devolvera objetos de Carrera
            CriteriaQuery<Carrera> query = cb.createQuery(Carrera.class);

            // definimos el origen de la consulta (FROM)
            Root<Carrera> source = query.from(Carrera.class);

            // obtenemos el resultado de la consulta
            allCarreras = em.createQuery(query.select(source)).getResultList();
        } catch (Exception e) {
            System.out.println("Error al recuperar las Carreras");
        }

        return allCarreras;
    }
}
