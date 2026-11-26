package TEMA2.Simulacion.Service;

import java.io.File;

import TEMA2.Simulacion.Exception.VeterinariaException;
import TEMA2.Simulacion.Models.Mascota;
import TEMA2.Simulacion.Repository.IRepositorioMascotas;
import TEMA2.Simulacion.Repository.RepositorioMascotas;

public class ServicioMascotas implements IServicioMascotas{
	IRepositorioMascotas repo=new RepositorioMascotas();

	@Override
	public boolean validarNombreYPropietario(Mascota m) throws VeterinariaException {
		boolean esValido=true;
		
		if(m.getNombre().equals(null)) {
			esValido=false;
			System.out.println("Nombre de animal vacio");
			throw new VeterinariaException();
		} else if(m.getPropietario().equals(null)) {
			esValido=false;
			System.out.println("Nombre de propietario vacio");
			throw new VeterinariaException();
		}
		
		return esValido;
	}

	@Override
	public void validarFichero(File fichero) {
		// TODO Auto-generated method stub
		
	}
	
}
