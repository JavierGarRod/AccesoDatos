package TEMA2.Simulacion.Repository;

import TEMA2.Simulacion.Models.Mascota;

public interface IRepositorioMascotas {
	public void findAll();
	
	public void save(Mascota m);
}
