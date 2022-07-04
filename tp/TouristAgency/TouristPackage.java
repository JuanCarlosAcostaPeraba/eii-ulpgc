package TouristAgency;

// Paquetes importados
import java.util.*;

/**
 * Calse TouristPackage
 * 
 * @author JuanSerberoCode
 */
public class TouristPackage extends TouristProduct {

	// Atributos
	private LinkedList<TouristProduct> paquete;
	private double discount;
	private double price;

	/**
	 * Constructor
	 * 
	 * @param nombre
	 * @param description
	 */
	public TouristPackage(String nombre, String descripcion) {
		super(nombre, descripcion);
		paquete = new LinkedList<TouristProduct>();
		price = 0.0;
		discount = 0.0;

	}

	/**
	 * Metodo que anade un producto
	 * 
	 * @param p
	 * @return boolean
	 */
	public boolean add(TouristProduct p) {
		for (int i = 0; i < paquete.size(); i++) {
			if (p.getName().equals(paquete.get(i).getName())) {
				return false;
			}
		}
		paquete.add(p);
		return true;
	}

	/**
	 * Metodo que elimina un producto
	 * 
	 * @param nombre
	 * @return boolean
	 */
	public boolean remove(String nombre) {
		for (int i = 0; i < paquete.size(); i++) {
			if (nombre.equals(paquete.get(i).getName())) {
				paquete.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que devuelve una lista con los productos
	 * 
	 * @return Lista de productos
	 */
	public List<TouristProduct> getProducts() {
		List<TouristProduct> paqueteCopia = new LinkedList<>(paquete);
		Collections.copy(paqueteCopia, paquete);
		Collections.sort(paqueteCopia, new Comparator<TouristProduct>() {
			public int compare(TouristProduct x, TouristProduct y) {
				return x.getName().compareTo(y.getName());
			}
		});
		return paqueteCopia;
	}

	/**
	 * Metodo que devuelve los datos del paquete
	 * 
	 * @return String con los datos del paquete
	 */
	public String toString() {
		String result = getName() + ": " + getDescription();
		for (int i = 0; i < getProducts().size(); i++) {
			if (i == getProducts().size() - 1) {
				result += getProducts().get(i).toString() + "]";
				break;
			} else if (i == 0) {
				result += "[" + getProducts().get(i).toString() + ", ";
			} else {
				result += getProducts().get(i).toString() + ", ";
			}
		}
		return result;
	}

	/**
	 * Metodo que establece el descuento
	 * 
	 * @param descuento
	 */
	public void setDiscount(double descuento) {
		discount = descuento;
	}

	/**
	 * Metodo que devuelve el precio
	 * 
	 * @return precio
	 */
	public double getPrice() {
		for (int i = 0; i < getProducts().size(); i++) {
			price += getProducts().get(i).getPriceWithDiscount();
		}
		return price;
	}

	/**
	 * Metodo que devuelve el precio con descuento
	 * 
	 * @return precio con descuento
	 */
	public double getPriceWithDiscount() {
		return getPrice() - getDiscount();
	}

	/**
	 * Metodo que devuelve el descuento
	 * 
	 * @return descuento
	 */
	public double getDiscount() {
		return getPrice() * discount;
	}

}
