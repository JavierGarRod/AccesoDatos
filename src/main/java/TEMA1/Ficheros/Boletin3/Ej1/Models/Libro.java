package TEMA1.Ficheros.Boletin3.Ej1.Models;

public class Libro {
	private String isbn;
	private String nombre;
	private String editorial;
	
	public Libro(String isbn, String nombre, String editorial) {
		super();
		this.isbn = isbn;
		this.nombre = nombre;
		this.editorial = editorial;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", nombre=" + nombre + ", editorial=" + editorial + "]";
	}
	
	
}
