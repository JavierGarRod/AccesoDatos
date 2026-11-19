package TEMA2.Boletin1;

import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GestionaXML {
	private static final String RUTA_RESOURCES="src/main/resources/";
	
	public static void main(String[] args) {
		UtilesDomXML utiles=new UtilesDomXML();
		
		Document doc=utiles.getDocumentFromXML("peliculas.xml");//Leo fichero y construyo Document
		Element raiz=doc.getDocumentElement();//Devuelve el elemento raiz del doc
		
		//Te devuelve la raiz Pelicula y el primer elemento de pelicula
        Pelicula peli=utiles.fromElement2Pelicula(raiz);
        System.out.println(peli);
        
      //Devuelve una lista con todos los elementos de la raiz de pelicula
        List<Pelicula> peliculas=utiles.leerPeliculasDesdeXML(raiz); 
        System.out.println(peliculas);
	}
}
