package Ficheros.Boletin1.Ej9;

import java.io.File;
import java.util.Scanner;

public class Ej9 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ej9 ref=new Ej9();

        System.out.print("Introduce la ruta del directorio: ");
        String ruta = sc.nextLine();

        File dir = new File(ruta);

        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("El directorio no existe.");
        }

        int total = ref.contarFicheros(dir);

        System.out.println("Se eliminarán " + total + " ficheros.");
        System.out.print("¿Quieres continuar? (s/n): ");

        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            if (ref.borrarDirectorio(dir)) {
                System.out.println("Borrado completado.");
            } else {
                System.out.println("El borrado no se completó.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    
    // Cuenta todos los ficheros que hay dentro
    // del directorio, incluidos los subdirectorios.
    public int contarFicheros(File dir) {
        int contador = 0;

        File[] contenido = dir.listFiles();

        if (contenido == null) {
            return 0;
        }

        for (File f : contenido) {
            if (f.isDirectory()) {
                contador += contarFicheros(f);
            } else {
                contador++;
            }
        }

        return contador;
    }

    // Borra primero el contenido y después el directorio.
    public boolean borrarDirectorio(File dir) {
    	boolean borrar=false;
        File[] contenido = dir.listFiles();

        if (contenido == null) {
            System.out.println("No se puede acceder a: "
                    + dir.getAbsolutePath());
            return borrar;
        }

        for (File f : contenido) {
            if (f.isDirectory()) {
                if (!borrarDirectorio(f)) {
                    return borrar;
                }
            } else {
                if (!f.delete()) {
                    System.out.println("No se pudo borrar: "
                            + f.getAbsolutePath());
                    return borrar;
                }
            }
        }

        // Ahora el directorio está vacío.
        if (!dir.delete()) {
            System.out.println("No se pudo borrar el directorio: "
                    + dir.getAbsolutePath());
            return borrar;
        }

        return borrar=true;
    }
}
