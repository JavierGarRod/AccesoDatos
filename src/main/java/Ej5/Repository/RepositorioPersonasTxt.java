package Ej5.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import Ej5.Exception.RepositorioException;
import Ej5.Models.Persona;

public class RepositorioPersonasTxt implements IRepositorioPersonas{
	File fichero;
	List<Persona> personas;
	
	public RepositorioPersonasTxt(String ruta) {
		super();
		this.fichero = new File(RUTA);
		this.personas =new ArrayList<Persona>();
	}
	
	public static final String RUTA="src\\main\\resources\\fichero2.txt";

	@Override
	public List<Persona> findAll() throws RepositorioException, FileNotFoundException {
		if(this.personas.size()==0) {
			this.personas=leeFicheroCargaPersona(RUTA);
		}
		
		return this.personas;
	}
	
	public List<Persona> leeFicheroCargaPersona(String rutaYNombre) throws RepositorioException, FileNotFoundException {
		Scanner in = null;
		List<Persona> personas=new ArrayList<Persona>(); 
		
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			
			//Se crea el flujo
			in = new Scanner(fichero);
			in.useLocale(Locale.ENGLISH);
			
			
			// lee el fichero palabra a palabra
			while (in.hasNext()) {
				String nombrePersona=in.next();
				Persona p=new Persona(nombrePersona);
				
				//lee el fichero numero a numero y lo guarda en el atributo "nota" de persona
				while (in.hasNextDouble()) {
			        double nota = in.nextDouble();
			        p.getNotas().add(nota);
			    }
				personas.add(p);
			}
		} finally {
			if (in != null) {
				in.close();
// OjO Al cerrar el Scanner cierra también el FileReader que envuelve. 
			}
		}
		return personas;
	}

}
