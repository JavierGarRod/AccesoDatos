
package Ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class Introduccion {

    private static final Logger logger = Logger.getLogger(Introduccion.class.getName());

    public static void main(String[] args) {

        File f = new File("src/main/resources/fichero1.txt");
        List<Persona> personas = new ArrayList<>();

        try (Scanner sc = new Scanner(f)) {

            sc.useLocale(Locale.ENGLISH);

            while (sc.hasNext()) {
                String nombre = sc.next();
                List<Double> notas = new ArrayList<>();

                while (sc.hasNextDouble()) {
                    notas.add(sc.nextDouble());
                }

                personas.add(new Persona(nombre, notas));
            }

            for (Persona persona : personas) {
                logger.info(persona.toString());
            }

        } catch (FileNotFoundException e) {
            logger.severe("No se encuentra el archivo: " + e.getMessage());
        }
    }
}
