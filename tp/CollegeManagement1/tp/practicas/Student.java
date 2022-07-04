package tp.practicas;

//Paquetes importados
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

/**
 * Clase Student
 * 
 * @author JuanSerberoCode
 */
public class Student {

	// Atributos
	private int id;
	private String name;
	private List<Course> coursesList;

	/**
	 * Constructor
	 * 
	 * @param studentID   [id] Id del estudiante
	 * @param studentName [String] Nombre del estudiante
	 */
	public Student(int studentID, String studentName) {
		id = studentID;
		name = studentName;
		coursesList = new LinkedList<Course>();
	}

	/**
	 * Metodo que devuelve el id del estudiante
	 * 
	 * @return Id del estudiante
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo que devuelve el nombre del estudiante
	 * 
	 * @return Nombre del estudiante
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metodo para matricular al estudiante en nuevos cursos
	 * 
	 * @param course [Course] Curso nuevo
	 * @return true si se matricula, false si no
	 */
	public boolean enrollCourse(Course course) {
		for (int i = 0; i < coursesList.size(); i++) {
			if (coursesList.get(i).getCode() == course.getCode()) {
				return false;
			}
		}
		coursesList.add(course);
		return true;
	}

	/**
	 * Metodo para desmatricular al estudiante de los cursos
	 * 
	 * @param code [int] Codigo de la asignatura
	 * @return true si se desmatricula, false si no estaba matriculado
	 */
	public boolean unenrollCourse(int code) {
		for (int i = 0; i < coursesList.size(); i++) {
			if (coursesList.get(i).getCode() == code) {
				coursesList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que devuelve una Collection de los cursos en los que esta matriculado
	 * el estudiante, ordenada por los codigos de curso
	 * 
	 * @return Collection de cursos en los que esta matriculado el estudiante
	 */
	public Collection<Course> getEnrolledCourses() {
		List<Course> tempCoursesList = new LinkedList<>(coursesList);
		Collections.copy(tempCoursesList, coursesList);
		sortByCode(tempCoursesList);
		return tempCoursesList;
	}

	/**
	 * Metodo que devuelve una String
	 * 
	 * @return String del alumno con los cursos en los que esta matriculado
	 */
	@Override
	public String toString() {
		List<Course> tempCoursesList = new LinkedList<>(coursesList);
		Collections.copy(tempCoursesList, coursesList);
		sortByCode(tempCoursesList);
		String temp = Integer.toString(getId()) + "-" + getName();
		for (int i = 0; i < tempCoursesList.size(); i++) {
			if (i == 0 && tempCoursesList.size() == 1) {
				temp += "[" + tempCoursesList.get(i).toString() + "]";
				break;
			}
			if (i == 0)
				temp += "[" + tempCoursesList.get(i).toString() + ", ";
			else if (i == (tempCoursesList.size() - 1))
				temp += tempCoursesList.get(i).toString() + "]";
			else
				temp += tempCoursesList.get(i).toString() + ", ";
		}
		if (tempCoursesList.size() == 0) {
			temp += "[]";
		}
		return temp;
	}

	/**
	 * Metodo para ordenar la lista de cursos por su codigo
	 * 
	 * @param listOfCourses [List<Course>] Lista de cursos
	 */
	private void sortByCode(List<Course> listOfCourses) {
		Collections.sort(listOfCourses, new Comparator<Course>() {
			public int compare(Course course1, Course course2) {
				return Integer.compare(course1.getCode(), course2.getCode());
			}
		});
	}
}
