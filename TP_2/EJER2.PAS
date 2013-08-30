program ejercicio2;
uses simulacion, distribuciones, estr_dat, monitor, crt;

const
     Tsim = 10000;
     lambda = 10.0;
     mu = 11.5;

type
  PDirector = ^TDirector;
  PCliente = ^TCliente;

  TCliente = object(TObjActivo)
    horaLlegada:real;    { hora de llegada al sistema }
    horaInicioServ:real; { hora de inicio de servicio (cada uno de los fragmentos) }
    tiempoRestServ: real;   { tiempo de servio restante }
    procedure CicloDeVida; virtual;
  End;

  TDirector = object(TObjActivo)
    procedure CicloDeVida; virtual;
  End;

var
  cola:TColaLIFO;   { Cola LIFO del sistema }
  ts: TPromedio;
  tserv: TPromedio;
procedure TCliente.CicloDeVida;
var
   tiempoServicio:real;
begin
  esperar(dExp(lambda));
  nuevo(new(PCliente, iniciar));
  horaLlegada:=horaActual;

  if not(cola.vacia) then  activar(cola.primero);  { interrumpir }
  cola.poner(yo);
  tiempoRestServ:= dExp(mu);
  tiempoServicio:=tiempoRestServ;
  horaInicioServ:=horaActual;
  while not(esperar(tiempoRestServ)) do
    begin
      tiempoRestServ:=tiempoRestServ-(horaActual-horaInicioServ);
      suspenderse;
      horaInicioServ:=horaActual;
    end;
  cola.sacar;
  if not(cola.vacia) then activar(cola.primero);  { reanimar al interrumpido }

  ts.acumular(horaActual - horaLlegada);
  tserv.acumular(tiempoServicio);
  borrarse;
end;

procedure TDirector.cicloDeVida;
begin
  ClrScr;
  Writeln('Simulando ...');
  Writeln;

  randomize;

  cola.inic;
  ts.iniciar('Tiempo promedio en el sistema: ');
  tserv.iniciar('Tiempo promedio de servicio: ');
  nuevo(new(PCliente, iniciar));

  esperar(Tsim);

  ts.mostrarEstd;
  tserv.mostrarEstd;
  readln;
  borrarse;
end;

begin
  iniciarSimulacion(new(PDirector, iniciar));
end.
