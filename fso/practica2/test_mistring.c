// test_mistring.c
// ===============
// Batería de pruebas de la biblioteca "mistring"
//
// Podrás empezar a usarla cuando hayas hecho un fichero "mistring.h"
// al menos con los cuerpos vacíos de las funciones mi_strlen, mi_strcat 
// y mi_strequals

#ifndef TEST
#include "lib/mistring/mistring.h"
#include <stdlib.h>
#endif

#include <assert.h>
#include <stdio.h>

// Este bloque es para probar que "test_mistring.c" está correctamente
// escrito, sin necesidad de que aún esté implementado "mi_string.c"
// En ese caso, compilar con la opción -DTEST
#ifdef TEST
#include <string.h>
#define mi_strlen strlen
#define mi_strcat strcat
#define mi_strequals(a,b) (strcmp(a,b)==0)
#endif

#define DebeSerCierto(x)	assert(x)
#define DebeSerFalso(x)		assert(!(x))

void INICIO_TEST (const char* titulo_test)
{
  printf("- batería de pruebas para %s: ", titulo_test); 
 	// fflush fuerza que se imprima el mensaje anterior
	// sin necesidad de utilizar un salto de línea
	fflush(stdout);
}

void FIN_TEST (const char* titulo_test)
{
  printf ("hecho\n");
}

void test_mi_strlen()
{
  char cadena_1000 [1000];
  int i;
  #define LONGITUD_TEST 500
  for (i=0; i<LONGITUD_TEST; i++) 
  {
    cadena_1000[i] = 32+(rand()%96);
  }
  cadena_1000[LONGITUD_TEST] = '\0'; 
  
  	INICIO_TEST("mi_strlen");
	
	DebeSerCierto(mi_strlen("hola mundo") == 10);		// Cadena de 10 caracteres
	DebeSerCierto(mi_strlen("") == 0);			// Cadena vacía
	DebeSerCierto(mi_strlen(cadena_1000) == LONGITUD_TEST);	// Cadena de 1000 caracteres
	FIN_TEST("mi_strlen");
}

void test_mi_strcat()
{
	char cadena1[15] = { 'h','o','l','a', '\0' };
	char* resultado = mi_strcat(cadena1," mundo");	// resultado = "hola mundo"
	
	INICIO_TEST("mi_strcat");

	DebeSerCierto(cadena1 == resultado);
	DebeSerCierto(mi_strlen(cadena1)==10);
	DebeSerCierto(mi_strequals(cadena1,"hola mundo"));
	DebeSerFalso(mi_strequals(cadena1,"hola"));

	// PRUEBA CONCATENACION DE UNA CADENA CON CARACTERES Y OTRA VACÍA
	DebeSerCierto(mi_strequals(mi_strcat(cadena1, ""), "hola mundo"));
	
	// PRUEBA CONCATENACION DE CADENA VACIA Y CADENA VACIA
	char cadena2[10] = {'\0'};					// cadena vacía	
	DebeSerCierto(mi_strequals(mi_strcat(cadena2, ""), ""));

	// PRUEBA CONCATENACION DE CADENAS DE LONGITUD 1
	char cadena3[3] = {' ', '\0'};
	DebeSerCierto(mi_strlen(cadena3) == 1);
	DebeSerCierto(mi_strequals(mi_strcat(cadena3, "h"), " h"));

	FIN_TEST("mi_strcat");	
}

void test_mi_strcpy()
{	
	char cadena1[10] = {'h','o','l','a','\0'};
	char cadena2[10] = {'m','u','n','d','o','\0'};
	
	INICIO_TEST("mi_strcpy");
	
	// PRUEBA DE COPIAR UNA CADENA EN OTRA
	mi_strcpy(cadena1, cadena2);	// cadena1 = "mundo" 
	DebeSerCierto(mi_strequals(cadena1, "mundo"));	
	DebeSerFalso(mi_strequals(cadena1, "hola"));

	// PRUEBA DE COPIAR UNA CADENA VACIA EN UNA NORMAL
	mi_strcpy(cadena2, "");
	DebeSerCierto(mi_strequals(cadena2, ""));
	DebeSerFalso(mi_strequals(cadena2, "mundo"));
	
	// PRUEBA DE COPIAR UNA CADENA PEQUEÑA EN UNA CADENA GRANDE
	char cadena3[35] = {'e','s','t','o', ' ','e', 's', ' ','u','n','a', ' ','p','r','u','e','b','a',' ','d','e',' ','c','a','d','e','n','a',' ','l','a','r','g','a','\0'};
	DebeSerCierto(mi_strlen(cadena3) == 34);
	mi_strcpy(cadena3, "a");
	DebeSerCierto(mi_strequals(cadena3, "a"));
	DebeSerCierto(mi_strequals(mi_strcpy(cadena3, "hola mundo"), "hola mundo"));

	// PRUEBA DE COPIAR UNA CADENA VACIA
	char cadena4[3] = {'\0'};
	DebeSerCierto(mi_strequals(mi_strcpy(cadena4, ""), ""));	
	DebeSerFalso(mi_strequals(mi_strcpy(cadena4, "hola"), ""));

	FIN_TEST("mi_strcpy");
}

void test_mi_strup()
{
	char cadena1[7] = {'h', 'o', 'l', 'a', '\0'};
	char* pointer1	= mi_strup(cadena1);

	INICIO_TEST("mi_strup");
	
	// PRUEBA DE DUPLICADO CON UNA CADENA CON CARACTERES
	DebeSerCierto(mi_strequals(cadena1, pointer1));
	free(pointer1);						// liberamos la memoria

	// PRUEBA DE DUPLICADO CON CADENA VACIA
	char* pointer2 = mi_strup("");
	DebeSerCierto(mi_strequals(pointer2, ""));
	free(pointer2);						// liberamos la memoria

	FIN_TEST("mi_strup");
}

void test_mi_strequals()
{
	char cadena1[10] = {'h','o','l','a','\0'};
	char cadena2[10] = {'m','u','n','d','o','\0'};	

	INICIO_TEST("mi_strequals");
	
	// PRUEBA DE IGUALDAD DE DOS CADENAS CON CARACTERES
	DebeSerCierto(mi_strequals(cadena1, cadena1));
	DebeSerFalso(mi_strequals(cadena1, cadena2));

	// PRUEBA DE IGUALDAD CON UNA CADENA VACIA
	char cadena3[3] = {'\0'};
	DebeSerCierto(mi_strequals(cadena3, ""));
	DebeSerFalso(mi_strequals(cadena3, " "));	// la cadena es un espacio, no un caracter nulo
	
	// PRUEBA DE IGUALDAD DE CADENAS PARECIDAS
	DebeSerFalso(mi_strequals(cadena1, "hola ")); 	// contiene un espacio al final
	DebeSerFalso(mi_strequals(cadena1, " hola"));	// contiene un espacio al inicio
	DebeSerFalso(mi_strequals(cadena1, "HOLA"));	// contiene todo mayúsculas
	DebeSerFalso(mi_strequals(cadena1, "Hola")); 	// contiene una mayúscula

	FIN_TEST("mi_strequals");
}


void ejecuta_tests ()
{
	test_mi_strlen();
	test_mi_strcat();
	test_mi_strcpy();
	test_mi_strup();
	test_mi_strequals();
}

main()
{
	puts("Comienza las pruebas de la biblioteca mistring");
	puts("Iniciando tests...");
	
	ejecuta_tests();
	
	puts("Batería de test completa con éxito.");
}
