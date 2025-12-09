package Logica;

public class Sistema {
	private static Sistema instancia;
	
	private Sistema() {
		
	}
	
	public static Sistema getInstancia() {
		if(instancia == null) {
			instancia =  new Sistema();
		}
		return instancia;
	}
	
}
