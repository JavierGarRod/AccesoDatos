package Ficheros.Boletin3.Ej2.Models;

public class Pais {
	private String nombrePais;
	private String nombreContinente;
	private int poblacion;
	private String idioma;
	private String moneda;
	
	public Pais(String nombrePais, String nombreContinente) {
		super();
		this.nombrePais = nombrePais;
		this.nombreContinente = nombreContinente;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getNombreContinente() {
		return nombreContinente;
	}

	public void setNombreContinente(String nombreContinente) {
		this.nombreContinente = nombreContinente;
	}

	public int getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(int poblacion) {
		this.poblacion = poblacion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@Override
	public String toString() {
		return "Pais [nombrePais=" + nombrePais + ", nombreContinente=" + nombreContinente + ", poblacion=" + poblacion
				+ ", idioma=" + idioma + ", moneda=" + moneda + "]";
	}

}
