import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!. Este es un archivo modificado en local");

        // Creacion de un Entity Manager Factory, es una fabrica que construye objetos Entity Managers.
        // Factory es un patron de diseño destinado a la fabricacion rapida de objetos
        // Se espeficica el tipo de objeto "EntityManagerFactory" y luego se asigna un nombre al objeto "emf".
        // Luego se llama al método 'createEntityManagerFactory' de la clase Persistence pasandole
        // el nombre de la PERSISTENCE_UNIT del persistence.xml que se desea manejar con ese EntityManagerFactory

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FacultadPersistance");

        // Ahora usando la Factory se crea un EntityManager
        // Especificamos el tipo de objeto "EntityManager" y seguido le asignamos nombre "em"
        // Luego llamamos al método 'createEntityManager' de la clase 'emf' que creamos en la linea 15

        EntityManager em = emf.createEntityManager();


    }
}
