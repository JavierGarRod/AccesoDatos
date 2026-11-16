package TEMA1.JSON.Ej1;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ManejaJSON {
	//private static final Logger logger = Logger.getLogger(ManejaJSON.class.getName());
	private static final String RUTA="src/main/resources/Empleado.json";
	
	public static void main(String[] args) {
		ManejaJSON ref=new ManejaJSON();
		ref.leeEmpleado(RUTA);
		Empleado e1=new Empleado("id1", "Pepe");
		Empleado e2=new Empleado("id2", "Pepa");
		List<Empleado> empleados=new ArrayList<Empleado>();
		empleados.add(e1);
		empleados.add(e2);
		ref.escribeEmpleados(empleados, RUTA+"Salida.json");
		
	}
	
	public void leeEmpleado(String ruta) {
		FileReader fichero;
		try {
			fichero = new FileReader(RUTA);
			Gson gson=new Gson();
			Empleado empleado = gson.fromJson(fichero, Empleado.class);
			System.out.println(empleado);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void escribeEmpleados(List<Empleado> empleados, String ruta) {
		for(Empleado e: empleados) {
			escribeEmpleado(e, ruta);
		}
	}
	
	public void escribeEmpleado(Empleado empleado, String ruta){// Convertir el objeto a JSON
		 Gson gson = new Gson();
		 String json = gson.toJson(empleado);
		 FileWriter fichero = null;
		 try {
			fichero = new FileWriter(ruta);
			fichero.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} catch (Exception e) {
					System.out.println("Error al escribir empleado");
				}			
			}		
		}	   
	}
}
