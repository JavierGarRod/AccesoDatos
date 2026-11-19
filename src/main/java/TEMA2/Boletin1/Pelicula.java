package TEMA2.Boletin1;

public class Pelicula {
	private String titulo;
	private String ano;
	private String director;
	
	
	
	public Pelicula(String titulo, String ano, String director) {
		super();
		this.titulo = titulo;
		this.ano = ano;
		this.director = director;
	}
	
	public Pelicula() {
		super();
	}



	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	
	
	@Override
	public String toString() {
		return "Pelicula [titulo=" + titulo + ", ano=" + ano + ", director=" + director + "]";
	}
	
	
}
