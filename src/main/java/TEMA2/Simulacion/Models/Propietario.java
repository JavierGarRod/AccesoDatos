package TEMA2.Simulacion.Models;

public class Propietario {
	private String nombre;

	//CONSTRUCTOR
	public Propietario(String nombre) {
		super();
		this.nombre = nombre;
	}

	//GETTER() Y SETTER()
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//METODO TOSTRING()
	@Override
	public String toString() {
		return "Propietario [nombre=" + nombre + "]";
	}
	
	

}
