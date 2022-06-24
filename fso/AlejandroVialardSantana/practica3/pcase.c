// Librerías
#include <stdio.h>	
#include <unistd.h>		// getopt() close()
#include <stdlib.h>		// malloc()
#include <errno.h>		// errno()
#include <sys/types.h> 		// open()
#include <sys/stat.h>
#include <fcntl.h>
#include <pwd.h>
#include <string.h>

// Autor: Alejandro Vialard Santana

// Variables flag para getopt()
int s = 0, l = 0, U = 0, C = 0, t = 0;


// Método que comprueba el error si se produce alguno
void compruebaError()
{
	fprintf(stderr, "errno: %d : %s\n", errno, strerror(errno));
	errno = 0;
}

// Método que abre un archivo en modo lectura y devuelve su identificador

int abrirArchivo(char *archivo) 
{
	int fid;
	if ((fid = open(archivo, O_RDWR)) == -1)
	{
		compruebaError();
		exit(-1);
	}
	return fid;
}

// Método que cierra un archivo mediante su identificador
int cerrarArchivo(int fid) 
{
	int ret;
	if ((ret = close(fid)) == -1)
	{
		compruebaError();
		exit(-1);
	}
	return ret;
}

// Método que comprueba los argumentos y las opciones de la función
void compruebaArgumentos(int argc, char *argv[]) 
{
	int c;

	if (argc < 3 || argc > 4) {
		fprintf(stderr, "%s: No se ha introducido el comando correctamente\n", argv[0]);
		printf("Por favor, pruebe con './archivos opción nombre1 [nombre2]'\n");
	}
	
	if (argc < 5) {
	// comprobacion de las opciones pasadas
	while ((c = getopt(argc, argv, "slUCt")) != -1) 
	{
		printf("Opción elegida: %c\n", (char)c);
		switch(c)
		{
			case 's':
				s = 1;
				break;
			case 'l':
				l = 1;
				break;
			case 'U':
				U = 1;
				break;
			case 'C':
				C = 1;
				break;
			case 't':
				t = 1;
				break;
			case '?':
				break;
		}
	}
	// Comprobación de no introducir ninguna opción
	if ((s == 0) && (l == 0) && (C == 0) && (U == 0) && (t == 0))
	{
		fprintf(stderr, "%s: Se ha introducido una opción no válida\n", argv[0]);
		printf("Introduce el comando según esta forma: './archivo [-s|l|U|C|t] fichero1 [fichero2]'\n");
		printf("#####################################\n");
		exit(-1);
	}

	// Comprobacion de introducir más de una opción
	else if ((s + l + U + C + t) > 1)	// si por ejemplo s = 1 y l = 1, daría 2 y podremos comprobar que hay error 
	{
		fprintf(stderr, "%s: Se ha introducido más de una opción permitida\n", argv[0]);
		printf("Introduce el comando según esta forma: './archivo [-s|l|U|C|t] fichero1 [fichero2]'\n");
		printf("#####################################\n");
		exit(-1);
	}
	
	// Comprobacion de no introducir ningún fichero
	else if (argc == 2)
	{
		fprintf(stderr, "%s: No se ha introducido fichero a tratar\n", argv[0]);
		printf("Introduce el comando según esta forma: './archivo [-s|l|U|C|t] fichero1 [fichero2]'\n");
		printf("#####################################\n");
		exit(-1);
	}
	}
}

// Método que toma cúantos argumentos se le pasa por la función
void extraeArgumentos(int argc, char *argv[])
{
	int i;
	printf("Se han pasado %d argumentos\n", argc);
	for (i = 0; i < argc; i++) {
		printf("argv[%d] = %s\n", i, argv[i]);	
	}
	printf("\n");
}

// Método que cambia todas las frases del fichero poniendo la primera letra de cada frase en mayúsculas
void sentenceCase(int fid) 
{
	char buf;
	int init = 0; // variable que nos permite saber si es la primera letra o no
	int c;
	while ((c = read(fid, &buf, sizeof(char))) != 0)
	{
		if (init == 0)
		{
			if (buf >= 97 && buf <= 122)	// si es minus la primera letra, se cambia a mayus
			{
				buf -= 32;
				lseek(fid, -1*sizeof(char), SEEK_CUR);
				write(fid, &buf, sizeof(char));
				init = 1;
			}
			
			else if (buf >= 65 && buf <= 90)
			{
				init = 1;
			}
		}
		else {
			if (buf == 46)
			{
				init = 0;
			}
		}
	}
	compruebaError();
	printf("\n");
}


