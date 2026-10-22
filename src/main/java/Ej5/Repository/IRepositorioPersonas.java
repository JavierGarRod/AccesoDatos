package Ej5.Repository;

import java.io.FileNotFoundException;
import java.util.List;

import Ej5.Exception.RepositorioException;
import Ej5.Models.Persona;

public interface IRepositorioPersonas {
	List<Persona> findAll() throws RepositorioException, FileNotFoundException;
	
	
}
