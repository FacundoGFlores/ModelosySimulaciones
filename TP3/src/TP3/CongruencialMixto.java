package TP3;

import java.util.Scanner;
import java.util.Vector;
import java.lang.Math;;
 
public class CongruencialMixto {
 
        private static Scanner scanner;
       
        /** Funcion principal donde se solicitan los parametros
         * @param args Argumentos para el uso dentro de la funcion main
         */
        public static void main(String[] args) {
 
                System.out.print("Ingrese el parametro c: ");
                long c = ingresanum(); //Ingresa el primer numero
                System.out.print("Ingrese el parametro m:");
                long m = ingresanum(); //Ingresa e1 segundo numero
                System.out.print("ingrese parametro a: ");
                long a = ingresanum();
                int semilla=0;
                for (Long i : congruencialMixto(c,m,a,semilla))
                        System.out.println(i);
               
        }
        
        /** Ejecutamos el algoritmo congruencialMixto con sus respectivos parametros
         * @param c Constante aditiva
         * @param m Modulo
         * @param a Factor multiplicativo
         * @param semilla X_0
         * @return Una lista con los valores generados
         */
        private static Vector<Long> congruencialMixto(long c,long m, long a, long semilla) {
                if(valida (c,m,a)) {
                        Vector<Long> lista = new Vector<Long>();
                        lista.addElement((long) 0);
                        for( int i=1; ; i++){
                                lista.addElement((a*lista.elementAt(i-1)+c)%m);
                                if( lista.firstElement() == lista.lastElement() ) return lista;
                        }
                }
                else System.out.println("No cumple con alguna de las condiciones");
                return null;
 
        }
 
        /**
         * Validamos los parametros segun condicion de periodo completo
         * @param c Constante aditiva
         * @param m Modulo
         * @param a Factor multiplicativo
         * @return Verdadero si se validaron los parametros
         */
        private static boolean valida(long c, long m, long a) {
 
                long mcd = divisor(c,m);
                if (mcd != 1) return false;
                if(!condicion2(m,a)) return false;
                if (!condicion3(m,a-1)) return false;
                return true;
 
        }
 
        /**
         * La condicion numero dos exige que cada primo que divide a m divide a a-1
         * @param m Modulo
         * @param a Factor multiplicativo
         * @return Verdadero si se valida la segunda condicion
         */
        private static boolean condicion2(long m, long a) {
                Vector<Long> L = new Vector<Long>();
                L = findFactors(m); //Forma la lista de divisores primos de m
                for (Long i: L)
                        if((a-1) % i.longValue() != 0) return false;
                return true;      
        }
 
       
        /**
         * La tercera condicion exige que m y a-1 sean multiplos de 4
         * @param m Factor multiplicativo
         * @param a_1 a-1
         * @return Verdadero si se cumple la tercera condicion
         */
        private static boolean condicion3(long m, long a_1) {
                if( m % 4 == 0 && a_1 % 4 == 0 )return true;
                return false;
        }
 
        /**
         * Scanner para el ingreso de numeros
         * @return un valor entero
         */
        private static long ingresanum() {
                scanner = new Scanner(System.in);
                long Numero = scanner.nextInt();
                return Numero;
        }
 
        /**
         * Algoritmo de MCD Euclides
         * @param a Un entero
         * @param b Un entero
         * @return MCD entre a y b
         */
        private static long divisor(long a, long b) {
                if (b == 0)
                        return a;
                return divisor(b,a % b);
        }
       
        /**
         * Buscamos los factores primos de un cierto numero entero
         * @param num El numero
         * @return Lista de factores primos
         */
        private static Vector<Long> findFactors(long num)
        {
                Vector<Long> list = new Vector<Long>();
                for (long i = 1; i <= num / 2; i++)
                {
                        if (num % i == 0)
                        {
                                if(isPrime(i)) list.addElement(i);
                        }
                }
                if(isPrime(num)) list.addElement(num);
                return list;
        }
 
        /**
         * Checkeamos que un numero sea primo
         * @param number Numero a verificar
         * @return Verdadero si el numero es primo
         */
        public static boolean isPrime(long number) {  
                long root = (long)Math.sqrt(number);  
                for(int i = 2; i <= root; i++) {  
                        if(number%i == 0) {  
                                return false;  
                        }  
                }  
                return true;  
        }  
 
 
}

