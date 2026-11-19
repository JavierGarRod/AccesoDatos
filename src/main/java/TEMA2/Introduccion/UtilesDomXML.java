package TEMA2.Introduccion;

import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
	
	
	//METODO QUE RECIBE UN ELEMENTO LIBRO Y DEVUELVE UN LIBRO
	public Libro fromElement2Libro(Element raiz) {
		Element tituloElement = (Element) raiz.getElementsByTagName("titulo").item(0);
		Element autorElement = (Element) raiz.getElementsByTagName("autor").item(0);
		
        //Leo del elemento Titulo su valor
		String titulo = tituloElement.getTextContent();
		String autor =autorElement.getTextContent();
		String idElement =raiz.getAttribute("id");
		String categoria =raiz.getAttribute("categoria");
		
        
        Libro libro=new Libro(titulo);
        libro.setAutor(autor);
        libro.setId(idElement);
        libro.setCategoria(categoria);
        
        return libro;
	}
	
	
	//METODO QUE RECIBE UNA LISTA DE ELEMENTOS LIBROS Y DEVUELVE LOS LIBROS
	public List<Libro> leerLibrosDesdeXML(Element librosElement){
		List<Libro> libros=new ArrayList<Libro>();
		
		NodeList nodosLibro=librosElement.getElementsByTagName("libro");
		for(int i=0;i<nodosLibro.getLength();i++) {
			Libro l=this.fromElement2Libro((Element) nodosLibro.item(i));
			libros.add(l);
		}
		
		return libros;
	}
	
	
}
