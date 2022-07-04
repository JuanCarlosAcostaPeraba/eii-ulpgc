package TextProcessing;

// Paquetes importados
import java.util.*;
import java.io.*;

/**
 * Clase Main
 * 
 * @author JuanSerberoCode
 */
public class Main {

    /**
     * Metodo main
     * 
     * @param args
     */
    public static void main(String[] args) {
        FileNames fileNames = new FileNames();
        FileContents fileContents = new FileContents(30, 100 * 1024);
        WordFrequencies wordFrequencies = new WordFrequencies();
        /*
         * Cree e inicie los hilos AQUÍ
         * 2 FileReader y 3 FileProcessor
         */
        FileReader fileReader1 = new FileReader(fileNames, fileContents);
        FileReader fileReader2 = new FileReader(fileNames, fileContents);
        FileProcessor fileProcessor1 = new FileProcessor(fileContents, wordFrequencies);
        FileProcessor fileProcessor2 = new FileProcessor(fileContents, wordFrequencies);
        FileProcessor fileProcessor3 = new FileProcessor(fileContents, wordFrequencies);

        fileReader1.start();
        fileReader2.start();
        fileProcessor1.start();
        fileProcessor2.start();
        fileProcessor3.start();

        Tools.fileLocator(fileNames, "datos");
        fileNames.noMoreNames();

        try {
            fileReader1.join();
            fileReader2.join();
            fileProcessor1.join();
            fileProcessor2.join();
            fileProcessor3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Esperar a que terminen los hilos creados
        for (String palabra : Tools.wordSelector(wordFrequencies.getFrequencies())) {
            System.out.println(palabra);
        }
    }
}

/**
 * Clase Tools
 * 
 * @author Group 41-5
 */
class Tools {

    /**
     * Metodo fileLocator
     * 
     * @param fn
     * @param dirname
     */
    public static void fileLocator(FileNames fn, String dirname) {
        File dir = new File(dirname);
        if (!dir.exists()) {
            return;
        }
        if (dir.isDirectory()) {
            String[] dirList = dir.list();
            for (String name : dirList) {
                if (name.equals(".") || name.equals("..")) {
                    continue;
                }
                fileLocator(fn, dir + File.separator + name);
            }
        } else {
            fn.addName(dir.getAbsolutePath());
            // System.out.println(dir.getAbsolutePath());
        }
    }

    /**
     * Clase estatica Order
     */
    public static class Order implements Comparator<Map.Entry<String, Integer>> {

        /**
         * Metodo compare
         * 
         * @return true/false
         */
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                return o1.getKey().compareTo(o2.getKey());
            }
            return o2.getValue().compareTo(o1.getValue());
        }
    }

    /**
     * Metodo wordSelector
     * 
     * @param wf
     * @return l
     */
    public static List<String> wordSelector(Map<String, Integer> wf) {
        Set<Map.Entry<String, Integer>> set = wf.entrySet();
        Set<Map.Entry<String, Integer>> orderSet = new TreeSet<Map.Entry<String, Integer>>(
                new Order());
        orderSet.addAll(set);
        List<String> l = new LinkedList<String>();
        int i = 0;
        for (Map.Entry<String, Integer> pair : orderSet) {
            l.add(pair.getValue() + " " + pair.getKey());
            if (++i >= 10)
                break;
        }
        return l;
    }

    /**
     * Metodo getContents
     * 
     * @param fileName
     * @return text.toString / error from catch
     */
    public static String getContents(String fileName) {
        StringBuilder text = new StringBuilder();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "ISO8859-1"));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append("\n");
            }
            br.close();
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
        return text.toString();
    }
}
