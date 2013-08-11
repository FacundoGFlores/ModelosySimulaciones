program tp0_ejer2;

uses crt;

const
        N = 100;

var
        gana1, gana2, tirosPartida, tirosTotales: word;
        estadoActual: string[4];
        i : byte;
        moneda: real;


function nuevoEstado(var estadoActual:string ; c:char):string;
begin
        estadoActual := estadoActual+c;
        estadoActual := copy(estadoActual, length(estadoActual)-2,3);
        nuevoEstado := estadoActual;
end;


begin
        clrscr;
        randomize;
        gana1 := 0;
        gana2 := 0;
        tirosTotales := 0;
        for i:=1 to N do
        begin

                estadoActual := 'AAA';
                tirosPartida := 0;

                while (estadoActual <> 'XCX') and (estadoActual <> 'XXC') do
                begin

                        tirosPartida := tirosPartida + 1;
                        moneda := random();
                        if moneda > 0.5
                        then
                                estadoActual := nuevoEstado(estadoActual, 'C')
                        else
                                estadoActual := nuevoEstado(estadoActual, 'X');
                end;

                tirosTotales := tirosTotales + tirosPartida;
                if estadoActual = 'XCX'
                then
                begin
                        gana1 := gana1 + 1;
                end
                else
                begin
                        gana2 := gana2 + 1;
                end;

        end;

        writeln('Frecuencia gana 1 :', gana1);
        writeln('Frecuencia gana 2 :', gana2);
        readln;

end.