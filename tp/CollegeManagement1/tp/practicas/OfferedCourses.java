package tp.practicas;

//Paquetes importados
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Clase OfferedCourses
 * 
 * @author JuanSerberoCode
 */
public class OfferedCourses {

	// Atributos
	private List<Course> offeredCoursesList;

	/**
	 * Constructor
	 */
	public OfferedCourses() {
		offeredCoursesList = new LinkedList<Course>();
	}

	/**
	 * Metodo para anadir un curso a la lista de ofertados
	 * 
	 * @param course [Course] Curso a anadir en la lista de ofertados
	 * @return true si se anade, false si no se anade
	 */
	public boolean addCourse(Course course) {
		for (int i = 0; i < offeredCoursesList.size(); i++) {
			if (offeredCoursesList.get(i).getCode() == course.getCode())
				return false;
		}
		offeredCoursesList.add(course);
		return true;
	}

	/**
	 * Metodo para eliminar un curso de la lista de ofertados
	 * 
	 * @param code [int] Codigo identificador del curso
	 * @return true si se elimina, false si no
	 */
	public boolean removeCourse(int code) {
		for (int i = 0; i < offeredCoursesList.size(); i++) {
			if (offeredCoursesList.get(i).getCode() == code) {
				offeredCoursesList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para obtener los datos de un curso
	 * 
	 * @param code [int] Codigo identificador del curso
	 * @return null si no se encuentra el curso, curso si se encuentra
	 */
	public Course getCourse(int code) {
		for (int i = 0; i < offeredCoursesList.size(); i++) {
			if (offeredCoursesList.get(i).getCode() == code) {
				return offeredCoursesList.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo para obtener una lista con los cursos ordenados
	 * alfabeticamente
	 * 
	 * @return Lista con los cursos ordenados
	 *         alfabeticamente
	 */
	public List<Course> getCourses() {
		List<Course> tempCoursesList = new LinkedList<>(offeredCoursesList);
		Collections.copy(tempCoursesList, offeredCoursesList);
		Collections.sort(tempCoursesList, new Comparator<Course>() {
			public int compare(Course course1, Course course2) {
				return course1.toString().compareTo(course2.toString());
			}
		});
		return tempCoursesList;
	}

}
