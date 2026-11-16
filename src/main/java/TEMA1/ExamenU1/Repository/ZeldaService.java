package TEMA1.ExamenU1.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import TEMA1.ExamenU1.Exception.ZeldaException;
import TEMA1.ExamenU1.Models.Epoca;
import TEMA1.ExamenU1.Models.Mazmorra;

public class ZeldaService {
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	
	public List<Mazmorra> findAll(String ruta) {
        Type tipoLista = new TypeToken<List<Mazmorra>>() { }.getType();
        List<Mazmorra> mazmorras=new ArrayList<Mazmorra>();
        
        try (BufferedReader br = Files.newBufferedReader(Paths.get(ruta), StandardCharsets.UTF_8)) {
            mazmorras = GSON.fromJson(br, tipoLista);
        } catch (IOException e) {
            System.out.println("Error al leer la lista: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.out.println("JSON mal formado: " + e.getMessage());
        }
        
        return mazmorras;
    }
	
	
	public List<Mazmorra> completarMazmorra(String ruta, String nombreMazmorra, Double tiempo) throws ZeldaException {
		List<Mazmorra> mazmorras=findAll(ruta);
		Mazmorra m=new Mazmorra(nombreMazmorra);
		
		if(mazmorras.contains(nombreMazmorra)) {
			m.setMejorTiempo(tiempo);
			mazmorras.set(mazmorras.indexOf(nombreMazmorra), m);
		} 
		
		return mazmorras;
	}
	
	
	public HashMap<Mazmorra, Epoca> agruparPorEpoca(String ruta, Epoca epoca) {
		HashMap<Mazmorra, Epoca> coleccion= new HashMap<Mazmorra, Epoca>();
		List<Mazmorra> mazmorras=findAll(ruta);
		boolean encontrado=false;
		int contador=0;
		
		while(!encontrado || contador<mazmorras.size()) {
			if(mazmorras.contains(epoca)) {
				//Mazmorra m=new Mazmorra();
				//coleccion.put(m, epoca);
			}
			else {
				contador++;
			}
		}
		
		
		return coleccion;
	}
	
	
	public void exportarCSV(List<Mazmorra> mazmorras, String nombreFichero) {
		String rutaSalida="src/main/resources/Exportaciones/";
		File fichero=new File(rutaSalida);
		
		if(fichero.isDirectory()) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaSalida+nombreFichero))) {
	            for (Mazmorra m : mazmorras) {
	                bw.write(m.toString2());
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error al escribir el CSV: " + e.getMessage());
	        }
		}
	}
	
}
