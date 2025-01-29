package fp.utiles;

public class Checkers {
	public static void check(String texto, Boolean condicion) {
		if (!condicion) {
			throw new IllegalArgumentException(texto);
			
		}
	}
}
