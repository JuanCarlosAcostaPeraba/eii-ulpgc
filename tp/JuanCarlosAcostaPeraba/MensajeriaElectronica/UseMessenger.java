package MensajeriaElectronica;

// Paquetes importados
import tp.practicas.*;

/**
 * Clase UseMessenger
 * 
 * @author JuanSerberoCode
 */
public class UseMessenger {

    public static void main(String[] args) {

        // --------------------------------------------------
        // TESTS
        // --------------------------------------------------
        org.junit.runner.JUnitCore.main("MessengerTest");

        // --------------------------------------------------
        // PRUEBAS DADAS POR PROFESOR
        // --------------------------------------------------
        System.out.println("\tPRUEBAS DADAS POR PROFESOR");
        Person pepe = new Person("Pepe", "5555380");
        Person juan = new Person("Juan", "55541501");
        Person antonio = new Person("Antonio", "5556380");
        Person maría = new Person("María", "5557780");
        Person ana = new Person("Ana", "5557781");
        Group familia = new Group("familia");
        Group amigos = new Group("amigos");
        Group amigas = new Group("amigas");
        Group todos = new Group("todos");
        Group noFamilia = new Group("no familiares");
        familia.add(juan);
        familia.add(maría);
        amigos.add(pepe);
        amigos.add(antonio);
        amigas.add(ana);
        noFamilia.add(amigos);
        noFamilia.add(amigas);
        todos.add(familia);
        todos.add(noFamilia);
        todos.sendSMS("TP superado ...");
        familia.sendSMS("Hoy cena de cumpleaños");
        noFamilia.sendSMS("Hoy (después de cena familiar) ¡fiesta!");
        amigas.remove(ana.getId());

        System.out.println("\n");

        // --------------------------------------------------
        // INICIALIZACION DE LOS OBJETOS PERSON
        // --------------------------------------------------
        Person person1 = new Person("Pepe", "5555380");
        Person person2 = new Person("Juan", "55541501");
        Person person3 = new Person("Antonio", "5556380");
        Person person4 = new Person("María", "5557780");
        Person person5 = new Person("Ana", "5557781");

        // --------------------------------------------------
        // INICIALIZACION DE LOS OBJETOS GROUP
        // --------------------------------------------------
        Group group1 = new Group("familia");
        Group group2 = new Group("amigos");
        Group group3 = new Group("amigas");
        Group group4 = new Group("todos");
        Group group5 = new Group("no familiares");

        // --------------------------------------------------
        // PRUEBA METODO PERSON.GETID()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.GETID()");
        System.out.println("ID del contacto person1: " + person1.getId());
        System.out.println("ID del contacto person2: " + person3.getId());
        System.out.println("ID del contacto person3: " + person2.getId());
        System.out.println("ID del contacto person4: " + person4.getId());
        System.out.println("ID del contacto person5: " + person5.getId());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO PERSON.GETNAME()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.GETNAME()");
        System.out.println("Nombre del contacto person1: " + person1.getName());
        System.out.println("Nombre del contacto person2: " + person2.getName());
        System.out.println("Nombre del contacto person3: " + person3.getName());
        System.out.println("Nombre del contacto person4: " + person4.getName());
        System.out.println("Nombre del contacto person5: " + person5.getName());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO PERSON.SENDSMS(MESSAGE)
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.SENDSMS(MESSAGE)");
        person1.sendSMS("Mensaje para " + person1.getName());
        person2.sendSMS("Mensaje para " + person2.getName());
        person3.sendSMS("Mensaje para " + person3.getName());
        person4.sendSMS("Mensaje para " + person4.getName());
        person5.sendSMS("Mensaje para " + person5.getName());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO PERSON.TOSTRING()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.TOSTRING()");
        System.out.println("toString del contacto person1:\n" + person1.toString());
        System.out.println("toString del contacto person2:\n" + person2.toString());
        System.out.println("toString del contacto person3:\n" + person3.toString());
        System.out.println("toString del contacto person4:\n" + person4.toString());
        System.out.println("toString del contacto person5:\n" + person5.toString());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.GETID()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO GROUP.GETID()");
        System.out.println("ID del contacto group1: " + group1.getId());
        System.out.println("ID del contacto group2: " + group2.getId());
        System.out.println("ID del contacto group3: " + group3.getId());
        System.out.println("ID del contacto group4: " + group4.getId());
        System.out.println("ID del contacto group5: " + group5.getId());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.GETNAME()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO GROUP.GETNAME()");
        System.out.println("Nombre del contacto group1: " + group1.getName());
        System.out.println("Nombre del contacto group2: " + group2.getName());
        System.out.println("Nombre del contacto group3: " + group3.getName());
        System.out.println("Nombre del contacto group4: " + group4.getName());
        System.out.println("Nombre del contacto group5: " + group5.getName());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.ADD(CONTACT) #TRUE
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO GROUP.ADD(CONTACT) #TRUE");
        System.out.println("Inserta contactos en el grupo: " + group1.add(person1));
        System.out.println("Inserta contactos en el grupo: " + group2.add(person2));
        System.out.println("Inserta contactos en el grupo: " + group1.add(group2));
        System.out.println("Inserta contactos en el grupo: " + group3.add(person3));
        System.out.println("Inserta contactos en el grupo: " + group4.add(person4));
        System.out.println("Inserta contactos en el grupo: " + group5.add(person5));

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.ADD(CONTACT) #FALSE
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO GROUP.ADD(CONTACT) #FALSE");
        System.out.println("Inserta contactos en el grupo: " + group1.add(person1));
        System.out.println("Inserta contactos en el grupo: " + group2.add(person2));
        System.out.println("Inserta contactos en el grupo: " + group1.add(group2));
        System.out.println("Inserta contactos en el grupo: " + group3.add(person3));
        System.out.println("Inserta contactos en el grupo: " + group4.add(person4));
        System.out.println("Inserta contactos en el grupo: " + group5.add(person5));

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.SENDSMS(MESSAGE)
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.SENDSMS(MESSAGE)");
        group1.sendSMS("Mensaje para gente del grupo " + group1.getName());
        group2.sendSMS("Mensaje para gente del grupo " + group2.getName());
        group3.sendSMS("Mensaje para gente del grupo " + group3.getName());
        group4.sendSMS("Mensaje para gente del grupo " + group4.getName());
        group5.sendSMS("Mensaje para gente del grupo " + group5.getName());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.ISMEMBER(ID)
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.ISMEMBER(ID)");
        System.out.println("G1: " + group1.isMember(person1.getId())); // TRUE
        System.out.println("G1: " + group1.isMember(person2.getId())); // TRUE
        System.out.println("G1: " + group1.isMember(person3.getId())); // FALSE
        System.out.println("G1: " + group1.isMember(person4.getId())); // FALSE
        System.out.println("G1: " + group1.isMember(person5.getId())); // FALSE
        // G2
        System.out.println("G2: " + group2.isMember(person1.getId())); // FALSE
        System.out.println("G2: " + group2.isMember(person2.getId())); // TRUE
        System.out.println("G2: " + group2.isMember(person3.getId())); // FALSE
        System.out.println("G2: " + group2.isMember(person4.getId())); // FALSE
        System.out.println("G2: " + group2.isMember(person5.getId())); // FALSE
        // G3
        System.out.println("G3: " + group3.isMember(person1.getId())); // FALSE
        System.out.println("G3: " + group3.isMember(person2.getId())); // FALSE
        System.out.println("G3: " + group3.isMember(person3.getId())); // TRUE
        System.out.println("G3: " + group3.isMember(person4.getId())); // FALSE
        System.out.println("G3: " + group3.isMember(person5.getId())); // FALSE
        // G4
        System.out.println("G4: " + group4.isMember(person1.getId())); // FALSE
        System.out.println("G4: " + group4.isMember(person2.getId())); // FALSE
        System.out.println("G4: " + group4.isMember(person3.getId())); // FALSE
        System.out.println("G4: " + group4.isMember(person4.getId())); // TRUE
        System.out.println("G4: " + group4.isMember(person5.getId())); // FALSE
        // G5
        System.out.println("G5: " + group5.isMember(person1.getId())); // FALSE
        System.out.println("G5: " + group5.isMember(person2.getId())); // FALSE
        System.out.println("G5: " + group5.isMember(person3.getId())); // FALSE
        System.out.println("G5: " + group5.isMember(person4.getId())); // FALSE
        System.out.println("G5: " + group5.isMember(person5.getId())); // TRUE

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.REMOVE(ID) #TRUE
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.REMOVE(ID) #TRUE");
        System.out.println("Remove contacto: " + group1.remove(person1.getId()));
        System.out.println("Remove contacto: " + group2.remove(person2.getId()));
        System.out.println("Remove contacto: " + group3.remove(person3.getId()));
        System.out.println("Remove contacto: " + group4.remove(person4.getId()));
        System.out.println("Remove contacto: " + group5.remove(person5.getId()));

        // --------------------------------------------------
        // Add contactos eleminados
        // --------------------------------------------------
        group1.add(person1);
        group2.add(person2);
        group3.add(person3);
        group4.add(person4);
        group5.add(person5);

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.REMOVE(ID) #FALSE
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.REMOVE(ID) #FALSE");
        System.out.println("Remove contacto: " + group1.remove(person3.getId()));
        System.out.println("Remove contacto: " + group2.remove(person5.getId()));
        System.out.println("Remove contacto: " + group3.remove(person2.getId()));
        System.out.println("Remove contacto: " + group4.remove(person1.getId()));
        System.out.println("Remove contacto: " + group5.remove(person4.getId()));

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.GETCONTACTS()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.GETCONTACTS()");
        System.out.println(group1.getContacts());
        System.out.println(group2.getContacts());
        System.out.println(group3.getContacts());
        System.out.println(group4.getContacts());
        System.out.println(group5.getContacts());

        System.out.println("\n");

        // --------------------------------------------------
        // PRUEBA METODO GROUP.TOSTRING()
        // --------------------------------------------------
        System.out.println("\tPRUEBA METODO PERSON.TOSTRING()");
        System.out.println(group1.toString());
        System.out.println(group2.toString());
        System.out.println(group3.toString());
        System.out.println(group4.toString());
        System.out.println(group5.toString());

    }

}
