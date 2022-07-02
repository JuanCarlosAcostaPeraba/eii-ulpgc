% --------------------
% Programa Transportistas
% --------------------
% Enunciado: Una empresa tiene que realizar n envios. Para ello dispone de k transportistas y t camiones. Cada transportista y cada camion se dedica en cada momento. Cada envio tarda un tiempo determinado en realizarse y requiere una cantidad prefijada de transportistas y de camiones. No pueden haber dos camiones comenzando un envio a la vez. Se trata de minimizar el tiempo total emplreado en realizar los envios.
% Salida:    Tiempo total: tiempo
%            array con los tiempos de inicio por camionero

% Codigo dado por el profesor ---------|
int: n;                              % |
int: k;                              % |
int: t;                              % |
int: maxTiempo;                      % |
array [1..n] of int: tiempo;         % |
array [1..n] of int: camiones;       % |
array [1..n] of int: transportistas; % |
% -------------------------------------|

% Entradas
n = 6; % Numero de envios
k = 5; % Numero de transportistas
t = 3; % Numero de camiones
maxTiempo = 100; % Tiempo maximo del que se dispone
tiempo = [2, 4, 6, 3, 1, 2]; % Tiempo de ejecucion de cada transporte
camiones = [3, 2, 1, 2, 2, 3]; % Camiones necesarios para cada transporte
transportistas = [2, 3, 2, 4, 1, 1]; % Transportistas necesarios para cada transporte

% Escriba su codigo a partir de aqui
include "cumulative.mzn";
var 0..maxTiempo: tiempoTotal; % Tiempo total para los envios
array[1..n] of var 0..maxTiempo: tiempoCamioneros; % Tiempo que tarda cada camionero en hacer un envio

constraint cumulative(tiempoCamioneros, tiempo, camiones, t);
constraint cumulative(tiempoCamioneros, tiempo, transportistas, k);
constraint forall (i in 1..n)(tiempoCamioneros[i] + tiempo[i] <= tiempoTotal);
%constraint tiempoTotal = sum(tiempoCamioneros);

solve minimize tiempoTotal;

output ["Tiempo total: \(tiempoTotal)\n\(tiempoCamioneros)"];