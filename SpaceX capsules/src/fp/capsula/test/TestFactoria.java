package fp.capsula.test;

import fp.capsula.Archivo;
import fp.capsula.FactoriaCapsula;

public class TestFactoria {

	public static void main(String[] args) {
		
		try {
			Archivo a1 = FactoriaCapsula.leerCapsulas("data/capsules2.0.csv");
			System.out.println(a1);
		}
		catch(Exception e) {
			System.out.println("Excepción capturada:\n" + e);
		}		
	
	}

}