// Método que pone todas las letras en minusculas
void lowerCase(int fid) 
{
	char buf;
	int c;
	while ((c = read(fid, &buf, sizeof(char))) != 0)
	{
		if (buf >= 65 && buf <= 90)	// si es mayus, se cambia a minus
			buf += 32;
		if (buf >= 97 && buf <= 122)	// si es minus, se lee y escribe
		{
			lseek(fid, -1*sizeof(char), SEEK_CUR);
			write(fid, &buf, sizeof(char));
		}
	}
	compruebaError();
	printf("\n");
}

// Método que pone todas las letras en mayusculas
void upperCase(int fid) 
{
	char buf;
	int c;
	while ((c = read(fid, &buf, sizeof(char))) != 0)
	{
		if (buf >= 97 && buf <= 122)	// si es minus, se cambia a mayus
			buf -= 32;
		if (buf >= 65 && buf <= 97)	// si es mayus, se lee y escribe
		{
			lseek(fid, -1*sizeof(char), SEEK_CUR);
			write(fid, &buf, sizeof(char));
		}
	}
	compruebaError();
	printf("\n");
}

// Método que pone la primera letra de cada palabra en mayuscula
void capitalizeEachWord(int fid)
{
	char buf;
	int init = 0; // variable que nos permite saber si es la primera letra o no
	int c;
	while ((c = read(fid, &buf, sizeof(char))) != 0)
	{
		if (init == 0)	// si es la primera letra
		{
			if (buf >= 97 && buf <= 122) // si es minus, se cambia a mayus
			{
				buf -= 32;
				lseek(fid, -1*sizeof(char), SEEK_CUR);
				write(fid, &buf, sizeof(char));
				init = 1;
			}
			
			else if (buf >= 65 && buf <= 90) // si es mayus, se pone init a 1
			{
				init = 1;
			}
		}	
		else {	// si init = 1, no es la primera letra
			if (buf == 32)	// si hay espacio, se cambia de palabra
			{
				init = 0;
			}
		}	
	}
	compruebaError();
	printf("\n");
}

// Metodo que ponde la primera letra de cada palabra en minuscula y el resto en mayuscula
void toogleCase(int fid)
{
	char buf;
	int init = 0;	// variable que nos permite saber si es la primera letra o no
	int c;
	while ((c = read(fid, &buf, sizeof(char))) != 0)
	{
		if (init == 0) 
		{
			if (buf >= 65 && buf <= 90) // Si es la primera letra y está en mayus, se cambia a minus
			{
				buf += 32;
				lseek(fid, -1*sizeof(char), SEEK_CUR);
				write(fid, &buf, sizeof(char));
				init = 1;
			}
			else if (buf >= 97 && buf <= 122) // si es minus, se queda igual 
			{
				init = 1;
			}
		}
		else 
		{
			if (buf >= 97 && buf <= 122) // si no es la inicial, y es minus, se cambia a mayus 
			{
				buf -= 32;
				lseek(fid, -1*sizeof(char), SEEK_CUR);
				write(fid, &buf, sizeof(char));
			}
			else if (buf == 32)	// si hay espacio, es una nueva palabra
			{
				init = 0;
			}
		}
	}
	compruebaError();
	printf("\n");
}

// Función principal
int main(int argc, char *argv[])
{
	int i;
	printf("Programa PCASE creado por Alejandro Vialard Santana\n\n");

	extraeArgumentos(argc, argv);		// Llamamos a la funcion para tomar los argumentos
	compruebaArgumentos(argc, argv);	// Los comprobamos	
	
	
	for (i = 2; i < argc; i++) {		// bucle que nos permite leer de uno a más archivos
		int fid = abrirArchivo(argv[i]);

		if (s == 1) sentenceCase(fid);
		if (l == 1) lowerCase(fid);
		if (U == 1) upperCase(fid); 	
		if (C == 1) capitalizeEachWord(fid);
		if (t == 1) toogleCase(fid);
		
		close(fid);		
		compruebaError();	
	}	
	printf("#####################################\n");

	return 0;
}
