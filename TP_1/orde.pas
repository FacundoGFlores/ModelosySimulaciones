program ordenamiento;

(* 
    ======================================================
    Descripcion: el programa ordena una lista de elementos
                 con enteros entre 0 y 32767 que son gene-
                 rados aleatoriamente
    Aviso: a pesar que el tipo de datos se encuentra ex-
           plicitamente declarado como [0..N], el cero
           es sólo por cuestión de implementación algo-
           rítmica
    ======================================================
*)

uses
    crt;

const
    MAX = 10000;

(* ======================================================
   Tipos de datos
   ======================================================
*)
type
    Data_Type = Word;
    Vector = array [0..MAX] of Data_Type;

(* 
    =====================================================================================
    Declaracion de funciones y procedimientos
    =====================================================================================
*)
procedure Show_Vector(var V: Vector; N : Data_Type);
var
    i : Data_Type; {Index}

begin
    for i := 1 to N do
        write(V[i], ' ');
    writeln;
end;

procedure Fill_Rand_Vector(var V: Vector; N : Data_Type);
var
    i : Data_Type; {Index}

begin
    for i := 1 to N do
        V[i] := trunc(Random(32767));
end;

(*
	Descripcion: Ordena un vector con el metodo de la baraja
				 y devuelve el numero total de comparaciones
				 realizadas
	Vector: Un vector de tipo Vector
	N: El numero de elementos en el vector
	Return: La cantidad de comparaciones realizadas
*)
function Sort_Vector(var V: Vector; N : Data_Type): Word;
var
    i,j : Data_Type; {Index}
	cnt : word; {Index counter}
begin
	cnt := 0;
    for i := 2 to N do 
    begin
        V[0] := V[i];
        j := i - 1;
        while V[0] < V[j] do
        begin
            V[j+1] := V[j];
            dec(j);
			inc(cnt);
        end;
		inc(cnt); {We need at least one comparison}
        V[j+1] := V[0]
    end;
	Sort_Vector := cnt;
end;

(*
	Descripcion: Realizo una simulacion de N elementos
				 y devuelvo la cantidad de comparaciones
*)
function Simu_N(N: Word): Word;
var
	v : Vector;
begin
	Fill_Rand_Vector(v, N);
	Simu_N := Sort_Vector(v, N);
end;

(*
	Descripcion: Realizo la simulacion para 10, 100, 1000, 10000
				 elementos y muestro la cantidad de comparacion-
				 es efectuadas en cada simulacion
*)

procedure Simulacion();
var
	cmp10, cmp100, cmp1000, cmp10000 : Word;
begin
	cmp10 := Simu_N(10);
	cmp100 := Simu_N(100);
	cmp1000 := Simu_N(1000);
	cmp10000 := Simu_N(10000);

	writeln('Cantidad de comparaciones realizadas');
	writeln('10 Elementos: ', cmp10);
	writeln('100 Elementos: ', cmp100);
	writeln('1000 Elementos: ', cmp1000);
	writeln('10000 Elementos: ', cmp10000);
end;
(* ====================================================================================== *)

(* ==============================================
    Variables a utilizar en el programa principal
   ============================================== *)

BEGIN
    Randomize;
	Simulacion();
END.
