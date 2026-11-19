package TEMA2.Introduccion;

import java.util.List;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GestionaXML {
	private static Logger logger = Logger.getLogger(GestionaXML.class.getName());
	private static final String RUTA_RESOURCES="src/main/resources/";
	
	public static void main(String[] args) {
		UtilesDomXML utiles=new UtilesDomXML();
		
		Document doc=utiles.getDocumentFromXML("Libro.xml");//Leo fichero y construyo Document
		Element raiz=doc.getDocumentElement(); //Te devuelve la raiz
		
		//Te devuelve la raiz Pelicula y el primer elemento de pelicula
        Libro libro=utiles.fromElement2Libro(raiz);
        System.out.println(libro);
        
        //Devuelve una lista con todos los elementos de la raiz de pelicula
        List<Libro> libros=utiles.leerLibrosDesdeXML(raiz);
        System.out.println(libros);
	}

}
