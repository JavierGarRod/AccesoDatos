package Ficheros.Boletin3.Ej2.Controller;

import java.util.List;

import Ficheros.Boletin3.Ej2.Models.Pais;
import Ficheros.Boletin3.Ej2.Repository.RepositorioPaises;

public class ControladorPaises {
	private static final String RUTA="src/main/resources/paises.csv";
	
	public static void main(String[] args) {
		RepositorioPaises repo=new RepositorioPaises();
		List<Pais> paises=repo.leerFichero(RUTA);
		
		for(Pais p: paises) {
			System.out.println(repo.validarSiPaisTieneNombreYContinente(p));
		}
		
	}

}
