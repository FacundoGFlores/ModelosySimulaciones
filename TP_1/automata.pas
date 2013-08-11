program automata;

uses crt;

type
	estados = 0..6;
	moneda = 0..1;
	jugador = 5..6;
	Vector = array[0..10000] of Word;

const
  matriz_estados : array[0..1,0..6] of estados =
                   ((1,2,2,4,6,0,0),(3,3,5,3,3,0,0));

Function siguiente_estado( e:estados; m: moneda): estados;
begin
   siguiente_estado:= matriz_estados[m,e];
end;

Function juego(var cnt_monedas: Word): estados;
var
	e: estados;
	m: moneda;
	cnt: Word;
begin
	e := 0;
	cnt := 0;
   	while ( (e<>5) and (e<>6) ) do
   	Begin
    	m:= Trunc(Random(2));
		inc(cnt);
    	e:= siguiente_estado(e,m);
   	end;
	cnt_monedas := cnt;
   	juego:= e;
end;

Procedure Muestra_Resultados(cnt1, cnt2: Word);
begin
	writeln('PLAYER 1 GANA: ', cnt1);
	writeln('PLAYER 2 GANA: ', cnt2);
end;

Procedure Probabilidad(cnt1, cnt2: Word);
begin
	writeln('Probabilidad PLAYER 1: ', cnt1 / 10000:10:4);
	writeln('Probabilidad PLAYER 2: ', cnt2 / 10000:10:4);
end;

Function Esperanza(var V: Vector): Real;
var
	i : Word;
	sum : Word;
begin
	sum := 0;
	for i := 0 to V[0] do
		inc(sum, V[i]);
	Esperanza := sum / V[0];	
end;

Procedure Muestra_Esperanza(Esp1, Esp2: Real);
begin
	writeln('Esperanza jugador 1: ', Esp1:10:4);
	writeln('Esperanza jugador 2: ', Esp2:10:4);
end;

Procedure Simulacion();
var
	i, cnt1, cnt2 : Word;
	j : jugador;
	Vector1, Vector2 : Vector;
	m,n : Word; {Indices para vectores}
	num_monedas : Word;
	E1, E2: Real; {Esperanzas}
begin
	cnt1 := 0;
	cnt2 := 0;
	m := 1;
	n := 1;
	for i := 1 to 10000 do
	begin
		j := juego(num_monedas);
		if j = 5 then
		begin
			inc(cnt1);
			Vector1[m] := num_monedas;
			inc(m);			
		end
		else
		begin
			inc(cnt2);
			Vector2[n] := num_monedas;
			inc(n);
		end;
	end;
	(*Actualizo tamaño de vectores*)
	Vector1[0] := m;
	Vector2[0] := n;
	E1 := Esperanza(Vector1);
	E2 := Esperanza(Vector2);
	Muestra_Esperanza(E1, E2);
	Muestra_Resultados(cnt1, cnt2);
	Probabilidad(cnt1, cnt2);
end;

BEGIN
	Randomize;
	Simulacion;
	Readln;{Esto simula una pausa}
END.
