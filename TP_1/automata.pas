program automata;

uses crt;

type
   estados = 0..6;
   moneda = 0..1;
   jugador = 5..6;
const
  matriz_estados : array[0..1,0..6] of estados =
                   ((1,2,2,4,6,0,0),(3,3,5,3,3,0,0));

Function siguiente_estado( e:estados; m: moneda): estados;
begin
   siguiente_estado:= matriz_estados[m,e];
end;

Function juego(): estados;
var
   e: estados;
   m: moneda;
begin
   e:= 0;
   while ( (e<>5) and (e<>6) ) do
   Begin
     m:= Trunc(Random(2));
     e:= siguiente_estado(e,m);
   end;
   juego:= e;
end;

var
	j : jugador;
BEGIN
   Randomize;
   j := juego;
   if j = 5 then
       writeln('Gana jugador numero 1')
   else
	   writeln('Gana jugador numero 2');
   readln();
END.