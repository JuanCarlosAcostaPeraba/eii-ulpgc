package ArbolB;

/**
 * @author Juan Carlos Rodriguez, comentado por Jose Perez
 */
public class Conversor {
    /**
     * Numero de bytes de un int
     */
    public static final int INTBYTES = 4;
    /**
     * Numero de bytes de un float
     */
    public static final int FLOATSBYTES = 4;

    /**
     * Obtiene una representacion como vector de bytes de un valor int.
     * 
     * @param dato Valor entero
     * @return Vector de bytes que representa al entero
     */
    public static byte[] aByte(int dato) {
        byte res[] = new byte[INTBYTES];
        long datol = dato - (long) Integer.MIN_VALUE; // Pasamos a Numeros positivos
        for (int i = 0; i < INTBYTES; i++) { // Los 4 bytes de un int se toman mediante divisiones
            res[i] = (byte) ((datol % 256) + Byte.MIN_VALUE); // Pasamos a con signo para almacenarlo
            datol /= 256;
        }
        return res;
    }

    /**
     * anade un vector de bytes a otro en el que deben caber.
     * 
     * @param acumulado Vector donde se anaden los elementos
     * @param datos     Vector de elementos a anadir
     * @param pos       Posicion de "acumulado" a partir de la cual se anaden los
     *                  valores de "datos"
     * @return Numero de elementos en "acumulado" tras la adicion de los de "datos"
     */
    public static int anade(byte[] acumulado, byte[] datos, int pos) {
        System.arraycopy(datos, 0, acumulado, pos, datos.length);
        pos += datos.length;
        // for (int i = 0; i < datos.length; i++) acumulado[pos++] = datos[i];
        return pos;
    }

    /**
     * Devuelve un subvector de bytes a partir de un vector de Bytes, la posicion de
     * comienzo y el Tamano.
     * 
     * @param acumulado Vector de partida
     * @param pos       Posicion de comienzo del subverctor
     * @param lon       Tamano del subvector, se supone pos+lon<acumulado.length
     * @return El subvector de bytes de "acumulado" que comienza en "pos" y tiene
     *         Tamano "lon"
     */
    public static byte[] toma(byte[] acumulado, int pos, int lon) {
        byte[] res = new byte[lon];
        System.arraycopy(acumulado, pos, res, 0, lon);
        // for (int i = 0; i < lon; i++) res[i] = acumulado[pos++];
        return res;
    }

    /**
     * Obtiene una representacion como vector de bytes de un valor float.
     * 
     * @param dato Valor entero
     * @return Vector de bytes que representa al entero
     */
    public static byte[] aByte(float dato) {
        return aByte(Float.floatToRawIntBits(dato));
    }

    /**
     * Obtiene una representacion como vector de bytes de una String, truncada si
     * supera un limite.
     * 
     * @param dato   Ristra a convertir
     * @param limite Tamano maximo de la ristra que se convierte, si la ristra
     *               supera "limite" se trunca.
     * @return Vector de bytes que representa a la ristra, truncada en su caso
     */
    public static byte[] aByte(String dato, int limite) {
        if (dato.length() > limite)
            dato = dato.substring(0, limite - 1);
        byte res[] = new byte[limite];
        do {
            byte aux[] = dato.getBytes();
            if (aux.length <= limite) {
                System.arraycopy(aux, 0, res, 0, aux.length);
                // for(int i=0; i <aux.length; i++) res[i]=aux[i];
                return res;
            }
            dato = dato.substring(0, dato.length() - 1);
        } while (dato.length() > 0);
        return null;
    }

    /**
     * Obtiene el valor entero representado por un vector de bytes.
     * 
     * @param dato Vector de bytes que representa a un entero: construido con aByte
     * @return El entero correspondiente
     */
    public static int aInt(byte dato[]) {
        long res = 0;
        for (int i = 3; i >= 0; i--) { // Los 4 bytes regeneran el int
            res *= 256;
            res += (long) dato[i] - Byte.MIN_VALUE; // Pasamos a positivo
        }
        return (int) (res + Integer.MIN_VALUE);// Pasamos a con signo
    }

    /**
     * Obtiene el valor real representado por un vector de bytes.
     * 
     * @param dato Vector de bytes que representa a un entero: construido con aByte
     * @return El valor en coma flotante correspondiente
     */
    public static float aFloat(byte dato[]) {
        return Float.intBitsToFloat(aInt(dato));
    }

    /**
     * Obtiene la ristra representada por un vector de bytes.
     * 
     * @param dato Vector de bytes que representa a una ristra: construido con aByte
     * @return La ristra correspondiente
     */
    public static String aString(byte dato[]) {
        return new String(dato);
    }
}
