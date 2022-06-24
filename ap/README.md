# Algoritmos y Programación

Prácticas de la asignatura de Algoritmos y Programación, enfocadas en el estudio de algoritmos y problemas de optimización.

**Desarrollado en Python y Minizinc**

## Prácticas

### Python

* Juan Carlos Acosta Perabá
  1. [Presentación](./JuanCarlosAcostaPeraba/python/presentacion/)
  2. [Grafos: Introducción](./JuanCarlosAcostaPeraba/python/grafosIntroduccion/)
  3. [Grafos: BFS - Shortest Path Length](./JuanCarlosAcostaPeraba/python/grafosBFSShortestPathLength/)
  4. [Grafo dirigido](./JuanCarlosAcostaPeraba/python/grafoDirigido/)
  5. [Grafos: DFS - Topological Sort](./JuanCarlosAcostaPeraba/python/grafosDFSTopologicalSort/)
  6. [Funciones generadoras](./JuanCarlosAcostaPeraba/python/funcionesGeneradoras/)
  7. [N-Queens](./JuanCarlosAcostaPeraba/python/nQueens/)
  8. [Branch and Bound: Node class](./JuanCarlosAcostaPeraba/python/branchAndBoundNodeClass/)
  9. [Branch and Bound: DFS (recorrido)](./JuanCarlosAcostaPeraba/python/branchAndBoundDFSRecorrido/)
  10. [Branch and Bound: DFS](./JuanCarlosAcostaPeraba/python/branchAndBoundDFS/)
  11. [House Robber](./JuanCarlosAcostaPeraba/python/houseRobber/)
  12. [Knapsack 0/1 (MEMOIZATION)](./JuanCarlosAcostaPeraba/python/knapsack01Memoization/)
  13. [Knapsack 0/1 (TABULATION)](./JuanCarlosAcostaPeraba/python/knapsack01Tabulation/)
  14. [TSP 2 - Aproximado](./JuanCarlosAcostaPeraba/python/TSP2Aproximado/)
  15. [TSP 3/2 - Aproximado Christofides](./JuanCarlosAcostaPeraba/python/TSP32AproximadoChristofides/)
  16. [Algoritmo genérico: Cruce de ciclo](./JuanCarlosAcostaPeraba/python/algoritmoGenericoCruceDeCiclo/)

### Minizinc

* Juan Carlos Acosta Perabá
  1. [Hello World](./JuanCarlosAcostaPeraba/minizinc/helloworld.mzn)
  2. [Input and Output](./JuanCarlosAcostaPeraba/minizinc/inout.mzn)
  3. [Variable de decisión](./JuanCarlosAcostaPeraba/minizinc/xvar.mzn)
  4. [X óptima](./JuanCarlosAcostaPeraba/minizinc/xoptima.mzn)
  5. [Array](./JuanCarlosAcostaPeraba/minizinc/array.mzn)
  6. [Count](./JuanCarlosAcostaPeraba/minizinc/count.mzn)
  7. [Army](./JuanCarlosAcostaPeraba/minizinc/army.mzn)
  8. [Sequence](./JuanCarlosAcostaPeraba/minizinc/sequence.mzn)
  9. [House Robber](./JuanCarlosAcostaPeraba/minizinc/houserobber.mzn)
  10. [Knapsack 0/1](./JuanCarlosAcostaPeraba/minizinc/knapsack01.mzn)
  11. [Knapsack con repetición](./JuanCarlosAcostaPeraba/minizinc/knapsack.mzn)
  12. [Parejas estables (beneficio hombres)](./JuanCarlosAcostaPeraba/minizinc/parejasestableshombres.mzn)
  13. [Parejas estables (beneficio mujeres)](./JuanCarlosAcostaPeraba/minizinc/parejasestablesmujeres.mzn)
  14. [Parejas estables (maxima igualdad)](./JuanCarlosAcostaPeraba/minizinc/parejasestablesigualdad.mzn)
  15. [Knapsack 0/1 limitada](./JuanCarlosAcostaPeraba/minizinc/knapsack01limitada.mzn)
  16. [Dieta](./JuanCarlosAcostaPeraba/minizinc/dieta.mzn)
  17. [Dieta 2](./JuanCarlosAcostaPeraba/minizinc/dieta2.mzn)
  18. [Planificación temporal](./JuanCarlosAcostaPeraba/minizinc/planning.mzn)
  19. [Flujo máximo](./JuanCarlosAcostaPeraba/minizinc/maxflow.mzn)
  20. [TSP trayecto parcial mínimo](./JuanCarlosAcostaPeraba/minizinc/tspminedge.mzn)
  21. [Emparejamiento perfecto de coste mínimo](./JuanCarlosAcostaPeraba/minizinc/perfectmatching.mzn)
  22. [Sudoku](./JuanCarlosAcostaPeraba/minizinc/sudoku.mzn)

Ejecutar programas de Minizinc por consola:

```shell
minizinc <nombre_archivo.mzn>
```

* Para mostrar todos los resultados añadimos el parámetro `-a`:
`minizinc <nombre_archivo.mzn> -a`

* Para establecer valores de entrada usamos el parámetro `-D`:
`minizinc <nombre_archivo.mzn> -D <nombre_variable> = <valor_variable> (-D <nombre_variable_siguiente> = <valor_variable_siguiente>...)`