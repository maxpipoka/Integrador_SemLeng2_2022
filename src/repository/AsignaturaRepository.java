package repository;

import java.util.List;
import java.util.Set;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.basic.Asignatura;
import model.basic.Carrera;
import model.basic.Docente;
import model.basic.Instituto;

/**
* Repository class to handle persistence with the Asignatura table.
*/
public class AsignaturaRepository {

    public AsignaturaRepository(){};
    

    /**
     * Persist a Asignatura object into the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params
     * @param asignatura instance of 'Asignatura' class to persis
     */
    public void saveAsignatura(EntityManager em, Asignatura asignatura){

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(asignatura);
            transaction.commit();
            // TODO: implement a return value to the main app with the persist's confirmation
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error en el guardado de la Asignatura");
        }
    }

    /**
     * Find a Asignatura object in the DB
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param codigo int value 'codigo' of the Asignatura's codigo to find.
     * @return findedAsignatura A instance of Asignatura class
     */
    public Asignatura findAsignaturaById(EntityManager em, int codigo){

        Asignatura findedAsignatura = null;
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            findedAsignatura = em.find(Asignatura.class, codigo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al buscar la Asignatura");
        }

        return findedAsignatura;
    }

    /** 
     * Delete a Asignatura object from the DB.
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param codigo int value 'codigo' of the Asignatura's codigo to delete.
     */
    public void removeAsignatura(EntityManager em, int codigo){

        Asignatura asignaturaToRemove = null;

        EntityTransaction transaction = em.getTransaction();

        try {
            asignaturaToRemove = this.findAsignaturaById(em, codigo);
        } catch (Exception e) {
            System.out.println("Error al buscar la Asignatura");
        }

        try {
            transaction.begin();
            em.remove(asignaturaToRemove);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al borrar la Asignatura");
        }
    }


    /**
     * Update a Asignatura in the DB.
     * @param em EntityManager 'em' created by the EntityManagerFactory with the PersistenceUnit´s params 
     * @param codigo int value 'codigo' of the Asignatura's codigo to update.
     * @param nombre string Asignatura's name
     * @param descripcion string Asignatura's descripcion
     * @param docente Docente class instance asociated to the Asignatura
     * @param instituto Instituto class instance asociated to the Asignatura
     * @param carreras Set<Carrera> collection asociated to the Asignatura
     * @return Return the instance of Asignatura updated, after persist in the DB.
     */
    public Asignatura updateAsignatura(EntityManager em, 
                                        int codigo, 
                                        String nombre, 
                                        String descripcion, 
                                        Docente docente, 
                                        Instituto instituto, 
                                        Set<Carrera> carreras){
        
        Asignatura asignaturaToUpdate = this.findAsignaturaById(em, codigo);

        if (nombre != null){
            asignaturaToUpdate.setNombre(nombre);
        }

        if (descripcion != null){
            asignaturaToUpdate.setDescripcion(descripcion);
        }

        if (docente != null){
            asignaturaToUpdate.setDocente(docente);
        }

        if (instituto != null){
            asignaturaToUpdate.setDocente(docente);
        }

        if (carreras != null){
            asignaturaToUpdate.setCarreras(carreras);
        }

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(asignaturaToUpdate);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Error al actualizar la Asignatura");
        }

        return asignaturaToUpdate; 
    }

    /**
     * Return a List of all Asignatura objects from the DB
     * @param em EntityManager created by the Factory in main app this the PersistenceUnit params
     * @return a List of Asignatura instances
     */
    public List<Asignatura> getAsignaturas(EntityManager em){
        
        List<Asignatura> allAsignaturas = null;

        try {
            // obtenemos (creamos) un objeto de tipo CriteriaBuilder
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // se crea un objeto de consulta que devolvera objetos de Instituto
            CriteriaQuery<Asignatura> query = cb.createQuery(Asignatura.class);

            // definimos el origen de la consulta (FROM)
            Root<Asignatura> source = query.from(Asignatura.class);

            // obtenemos el resultado de la consulta
            allAsignaturas = em.createQuery(query.select(source)).getResultList();
        } catch (Exception e) {
            System.out.println("Error al recuperar las asignaturas");
        }

        return allAsignaturas;
    }

}
