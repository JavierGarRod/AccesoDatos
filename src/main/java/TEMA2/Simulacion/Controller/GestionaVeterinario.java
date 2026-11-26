package TEMA2.Simulacion.Controller;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import TEMA2.Simulacion.Models.Mascota;
import TEMA2.Simulacion.Utiles.UtilesDomXML;

public class GestionaVeterinario {
	private static final String RUTA="src/main/resources/";
	
	public static void main(String[] args) {
		UtilesDomXML utiles=new UtilesDomXML();
		
		Document doc=utiles.getDocumentFromXML(RUTA+"veterinaria.xml");//Leo fichero y construyo Document
		Element raiz=doc.getDocumentElement();//Devuelve el elemento raiz del doc
		
		//Te devuelve la raiz Pelicula y el primer elemento de pelicula
        Mascota mascota=utiles.fromElement2Mascota(raiz);
        System.out.println(mascota);
        
      //Devuelve una lista con todos los elementos de la raiz de pelicula
        List<Mascota> mascotas=utiles.leerMascotasDesdeXML(raiz); 
        System.out.println(mascotas);
	}
}
