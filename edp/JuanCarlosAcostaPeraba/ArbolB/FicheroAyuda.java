package ArbolB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 
 * @author Jose Perez basado en Juan Carlos Rodriguez
 *
 */
public class FicheroAyuda {

    public static class ExcepcionFichero extends RuntimeException {
        /**
         * Constructor de ExcepcionFichero.
         * 
         * @param e RuntimeException
         */
        public ExcepcionFichero(Exception e) {
            super(e);
        }

        /**
         * Constructor de ExcepcionFichero.
         * 
         * @param r Ristra identificativa
         */
        public ExcepcionFichero(String r) {
            super(r);
        }
    }

    private int numeroAdjuntos; // Numero de datos enteros anadidos
    private int listaVacias; // Lista de paginas vacias
    private int desplazamiento; // Inicio de datos cliente
    private int tamanoPagina; // Tamano de cada pagina
    private int adjuntos[]; // Datos adjuntos al fichero
    private RandomAccessFile fichero;
    private String nombre;
    public static final int dirNula = -1;

    /**
     * Crea un fichero con adjuntos y lo asocia al objeto actual. Puede producir
     * ExcepcionFichero.
     * 
     * @param nombre Ruta del fichero
     * @param lp     Tamano de pagina = numero de bytes de los registros
     * @param adj    Numero de adjuntos, cada uno de los cuales sera un int,
     *               numerados de 0 a adj-1.
     */
    public void crear(String nombre, int lp, int adj) {
        cerrar();
        this.nombre = nombre;
        File manejador = new File(nombre);
        if (manejador.exists())
            if (!manejador.delete())
                throw new ExcepcionFichero("El fichero no se ha podido borrar");
        try {
            fichero = new RandomAccessFile(nombre, "rw");
        } catch (FileNotFoundException e) {
            throw new ExcepcionFichero(e);
        }
        tamanoPagina = lp;
        numeroAdjuntos = adj;
        listaVacias = dirNula;
        desplazamiento = (3 + numeroAdjuntos) * Conversor.INTBYTES;
        posicionarInterno(0);
        escribirInterno(Conversor.aByte(tamanoPagina));
        escribirInterno(Conversor.aByte(numeroAdjuntos));
        escribirInterno(Conversor.aByte(listaVacias));
        if (numeroAdjuntos > 0) { // Escribimos los adjuntos en el fichero
            adjuntos = new int[numeroAdjuntos];
            for (int i = 0; i < numeroAdjuntos; i++) {
                adjuntos[i] = 0;
                escribirInterno(Conversor.aByte(0));
            }
        }
    }

    /**
     * Crea un fichero sin adjuntos y lo asocia al objeto actual. Puede producir
     * ExcepcionFichero.
     * 
     * @param nombre Ruta del fichero
     * @param lp     Tamano de pagina = numero de bytes de los registros
     */
    public void crear(String nombre, int lp) {
        crear(nombre, lp, 0);
    }

    /**
     * Abre un fichero de paginas reutilizables que puede tener adjuntos y lo asocia
     * al objeto actual. Puede producir ExcepcionFichero.
     * 
     * @param nombre Ruta del fichero
     */
    public void abrir(String nombre) {
        cerrar();
        this.nombre = nombre;
        try {
            fichero = new RandomAccessFile(nombre, "rw");
        } catch (FileNotFoundException e) {
            throw new ExcepcionFichero(e);
        }

        if (tamanoByte() < Conversor.INTBYTES * 2)
            throw new ExcepcionFichero("Error al abrir el fichero");
        posicionarInterno(0);
        tamanoPagina = Conversor.aInt(leerInterno(Conversor.INTBYTES));
        numeroAdjuntos = Conversor.aInt(leerInterno(Conversor.INTBYTES));
        listaVacias = Conversor.aInt(leerInterno(Conversor.INTBYTES));
        if (numeroAdjuntos > 0) {
            adjuntos = new int[numeroAdjuntos];
            for (int i = 0; i < numeroAdjuntos; i++) {
                adjuntos[i] = Conversor.aInt(leerInterno(Conversor.INTBYTES));
            }
        }
        desplazamiento = (3 + numeroAdjuntos) * Conversor.INTBYTES;
    }

