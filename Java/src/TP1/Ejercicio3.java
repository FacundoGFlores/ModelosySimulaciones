package TP1;

public class Ejercicio3 {

	private final float lambda = 0.25f, mu = 0.5f;
	
	private Estado estado = new Estado();
	
	private float Tsim;
	
	private int colaPrevia;
	
	private float horaPrevia;
	
	private int clientes;//toma de estadisticas
	
	private float Ttotal;//toma de estadisticas
	
	private float tOcioso;
	
	
	public Ejercicio3(float Tsim){
		this.Tsim = Tsim;
	}
	
	
	public void inicializar(){
		estado.hora = 0;
		estado.cola = 0;
		estado.proximaLlegada = dExp(lambda);
		estado.proximaSalida = 0;
	}
	
	
	private float dExp(float parametro){
		return  (float)(-Math.log(Math.random())/parametro);
	}
	
	
	public void avanzar(){
		
		if(estado.cola == 0){
			
			estado.hora = estado.proximaLlegada;
			estado.cola = 1;
			estado.proximaLlegada = estado.hora + dExp(lambda);
			estado.proximaSalida = estado.hora + dExp(mu);
			
		}else if (estado.proximaLlegada < estado.proximaSalida){
			
			estado.hora = estado.proximaLlegada;
			estado.cola += 1;
			estado.proximaLlegada = estado.hora + dExp(lambda);
			
		}else{
			
			estado.hora = estado.proximaSalida;
			estado.cola -= 1;
			if(estado.cola > 0){
				estado.proximaSalida = estado.hora + dExp(mu);
			}
		}
		
	}// fin avanzar.
	
	
	public void simulacion(){
		inicializar();
		clientes = 0;//toma de estadisticas
		Ttotal = 0;//toma de estadisticas
		tOcioso = 0;
		while(estado.hora < Tsim){
			colaPrevia = estado.cola;
			horaPrevia = estado.hora;
			
			if(estado.cola == 0){
				tOcioso += estado.proximaLlegada - estado.hora;//toma de estadisticas
			}
			
			avanzar();
			
			if(estado.cola > colaPrevia){
				clientes++;//toma de estadisticas
			}
			
			Ttotal += colaPrevia * (estado.hora - horaPrevia);//toma de estadisticas
		}
		imprimir();
	}// fin simulacion
	
	
	public void imprimir(){
		System.out.println("Tiempo medio en el sistema ts = "+Ttotal/clientes);
		System.out.println("Cola promedio = "+Ttotal/Tsim);
		System.out.println("Probabilidad tiempo ocioso = "+tOcioso/Tsim);
		System.out.println();
	}
	
	
	public static void main(String [] args){
		for(int i = 0;i < 10;i++){
			Ejercicio3 ej3 = new Ejercicio3(1000);
			ej3.simulacion();
		}
	}
	
	
	
	
	public class Estado{
			
		private float hora;
		private int cola;
		private float proximaLlegada;
		private float proximaSalida;
		
	}//fin clase Estado.

}


