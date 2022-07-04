package tp.practicas;

/**
 * Clase Course
 * 
 * @author JuanSerberoCode
 */
public class Course {

	// Atributos
	private int code;
	private String name;

	/**
	 * Constructor
	 * 
	 * @param courseCode  [int] Codigo del curso
	 * @param subjectName [String] Nombre del curso
	 */
	public Course(int courseCode, String subjectName) {
		code = courseCode;
		name = subjectName;
	}

	/**
	 * Metodo que devuelve el codigo del curso
	 * 
	 * @return Codigo del curso
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Metodo que devuelve el nombre del curso
	 * 
	 * @return Nombre del curso
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo que devuelve una string con los datos del curso
	 * 
	 * @return Strign con los datos del curso
	 */
	@Override
	public String toString() {
		return "(" + Integer.toString(getCode()) + ")" + getName();
	}

}
