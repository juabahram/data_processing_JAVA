package fp.capsula;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaCapsula {

	public static Archivo leerCapsulas(String ruta) {
			
			List<String> lineas = Ficheros.leeFichero(ruta);
			Archivo capsulas = new Archivo();
			for (String linea : lineas) {
				Capsula c = parseaCapsula(linea);
				capsulas.añadirCapsula(c);
			}
			return capsulas;
			
		}
	
	public static Capsula parseaCapsula(String s) {
		String[] trozos= s.split(";");
		Checkers.check("La cadena est mal formada", trozos.length==7);
		String Id = trozos[0].trim();
		String Serial = trozos[1].trim();
		Status status = Status.valueOf(trozos[2].trim());
		Boolean Reuse = Boolean.valueOf(trozos[3].trim());
		Integer Water = Integer.valueOf(trozos[4].trim());
		Integer Land = Integer.valueOf(trozos[5].trim());
		LocalDate Launch = LocalDate.parse(trozos[6].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		return new Capsula(Id, Serial, status, Reuse, Water, Land, Launch);
	}
}
