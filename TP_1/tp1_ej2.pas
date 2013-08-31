program tp1_ej2;

uses
	crt;


const
	auto_third : array[0..3, 0..1] of integer = ((1,2), (3,0), (2,2), (3,3));

function Coin(number : integer) : char;
begin
	if number <= 0.5 then
		Coin := 'C'
	else
		Coin := 'X';
end;

function binToCoin(c : string) : string;
begin
	if c = '0' then
		binToCoin := 'C'
	else
		binToCoin := 'X';
end;

Function IntToStr (I : Longint) : String;

Var S : String;

begin
 Str (I,S);
 IntToStr:=S;
end;

function numtoBinCad(number : real) : string;
var
	binNumber : string;
	parte_entera : integer;
	iters : integer;

begin
	iters := 0;
	binNumber := '0.';
	while ((number <> 0.0) and (iters < 25)) do
	begin
		inc(iters);
		number := number * 2;
		parte_entera := trunc(number);
		number := number - parte_entera; 
		binNumber := concat(binNumber, IntToStr(parte_entera));
	end;
	numtoBinCad := binNumber;
end;

function makeQueue(strBinNumber : string) : string;
var
	auxNumber : string;
	tmp : string;
	index : integer;
	c : string;
begin
	//Take zeros and ones
	tmp := '';
	auxNumber := copy(strBinNumber, 3, Length(strBinNumber) - 1);

	for index := 1 to Length(auxNumber) do
	begin
		c := auxNumber[index];
		c := binToCoin(c);
		tmp := concat(tmp, c);
	end;

	makeQueue := tmp;
end;

function takeState(state : integer; coin : char) : integer;
var
	next : integer;
begin
	if coin = 'C' then
		next := auto_third[state, 0]
	else
		next := auto_third[state, 1];
	takeState := next;
end;

function validate_third(sQueue : string) : string;
Var
	index : integer;
	state : integer;

begin
	state := 0;
	index := 1;
	while((index <= Length(sQueue)) and (state <> 2) and (state <> 3)) do
	begin
		if sQueue[index] = 'C' then
			state := takeState(state, 'C')
		else
			state := takeState(state, 'X');
		inc(index);
	end;
	if state = 3 then
		validate_third := 'Good'
	else
		validate_third := 'Bad';
end;

Var
	rNumber : real;

BEGIN
	clrscr;
	Randomize();
	rNumber := random();
	writeln(rNumber:1:10);
	writeln(numtoBinCad(rNumber));
	writeln(makeQueue(numtoBinCad(rNumber)));
	writeln(validate_third(makeQueue(numtoBinCad(rNumber))));
END.