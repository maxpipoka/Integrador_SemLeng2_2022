import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.basic.Instituto;


public class App {
    public static void main(String[] args) throws Exception {

        // Creacion de un Entity Manager Factory, es una fabrica que construye objetos Entity Managers.
        // Factory es un patron de diseño destinado a la fabricacion rapida de objetos, se utiliza cuando
        // un sistema va a necesitar la creacion habitual de un cierto tipo de objetos.
        // Creando una factory, se evita tener que realizar y conocer el proceso artesanal de la creacion de ese objeto.
        // Se espeficica el tipo de objeto "EntityManagerFactory" y luego se asigna un nombre al objeto "emf_facultad".
        // Luego se llama al método 'createEntityManagerFactory' de la clase Persistence pasandole
        // el nombre de la PERSISTENCE_UNIT del persistence.xml que se desea manejar con ese EntityManagerFactory.
        // Nuestra fabrica queda creada con los parametros del persistence.xml.

        EntityManagerFactory emf_facultad = Persistence.createEntityManagerFactory("FacultadPersistance");

        // Ahora usando la Factory se crea un EntityManager
        // Especificamos el tipo de objeto "EntityManager" y seguido le asignamos nombre "em_entidadesFacultad"
        // Luego llamamos al método 'createEntityManager' de la clase 'emf_facultad' que creamos en la linea 19
        // este metodo 'createEntityManager' es un metodo de la 'factory' que mencionamos antes.

        EntityManager em_entidadesFacultad = emf_facultad.createEntityManager();

        // Iniciamos una transaccion
        em_entidadesFacultad.getTransaction().begin();

        //creamos un Instituto inicial
        Instituto inst1 = new Instituto(1, "Primer instituto");

        // Preparamos el guardado del insituto creado en "inst1"
        em_entidadesFacultad.persist(inst1);

        // Confirmamos la transaccion
        em_entidadesFacultad.getTransaction().commit();

        System.out.println("Termino sin errores");
    }
}
