program ejercicio1;
uses
    simulacion, distribuciones, semaforos, monitor, crt ;

const
     Tsim = 10000;
     lambda = 10.0;
     mu = 11.5;

type
    PDirector = ^TDirector;
    PCliente = ^TCliente;

    TCliente = object(TObjActivo)
             horaLlegada: real;
             procedure CicloDeVida; virtual;
    end;

    TDirector = object(TObjActivo)
              procedure CicloDeVida; virtual;
    end;

var
   cajero:TSemaforo;{El cajero se declara como semaforo}
   ts:TPromedio;{declaro un observador que tomara la estadistica de ts}
   tserv:TPromedio;

procedure TCliente.CicloDeVida;
var
   tiempoServicio: real;
begin
     esperar(dExp(lambda));
     nuevo(new(PCliente, iniciar));

     horaLlegada:=horaActual;
     cajero.esperar(yo);
     tiempoServicio:=dExp(mu);
     esperar(tiempoServicio);
     cajero.continuar;

     ts.acumular(horaActual - horaLlegada);
     tserv.acumular(tiempoServicio);
     borrarse;
end;

procedure TDirector.CicloDeVida;
begin
     clrscr;
     writeln('simulando...');
     writeln;

     randomize;

     cajero.inic;
     ts.iniciar('Tiempo Promedio en el sistema: ');
     tserv.iniciar('Tiempo Promedio de servicio: ');
     nuevo(new(PCliente, iniciar));
     esperar(Tsim);
     ts.mostrarEstd;
     tserv.mostrarEstd;
     readln;
     borrarse;
end;


begin
     IniciarSimulacion(new(PDirector, iniciar));
end.



