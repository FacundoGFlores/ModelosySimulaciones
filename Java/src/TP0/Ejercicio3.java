package TP0;

public class Ejercicio3 {
	
	private int N = 100;
	
	private int caras, cruces;
	
	private int plataAntonio, plataManolo;
	
	private int ganaAntonio;

	private int ganaManolo;
	
	
	private void juego(){
		
		caras = 0;
		cruces = 0;
		plataAntonio = 0;
		plataManolo = 0;
		
		int diferencia = cruces - caras;
		while(diferencia != 3){
			plataAntonio += 100;//Incrementa 100$ por cada tirada.
			if(Math.random() < 0.5){
				caras++;
			}else{
				cruces++;
			}
			diferencia = cruces - caras;
		}
		plataManolo += 1000;
		System.out.println("Cantidad de cruces "+cruces);
		System.out.println("Cantidad de caras "+caras);
		System.out.println("Plata de Antonio "+plataAntonio);
		System.out.println("Plata de Manolo "+plataManolo);
		System.out.println();
	}
	
	
	public void simulacion(){
		
		ganaAntonio = 0;
		ganaManolo = 0;
		
		for(int i = 0;i < N;i++){
			juego();
			if(plataAntonio > plataManolo){
				ganaAntonio++;
			}else{
				ganaManolo++;
			}
		}
		
		System.out.println("Manolo gano "+ganaManolo+" veces");
		System.out.println("Antonio gano "+ganaAntonio+" veces");
	}
	
	
	public static void main(String [] args){
		
		Ejercicio3 ej3 = new Ejercicio3();
		ej3.simulacion();
		
	}



	
}
