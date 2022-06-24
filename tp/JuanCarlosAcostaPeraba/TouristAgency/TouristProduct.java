package TouristAgency;

/**
 * Clase TouristProduct
 * 
 * @author JuanSerberoCode
 */
public abstract class TouristProduct {

	// Atributos
	private String name;
	private String description;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param descripcion
	 */
	public TouristProduct(String nombre, String descripcion) {
		name = nombre;
		description = descripcion;
	}

	/**
	 * Metodo que devuelve el nombre del producto
	 * 
	 * @return Nombre producto
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo que devuelve la descripcion del producto
	 * 
	 * @return Descripcion del producto
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Metodo que devuelve el precio del producto
	 */
	public abstract double getPrice();

	/**
	 * Metodo que devuelve el precio con descuento
	 */
	public abstract double getPriceWithDiscount();

	/**
	 * Metodo que devuelve el descuento
	 */
	public abstract double getDiscount();

	/**
	 * Metodo que devuelve una ristra con el nombre del producto y la descripcion
	 * 
	 * @return String con el nombre y la descripcion
	 */
	@Override
	public String toString() {
		return getName() + ": " + getDescription() + " " + getPriceWithDiscount() + "€";
	}

}
