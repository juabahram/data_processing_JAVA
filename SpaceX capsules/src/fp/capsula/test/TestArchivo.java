package fp.capsula.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import fp.capsula.Archivo;
import fp.capsula.FactoriaCapsula;
import fp.capsula.Status;


public class TestArchivo{
	
	public static void main(String[] args) {
		
		testConstructor(); //constructor para comprobar el correcto funcionamiento de los tratamientos
	}

	
	private static void testConstructor() {
		try {
			
			Archivo archive = FactoriaCapsula.leerCapsulas("data/capsules2.0.csv");
			
			System.out.println("Test del constructor: "+archive);
			
			//Test tratamientos secuenciales
			
			System.out.println("Test del método CalcularStatus: "+archive.CalcularStatus(Status.retired)); //calcula cuántas cápsulas se encuentran en el estado dado como parámetro
			
			System.out.println("Test del método Reciclar: "+archive.Reciclar()); //calcula si se han reciclado todas las cápsulas al menos una vez
			
			System.out.println("Test del método CalculaIdComun: "+archive.CalculaIdComun()); //calcula si todas las Ids tienen el primer carácter igual
			
			System.out.println("Test del método CalculaMediaLandings: "+archive.CalculaMediaLandings()); //calcula la media de aterrizajes totales del archivo de cápsulas
			
			System.out.println("Test del método SalvarPlaneta: "+archive.SalvarPlaneta()); //devuelve un map que asocia el serial con la cápsula, para aquellas que han sido reutilizadas, al menos 4 veces
			
			System.out.println("Test del método ordenFecha: "+archive.ordenFecha()); //devuelve un Stream con las cápsulas ordenadas por fecha
			
			System.out.println("Test del método minimotierra: "+archive.minimotierra(LocalDate.parse("01/01/2010",DateTimeFormatter.ofPattern("dd/MM/yyyy")))); //calcula el menor número de aterrizajes en tierra de 
																															  //las cápsulas cuyo lanzamiento fue previo a la fecha dada como parámetro
		
			System.out.println("Test del método AgruparStatus: "+archive.AgruparStatus()); //devuelve un map que asocia los Status con las cápsulas que se encuentran en dicho estado
			
			System.out.println("Test del método calcularCapsulaMayorAterrizajes: "+archive.calcularCapsulaMayorAterrizajes()); //retorna la cápsula que ha tenido mayor número de aterrizajes totales
			
			System.out.println("Test del método lost: "+archive.lost()); //devuelve un map que asocia las Id's de las cápsulas con las mismas, para las que cuyo estado es "unknown"
			
			System.out.println("Test del método waterfriendly: "+archive.waterfriendly()); //devuelve un map que asocia los códigos seriales de las cápsulas con aquellas que han aterrizado 2 o más veces en masas de agua
		}
		catch(Exception e) {
			System.out.println("Excepción capturada:" + e);
		}	
	}
	
}
