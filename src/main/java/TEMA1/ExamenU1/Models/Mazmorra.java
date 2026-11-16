package TEMA1.ExamenU1.Models;

import java.util.Objects;

public class Mazmorra {
	private String nombre;
	private String region;
	Epoca epoca;
	private String objeto;
	private String recompensa;
	Jefe jefe;
	private boolean completada;
	private Double mejorTiempo;
	
	
	//CONSTRUCTOR
	public Mazmorra(String nombre, String region, Epoca epoca, String objeto, String recompensa, Jefe jefe,
			boolean completada, Double mejorTiempo) {
		super();
		this.nombre = nombre;
		this.region = region;
		this.epoca = epoca;
		this.objeto = objeto;
		this.recompensa = recompensa;
		this.jefe = jefe;
		this.completada = completada;
		this.mejorTiempo = mejorTiempo;
	}
	
	public Mazmorra(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public Mazmorra(String nombre, Double mejorTiempo) {
		super();
		this.nombre = nombre;
		this.mejorTiempo = mejorTiempo;
	}
	

	//GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public Epoca getEpoca() {
		return epoca;
	}


	public void setEpoca(Epoca epoca) {
		this.epoca = epoca;
	}


	public String getObjeto() {
		return objeto;
	}


	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}


	public String getRecompensa() {
		return recompensa;
	}


	public void setRecompensa(String recompensa) {
		this.recompensa = recompensa;
	}


	public Jefe getJefe() {
		return jefe;
	}


	public void setJefe(Jefe jefe) {
		this.jefe = jefe;
	}


	public boolean isCompletada() {
		return completada;
	}


	public void setCompletada(boolean completada) {
		this.completada = completada;
	}


	public Double getMejorTiempo() {
		return mejorTiempo;
	}


	public void setMejorTiempo(Double mejorTiempo) {
		this.mejorTiempo = mejorTiempo;
	}


	//EQUALS() Y HASHCODE()
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mazmorra other = (Mazmorra) obj;
		return Objects.equals(nombre, other.nombre);
	}


	//TOSTRING()
	@Override
	public String toString() {
		return "Mazmorra [nombre=" + nombre +", epoca=" + epoca +", jefe=" + jefe + "]";
	}
	
	public String toString2() {
		return nombre +";" + epoca +";" + jefe.getNombre() + ";"+mejorTiempo;
	}
}
