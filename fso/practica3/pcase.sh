#!/bin/bash

# Pruebas con un solo fichero

./pcase -s ./prueba-sentenceCase/prueba-sentenceCase-1.txt

./pcase -l ./prueba-lowerCase/prueba-lowerCase-1.txt

./pcase -U ./prueba-upperCase/prueba-upperCase-1.txt

./pcase -C ./prueba-capitalizeWord/prueba-capitalizeWord-1.txt

./pcase -t ./prueba-toogleCase/prueba-toogleCase-1.txt

# Pruebas con dos ficheros

./pcase -s ./prueba-sentenceCase/prueba-sentenceCase-2.txt ./prueba-sentenceCase/prueba-sentenceCase-3.txt

./pcase -l ./prueba-lowerCase/prueba-lowerCase-2.txt ./prueba-lowerCase/prueba-lowerCase-3.txt

./pcase -U ./prueba-upperCase/prueba-upperCase-2.txt ./prueba-upperCase/prueba-upperCase-3.txt

./pcase -C ./prueba-capitalizeWord/prueba-capitalizeWord-2.txt ./prueba-capitalizeWord/prueba-capitalizeWord-3.txt

./pcase -t ./prueba-toogleCase/prueba-toogleCase-2.txt ./prueba-toogleCase/prueba-toogleCase-3.txt

# Pruebas para los casos de error

# 1. No se introduce opción
./pcase ./prueba-sentenceCase/prueba-sentenceCAse-1.txt

# 2. Se introduce más de una opción
./pcase -sU ./prueba-sentenceCase/prueba-sentenceCase-1.txt

# 3. No se introduce fichero
./pcase -s

# 4. Se introduce más de 4 argumentos
./pcase -s ./prueba-sentenceCase/prueba-sentenceCase-1.txt ./prueba-sentenceCase/prueba-sentenceCase-2.txt ./prueba-sentenceCase/prueba-sentenceCase-3.txt

# Prueba de errno Ej: no such file or directory
./pcase -s ./prueba-sentenceCase/prueba-sentenceCASE-1.txt
