package TEMA2.Simulacion.Models;

import java.util.Objects;

public class Mascota {
	private int id;
	private String nombre;
	private EspecieMascota especie;
	private Propietario propietario;
	
	//CONSTRUCTOR
	public Mascota(int id, String nombre, EspecieMascota especie, Propietario propietario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.propietario = propietario;
	}
	
	public Mascota() {
		super();
	}

	//GETTERS() AND SETTERS()
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public EspecieMascota getEspecie() {
		return especie;
	}
	public void setEspecie(EspecieMascota especie) {
		this.especie = especie;
	}
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	
	//EQUALS() Y HASHCODE() DE ID
	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(id));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return id == other.id;
	}

	//METODO TOSTRING()
	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", propietario=" + propietario
				+ "]";
	}
	
	
}
