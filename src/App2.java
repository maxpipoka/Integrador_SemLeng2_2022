import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import models.basic.Instituto;


public class App2 {
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
        // Las 'Transactions' son sesiones de ejecución de instrucciones sql.
        // El concepto de Sesiones responde a que se agrupan instrucciones a ejecutar
        // contra la base de datos. Responden a un principio ACID.
        // A - Atomic. Operación indivisible. Se ejecutan todas o ninguna instruccion de la transaccion. Si se intentan ejecutar y una tira error, no se ejecuta ninguna.
        // C - Consistente. Si se solicitan dos actualizaciones de la DB, donde se saquen 100 unidades de un lugar para poner en otro lugar. La transaccion debe ser
        //      completa, no se pueden permitir transacciones parciales.
        // I - Isolated. Aislada , la operación que nosotros realizamos no puede verse afectada por otras operaciones que se estén ejecutando en ese momento.
        // D - Durable: La información queda grabada en la base de datos para el futuro y persiste
        
        // Iniciamos una transaccion invocando al método 'begin()' de la interface 'EntityTransaction' implementado por el método 'getTransaction()' de la clase 'EntityManager'.
        // em_entidadesFacultad.getTransaction().begin();
        // La 'Transaction' no ejecutó nada, solamente fue iniciada.
        // Es decir, nuestro EntityManager 'em_entidadesFacultad' es un EntityManager con 'Transaction' iniciada.

        // -----------------------------------------------------------------------
        // PARA SEPARAR MEJOR LOS ELEMENTOS, vamos a utilizar una implementacion de la interface 'EntityTransaction' directamente.
        // Se hace solamente la obtencion de la 'Transaction' sin iniciarla todavia, el iniciado vamos a hacerlo dentro de un
        // Try / Catch para manejar posibles errores.

        // Entonces especificamos el tipo de dato interface 'EntityTransaction', le ponemos el nombre a la variable 'transac'
        // y se le asigna la obtencion de una 'transaction' con el EntityManager que creamos antes.
        EntityTransaction transac = em_entidadesFacultad.getTransaction();


        // Como ejemplo de creación de un registro en una tabla, vamos a hacer la creación de un nuevo 'Instituto'. Los datos para la instancia
        // pueden venir de hardcodeo es decir codigo a mano, o pueden ser variables obtenidas desde un formulario de interface grafica.

        // creamos un Instituto inicial, con datos proporcionados por nosotros a mano.
        // Indicamos el tipo de dato 'Instituto', al hacerlo hay que importar la clase desde el paquete 'models.basic' como se ve en la linea 4.
        // Luego definimos el nombre para la viariable que va a ser el objeto, en este caso le ponemos 'inst1'.
        // Luego indicamos la instanciacion del objeto con 'new Instituto()' y entre los parentesis los datos necesarios.
        // Que para el objeto 'Instituto' deben ser: un código que es un 'int' y una denominación que es un 'String'.
        // Visual studio code deberia mostrar una ayuda con los nombres de los atributos que deben cargarse.
        Instituto inst1 = new Instituto(1, "Primer instituto");
        // El objeto queda solamente creado dentro de la variable 'inst1' que ahora es nuestro objeto con sus atributos y métodos que definimos en 'Instituto.java'

        // Preparamos el guardado del instituto creado en "inst1"
        // Una vez iniciada la 'Transaction' las instrucciones contra la DB se van 'anotando' o 'agendando'
        // Para cada accion: crear, borrar, modificar, o buscar en la DB. hay una instruccion. 
        // Guardar un objeto en la DB se hace con la instruccion 'persist()' y entre los parentesis se le pasa que debe guardar.
        // en nuestro caso es el objeto 'inst1' que instanciamos mas arriba.
        // Entonces, se le indica al EntityManager 'em_entidadesFacultad' que debe 'Persistir/Guardar' el objeto/s pasado.
        // em_entidadesFacultad.persist(inst1);
        // Recordemos que esta instruccion solamente se agrega a la 'agenda' de instrucciones a hacer en la DB, todavia no se ejecuta.

        // Confirmamos la transaccion
        // Para ejecutar la 'agenda' de instrucciones que veniamos armando, se utiliza el método 'commit()' de la interface 'EntityTransaction'
        // implementado por el método 'getTransaction()' de la clase 'EntityManager'
        // em_entidadesFacultad.getTransaction().commit();

        // -----------------------------------
        // Se comentaron las lineas 72 y 78 para meter esas instrucciones en un try / catch
        // Recordamos que el try / catch es para ejecutar lineas dentro de un ambito de manejo de errores
        // try: intenta ejecutar esto... si no se puede, se hace lo que esta dentro del catch
        // Ya instanciamos un objeto Instituto en la linea 63 y creamos una 'Transaction' sin iniciar en la linea 51
        // Ahora metemos las lineas de ejecucion de preparado de consulta y consulta propiamente dicha en el try/catch

        try { // abrimos el bloque del try, para que intente ejecutar las siguientes 3 lineas
            transac.begin(); // Iniciamos la 'Transaction'
            em_entidadesFacultad.persist(inst1); // Agregamos a la cola el persist()
            transac.commit(); // Confirmamos la cola preparada antes, en nuestro caso solo tiene un persist()
        } catch (Exception e ) { // abrimos el bloque del catch, para especificar que hacer si falla el bloque try
            transac.rollback(); // El rollback() lo que hace es volver todo a como estaba antes de iniciar el try
            System.out.println("No se pudo insertar el registro"); // Mostramos un mensaje en consola para saber del error.
        }
        
        // La principal utilidad del Try/Catch es 'manejar' los errores, es decir que no tire error de ejecucion
        // nuestro aplicativo, sino que los errores sean gestionados.
        
        // Si todo va bien, se deberia:
        // Crear la Factory para fabricar Entity's Managers con los datos de la unidad de persistencia
        // Crear un Entity Manager con la fabrica.
        // Abrir una 'Transaction' en el EntityManager inciandola.
        // Crear un objeto de una instancia de la clase Instituto con datos
        // Indicar que se debe guardar con el 'persist()'
        // Confirmar la lista de instrucciones preparadas en el 'Transaction' con el 'commit()'

        // Esta linea a continuacion solo imprime el texto en consola al terminar todo.
        System.out.println("Termino sin errores");
    }
}
