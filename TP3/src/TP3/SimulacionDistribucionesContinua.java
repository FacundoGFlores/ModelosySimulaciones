package TP3;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class SimulacionDistribucionesContinua {

	//Variables.
	private File archivo;
	private Scanner escaner = new Scanner(System.in);
	private int seleccion;

	//Constructor
	public SimulacionDistribucionesContinua(){
		seleccionSimulacion();
		CrearArchivo();
		escribirArchivo();
	}
	
	/*
	 * Selecciona el método a aplicar.
	 */
	public void seleccionSimulacion(){
		System.out.println("Seleccione una distribucion para generar el archivo.txt");
		System.out.println("1 -> distribucion exponencial metodo del inverso");
		System.out.println("2 -> distribucion exponencial metodo de Von Neuman");
		System.out.println("3 -> distribucion Normal metodo TCL");
		System.out.println("4 -> distribucion Normal metodo del rechazo");
		System.out.println("5 -> distribucion Normal metodo polar");
		System.out.println("6 -> distribucion k-Erlang metodo de convolucion");
		System.out.println("7 -> distribucion weibull metodo del inverso");
		System.out.println("8 -> distribucion Gamma");
		seleccion = escaner.nextInt();
		
	}
	
	
	/*
	 * Crear un archivo de texto.
	 */
	public void CrearArchivo(){
		String path = "Archivos/";
		switch(seleccion){
		case 1:
			path += "dExpInverso.txt";
			break;
		case 2:
			path += "VNdExp.txt";
			break;
		case 3:
			path += "TCLNormal.txt";
			break;
		case 4:
			path += "rechazoNormal.txt";
			break;
		case 5:
			path += "polarNormal.txt";
			break;
		case 6:
			path += "k-Erlang.txt";
			break;
		case 7:
			path += "weibull.txt";
			break;
		case 8:
			path += "Gamma.txt";
			break;
		default:
			path += "error.txt";
			break;
		}
		try{
			archivo = new File(path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Escribir sobre el archivo de texto 1000 valores para la distribucion seleccionada.
	 */
	public void escribirArchivo(){
		DecimalFormat formateador = new DecimalFormat("#.####");//Para escribir los numeros con 4 cifras decimales.
		try{
			FileWriter fw = new FileWriter(archivo);
			PrintWriter pw = new PrintWriter(fw);
			float lambda, mu, sigma, beta, alfa;
			int k;
			switch(seleccion){
			case 1:
				System.out.println("Ingrese lambda");
				lambda = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(dExp(lambda)));
				}
				break;
			case 2:
				System.out.println("Ingrese lambda");
				lambda = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(VNdExp(lambda)));
				}
				break;
			case 3:
				System.out.println("Ingrese mu");
				mu = escaner.nextFloat();
				System.out.println("Ingrese sigma");
				sigma = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(TCLNormal(mu,sigma)));
				}
				break;
			case 4:
				System.out.println("Ingrese mu");
				mu = escaner.nextFloat();
				System.out.println("Ingrese sigma");
				sigma = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(rechazoNormal(mu,sigma,-3,3)));
				}
				break;
			case 5:
				System.out.println("Ingrese mu");
				mu = escaner.nextFloat();
				System.out.println("Ingrese sigma");
				sigma = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(polarNormal(mu,sigma)));
				}
				break;
			case 6:
				System.out.println("Ingrese K");
				k = escaner.nextInt();
				System.out.println("Ingrese beta");
				beta = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(Erlang(k,beta)));
				}
				break;
			case 7:
				System.out.println("Ingrese alfa");
				alfa = escaner.nextFloat();
				System.out.println("Ingrese beta");
				beta = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(weibull(alfa,beta)));
				}
				break;
			case 8:
				System.out.println("Ingrese alfa natural");
				alfa = escaner.nextFloat();
				System.out.println("Ingrese beta");
				beta = escaner.nextFloat();
				for(int i = 0;i < 10000;i++){
					pw.println(i+"\t"+formateador.format(Gamma((int) alfa,beta)));
				}
				break;
			default:
				System.out.println("Ingreso salir");
				break;
			}
			System.out.println("Se genero el archivo");
			fw.close();
			pw.close();
		}catch(Exception e){
			e.printStackTrace();
        } 
	}//fin metodo escribirArchivo
	
	
	/*
	 * Distribucion exponencial metodo del inverso.
	 */
	public float dExp(float lambda){
		return (float)(-Math.log(Math.random())/lambda);
	}
	
	
	/*
	 * distribucion exponencial metodo de Von Neuman
	 */
	public float VNdExp(float lambda){
		int n;
		int k = 0;
		ArrayList<Float> sucesion;
		do{
			sucesion = generarSucesion();
			n = sucesion.size();
			k++;
		}while(!esPar(n));
		return (sucesion.get(0) + (k-1)) / lambda;
	}//fin metodo 
	
	
	public boolean esPar(int n){
		if((n % 2) == 0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/*
	 * Genera la sucesion decreciente de numeros random, para el metodo
	 * de Von Neuman.
	 */
	public ArrayList<Float> generarSucesion(){
		ArrayList<Float> sucesion = new ArrayList<Float>();
		float u = (float) Math.random();
		float v = (float) Math.random();
		sucesion.add(u);
		sucesion.add(v);
		while(u > v){
			u = v;
			v = (float) Math.random();
			sucesion.add(v);
		}
		return sucesion;
	}//fin metodo
	
	
	/*
	 * Genera numeros que distribuyen Normal(mu,sigma) con el metodo TCL.
	 */
	public float TCLNormal(float mu, float sigma){
		float t = 0;
		for(int i = 0;i < 12;i++){
			t += Math.random();//distribuye aprox. N(6,1)
		}
		t = (t - 6); //distribuye aprox. N(0,1)
		t = t * sigma + mu;//distribuye N(mu,sigma)
		return t;
	}
	
	/*
	 * Genera numeros que distribuyen Normal(mu,sigma) con el metodo polar.
	 */
	public float polarNormal(float mu, float sigma){
		float u = (float) Math.random();//numero aleatorio uniforme
		float v = (float) Math.random();//numero aleatorio uniforme
		float r = (float) Math.sqrt(-Math.log(u)/0.5);
		float theta = (float) (2 * Math.PI * v);
		theta = (float) ((theta * 180)/Math.PI);
		float x = (float) (r * Math.sin(theta));//distribuye N(0,1)
		x = x * sigma + mu;//distribuye N(mu,sigma)
		return x;
	}
	
	/*
	 * Genera numeros que distribuyen Normal(mu,sigma) con el metodo del rechazo,
	 * en el intervalo (a,b) frecuentemente (-3,3)
	 */
	public float rechazoNormal(float mu, float sigma, float a, float b){
		float c = funcionDensidadNormal(mu, mu, sigma);
		float x, y;
		do{
			x = (float) (a + (b-a) * Math.random());
			y = (float) (c * Math.random());
		}while(y > funcionDensidadNormal(x, mu, sigma));
		return x;
	}
	
	
	public float funcionDensidadNormal(float x, float mu, float sigma){
		return (float) (Math.pow(2*Math.PI*sigma, -0.5)*Math.exp(-0.5*Math.pow((x-mu)/sigma, 2)));
	}
	
	/*
	 * Genera numeros que distribuyen k-Erlang con el metodo de convolucion
	 */
	public float Erlang(int k, float beta){
		float productoria = 0;
		for(int i = 0;i < k;i++){
			productoria = (float) (productoria * Math.random());
		}
		float E = (float) (-Math.log(productoria)/beta);
		return E;
	}
	
	/*
	 * Genera numeros que distribuyen Gamma
	 */
	public float Gamma(int alfa, float beta){
		if(alfa == 1){
			return dExp(beta);
		}else if (alfa > 1){
			return Erlang(alfa, beta);
		}else{
			return -1;
		}
	}
	
	/*
	 * Genera numeros que distribuyen weibull
	 */
	public float weibull(float alfa, float beta){
		float u = (float) Math.random();
		float x = (float) Math.pow(-Math.log(u)*Math.pow(beta, alfa), 1/alfa);
		return x;
	}
	
	
	public static void main(String args[]){
		SimulacionDistribucionesContinua sdc = new SimulacionDistribucionesContinua();
	}
	
}
