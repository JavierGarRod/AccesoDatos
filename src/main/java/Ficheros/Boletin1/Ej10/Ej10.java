package Ficheros.Boletin1.Ej10;

import java.io.File;

public class Ej10 {

    public static void main(String[] args) {
        String ruta = "C:\\Descargas";
        Ej10 ref=new Ej10();

        File dir = new File(ruta);

        ref.organizar(dir);
    }
    
    public void organizar(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("El directorio no existe.");
        }

        File[] archivos = dir.listFiles();

        if (archivos == null) {
            System.out.println("No se puede leer el directorio.");
        }

        for (File archivo : archivos) {

            String nombre = archivo.getName();
            int punto = nombre.lastIndexOf('.');

            // Los ficheros sin extensión van a "sin_extension".
            String extension;

            if (punto <= 0) {
                extension = "sin_extension";
            } else {
                extension = nombre.substring(punto + 1)
                                  .toLowerCase();
            }

            // Crear la carpeta de la extensión.
            File carpeta = new File(dir, extension);

            if (!carpeta.exists() && !carpeta.mkdir()) {
                System.out.println("No se pudo crear: " + carpeta.getName());
            }

            // Preparar el destino.
            File destino = new File(carpeta, nombre);

            // Evitar sobrescribir archivos existentes.
            if (destino.exists()) {
                String base;
                String ext;

                if (punto <= 0) {
                    base = nombre;
                    ext = "";
                } else {
                    base = nombre.substring(0, punto);
                    ext = nombre.substring(punto);
                }

                int contador = 1;

                do {
                    destino = new File(carpeta,
                            base + "(" + contador + ")" + ext);
                    contador++;
                } while (destino.exists());
            }

            // Mover el fichero.
            if (archivo.renameTo(destino)) {
                System.out.println(nombre + " -> " + carpeta.getName() + "/" + destino.getName());
            } else {
                System.out.println("Error al mover: " + nombre);
            }
        }

        System.out.println("Organización completada.");
    }
}