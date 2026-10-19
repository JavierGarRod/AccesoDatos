package Ej5.Repository;

import java.io.File;
import java.util.List;

import Ej5.Exception.RepositorioException;
import Ej5.Models.Persona;

public class RepositorioPersonasTxt implements IRepositorioPersonas{
	private String separador;
	File fichero;
	List<Persona> personas;

	@Override
	public List<Persona> findAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona findById(String nombre) throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String nombre) throws RepositorioException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() throws RepositorioException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void save(Persona persona) throws RepositorioException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deleteById(String nombre) throws RepositorioException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
