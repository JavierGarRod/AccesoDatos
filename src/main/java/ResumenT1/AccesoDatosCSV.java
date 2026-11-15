package ResumenT1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
 * =====================================================================
 *  RESUMEN: LECTURA Y ESCRITURA DE FICHEROS CSV (sin librerías externas)
 * =====================================================================
 *
 *  QUÉ ES UN CSV
 *  - Fichero de texto plano: una fila por línea y los campos separados por
 *    un delimitador (',' o ';'). En España Excel suele usar ';'.
 *  - La primera línea suele ser la CABECERA (nombres de las columnas).
 *
 *  CLASES BÁSICAS (paquete java.io)
 *  - FileWriter(ruta)          -> escribe y SOBRESCRIBE el fichero.
 *  - FileWriter(ruta, true)    -> escribe AÑADIENDO al final (append).
 *  - BufferedWriter            -> escritura con buffer: write() + newLine().
 *  - FileReader(ruta)          -> lee caracteres.
 *  - BufferedReader            -> readLine() devuelve una línea o null al final.
 *  - Scanner(new File(ruta))   -> alternativa para leer: hasNextLine()/nextLine().
 *
 *  CLASES NIO (java.nio.file) - más cortas
 *  - Files.write(path, lineas, charset)   -> escribe una lista de líneas.
 *  - Files.readAllLines(path, charset)    -> lee todo el fichero en una List<String>.
 *
 *  IDEAS CLAVE
 *  - try-with-resources: try (BufferedReader br = ...) { }  cierra el fichero solo.
 *  - Excepciones: IOException (general) y FileNotFoundException (no existe).
 *  - Para leer: split(separador) trocea la línea en campos -> String[].
 *    Usar split(sep, -1) para no perder campos vacíos del final.
 *    Si el separador es un carácter especial de regex ( | . ) hay que
 *    escaparlo: split("\\|")  o  split(Pattern.quote("|")).
 *  - Hay que convertir los tipos a mano: Integer.parseInt(...), Double.parseDouble(...)
 *    y controlar NumberFormatException.
 *  - Codificación: indicar UTF-8 para no perder tildes ni la ñ.
 *  - LIMITACIÓN: si un campo contiene el separador (ej: "Pérez; Juan") el
 *    split se rompe. En ese caso se usan comillas y una librería (OpenCSV:
 *    CSVReader / CSVWriter).
 */
public class AccesoDatosCSV {

    static final String FICHERO = "personas.csv";
    static final String SEPARADOR = ";";
    static final String CABECERA = "nombre;edad;ciudad";

    // ---------------------------------------------------------------
    // Clase modelo (POJO): cada fila del CSV = un objeto Persona
    // ---------------------------------------------------------------
    static class Persona {
        private String nombre;
        private int edad;
        private String ciudad;

        public Persona(String nombre, int edad, String ciudad) {
            this.nombre = nombre;
            this.edad = edad;
            this.ciudad = ciudad;
        }

        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
        public String getCiudad() { return ciudad; }

        // Objeto -> línea CSV
        public String toCsv() {
            return nombre + SEPARADOR + edad + SEPARADOR + ciudad;
        }

        @Override
        public String toString() {
            return nombre + " (" + edad + ") - " + ciudad;
        }
    }

    // ---------------------------------------------------------------
    // 1) ESCRIBIR un CSV completo (sobrescribe si ya existe)
    // ---------------------------------------------------------------
    static void escribirCsv(String ruta, List<Persona> personas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(CABECERA);
            bw.newLine();                       // salto de línea (independiente del SO)
            for (Persona p : personas) {
                bw.write(p.toCsv());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el CSV: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // 2) AÑADIR una fila al final (append = true)
    // ---------------------------------------------------------------
    static void anadirPersona(String ruta, Persona p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            bw.write(p.toCsv());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al añadir al CSV: " + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // 3) LEER un CSV con BufferedReader (el más usado)
    // ---------------------------------------------------------------
    static List<Persona> leerCsv(String ruta) {
        List<Persona> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            boolean esCabecera = true;
            while ((linea = br.readLine()) != null) {   // null = fin del fichero
                if (esCabecera) {                       // saltamos la primera línea
                    esCabecera = false;
                    continue;
                }
                if (linea.trim().isEmpty()) {           // ignoramos líneas vacías
                    continue;
                }
                String[] campos = linea.split(SEPARADOR, -1);
                if (campos.length < 3) {
                    System.out.println("Línea incompleta, se ignora: " + linea);
                    continue;
                }
                try {
                    String nombre = campos[0].trim();
                    int edad = Integer.parseInt(campos[1].trim());
                    String ciudad = campos[2].trim();
                    lista.add(new Persona(nombre, edad, ciudad));
                } catch (NumberFormatException e) {
                    System.out.println("Edad no válida, se ignora: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el fichero: " + ruta);
        } catch (IOException e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
        }
        return lista;
    }

    // ---------------------------------------------------------------
    // 4) VERSIÓN NIO (más corta, con UTF-8 explícito)
    // ---------------------------------------------------------------
    static void escribirCsvNio(String ruta, List<Persona> personas) throws IOException {
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Persona p : personas) {
            lineas.add(p.toCsv());
        }
        Files.write(Paths.get(ruta), lineas, StandardCharsets.UTF_8);
        // Para AÑADIR al final con NIO:
        // Files.write(Paths.get(ruta), lineas, StandardCharsets.UTF_8,
        //             StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    static List<Persona> leerCsvNio(String ruta) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(ruta), StandardCharsets.UTF_8);
        List<Persona> lista = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {        // i = 1 -> saltamos la cabecera
            String[] c = lineas.get(i).split(SEPARADOR, -1);
            lista.add(new Persona(c[0].trim(), Integer.parseInt(c[1].trim()), c[2].trim()));
        }
        return lista;
    }

    // ---------------------------------------------------------------
    // 5) LEER con Scanner (alternativa)
    // ---------------------------------------------------------------
    static void leerConScanner(String ruta) {
        try (Scanner sc = new Scanner(new File(ruta))) {
            if (sc.hasNextLine()) {
                sc.nextLine();                           // cabecera
            }
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) {
                    continue;
                }
                String[] c = linea.split(SEPARADOR);
                System.out.println("Scanner -> " + c[0] + " / " + c[1] + " / " + c[2]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existe el fichero: " + ruta);
        }
    }

    // ---------------------------------------------------------------
    // PRUEBA
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>(Arrays.asList(
                new Persona("Ana", 21, "Madrid"),
                new Persona("Luis", 34, "Valencia"),
                new Persona("María", 28, "Bilbao")));

        escribirCsv(FICHERO, personas);                              // crear
        anadirPersona(FICHERO, new Persona("Pablo", 19, "Cádiz"));   // añadir

        List<Persona> leidas = leerCsv(FICHERO);                     // leer
        for (Persona p : leidas) {
            System.out.println(p);
        }

        leerConScanner(FICHERO);

        try {
            escribirCsvNio("personas_nio.csv", leidas);
            System.out.println("NIO -> " + leerCsvNio("personas_nio.csv").size() + " personas");
        } catch (IOException e) {
            System.out.println("Error NIO: " + e.getMessage());
        }
    }
}
