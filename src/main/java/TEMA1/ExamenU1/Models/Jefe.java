package TEMA1.ExamenU1.Models;

public class Jefe {
	private String nombre;
	private boolean contenedorCorazon;
	
	
	//CONSTRUCTOR
	public Jefe(String nombre, boolean contenedorCorazon) {
		super();
		this.nombre = nombre;
		this.contenedorCorazon = contenedorCorazon;
	}
	
	
	//GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isContenedorCorazon() {
		return contenedorCorazon;
	}
	public void setContenedorCorazon(boolean contenedorCorazon) {
		this.contenedorCorazon = contenedorCorazon;
	}
	
	
	//TOSTRING()
	@Override
	public String toString() {
		return "Jefe [nombre=" + nombre + ", contenedorCorazon=" + contenedorCorazon + "]";
	}
	
	
}
