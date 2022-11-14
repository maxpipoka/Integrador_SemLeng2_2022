package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.basic.CargoDocente;
import model.basic.Instituto;

/**
 * Repository class to handle persistance with the CargoDocente table.
 */
public class CargoDocenteRepository {
    

    /**
     * Persist a CargoDocente object into the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param cargoDocente instance of 'CargoDocente' class to persist.
     */
    public void saveCargoDocente(EntityManager em, CargoDocente cargoDocente){
    
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(cargoDocente);
            transaction.commit();
            // TODO: implement a return value to the main app with the persist's confirmation
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el guardado del Cargo Docente");
        }
    }

    /**
     * Find a CargoDocente object in the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param numero int value 'numero' of the CargoDocente's numero to find.
     * @return findedCargoDocente A instance o CargoDocente class
     */
    public CargoDocente findCargoDocenteById(EntityManager em, int numero){

        CargoDocente findedCargoDocente = null;

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.find(CargoDocente.class, numero);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al buscar el Cargo Docente");
        }
        return findedCargoDocente;
    }

    /**
     * Remove a CargoDocente object from the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param numero int value 'numero' of the CargoDocente's to delete.
     */
    public void removeCargoDocente(EntityManager em, int numero){

        CargoDocente cargoDocenteToRemove = this.findCargoDocenteById(em, numero);

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(cargoDocenteToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el borrado del Cargo Docente");
        }
    }

    /**
     * Update a CargoDocente in the DB.
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param numero int value 'numero' of the CargoDocente's numero to update.
     * @param dedicacionHoras int CargoDocente's dedicacionHoras
     * @param instituto Instituto class instance asociated to the CargoDocente
     * @return Return the instance of CargoDocente updated, after persis in the DB.
     */
    public CargoDocente updateCargoDocente(EntityManager em,
                                            int numero,
                                            int dedicacionHoras,
                                            Instituto instituto){
        CargoDocente cargoDocenteToUpdate = this.findCargoDocenteById(em, numero);

        // No es posible evaluar si un 'int' es null, porque los 'int' son valores primitivos y no es posible.
        // Para poder hacer la evaluacion creamos una variable 'Integer' y le cargamos el valor del 'int'
        // los 'Integer' al ser clases si pueden ser evaluados si son null o no.
        Integer integerDedHoras = Integer.valueOf(dedicacionHoras);
        
        if (integerDedHoras != null){
            cargoDocenteToUpdate.setDedicacionHoras(dedicacionHoras);
        }

        if (instituto != null){
            cargoDocenteToUpdate.setInstituto(instituto);
        }

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(cargoDocenteToUpdate);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al actualizar el Cargo Docente");
        }

        return cargoDocenteToUpdate;
    }

    /**
     * Return a List of all CargoDocente objects from the DB.
     * @param em EntityManager created by the Factory in main app this the PersistenceUnit params
     * @return a List of CargoDocente instances.
     */
    public List<CargoDocente> getCargosDocente(EntityManager em){

        List<CargoDocente> allCargosDocentes = null;

        try {
            // obtenemos (creamos) un objeto de tipo CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // se crea un objeto de consulta que devolvera objetos de CargoDocente
            CriteriaQuery<CargoDocente> query = cb.createQuery(CargoDocente.class);

            // definimos el origen de la consulta (FROM)
            Root<CargoDocente> source = query.from(CargoDocente.class);

            // obtenemos el resultado de la consulta
            allCargosDocentes = em.createQuery(query.select(source)).getResultList();
        } catch (Exception e) {
            System.out.println("Error al recuperar los Cargos Docentes");
        }
        
        return allCargosDocentes;
    }
}
