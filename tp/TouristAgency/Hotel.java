package TouristAgency;

/**
 * Calse Hotel
 * 
 * @author JuanSerberoCode
 */
public class Hotel extends TouristProduct {

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
	public Hotel(String nombre, String descripcion, double precio) {
		super(nombre, descripcion);
		price = precio;
		discount = 0.0;
	}

	/**
	 * Metodo que establece el descuento
	 * 
	 * @param descuento double
	 */
	public void setDiscount(double descuento) {
		discount = descuento;
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
