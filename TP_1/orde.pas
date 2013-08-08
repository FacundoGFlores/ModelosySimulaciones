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
    N = 10;

(* ======================================================
   Tipos de datos
   ======================================================
*)
type
    Data_Type = Word;
    Vector = array [0..N] of Data_Type;

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

procedure Sort_Vector(var V: Vector; N : Data_Type);
var
    i,j : Data_Type; {Index}
begin
    for i := 2 to N do 
    begin
        V[0] := V[i];
        j := i - 1;
        while V[0] < V[j] do
        begin
            V[j+1] := V[j];
            dec(j);
        end;
        V[j+1] := V[0]
    end;
end;
(* ====================================================================================== *)

(* ==============================================
    Variables a utilizar en el programa principal
   ============================================== *)

var
    MyVector : Vector;

BEGIN
    Randomize;
    Fill_Rand_Vector(MyVector, N); {Llenamos el vector con números aleatorios}
    Show_Vector(MyVector, N); {Mostrarmos el vector generado}
    Sort_Vector(MyVector, N); {Ordenamos el vector}
    Show_Vector(MyVector, N); {Mostrar el vector ordenado}
END.
