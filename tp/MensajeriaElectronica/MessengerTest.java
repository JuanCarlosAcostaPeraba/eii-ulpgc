package MensajeriaElectronica;

//Paquetes importados
import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import tp.practicas.*;

/**
 * Clase MessengerTest
 * 
 * @author JuanSerberoCode
 */
public class MessengerTest {

	@Test
	public void getNameDeClasePerson() {
		Person p = new Person("Juan Carlos", "666777888");
		assertEquals("Metodo getName de la clase Person devuelve mal",
				"Juan Carlos", p.getName());
	}

	@Test
	public void toStringDeClasePerson() {
		Person p = new Person("Juan Carlos", "666777888");
		assertEquals("Metodo toString de la clase Person devuelve mal",
				"Juan Carlos: 666777888", p.toString());
	}

	@Test
	public void getNametDeClaseGroup() {
		Group g = new Group("Familia");
		assertEquals("Metodo getName de la clase Group devuelve mal",
				"Familia", g.getName());
	}

	@Test
	public void isMemberDeClaseGroupTrue() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		g.add(p);
		assertEquals("Metodo isMember de la clase Group devuelve false y debe ser true",
				true, g.isMember(p.getId()));
	}

	@Test
	public void isMemberDeClaseGroupFalse() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		assertNotEquals("Metodo isMember de la clase Group devuelve true y debe ser false",
				true, g.isMember(p.getId()));
	}

	@Test
	public void addDeClaseGroupTrue() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		assertEquals("Metodo add de la clase Group devuelve false y debe ser true",
				true, g.add(p));
	}

	@Test
	public void addDeClaseGroupFalse() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		g.add(p);
		assertNotEquals("Metodo add de la clase Group devuelve true y debe ser false",
				true, g.add(p));
	}

	@Test
	public void removeDeClaseGroupTrue() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		g.add(p);
		assertEquals("Metodo remove de la clase Group devuelve false y debe ser true",
				true, g.remove(p.getId()));
	}

	@Test
	public void removeDeClaseGroupFalse() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		assertNotEquals("Metodo remove de la clase Group devuelve true y debe ser false",
				true, g.remove(p.getId()));
	}

	public void getContactsDeClaseGroup() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		g.add(p);
		List<Contact> lista = new LinkedList<Contact>();
		lista.add((Contact) p);
		assertEquals("Metodo getContacts de la clase Group devuelve mal",
				lista, g.getContacts());
	}

	@Test
	public void toStringDeClaseGroup() {
		Person p = new Person("Juan Carlos", "666777888");
		Group g = new Group("Familia");
		g.add(p);
		assertEquals("Metodo toString de la clase Group devuelve mal",
				"Familia\nJuan Carlos: 666777888\n", g.toString());
	}

}
