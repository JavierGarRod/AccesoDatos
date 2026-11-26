package TEMA2.Simulacion.Service;

import java.io.File;

import TEMA2.Simulacion.Exception.VeterinariaException;
import TEMA2.Simulacion.Models.Mascota;

public interface IServicioMascotas{
	public boolean validarNombreYPropietario(Mascota m) throws VeterinariaException;
	
	public void validarFichero(File fichero) throws VeterinariaException;
}
