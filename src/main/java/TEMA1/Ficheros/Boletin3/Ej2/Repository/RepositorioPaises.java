package TEMA1.Ficheros.Boletin3.Ej2.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import TEMA1.Ficheros.Boletin3.Ej2.Models.Pais;

public class RepositorioPaises{
	
	List<Pais> repositorioPaises=new ArrayList<Pais>();
	private String ruta=null;
	
	public List<Pais> leerFichero(String ruta) {
		List<Pais> listaDePaises=new ArrayList<Pais>();
		Pais pais=null;

		try {
			FileReader lecturaDeFichero=new FileReader(ruta);
			Scanner in=new Scanner(lecturaDeFichero);
			
			while(in.hasNextLine()) {
				String linea=in.nextLine();
				String [] campos=linea.split(",");
				pais=new Pais(campos[0], campos[1]);
				
				listaDePaises.add(pais);
			}
			
		}catch(Exception e) {
			e.getMessage();
		}
		
		return listaDePaises;
	}
	
	public boolean validarSiPaisTieneNombreYContinente(Pais paisAValidar){
		boolean validacion=false;
		Pais paisAvalidar=new Pais(null,null);
		
		if(paisAvalidar.getNombreContinente()==null || paisAvalidar.getNombrePais()==null) {
			validacion=false;
		}else {
			validacion=true;
		}
		
		
		return validacion;
	}
}