    /**
     * Cierra el fichero en uso por el objeto actual. Puede producir
     * ExcepcionFichero.
     */
    public void cerrar() {
        try {
            if (fichero != null) {
                fichero.close();
                fichero = null;
            }
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }

    /**
     * Reserva una pagina para un registro, reutilizando las liberadas. Puede
     * producir ExcepcionFichero.
     * 
     * @return La direccion de la pagina, si no se produce excepcion
     */
    public int tomarPagina() {
        if (listaVacias == -1)
            return (int) tamano();
        int pnueva = listaVacias;
        posicionar(listaVacias);
        try {
            listaVacias = fichero.readInt();
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
        posicionarInterno(2 * Conversor.INTBYTES);
        escribirInterno(Conversor.aByte(listaVacias));
        return pnueva;
    }

    /**
     * Declara una pagina como libre para ser reutilizada. Puede producir
     * ExcepcionFichero.
     * 
     * @param pos Direccion de la pagina a liberar
     */
    public void liberarPagina(int pos) {
        posicionar(pos);
        escribirInterno(Conversor.aByte(listaVacias));
        listaVacias = pos;
        posicionarInterno(2 * Conversor.INTBYTES);
        escribirInterno(Conversor.aByte(listaVacias));
    }

    /**
     * Proporciona el valor de un adjunto: Los adjuntos se numeran de 0 a
     * numeroAdjuntos-1.
     * 
     * @param pos Numero del adjunto que se desea
     * @return Valor actual del adjunto
     */
    public int adjunto(int pos) {
        return adjuntos[pos];
    }

    /**
     * Modifica el valor de un adjunto: Los adjuntos se numeran de 0 a
     * numeroAdjuntos-1
     * 
     * @param pos   Numero del adjunto que se desea modificar
     * @param valor Nuevo valor del adjunto
     */
    public void adjunto(int pos, int valor) {
        adjuntos[pos] = valor;
        posicionarInterno((3 + pos) * Conversor.INTBYTES);
        escribirInterno(Conversor.aByte(valor));
    }

    /**
     * Lee una pagina del fichero. Puede producir ExcepcionFichero.
     * 
     * @param pos Numero de la pagina a leer
     * @return Contenido de la pagina en bytes
     */
    public byte[] leer(int pos) {
        posicionar(pos);
        return leerInterno(tamanoPagina);
    }

    /**
     * Escribe una pagina en el fichero. Debe estar reservada previamente. Puede
     * producir ExcepcionFichero.
     * 
     * @param dato Contenido de la pagina en bytes
     * @param pos  Posicion de la pagina a escribir
     */
    public void escribir(byte[] dato, int pos) {
        if (dato.length > tamanoPagina)
            throw new ExcepcionFichero("Intento de almacenar un dato mayor del permitido");
        posicionar(pos);
        if (dato.length < tamanoPagina) {
            byte[] nuevoDato = new byte[tamanoPagina];
            for (int i = 0; i < dato.length; i++)
                nuevoDato[i] = dato[i];
            dato = nuevoDato;
        }
        escribirInterno(dato);
    }

    /**
     * Situa el indicador de posicion en la pagina indicada. Puede producir
     * ExcepcionFichero.
     * 
     * @param pos Registro que se desea leer/escribir
     */
    public void posicionar(int pos) {
        posicionarInterno(desplazamiento + ((long) pos) * tamanoPagina);
    }

    /**
     * Devuelve el tamano del fichero medido en numero de registros. Puede producir
     * ExcepcionFichero.
     * 
     * @return Numero de paginas del fichero
     */
    public long tamano() {
        try {
            return (fichero.length() - desplazamiento) / tamanoPagina;
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }

    /**
     * Devuelve la ruta del fichero asociado
     * 
     * @return Nombre del fichero asociado
     */
    public String nombre() {
        return nombre;
    }

    // Realizan las operaciones correspondientes sobre el fichero
    private void posicionarInterno(long pos) {
        try {
            fichero.seek(pos);
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }

    private byte[] leerInterno(int lon) {
        try {
            byte datos[] = new byte[lon];
            // long tama = fichero.length();
            // long pos = fichero.getFilePointer();
            int leido = fichero.read(datos);
            if (leido != lon)
                throw new ExcepcionFichero("Error en lectura de un fichero");
            return datos;
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }

    private void escribirInterno(byte[] dato) {
        try {
            fichero.write(dato);
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }

    private long tamanoByte() {
        try {
            return fichero.length();
        } catch (IOException e) {
            throw new ExcepcionFichero(e);
        }
    }
}
