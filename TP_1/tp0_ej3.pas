Program ejercicio3;

uses
	crt;

const
	MAX_JUEGOS = 1000;

type
	Plata = LongInt;
	Contador = Word;
	Diferencia = Integer;

Procedure Juego(var X, C : Contador; var A, M: Plata);
var
	d: Diferencia; {Diferencia entre X y C }
	moneda : Real;
begin
	d := X - C;
	while d <> 3 do
	begin
		inc(A, 100);{Antonio suma 100 por cada tirada}
		moneda := random;
		if moneda < 0.5 then
			inc(X)
		else
			inc(C);
		d := X - C;
	end;
	inc(M, 1000);{Manolo suma 1000 por cada diferencia de 3 cruces}
end;

Procedure Mostrar_Resultados(cnt_ant, cnt_man : Contador);

begin
	writeln('Manolo gano: ', cnt_man, ' juegos');
	writeln('Antonio gano: ', cnt_ant, ' juegos');
end;

Procedure Simulacion();
var
    cruces, caras: Contador; {Cuenta cantidad de caras y cruces}
    antonio, manolo: Plata; {Cuenta cantidad de plata en simulacion}
    cuenta_ant, cuenta_man: Contador; {Cuenta cantidad de ganadas para c/jug.}
	i : Word; {Indice}

begin
	cuenta_ant := 0; cuenta_man := 0;

	for i := 0 to MAX_JUEGOS do
	begin
		cruces := 0; caras := 0;
		antonio := 0; manolo := 0;
		Juego(cruces, caras, antonio, manolo);
		if antonio > manolo then
			inc(cuenta_ant)
		else
			inc(cuenta_man);
	end;		
	Mostrar_Resultados(cuenta_ant, cuenta_man);
end;

BEGIN
	clrscr;
	Randomize;	
	Simulacion;
	readln;
END.
