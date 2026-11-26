package TEMA2.Simulacion.Utiles;

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
import TEMA2.Simulacion.Models.EspecieMascota;
import TEMA2.Simulacion.Models.Mascota;
import TEMA2.Simulacion.Models.Propietario;

public class UtilesDomXML {
	private static final String RUTA="src/main/resources/veterinaria.xml";
	
	//METODO QUE RECIBE UN NOMBRE DE UN FICHERO, LO LEE Y DEVUELVE EL DOCUMENTO DOM
		public Document getDocumentFromXML(String nombreFichero) {
			// 1. Crear el analizador DOM
	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder builder;
	        // 2. Leer el fichero XML
	        Document doc = null;
	        
	        try {
	        	builder=factory.newDocumentBuilder();
	        	
	        	doc=builder.parse(new File(RUTA));
	        	
	        }catch (ParserConfigurationException e) {
	        	e.printStackTrace();
	        	
			} catch (SAXException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				e.printStackTrace();
				
			}
	        
	        return doc;
		}
		
		//METODO QUE RECIBE UN ELEMENTO Clinica Y DEVUELVE UNA mascota
		public Mascota fromElement2Mascota(Element raiz) {
			Element mascota=(Element) raiz.getElementsByTagName("mascota");
			Element idElement = (Element) mascota.getAttributeNode("id");
			Element nombreElement = (Element) mascota.getElementsByTagName("nombre").item(0);
			Element especieElement = (Element) mascota.getAttributeNode("especie");
			Element propietarioElement = (Element) mascota.getElementsByTagName("propietario").item(0);
			Element nombrePropietarioElement= (Element) propietarioElement.getElementsByTagName("nombre").item(0);
			
	        //Leo del elemento Titulo su valor
			String id = idElement.getTextContent();
			String nombre =nombreElement.getTextContent();
			String especieString =especieElement.getTextContent();
			String propietarioString =nombrePropietarioElement.getTextContent();
			
			//Pasar de String a Enum (Con fulleria)
			EspecieMascota especie=null;
			switch (especieString) {
			case "PERRO":
				especie=EspecieMascota.PERRO;
				break;
				
			case "GATO":
				especie=EspecieMascota.GATO;
				break;
				
			case "AVE":
				especie=EspecieMascota.AVE;
				break;
				
			case "EXOTICO":
				especie=EspecieMascota.EXOTICO;
				break;
				
			default:
				break;
			}
			
			//Pasar de String a Propietario (Con fulleria)
			Propietario propietario=new Propietario(propietarioString);
	        
			//Creo Mascota con todas sus cosas
	        Mascota m=new Mascota();
	        m.setId(Integer.parseInt(id));
	        m.setNombre(nombre);
	        m.setEspecie(especie);
	        m.setPropietario(propietario);
	        
	        return m;
		}
		
		
		//METODO QUE RECIBE UNA LISTA DE ELEMENTOS Mascotas Y DEVUELVE LOS Mascotas
		public List<Mascota> leerMascotasDesdeXML(Element mascotaElement){
			List<Mascota> mascotas=new ArrayList<Mascota>();
			
			NodeList nodosLibro=mascotaElement.getElementsByTagName("mascota");
			for(int i=0;i<nodosLibro.getLength();i++) {
				Mascota m=this.fromElement2Mascota((Element) nodosLibro.item(i));
				mascotas.add(m);
			}
			
			return mascotas;
		}
}
