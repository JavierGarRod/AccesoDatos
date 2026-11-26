package TEMA2.Boletin1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
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
	
	//Método que devuelve un Document a partir de la raíz
	private Document construyoObjetoDocumento(String nombreRaiz) throws ParserConfigurationException {
		Document documento = null;
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();
		DOMImplementation implementacion = builder.getDOMImplementation();
		documento = implementacion.createDocument(null, nombreRaiz, null);
		// Primer parámetro uri: si null no está validado por ninguna ruta
		// segundo parámetro: nombre fichero
		// tercer parámetro: document type Por defecto null  Permite asociar DTD
		return documento;
	}

	//Método que agrega al documento un empleado
	private void agregaPeliculaADocumento(Document documento,Element padre,  Pelicula p) {
		// Para cada una de los atributos de persona, creo un elemento hijo
		Element titulo = this.creaElemento("Titulo", p.getTitulo(), padre, documento);
		Element ano = this.creaElemento("Ano", p.getAno(), padre, documento);
		Element director = this.creaElemento("Director", p.getDirector(), padre, documento);
		// El identificador lo vamos a crear como un atributo de la etiqueta empleado
	}
	
	//Método que genera un Element a partir de su nombre y su valor
	private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {
		Element elemento = documento.createElement(nombreElemento);
		Text texto = documento.createTextNode(valorElemento);
		padre.appendChild(elemento);// Se lo asigno a su padre como Hijo
		elemento.appendChild(texto);// Cargo el elemento con el valor
		return elemento;
	}

	//Método que a partir de un Document, genera un fichero XML
	private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {
		// clases necesarias finalizar la creación del archivo XML
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(documento);
		StreamResult resultado = new StreamResult(new File(RUTA_RESOURCES + nombreFichero));
		transformer.transform(source, resultado);
	}

	//Método que escribe una lista de empleados a un fichero
	public void escribeEmpleadosEnXML(String nombreFichero, List<Pelicula> empleados) {
		try {
			Document documento = this.construyoObjetoDocumento("empleados");
			for(Pelicula p : empleados)
			{
				Element elemento = this.creaElemento("empleado", null, documento.getDocumentElement(), documento);
				agregaPeliculaADocumento(documento, elemento, p);
			}
			escribeDocumentoEnFichero(documento, nombreFichero);
		} catch (ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
		} catch (TransformerException e1) {
			System.out.println(e1.getMessage());
		}
	}

	//Metodo que escribe una pelicula en un fichero
	public void escribeEmpleadoEnXML(String nombreFichero, Pelicula p) {
		try {
			Document documento = this.construyoObjetoDocumento("pelicula");
			// Recupero la raíz del documento
			Element raiz = documento.getDocumentElement();
			agregaPeliculaADocumento(documento, raiz, p);
			escribeDocumentoEnFichero(documento, nombreFichero);
		} catch (ParserConfigurationException e1) {
			System.out.println(e1.getMessage());
		} catch (TransformerException e1) {
			System.out.println(e1.getMessage());
		}
	}	
}
