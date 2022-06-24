package tp.practicas;

/**
 * Clase Person
 * 
 * @author JuanSerberoCode
 */
public class Person extends Contact {

	/**
	 * Atributos
	 */
	private String phone;

	/**
	 * Constructor
	 * 
	 * @param name  String nombre de la persona
	 * @param phone String telefono de la persona
	 */
	public Person(String name, String phone) {
		super(name); // nombre que se establece en la clase padre
		this.phone = phone;
	}

	/**
	 * Metodo que devuelte
	 * 
	 * @return nombre de la persona
	 */
	@Override
	public String toString() {
		String temp = this.getName() + ": " + this.phone;
		return temp;
	}

	/**
	 * Metodo para enviar mensaje a esta persona
	 * 
	 * @param message String mensaje a enviar
	 */
	@Override
	public void sendSMS(String message) {
		SMSTools.sendMessage(this.phone, message);
	}

}
