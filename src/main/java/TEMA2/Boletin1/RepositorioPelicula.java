package TEMA2.Boletin1;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPelicula {
	private static UtilesDomXML utiles=new UtilesDomXML();
	List<Pelicula> peliculas=new ArrayList<Pelicula>();
	
	
	public List<Pelicula> cargaSiHaceFalta(List<Pelicula> repo){
		List<Pelicula> peliculas=new ArrayList<Pelicula>();
		if(repo.isEmpty()) {
			peliculas=utiles.leerPeliculasDesdeXML(null);
		} else {
			peliculas=repo;
		}
		
		return peliculas;
	}
}
