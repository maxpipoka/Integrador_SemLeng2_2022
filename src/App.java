import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;
import model.basic.Instituto;


public class App extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception{

        EntityManagerFactory emf_facultad = Persistence.createEntityManagerFactory("FacultadPersistance");

        EntityManager em_entidadesFacultad = emf_facultad.createEntityManager();

        EntityTransaction transac = em_entidadesFacultad.getTransaction();

        Instituto inst1 = new Instituto(1, "Primer instituto");

        try { // abrimos el bloque del try, para que intente ejecutar las siguientes 3 lineas
            transac.begin(); // Iniciamos la 'Transaction'
            em_entidadesFacultad.persist(inst1); // Agregamos a la cola el persist()
            transac.commit(); // Confirmamos la cola preparada antes, en nuestro caso solo tiene un persist()
        } catch (Exception e ) { // abrimos el bloque del catch, para especificar que hacer si falla el bloque try
            transac.rollback(); // El rollback() lo que hace es volver todo a como estaba antes de iniciar el try
            System.out.println("No se pudo insertar el registro"); // Mostramos un mensaje en consola para saber del error.
        }
        
        System.out.println("Termino sin errores");
    }
}
