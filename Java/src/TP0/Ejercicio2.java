package TP0;

public class Ejercicio2 {
	
	private static final int N = 10000;
	
	
	public static String getNuevoEstado(String estadoActual, String c){
		
		estadoActual = estadoActual + c;
		return estadoActual.substring(estadoActual.length()-3, estadoActual.length());
	}
	
	
	public static void main(String [] args){
		
		
		int gana1 = 0, gana2 = 0, tirosTotales = 0;
		
		for(int i = 0;i < N;i++){
			
			String estadoActual = "AAA";
			int tirosPartida = 0;
			
			while (!estadoActual.equals("XCX") && !estadoActual.equals("XXC")){
					tirosPartida++;
					if(Math.random() < 0.5){
						estadoActual = getNuevoEstado(estadoActual, "C");
					}else{
						estadoActual = getNuevoEstado(estadoActual, "X");
					}
			}
			
			tirosTotales += tirosPartida;
			if(estadoActual.equals("XCX")){
				gana1++;
				//System.out.println("Ganador 1 :"+estadoActual+" en: "+tirosPartida+" tiros");
			}else{
				gana2++;
				//System.out.println("Ganador 2 :"+estadoActual+" en: "+tirosPartida+" tiros");
			}
			
		}
		
		System.out.println();
		System.out.println("Frecuencia gana jugador 1 :"+gana1/100.0);
		System.out.println("Frecuencia gana jugador 2 :"+gana2/100.0);
		System.out.println("Valor esperado de cantidad de tiros :" + tirosTotales/10000.0);
		
	}
	
	
	
}
