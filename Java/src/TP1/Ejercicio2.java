package TP1;

public class Ejercicio2 {
	
	private final int[][] automataUnTercio = {{1,3},{2,0},{2,2},{3,3}};
	private final int[][] automataUnCuarto = {{1,3},{2,3},{2,2},{3,3}};
	
	
	
	private int cola = 0, tOcioso = 0, tTotal = 0, tEspera = 0, clientes = 0;
	
	private static String cadena = "CXXXCCXCCCCCCXCXCCXXCXXCCXXCXCCCCXCXXXXCXXCCCXXCXCCXXXXCXCXC";
	
	private float T = 0.0f;//Tiempo de simulacion en minutos
	
	
	/*
	 * Obtiene el siguiente estado resultante del automta 1/4
	 */
	public int nuevoEstado1(int estadoActual, String c){
		
		if(c.equals("C")){
			return automataUnCuarto[estadoActual][0];
		}else{
			return automataUnCuarto[estadoActual][1];
		}
		
	}
	
	/*
	 * Obtiene el siguiente estado resultante del automta 1/3
	 */
	public int nuevoEstado2(int estadoActual, String c){
		
		if(c.equals("C")){
			return automataUnTercio[estadoActual][0];
		}else{
			return automataUnTercio[estadoActual][1];
		}
		
	}
	
	
	public void simulacion(){
		
		int estadoActual;
		while(cadena.length() > 0){
			T++;
			if(cola > 0){
				estadoActual = 0;
				//decido si le doy un minuto más de servicio
				while(estadoActual != 2 && estadoActual != 3 && cadena.length() > 0){
					estadoActual = nuevoEstado2(estadoActual, cadena.substring(0, 1));
					cadena = cadena.substring(1, cadena.length());			
				}
				if(estadoActual != 3){
					cola--;
				}
			}
			estadoActual = 0;
			//decido la llegada de un nuevo cliente
			while(estadoActual != 2 && estadoActual != 3 && cadena.length() > 0){
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
