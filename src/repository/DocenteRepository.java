package repository;

import java.util.Calendar;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import models.basic.CargoDocente;
import models.basic.Docente;

/**
 * Repository class to handle persistence with the Docente table.
 */
public class DocenteRepository {

    /**
     * Persist a Docente object into the DB.
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param docente instance of 'Docente' class to persist.
     */
    public void saveDocente(EntityManager em, Docente docente){
        
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(docente);
            transaction.commit();
            // TODO: implement a return value to the main app with the persist's confirmation
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el guardado del Docente");
        }
    }

    /**
     * Find a Docente object in the DB.
     * @param em a EntityManager created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param legajo int value 'legajo' of the Docente's legajo to find
     * @return findedDocente, a instance of Docente class
     */
    public Docente findDocenteById(EntityManager em, int legajo){

        Docente findedDocente = null;

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            findedDocente = em.find(Docente.class, legajo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al buscar el Docente");
        }

        return findedDocente;
    }
    
    /**
     * Remove a Docente object from the DB.
     * @param em a a EntityManager created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param legajo int value with the Docente's legajo to remove from the DB. 
     */
    public void removeDocente(EntityManager em, int legajo){

        Docente docenteToRemove = this.findDocenteById(em, legajo);

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(docenteToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el boorrado del Docent");
        }
    }

    /**
     * Update a Docente in the DB.
     * @param em a EntityManager created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param legajo int value to find the Docente to update
     * @param docUnico int Docente's docUnico
     * @param nombres String Docente's nombres
     * @param apellidos String Docente's apellidos
     * @param fechaNac Calendar Docente's fechaNac
     * @param direccionNotif String Docente's direccionNotif
     * @param cargoDocente CargoDocente instance asociated with the Docente
     * @return docenteToUpdate the instance of Docente updated after persist.
     */
    public Docente updateDocente(EntityManager em, 
                                    int legajo, 
                                    int docUnico, 
                                    String nombres,
                                    String apellidos,
                                    Calendar fechaNac,
                                    String direccionNotif,
                                    CargoDocente cargoDocente){

        Docente docenteToUpdate = this.findDocenteById(em, legajo);
        
        Integer integerDocUnico = Integer.valueOf(docUnico);

        if (integerDocUnico !=  null){
            docenteToUpdate.setDocUnico(docUnico);
        }

        if (nombres != null){
            docenteToUpdate.setNombres(nombres);
        }

        if (apellidos != null){
            docenteToUpdate.setApellidos(apellidos);
        }

        if (fechaNac != null){
            docenteToUpdate.setFechaNac(fechaNac);
        }

        if (direccionNotif != null){
            docenteToUpdate.setDireccionNotif(direccionNotif);
        }

        if (cargoDocente != null){
            docenteToUpdate.setCargoDocente(cargoDocente);
        }

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(docenteToUpdate);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al actualizar el Docente");
        }

        return docenteToUpdate;
    }

    public List<Docente> getDocentes(EntityManager em){
        
        List<Docente> allDocentes = null;

        try {
            // Creamos un objeto de tipo CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Creamos un objeto de consulta que devolvera objetos de Docente
            CriteriaQuery<Docente> query = cb.createQuery(Docente.class);

            // Definimos el origen de la consulta (From)
            Root<Docente> source = query.from(Docente.class);

            // Obtenemos el resultado de la consulta
            allDocentes = em.createQuery(query.select(source)).getResultList();
        } catch (Exception e) {
            System.out.println("Error al recuperar los Docentes");
        }

        return allDocentes;
    }
}
