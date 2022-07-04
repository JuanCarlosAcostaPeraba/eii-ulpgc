package tp.practicas;

// Paquetes importados
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase Group
 * 
 * @author JuanSerberoCode
 */
public class Group extends Contact {

	/**
	 * Atrinutos
	 */
	private List<Contact> contactList = new LinkedList<Contact>();

	/**
	 * Constructor
	 * 
	 * @param name [String] nombre del grupo
	 */
	public Group(String name) {
		super(name); // nombre que se establece en la clase padre
	}

	/**
	 * Metodo para comprobar si un contacto se encuentra
	 * en el grupo
	 * 
	 * @param id [int] ID del miembro a buscar
	 * @return true si se encuentra el contacto
	 *         false si no se encuentra el contacto
	 */
	public boolean isMember(int id) {
		for (int i = 0; i < this.contactList.size(); i++) {
			if (this.contactList.get(i) instanceof Group
					&& ((Group) this.contactList.get(i)).isMember(id)) {
				return true;
			}
			if (this.contactList.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para anadir contactos en un grupo
	 * 
	 * @param contact [Contact] Contacto a anadir en la grupo
	 * @return true cuando anade el contacto;
	 *         false cuando el contacto ya está en el grupo
	 */
	public boolean add(Contact contact) {
		if ((this.isMember(contact.getId()))
				|| (contact.getId() == this.getId())) {
			return false;
		}
		this.contactList.add(contact);
		return true;
	}

	/**
	 * Metodo para eliminar contactos del grupo
	 * 
	 * @param id [int] ID del contacto a eliminar
	 * @return true si se elimina el contacto del grupo;
	 *         false si no se elimina, porque no se encuetra en el
	 */
	public boolean remove(int id) {
		for (int i = 0; i < this.contactList.size(); i++) {
			if (this.contactList.get(i).getId() == id) {
				this.contactList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que devuelve una lista con los nombres
	 * de los contactos directos del grupo
	 * 
	 * @return Lista de contactos
	 */
	public List<Contact> getContacts() {
		List<Contact> tempContactList = new LinkedList<Contact>();
		for (int i = 0; i < this.contactList.size(); i++) {
			tempContactList.add((Contact) (this.contactList.get(i).clone()));
		}
		Collections.sort(tempContactList, new Comparator<Contact>() {
			public int compare(Contact contact1, Contact contact2) {
				return Integer.compare(contact1.getId(), contact2.getId());
			}
		});
		return tempContactList;
	}

	/**
	 * Metodo que devuelve un String con todos los nombres de los contactos
	 * ordenados alfabeticamente
	 * 
	 * @return String con nombres de los contactos,
	 *         ordenador alfabeticamente
	 */
	@Override
	public String toString() {
		List<Contact> tempContactList = new LinkedList<Contact>();
		for (int i = 0; i < this.contactList.size(); i++) {
			tempContactList.add((Contact) (this.contactList.get(i).clone()));
		}
		Collections.sort(tempContactList, new Comparator<Contact>() {
			public int compare(Contact contact1, Contact contact2) {
				return contact1.getName().compareTo(contact2.getName());
			}
		});
		String data = "";
		data = data + this.getName() + "\n";
		for (int i = 0; i < tempContactList.size(); i++) {
			if (tempContactList.get(i) instanceof Group) {
				data = data + ((Group) tempContactList.get(i)).toString() + "\n";
			} else {
				data = data + tempContactList.get(i).toString() + "\n";
			}
		}
		return data;
	}

	/**
	 * Metodo para enviar mensaje al grupo
	 * 
	 * @param message [String] Mensaje a enviar
	 */
	@Override
	public void sendSMS(String message) {
		for (int i = 0; i < this.contactList.size(); i++) {
			if (this.contactList.get(i) instanceof Group) {
				((Group) this.contactList.get(i)).sendSMS(message);
			} else if (this.contactList.get(i) instanceof Person) {
				this.contactList.get(i).sendSMS(message);
			}
		}
	}

}
