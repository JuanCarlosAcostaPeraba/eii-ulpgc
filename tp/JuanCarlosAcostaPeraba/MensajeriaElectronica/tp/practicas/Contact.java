package tp.practicas;

/**
 * Clase abstract Contact
 * 
 * @author JuanSerberoCode
 */
public abstract class Contact implements Cloneable {

	/**
	 * Atributos
	 */
	private String name;
	private int id;

	/**
	 * Constructor
	 * 
	 * @param name String con el nombre del contacto
	 */
	public Contact(String name) {
		this.name = name;
		this.id = SMSTools.getUniqueId();
	}

	/**
	 * Metodo que inica el id del contacto
	 * 
	 * @return id unico
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Metodo que devuelve el nombre del contacto
	 * 
	 * @return nombre del contacto
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Metodo abstacto para enviar mesajes
	 * 
	 * @param message String con mensaje a enviar
	 */
	public abstract void sendSMS(String message);

	/**
	 * Metodo para clonar los contactos
	 */
	@Override
	protected Object clone() {
		Contact clone = null;
		try {
			clone = (Contact) super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return clone;
	}

	/**
	 * Metodo para comprobar que un objeto, pasado por parametro,
	 * es igual al objeto de la clase
	 * 
	 * @param obj [Object] Objeto a comparar
	 * @return true si el objeto pasado es el mismo que el de la clase,
	 *         false si no lo es
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Contact)) { // obj == null || obj != Contact
			return false;
		}
		Contact temp = (Contact) obj;
		return (Integer.compare(this.getId(), temp.getId())) == 0
				&& (this.getName() == temp.getName());
	}

}