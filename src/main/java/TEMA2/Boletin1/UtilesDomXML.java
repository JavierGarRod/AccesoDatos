package TEMA2.Boletin1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import TEMA2.Introduccion.Libro;

public class UtilesDomXML {
	private static final String RUTA_RESOURCES="src/main/resources/";
	
	
	//METODO QUE RECIBE UN NOMBRE DE UN FICHERO, LO LEE Y DEVUELVE EL DOCUMENTO DOM
	public Document getDocumentFromXML(String nombreFichero) {
		// 1. Crear el analizador DOM
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        // 2. Leer el fichero XML
        Document doc = null;
        
        try {
        	builder=factory.newDocumentBuilder();
        	
        	doc=builder.parse(new File(RUTA_RESOURCES+nombreFichero));
        	
        }catch (ParserConfigurationException e) {
        	e.printStackTrace();
        	
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
        
        return doc;
	}
	
	//METODO QUE RECIBE UN ELEMENTO PELICULA Y DEVUELVE UN PELICULA
	public Pelicula fromElement2Pelicula(Element raiz) {
		Element tituloElement = (Element) raiz.getElementsByTagName("Titulo").item(0);
		Element anoElement = (Element) raiz.getElementsByTagName("Fecha").item(0);
		Element directorElement = (Element) raiz.getElementsByTagName("Director").item(0);
		
        //Leo del elemento Titulo su valor
		String titulo = tituloElement.getTextContent();
		String ano =anoElement.getTextContent();
		String director =directorElement.getTextContent();
		
        
        Pelicula peli=new Pelicula();
        peli.setTitulo(titulo);
        peli.setAno(ano);
        peli.setDirector(director);
        
        return peli;
	}
	
	
	//METODO QUE RECIBE UNA LISTA DE ELEMENTOS LIBROS Y DEVUELVE LOS LIBROS
	public List<Pelicula> leerPeliculasDesdeXML(Element peliculaElement){
		List<Pelicula> peliculas=new ArrayList<Pelicula>();
		
		NodeList nodosLibro=peliculaElement.getElementsByTagName("Pelicula");
		for(int i=0;i<nodosLibro.getLength();i++) {
			Pelicula p=this.fromElement2Pelicula((Element) nodosLibro.item(i));
			peliculas.add(p);
		}
		
		return peliculas;
	}
		
		
}
