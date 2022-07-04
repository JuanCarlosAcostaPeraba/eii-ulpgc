package TouristAgency;

/**
 * Calse Flight
 * 
 * @author JuanSerberoCode
 */
public class Flight extends TouristProduct {

	// Atributos
	private double price;
	private double discount;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 */
	public Flight(String nombre, String descripcion, double precio) {
		super(nombre, descripcion);
		price = precio;
		discount = 0.0;
	}

	/**
	 * Metodo que establece si un vuelo es para un residente
	 * 
	 * @param residente boolean
	 */
	public void setResident(boolean residente) {
		if (residente) {
			discount = 0.75;
		}
	}

	/**
	 * Metodo que devuelve el precio
	 * 
	 * @return precio
	 */
	@Override
	public double getPrice() {
		return price;
	}

	/**
	 * Metodo que devuelve el precio con descuento
	 */
	@Override
	public double getPriceWithDiscount() {
		return getPrice() - getDiscount();
	}

	/**
	 * Metodo que devuelve el descuento
	 */
	@Override
	public double getDiscount() {
		return getPrice() * discount;
	}

}
