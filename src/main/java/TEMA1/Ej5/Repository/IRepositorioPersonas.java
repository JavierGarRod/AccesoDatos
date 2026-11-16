package TEMA1.Ej5.Repository;

import java.io.FileNotFoundException;
import java.util.List;

import TEMA1.Ej5.Exception.RepositorioException;
import TEMA1.Ej5.Models.Persona;

public interface IRepositorioPersonas {
	List<Persona> findAll() throws RepositorioException, FileNotFoundException;
	
	
}
