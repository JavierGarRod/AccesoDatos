
package Ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class Introduccion2 {

    private static final Logger logger = Logger.getLogger(Introduccion2.class.getName());
    private static final String ruta="src/main/resources/";
    
    
    public static void main(String[] args) {
        Introduccion2 ref = new Introduccion2();
        
        Double[] notas1= {3.0, 4.0, 6.0, 8.0, 9.0, 7.0};
        Persona p1=new Persona("Andrea", Arrays.asList(notas1));

        Double[] notas2= {4.0, 6.0, 8.0, 2.0};
        Persona p2=new Persona("Lucia", Arrays.asList(notas2));
        
        List<Persona> personas = new ArrayList<>();
        personas.add(p2);
        personas.add(p1);

        ref.escribePersonas(personas, "fichero2.txt");
    }

    public void escribePersonas(List<Persona> personas,String nombreFichero) {
    	PrintWriter out=null;
    	String rutaFichero = ruta+nombreFichero;
        try {
        	FileWriter ficheroSalida;
        	ficheroSalida = new FileWriter(rutaFichero);
        	out=new PrintWriter(ficheroSalida);
        	out.printf("Nombre       Notas%n");
        	
            // Escribe los datos de cada persona
            for (Persona p : personas) {
                out.printf(Locale.ENGLISH, "%-10s", p.getNombre());

                for (double nota : p.getNotas()) {
                    out.printf(Locale.ENGLISH, "%6.2f", nota);
                }

                // Salto de línea al terminar cada persona
                out.println("%n");
            }

        } catch (IOException e) {
            logger.severe("Error al escribir el fichero: " + e.getMessage());
        } finally {
        	if(out !=null) {
        		out.close();
        	}
        }
    }
}
