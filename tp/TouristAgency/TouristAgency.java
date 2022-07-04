package TouristAgency;

/**
 * Calse TouristAgency
 * 
 * @author JuanSerberoCode
 */
public class TouristAgency {

	public static void main(String[] args) {
		Flight f = new Flight("vuelo", "louka", 100.0);
		Hotel h = new Hotel("hotel", "nuha", 100.0);

		h.setDiscount(0.8);

		f.setResident(true);

		System.out.println(f.toString());
		System.out.println(f.getPrice());
		System.out.println(f.getPriceWithDiscount());

		System.out.println();

		System.out.println(h.toString());
		System.out.println(h.getPrice());
		System.out.println(h.getPriceWithDiscount());
	}

}
