package TouristAgency;

// Paquetes importados
import java.util.*;

/**
 * Calse Sales
 * 
 * @author JuanSerberoCode
 */
public class Sales {

	// Atributos
	private ArrayList<Sale> myList = new ArrayList<Sale>();

	/**
	 * Metodo que anade una venta a la lista
	 * 
	 * @param s
	 */
	public void add(Sale s) {
		myList.add(s);
	}

	/**
	 * Metodo que elimina una venta de la lista
	 * 
	 * @param pos
	 */
	public void remove(int pos) {
		myList.remove(pos);
	}

	/**
	 * Metodo que devuelve el importe total de las ventas
	 * 
	 * @param ventas
	 * @return Importe total
	 */
	public double totalSales(String ventas) {
		double importe = 0;
		for (int i = 0; i < myList.size(); i++) {
			importe += myList.get(i).getPrice();
		}
		return importe;
	}

	/**
	 * Metodo que devuelve una lista con las ventas ordenadas por el precio
	 * 
	 * @param max_ventas
	 * @return Lista de ventas ordenadas
	 */
	public List<Sale> salesOrderByPrice(int max_ventas) {
		ArrayList<Sale> tempMyList = new ArrayList<>(myList);
		Collections.copy(tempMyList, myList);
		Collections.sort(tempMyList, new Comparator<Sale>() {
			public int compare(Sale s1, Sale s2) {
				if (s1.getVentas().compareTo(s2.getVentas()) == 0) {
					return Integer.compare(s1.getId(), s2.getId());
				}
			}
		});
	}

	/**
	 * Metodo que devuelve todas las ventas existentes
	 * 
	 * @return Lista de ventas
	 */
	public List<Sale> getAllSales() {
		ArrayList<Sale> tempMyList = new ArrayList<>(myList);
		Collections.copy(tempMyList, myList);
		Collections.sort();
	}

}
