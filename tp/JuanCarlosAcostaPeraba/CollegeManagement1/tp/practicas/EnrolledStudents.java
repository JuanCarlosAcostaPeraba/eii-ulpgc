package tp.practicas;

//Paquetes importados
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

/**
 * Clase EnrolledStudents
 * 
 * @author JuanSerberoCode
 */
public class EnrolledStudents {

	// Atributos
	private List<Student> studentsList;

	/**
	 * Constructor
	 */
	public EnrolledStudents() {
		studentsList = new LinkedList<Student>();
	}

	/**
	 * Metodo para anadir estudiantes en la lista de matriculados
	 * 
	 * @param student [Student] Estudiante a matricular
	 * @return true si se mete, false si no
	 */
	public boolean addStudent(Student student) {
		for (int i = 0; i < studentsList.size(); i++) {
			if (studentsList.get(i).getId() == student.getId()) {
				return false;
			}
		}
		studentsList.add(student);
		return true;
	}

	/**
	 * Metodo para eliminar un estudiante de la lista de matriculados
	 * 
	 * @param id [int] Id del estudiante a desmatricular
	 * @return true si se elimina, false si no
	 */
	public boolean removeStudent(int id) {
		for (int i = 0; i < studentsList.size(); i++) {
			if (studentsList.get(i).getId() == id) {
				studentsList.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo para conseguir los datos de un estudiante
	 * 
	 * @param id [int] Id del estudiante
	 * @return null si no se encuentra el estudiante, estudiante si se encuentra
	 */
	public Student getStudent(int id) {
		for (int i = 0; i < studentsList.size(); i++) {
			if (studentsList.get(i).getId() == id) {
				return studentsList.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo para obtener una lista con los estudiantes
	 * que estan matriculados en el curso que se indica por parametro
	 * 
	 * @param code [int] Codigo del curso
	 * @return Lista con los estudiantes que estan matriculados en el curso
	 */
	public List<Student> getStudentsByCourse(int code) {
		List<Student> tempStudentsList = new LinkedList<Student>();
		for (int i = 0; i < studentsList.size(); i++) {
			for (Course e : studentsList.get(i).getEnrolledCourses()) {
				if (e.getCode() == code) {
					tempStudentsList.add(studentsList.get(i));
					break;
				}
			}
		}
		Collections.sort(tempStudentsList, new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				if (student1.getName().compareTo(student2.getName()) == 0) {
					return Integer.compare(student1.getId(), student2.getId());
				}
				return student1.getName().compareTo(student2.getName());
			}
		});
		return tempStudentsList;
	}

	/**
	 * Metodo para ordenar a los estudiantes por su nombre.
	 * Si tienen el mismo nombre se ordena por el id
	 * 
	 * @return Lista de estudiantes ordenada por su nombre
	 */
	public List<Student> getStudentsOrderByName() {
		List<Student> tempStudentsList = new LinkedList<>(studentsList);
		Collections.copy(tempStudentsList, studentsList);
		Collections.sort(tempStudentsList, new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				if (student1.getName().compareTo(student2.getName()) == 0) {
					return Integer.compare(student1.getId(), student2.getId());
				}
				return student1.getName().compareTo(student2.getName());
			}
		});
		return tempStudentsList;
	}

	/**
	 * Metodo para ordenar a los estudiantes por su id
	 * 
	 * @return Lista de estudiantes ordenada por id
	 */
	public List<Student> getStudentsOrderById() {
		List<Student> tempStudentsList = new LinkedList<>(studentsList);
		Collections.copy(tempStudentsList, studentsList);
		Collections.sort(tempStudentsList, new Comparator<Student>() {
			public int compare(Student student1, Student student2) {
				return Integer.compare(student1.getId(), student2.getId());
			}
		});
		return tempStudentsList;
	}

}
