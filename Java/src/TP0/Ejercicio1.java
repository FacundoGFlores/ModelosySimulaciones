package TP0;

import java.util.Random;

public class Ejercicio1 {
	
	private int n;
	private int[] listNum;
	private int cantidadComparaciones = 0;
	
	
	public Ejercicio1(int n){
		this.n = n;
		listNum = new int[n+1];
	}
	
	
	public static double formulaRecurrencia(int n){	
		if(n == 1){
			return 0;
		}else{
			return formulaRecurrencia(n-1)+((n+1)/2.0)-(1.0/n);
		}
		
	}
	
	
	public void generarListaAleatoria(){
		
		Random r = new Random();
		for(int i = 1;i < n+1;i++){
			listNum[i] = r.nextInt(100);
		}
		
	}
	
	
	public void mostrarLista(){
	
		System.out.print("[");
		for(int i = 1;i < n;i++){
			System.out.print(listNum[i]+",");
		}
		System.out.print(listNum[n]+"]");
	}
	
	
	public void baraja(){
		
		int j = 0;
		for(int i = 2;i < n+1;i++){
			listNum[0] = listNum[i];
			j = i - 1;
			while(listNum[0] < listNum[j]){
				cantidadComparaciones++;
				listNum[j+1] = listNum[j];
				j--;
			}
			cantidadComparaciones++;
			
			listNum[j+1] = listNum[0];
		}
		
	}
	
	
	public static void main(String [] args){
		int cantidadComparacionesTotal = 0;
		
		for(int i = 0;i < 1000;i++){
			Ejercicio1 ej1 = new Ejercicio1(100);
			ej1.generarListaAleatoria();
			ej1.baraja();
			cantidadComparacionesTotal += ej1.cantidadComparaciones;
			System.out.println("Cantidad de comparaciones :"+ej1.cantidadComparaciones);
			//System.out.println("valor de la formula :"+ej1.formulaRecurrencia(100));
			System.out.println();
			System.out.println();
		}
		System.out.println("Valor esperado de comparaciones :"+cantidadComparacionesTotal/1000.0);
		System.out.println("valor de la formula :"+formulaRecurrencia(100));
	}
	
	
	
	
	
	
}
