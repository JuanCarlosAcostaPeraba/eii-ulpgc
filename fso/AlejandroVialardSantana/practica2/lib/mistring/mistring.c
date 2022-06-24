// Código que implementa la bibilioteca "mistring"
// Autor: Alejandro Vialard Santana

// Cabecera
#include <stdlib.h>

/**
	Método que devuelve un entero con la longitud del paramétro que le pase el usuario
*/
int mi_strlen (char* str) {
	int contador = 0;
	while (*str != '\0') {  	// hasta que se encuentre el valor NULL 
		contador++;
		str++;
	}
	return contador;
}

/**
	Método que copia los caracteres de s2 a s1 y añade NULL al final	
*/
char* mi_strcpy (char* s1, char* s2) {	
	char* inicio = s1;			// guardamos la direccion de inicio de la cadena 
	while (*s2 != '\0') {			// hasta que el contenido sea el caracter nulo
		*s1 = *s2;			// el contenido de s2 se copia en s1
		s1++;
		s2++;
	}
	*s1 = '\0';				// se añade el caracter nulo
	return inicio;
}

/**
	Método que concatena los caracteres de s2 a s1 y devuelve la direccion de s1
*/
char* mi_strcat (char* s1, char* s2) {
	char* pointer = s1 + mi_strlen(s1);		// puntero que apunta al final de la cadena
	while (*s2 != '\0') {
		*pointer++ = *s2++;
	}
	
	*pointer = '\0';				// se añade null al final
	return s1;
}

/**
	Método que crea un duplicado de la cadena mediante memoria dinámica y devuelve la direccion
    del duplicado
*/
char* mi_strup (char* str) {
	char* ptr = (char *)malloc(mi_strlen(str)*sizeof(char));	// reservamos tantos caracteres como el tamaño de la cadena 
	return mi_strcpy(ptr, str);					// duplicamos los caracteres											
}


/**
	Método que compara dos cadenas y devuelve 1 si son iguales y 0 si son diferentes
*/
int mi_strequals (char* s1, char* s2) {
	while (*s1 != '\0' || *s2 != '\0') {
		if (*s1 != *s2) {		// si el contenido de las cadenas son distintos retorna 0
			return 0;
		}
		s1++;
		s2++;
	}
	return 1;				// si no hay distinto, son iguales y retorna 1
}
