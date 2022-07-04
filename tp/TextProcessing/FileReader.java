package TextProcessing;

/**
 * Clase FileReader
 * 
 * @author JuanSerberoCode
 */
public class FileReader extends Thread {

	// Atributos
	private FileNames names;
	private FileContents contents;

	/**
	 * Constructor
	 * 
	 * @param fileNames    FileNames
	 * @param fileContents FileContents
	 */
	public FileReader(FileNames fileNames, FileContents fileContents) {
		names = fileNames;
		contents = fileContents;
	}

	/**
	 * Metodo que recibe un nombre de fichero de FileNames
	 * y se anade a un objeto FileContents
	 */
	public void run() {
		contents.registerWriter();
		String name = names.getName();
		while (name != null) {
			contents.addContents(Tools.getContents(name));
			name = names.getName();
		}
		contents.unregisterWriter();
	}
}