package TP1;

public class Ejercicio2 {
	
	
	private final int[][] matrizDeEstados1 = {{1,3}, {4,2}, {2,3}, {3,3}, {4,4}};
	private final int[][] matrizDeEstados2 = {{1,2}, {3,0}, {2,2}, {3,3}};
	
	private int cola = 0, tOcioso = 0, tTotal = 0, tEspera = 0, clientes = 0;
	
	private static String cadena = "CXXXCCXCCCCCCXCXCCXXCXXCCXXCXCCCCXCXXXXCXXCCCXXCXCCXXXXCXCXC";
	
	private float T = 0.0f;//Tiempo de simulacion en minutos
	
	
	
	public int nuevoEstado1(int estadoActual, String c){
		
		if(c.equals("C")){
			return matrizDeEstados1[estadoActual][0];
		}else{
			return matrizDeEstados1[estadoActual][1];
		}
		
	}
	
	
	public int nuevoEstado2(int estadoActual, String c){
		
		if(c.equals("C")){
			return matrizDeEstados2[estadoActual][0];
		}else{
			return matrizDeEstados2[estadoActual][1];
		}
		
	}
	
	
	public void simulacion(){
		
		int estadoActual;
		while(cadena.length() > 0){
			T++;
			if(cola > 0){
				estadoActual = 0;
				while(estadoActual != 2 && estadoActual != 3 && cadena.length() > 0){
					estadoActual = nuevoEstado2(estadoActual, cadena.substring(0, 1));
					cadena = cadena.substring(1, cadena.length());			
				}
				if(estadoActual != 2){
					cola--;
				}
			}
			estadoActual = 0;
			while(estadoActual != 3 && estadoActual != 4 && cadena.length() > 0){
				estadoActual = nuevoEstado1(estadoActual, cadena.substring(0, 1));
				cadena = cadena.substring(1, cadena.length());			
			}
			if(estadoActual != 3){
				cola++;
				clientes++;
			}
			tTotal += cola;
			if(cola == 0){
				tOcioso += 1;
			}else{
				tEspera += cola - 1;
			}
			
		}//fin while
		
	}
	
	
	public void imprimir(){
		
		System.out.println("Tiempo promedio en el sistema ts = "+(float)tTotal/clientes);
		System.out.println("Cola promedio = "+tTotal/T);
		System.out.println("Tiempo de simulacion = "+T);
		
	}

	public static void main(String [] args){
		
		Ejercicio2 ej2 = new Ejercicio2();
		ej2.simulacion();
		ej2.imprimir();
		
	}
}
