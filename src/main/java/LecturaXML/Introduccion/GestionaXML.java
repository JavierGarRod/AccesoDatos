package LecturaXML.Introduccion;

import java.util.List;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class GestionaXML {
	private static Logger logger = Logger.getLogger(GestionaXML.class.getName());
	private static final String RUTA_RESOURCES="src/main/resources/";
	
	public static void main(String[] args) {
		UtilesDomXML utiles=new UtilesDomXML();
		
		//Leo fichero y construyo Document
		Document doc=utiles.getDocumentFromXML("Libro.xml");
		
		//Te devuelve la raiz
        Element raiz=doc.getDocumentElement();
        Libro libro=utiles.fromElement2Libro(raiz);
        System.out.println(libro);
        
        //Leo fichero y construyo Document
        Document docs=utiles.getDocumentFromXML("Libros.xml");
        Element raiz2=docs.getDocumentElement();
        List<Libro> libros=utiles.leerLibrosDesdeXML(raiz2);
        System.out.println(libros);
	}

}
