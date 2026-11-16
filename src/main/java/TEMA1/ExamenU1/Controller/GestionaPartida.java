package TEMA1.ExamenU1.Controller;

import java.io.IOException;
import java.util.List;

import TEMA1.ExamenU1.Exception.ZeldaException;
import TEMA1.ExamenU1.Models.Mazmorra;
import TEMA1.ExamenU1.Repository.ZeldaService;


public class GestionaPartida {
	private static final String RUTA="src/main/resources/ocarina.json";
	
	public static void main(String[] args) throws ZeldaException, IOException {
		ZeldaService ref=new ZeldaService();
		List<Mazmorra> mazmorras=ref.findAll(RUTA);
		System.out.println(mazmorras);
		
		System.out.println("LO DE COMPLETAR TAMBIEN ESTA REGULAR");
		mazmorras=ref.completarMazmorra(RUTA, "Templo Del Fuego", 48.3);
		System.out.println(mazmorras);
		
		System.out.println("LO DE ORDENAR NO ME SALE");
		
		ref.exportarCSV(mazmorras, "fichero.txt");
	}
}
